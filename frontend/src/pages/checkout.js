import React, { useState } from "react";
import '../styling.css'

export default function checkout(){
  const [formData, setFormData] = useState({
    email: "", firstName: "", lastName: "",
    address: "", city: "", postcode: "", phone: ""
  });

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
FYI: - The whole code is implemented inside two containers: left (my order), right( delivery info)
MY ORDER CONTAINER: 
  - My order container has EACH ITEM with the following functions:
      ~ cancel item
      ~ item image
      ~ item name and item price
  - Total = Subtotal + Shipping Fee
DELIVERY INFO CONTAINER:
  - email, first name, last name, address, city, state, postcode, phone
CONTINUE SHIPPING BUTTON:
  - will lead to delivery page
*/

  return (
      <main> 
        <head>
          <title>Checkout</title>
        </head>

        <body>
          <br></br>
          <form onSubmit={handleSubmit}>
            <table width={"100%"}>
              {/* ---------------  Left container contains "my order" ----------------- */}
              <tr> 
                <td width={'30%'} className='vertical'>
                  <table className='containerCheckout'>
                    <br></br>
                    <tr>
                      <td colSpan={3}><div className='subtitle'><b>My order</b></div></td>
                    </tr>
                    <br></br>
                    <tr><td colSpan={3}><hr></hr></td></tr>
                    <br></br>
                    <tr>
                      {/*   Item cancel */}
                      <td width={'15%'}> <button className='cancelButton'> X </button> </td>
                      {/*   Item image  */}
                      <td align='center'><img src="../assets/milk.jpeg" alt="itemImage" width={70} height={70}/> </td>
                      {/*   Item name and price  */}
                      <td>
                        <span className='productName'>Product Name</span>
                        <br></br>
                        <span className='productPrice'>Price</span>
                      </td>
                    </tr>
                    <br></br>
                    
                    <br></br>
                    <tr><td colSpan={3}><hr></hr></td></tr>
                    <br></br>
                    {/*   Subtotal  */}
                    <tr >
                      <td colSpan={2} className='left'><span>Subtotal</span></td>
                      <td className='right'><span>$ Subtotal</span></td>
                    </tr>
                    {/*   Shipping fee */}
                    <tr>
                      <td colSpan={2} className='left'><span>Shipping fee</span></td>
                      <td className='right'><span>$ Shipping</span></td>
                    </tr>
                    <br></br>
                    <tr><td colSpan={3}><hr></hr></td></tr>
                    {/*   Grand Total */}
                    <tr>
                      <td colSpan={2} className='left'><span><b>Total</b></span></td>
                      <td className='right'><span><b>$ Total</b></span></td>
                    </tr>
                    <br></br>
                  </table>
                </td>

                {/* ---------------  Right container contains contact information ----------------- */}

                <td width={'60%'} className='vertical'>
                  <table className='containerCheckout'>
                    <br></br>
                    <tr>
                      <td colSpan={4}><div className='subtitle'><b>Contact Information</b></div></td>
                    </tr>
                    <br></br>
                    <tr><td colSpan={4}><hr></hr></td></tr>
                    <br></br>

                    {/*   Email  */}
                    <tr>
                      <td className='left' width={'15%'}>
                        <label>Email</label> 
                      </td>
                      <td align='left'>
                        <input type="text" name="email" width={'35%'}></input>
                      </td>
                    </tr>
                    <br></br>
                    <br></br>

                    {/*  ------- Delivery Information ------  */}
                    <tr>
                      <td colSpan={4}><div className='subtitle'><b>Delivery Information</b></div></td>
                    </tr>
                    <br></br>
                    <tr><td colSpan={4}><hr></hr></td></tr>
                    <br></br>

                    {/*   First Name  */}
                    <tr>
                      <td className='left'>
                        <label>First Name</label> 
                      </td>
                      <td align='left' >
                        <input type="text" name="firstName"></input>
                      </td>

                      {/*   Last Name  */}
                      <td className='left' width={'15%'}>
                        <label>Last Name</label> 
                      </td>
                      <td align='left' >
                        <input type="text" name="lastName"></input>
                      </td>
                    </tr>
                    <br></br>

                    {/*   Address  */}
                    <tr>
                      <td className='left'>
                        <label>Address</label> 
                      </td>
                      <td align='left' colSpan={3}>
                        <input type="text" name="address"></input>
                      </td>
                    </tr>
                    <br></br>

                    {/*   City  */}
                    <tr>
                      <td className='left'>
                        <label>City</label> 
                      </td>
                      <td align='left' >
                        <input type="text" name="city"></input>
                      </td>

                    {/*   State  */}
                      <td className='left'>
                        <label>State</label> 
                      </td>
                      <td align='left' >
                        <input type="text" name="state"></input>
                      </td>
                    </tr>
                    <br></br>

                    {/*   Postcode  */}
                    <tr>
                      <td className='left'>
                        <label>Postcode</label> 
                      </td>
                      <td align='left' >
                        <input type="text" name="postcode"></input>
                      </td>

                    {/*   Phone  */}
                      <td className='left'>
                        <label>Phone</label> 
                      </td>
                      <td align='left' >
                        <input type="text" name="phone"></input>
                      </td>
                    </tr>
                    <br></br>
                    <br></br>

                    {/* ---------------  Button for continue shipping ----------------- */}

                    <tr>
                      <td colSpan={4} align='right'>
                        <button className='checkoutButton'>
                          <span> Continue Shipping </span>
                        </button>
                      </td>
                    </tr>
                    <br></br>
                  </table>
                </td>
              </tr>

            </table>
          </form>
        </body>
        
      </main>
      );
} 
