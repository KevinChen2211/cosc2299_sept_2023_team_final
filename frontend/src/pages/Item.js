import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; 
import '../styling.css'

export default function Item({cartItems, updateCartItems}) {
  const [clickCount, setCount] = useState(1);

  const cartItem = {
    id: 1, name: "Product 1", price: 10 , image: '../assets/milk.jpeg', avgRating: 2,
    chain: 'Coles', category: 'dairy-and-eggs', quantity: clickCount
  }

  const handleAddToCart = () => {
    if (!Array.isArray(cartItems)) {
      cartItems = [];
    }
    const itemIndex = cartItems.findIndex((item) => item.id === cartItem.id);
  
    if (itemIndex !== -1) {
      const updatedCartItems = [...cartItems];
      updatedCartItems[itemIndex].quantity ++;
      updateCartItems(updatedCartItems);
    }
    else {
      const updatedCartItems = [...cartItems, { ...cartItem }];
      updateCartItems(updatedCartItems);
    }
    setCount(clickCount + 1);
  };
  
  const navigate = useNavigate();  
  
  return (
    <tbody>
      <td align='right'>
          <button onClick={() => navigate('/shoppingCart')}>
            <span >
              Shopping Cart
            </span>
          </button>
        </td>
        <br/>
      <td className="itemInformation" align="center" width={'50%'}>  <br></br><br></br>
          <div style={{display:'flex'}}>
            <div>
              <span><img src={cartItem.image} width={'250px'} height={'250px'} ></img> </span>
              <span width={'10px'}></span>
            </div>
            <div>
              <h3><span className='productName'>{cartItem.name}</span></h3><br></br>
              <h4><span className='productPrice'>Price ${cartItem.price}</span></h4><br></br>
              Category: {cartItem.category}<br/>
              Average Rating: {cartItem.avgRating}<br/>
              Chain: {cartItem.chain}<br/>
              <br/>
              <button onClick={handleAddToCart}> Add to cart</button>
              <br/><br/>
            </div>
          </div>
      </td>
      <br></br>
    </tbody>
  );
}