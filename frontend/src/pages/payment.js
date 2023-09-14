import React, { useState } from "react";
import '../styling.css';

export default function Payment() {
  const [formData, setFormData] = useState({
    cardNumber: "",
    expiryDate: "",
    CVC: "",
    nameOnCard: "",
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Form submitted with data: ", formData);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const totalAmount = 100; 

  return (
    <main>
      <head>
        <title>Payment</title>
      </head>

      <body className="containerPayment" align='center'>
        <form onSubmit={handleSubmit}>
          <div className="center">Payment</div>
          <br></br><br></br>
          <table align="center" cellPadding={'10px'}>
            <tbody>
              <tr>
                <td align="left"><b>Total Amount</b></td>
                <td align="right"><b>$ {totalAmount}</b></td>
              </tr>
              <tr><i>Credit Card</i></tr>

              {/* Card Number */}
              <tr>
                <td className="left">
                  <label>Card Number</label>
                </td>
              </tr>
              <tr>
                <td align="left" colSpan={2}>
                  <input
                    type="text"
                    name="cardNumber"
                    value={formData.cardNumber}
                    onChange={handleInputChange}
                    placeholder="Card Number"
                    width={'100%'}
                  />
                </td>
              </tr>

              {/* Expiry date and CVC / CVV */}
              <tr>
                <td className="left">
                  <label>Expiry date</label>
                </td>
                <td>
                  <label>CVC/CVV</label>
                </td>
              </tr>
              <tr>
                <td align="left">
                  <input
                    type="text"
                    name="expiryDate"
                    value={formData.expiryDate}
                    onChange={handleInputChange}
                    placeholder="Expiry date"
                  />
                </td>
                <td>
                  <input
                    type="text"
                    name="CVC"
                    value={formData.CVC}
                    onChange={handleInputChange}
                    placeholder="CVC/CVV"
                  />
                </td>
              </tr>

              {/* Name on Card */}
              <tr>
                <td className="left">
                  <label>Name on card</label>
                </td>
              </tr>
              <tr>
                <td align="left" colSpan={2}>
                  <input
                    type="text"
                    name="nameOnCard"
                    value={formData.nameOnCard}
                    onChange={handleInputChange}
                    placeholder="Name on Card"
                    width={'100%'}
                  />
                </td>
              </tr><br></br>

              {/* Pay Button */}
              <tr>
                <td align="center" colSpan={2}>
                  <button className="checkoutButton" >
                       <span>Pay $ {totalAmount}</span>
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </form>
      </body>
    </main>
  );
}
