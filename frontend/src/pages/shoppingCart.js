import '../styling.css';
import React, { useState } from "react";
import { useNavigate, useLocation } from "react-router-dom"; 


export default function ShoppingCart({cartItems, updateCartItems}){

  const navigate = useNavigate();

  const calculateTotal = () => {
    let total = 0;
    for (const item of cartItems) {
      total += item.price * item.quantity;
    }
    return total;
  }

  const handleQuantityChange = (index, amount) => {
    const updatedData = [...cartItems];
    updatedData[index].quantity += amount;
    updateCartItems(updatedData);
  }
  
  const handleRemoveItem = (indexToRemove) => {
    const updatedFormData = cartItems.filter((_, index) => index !== indexToRemove);
    updateCartItems(updatedFormData);
  };    
    
  const handleCheckout = () => {
    navigate("/checkout");
  }
  
  return (
        <div>
              <button onClick={() => navigate('/item')}>
                  Back to item details
              </button>
          <div className='center'>Shopping cart</div>
          <br/>
        
        {cartItems.map((item, index) => (
          <div align='center'>
            <span> <button className='cancelButton' onClick={() => handleRemoveItem(index)}> 
              X </button> </span> 
            <img src="../assets/milk.jpeg" alt="itemImage" width={70} height={70}/>
            <span key={item.id}> {item.name} </span> 
            $ {item.price} 
            <span>
              <button 
                  className='quantityButton'
                  onClick={() => handleQuantityChange(index, -1)}> 
              - </button>
              <span className='quantityContainer'>{item.quantity}</span>
              <button className='quantityButton'
                  onClick={() => handleQuantityChange(index, 1)}> 
              + </button>
            </span>
            $ {item.price * item.quantity}
          </div>
          ))}
          
          <div align='center'>
          <hr></hr>
                <span className='grayColor'><b>Total:</b></span>
                 <b>$ {calculateTotal()}</b>
          </div>
          <br></br><br></br>
          

          <div className='button' align='center'>
                <button className='checkoutButton'  onClick={handleCheckout} >
                  <span >
                    Continue to checkout
                  </span>
                </button>
          </div>
        </div>
      );
}