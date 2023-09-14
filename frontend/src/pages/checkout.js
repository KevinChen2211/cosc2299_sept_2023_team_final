import React, { useState } from "react";
import '../styling.css'

export default function Checkout() {
  const [formData, setFormData] = useState({
    email: "",
    firstName: "",
    lastName: "",
    address: "",
    city: "",
    postcode: "",
    phone: ""
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Form submitted with data: ", formData);
  }

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  return (
    <main>
      <head>
        <title>Checkout</title>
      </head>

      <body>
        <br></br>
        <form onSubmit={handleSubmit}>
          <table width={"100%"}>
            <tbody>
              {/* Left container contains "my order" */}
              <tr>
                <td width={'30%'} className='vertical'>
                  <table className='containerCheckout' cellPadding={'10px'}> 
                    <tbody>
                      <tr>
                        <td colSpan={3}><div className='subtitle'><b>My order</b></div></td>
                      </tr>
                      <tr><td colSpan={3}><hr></hr></td></tr>
                      <tr>
                        {/* Item cancel */}
                        <td width={'15%'}> <button className='cancelButton'> X </button> </td>
                        {/* Item image */}
                        <td align='center'><img src="../assets/milk.jpeg" alt="itemImage" width={70} height={70} /> </td>
                        {/* Item name and price */}
                        <td>
                          <span className='productName'>Product Name</span>
                          <br></br>
                          <span className='productPrice'>Price</span>
                        </td>
                      </tr>
                      <tr><td colSpan={3}><hr></hr></td></tr>
                      {/* Subtotal */}
                      <tr>
                        <td colSpan={2} className='left'><span>Subtotal</span></td>
                        <td className='right'><span>$ Subtotal</span></td>
                      </tr>
                      {/* Shipping fee */}
                      <tr>
                        <td colSpan={2} className='left'><span>Shipping fee</span></td>
                        <td className='right'><span>$ Shipping</span></td>
                      </tr>
                      <tr><td colSpan={3}><hr></hr></td></tr>
                      {/* Grand Total */}
                      <tr>
                        <td colSpan={2} className='left'><span><b>Total</b></span></td>
                        <td className='right'><span><b>$ Total</b></span></td>
                      </tr>
                      <br></br>
                    </tbody>
                  </table>
                </td>

                {/* Right container contains contact information */}
                <td width={'70%'} className='vertical'>
                  <table className='containerCheckout' cellPadding={'10px'}>
                    <tbody>
                      <tr>
                        <td colSpan={4}><div className='subtitle'><b>Contact Information</b></div></td>
                      </tr>
                      <tr><td colSpan={4}><hr></hr></td></tr>
                      {/* Email */}
                      <tr>
                        <td className='left' width={'15%'}>
                          <label>Email</label>
                        </td>
                        <td align='left'>
                          <input type="text" name="email" value={formData.email} onChange={handleInputChange} />
                        </td>
                      </tr>
                      {/* Delivery Information */}
                      <tr>
                        <td colSpan={4}><div className='subtitle'><b>Delivery Information</b></div></td>
                      </tr>
                      <tr><td colSpan={4}><hr></hr></td></tr>
                      {/* First Name and Last Name */}
                      <tr>
                        <td className='left'>
                          <label>First Name</label>
                        </td>
                        <td align='left'>
                          <input type="text" name="firstName" value={formData.firstName} onChange={handleInputChange} />
                        </td>
                        <td className='left' width={'15%'}>
                          <label>Last Name</label>
                        </td>
                        <td align='left'>
                          <input type="text" name="lastName" value={formData.lastName} onChange={handleInputChange} />
                        </td>
                      </tr>
                      {/* Address */}
                      <tr>
                        <td className='left'>
                          <label>Address</label>
                        </td>
                        <td align='left' colSpan={3}>
                          <input type="text" name="address" value={formData.address} onChange={handleInputChange} />
                        </td>
                      </tr>
                      {/* City and State */}
                      <tr>
                        <td className='left'>
                          <label>City</label>
                        </td>
                        <td align='left'>
                          <input type="text" name="city" value={formData.city} onChange={handleInputChange} />
                        </td>
                        <td className='left'>
                          <label>State</label>
                        </td>
                        <td align='left'>
                          <input type="text" name="state" value={formData.state} onChange={handleInputChange} />
                        </td>
                      </tr>
                      {/* Postcode and Phone */}
                      <tr>
                        <td className='left'>
                          <label>Postcode</label>
                        </td>
                        <td align='left'>
                          <input type="text" name="postcode" value={formData.postcode} onChange={handleInputChange} />
                        </td>
                        <td className='left'>
                          <label>Phone</label>
                        </td>
                        <td align='left'>
                          <input type="text" name="phone" value={formData.phone} onChange={handleInputChange} />
                        </td>
                      </tr>
                      {/* Button for continue shipping */}
                      <br></br>
                      <tr>
                        <td colSpan={4} align='center'>
                          <button className='checkoutButton'>
                            <span>Continue Shipping</span>
                          </button>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </td>
              </tr>
            </tbody>
          </table>
        </form>
      </body>
    </main>
  );
}