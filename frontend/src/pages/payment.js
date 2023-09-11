import '../styling.css';
import React, { useState } from "react";

export default function payment(){
    const [formData, setFormData] = useState({
       cardNumber: '',
       expiryDate: '',
       CVC: '',
       NameOnCard: ''
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
- total amount = total price of items + shipping fee
- need to validate input format
*/

  return (
      <main> 
        <head>
          <title>Payment</title>
        </head>

        <body className='container'>
        <form onSubmit={handleSubmit}>
          <div className='center'>Payment</div>
          <table>
            <tr><span align='center'>Credit Card</span></tr>
            <br></br>

            <tr>
                <td align='left'>Total Amount</td>
                <td align='right'>$ total</td>
            </tr>

            {/* -------------------- Card Number ---------------- */}
            <tr  className='left'> <label>Card Number</label>  </tr>
            <tr align='left'>
                <input type="text" name="card number"
                    value={formData.cardNumber} onChange={handleInputChange} placeholder="Card Number">
                    Card Number
                </input>
            </tr>

            {/* -------------------- Expiry date ---------------- */}
            {/* -------------------- CVC / CVV ---------------- */}
            <tr  className='left'> 
                <td> <label>Expiry date</label>  </td>
                <td> <label>CVC/CVV</label>  </td>
            </tr>
            <tr align='left'>
                <td> <input type="text" name="Expiry date"
                    value={formData.expiryDate} onChange={handleInputChange} placeholder="Expiry date">
                    Expiry date
                </input> </td>
                <td> <input type="text" name="CVC/CVV"
                    value={formData.CVC} onChange={handleInputChange} placeholder="CVC/CVV">
                    CVC/CVV
                </input></td>
            </tr>

            {/* -------------------- Name on Card ---------------- */}
            <tr  className='left'> <label>Name on card</label>  </tr>
            <tr align='left'>
                <input type="text" name="Name on Card"
                    value={formData.NameOnCard} onChange={handleInputChange} placeholder="Name on Card">
                    Name on Card
                </input>
            </tr>
            
            {/* -------------------- Pay Button ---------------- */}
            <tr>
                <button className='checkoutButton'>
                    <span >
                        Pay $ <span>totalAmount</span>
                    </span>
                </button>
            </tr>

          </table>
        </form>
        </body>
      </main>
      );
} 
