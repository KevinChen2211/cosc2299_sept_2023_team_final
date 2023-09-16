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
    const validationErrors = {};
    if (!validateCardNumber(formData.cardNumber)) {
      validationErrors.cardNumber = "Invalid card number";
    }
    if (!validateExpiryDate(formData.expiryDate)) {
      validationErrors.expiryDate = "Invalid expiry date";
    }
    if (!validateCVC(formData.CVC)) {
      validationErrors.CVC = "Invalid CVC/CVV";
    }

    if (Object.keys(validationErrors).length === 0) {
        console.log("Form submitted with data: ", formData);
      } else {
        console.log("Form has validation errors:", validationErrors);
      }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const validateCardNumber = (cardNumber) => {
    return /^\d{12,19}$/.test(cardNumber);
  };

  const validateExpiryDate = (expiryDate) => {
    return /^\d{2}\/\d{2}$/.test(expiryDate);
  };

  const validateCVC = (cvc) => {
    return /^\d{3,4}$/.test(cvc);
  };

  const totalAmount = 100; 

  return (
      <div className="containerPayment" align='center'>
        <div className="center">Payment</div>
        <form onSubmit={handleSubmit}>
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
        </div>
  );
}
