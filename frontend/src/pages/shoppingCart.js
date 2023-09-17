import '../styling.css';
import React, { useState } from "react";
import { useNavigate, useLocation } from "react-router-dom"; 


export default function ShoppingCart(){
    const [formData, setFormData] = useState([
      { id: 1, name: 'Product 1', price: 10, quantity: 2, imageUrl: '../assets/milk.jpeg',},
      { id: 1, name: 'Product 2', price: 15, quantity: 1, imageUrl: '../assets/milk.jpeg',},
    ]);

    const handleSubmit = (e) =>{
      e.preventDefault();
      console.log("Form submitted with data: ",formData);
      }

    const calculateTotal = () => {
      let total = 0;
      for (const item of formData) {
        total += item.price * item.quantity;
      }
      return total;
    }

    const handleQuantityChange = (index, amount) => {
      const updatedData = [...formData];
      updatedData[index].quantity += amount;
      setFormData(updatedData);
    }
    
    const handleRemoveItem = (indexToRemove) => {
      const updatedFormData = formData.filter((_, index) => index !== indexToRemove);
      setFormData(updatedFormData);
    };    
    
    const navigate = useNavigate();

    const handleCheckout = () => {
      navigate("/checkout");
    }
    
    // const location = useLocation();
    // console.log(location.state.name);


/*
FYI: the whole code is based on the use of html tables
- Each row of product has: 
      ~ cancel button
      ~ image
      ~ name
      ~ unit price
      ~ - and + buttons for modifying quantity
      ~ total price = quantity * unit price
- Total = Sum of all total prices of items
- Checkout button will lead to check out page
*/



  return (
      
        <body className='container' width="50%">
          {/* <h1>Product template here of :{location.state.productName}</h1> */}
        <form onSubmit={handleSubmit}>
          <td align='left'>
              <button onClick={() => navigate('/login')}>
                <span >
                  Back to home page
                </span>
              </button>
            </td>
          <div className='center'>Shopping cart</div>
          <br/>
        
        {formData.map((item, index) => (
          <div align='center'>
          {/* ---------------  Product row ----------------- */}
          {/*   Cancel Item  */}
          <td className='setPadding'> 
            <button className='cancelButton' onClick={() => handleRemoveItem(index)}> 
            X </button> </td>  

          {/*   Item Image  */}
          <td align='center' className='setPadding'><img src="../assets/milk.jpeg" alt="itemImage" width={70} height={70}/> </td>
          
          {/*   Item Name  */}
          <td className='setPadding'> {item.name} </td> 
          {/* <td className='setPadding'> {location.state.productName} </td>  */}

          {/*   Item Unit Price  */}
          <td> $ {item.price} </td> 

          {/*   Item quantity  */}
          <td align='center' width={'140px'}> 
            <div className="quantityBar">
              <button 
                  className='quantityButton'
                  onClick={() => handleQuantityChange(index, -1)}> 
              - </button>
              <span className='quantityContainer'>{item.quantity}</span>
              <button className='quantityButton'
                  onClick={() => handleQuantityChange(index, 1)}> 
              + </button>
            </div>
          </td>
          {/*   Item total price  */}
          <td width="100px" align='center'> <span>$ {item.price * item.quantity}</span> </td>
          </div>
          ))}
          
          <div>
          <hr></hr>
            <table align='center' width="60%">
              {/* ---------------  Total price ----------------- */}
              <tr>
                <td align='right' width="60%"><span className='grayColor'><b>Total:</b></span></td>
                <td className='totalAmount' align='center'> <b>$ {calculateTotal()}</b> </td>
               </tr>
            </table>  
          </div>
          <br></br><br></br>
          {/* ---------------  Check out button ----------------- */}

          <div className='button'>
            <table align='center'>
              <td align='right'>
                <button className='checkoutButton'  onClick={handleCheckout} >
                  <span >
                    Continue to checkout
                  </span>
                </button>
              </td>
            </table>
          </div>
          </form>
        </body>
      );
}