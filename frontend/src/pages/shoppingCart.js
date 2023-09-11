import '../styling.css';
import React, { useState } from "react";

export default function shoppingCart(){
    const [formData, setFormData] = useState([
      { id: 1, name: 'Product 1', price: 10, quantity: 2, imageUrl: '../assets/milk.jpeg',},
      { id: 1, name: 'Product 2', price: 15, quantity: 1, imageUrl: '../assets/milk.jpeg',},
    ]);

    const handleSubmit = (e) =>{
      e.preventDefault();
      console.log("Form submitted with data: ",formData);
      }
  
      const handleInputChange = (e) =>{
          const {name, value} = e.target;
          setFormData({
              ...formData,
              [name]: value
          });
      };

/*
FYI: the whole code is based on the use of html tables
- Each row of product has: 
      ~ cancel button
      ~ image
      ~ name
      ~ unit price
      ~ - and + buttons for modifying quantity
      ~ item quantity, that can be typed in to directly modify without the use of -+ buttons
      ~ total price = quantity * unit price
- Total = Sum of all total prices of items
- Checkout button will lead to check out page
*/

  return (
      <main> 
        <head>
          <title>Shopping Cart</title>
        </head>

        <body className='container'>
        <form onSubmit={handleSubmit}>
          <div className='center'>Shopping cart</div>
          <div>
            <table align='center'>
              <tr>
                {/* ---------------  Subheading ----------------- */}
                <td></td>
                <td></td>
                <td className='title' align='left'>Item Name</td>
                <td className='title' align='right'>Price</td>
                <td></td>
                <td className='title' align='right'>Total Price</td>
              </tr>
              <br></br>
              <tr>
                {/* ---------------  Product row ----------------- */}
                {/*   Cancel Item  */}
                <td> <button className='cancelButton'> X </button> </td>  

                {/*   Item Image  */}
                <td align='center'><img src="../assets/milk.jpeg" alt="itemImage" width={70} height={70}/> </td>
                
                {/*   Item Name  */}
                <td> item.name </td> 

                {/*   Item Unit Price  */}
                <td align='right'> <p> ${item.price.toFixed(2)} </p> </td>

                {/*   Item quantity  */}
                <td align='right'> 
                  <div className="quantityBar">
                    <button className='quantityButton'> - </button>
                    <span className='quantityContainer'> {item.quantity} </span>
                    <button className='quantityButton'> + </button>
                  </div>
                </td>
                {/*   Item total price  */}
                <td width="15%" align='right'> <span>  ${(item.price * item.quantity).toFixed(2)}  </span> </td>
              </tr>

            </table>  
          </div>
            <hr></hr>
          <div>
            <table align='center' width="60%">
              {/* ---------------  Total price ----------------- */}
              <tr>
                <td align='right' width="80%"><p className='grayColor'>Total:</p></td>
                <td className='totalAmount'> $ Total </td>
              </tr>
            </table>
          </div>

          {/* ---------------  Check out button ----------------- */}

          <div className='button'>
            <table align='center'>
              <td align='right'>
                <button className='checkoutButton'>
                  <span >
                    Continue to checkout
                  </span>
                </button>
              </td>
            </table>
          </div>
          </form>
        </body>
      </main>
      );
} 
