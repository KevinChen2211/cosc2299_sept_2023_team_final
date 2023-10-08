import React, { useState } from "react";
import '../styling.css';
import { useLocation, useNavigate } from "react-router-dom";
import Popup from "reactjs-popup";
import "reactjs-popup/dist/index.css";
import BackButton from "./components/BackButton";
export default function Payment({ cartItems }) {

  const navigate = useNavigate();
  const location = useLocation();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const deliveryFee = location.state.deliveryFee;

  const calculateTotal = () => {
    let total = 0;
    for (const item of cartItems) {
      total += (item.price * item.boughtQuantity);
    }
    return total.toFixed(2);
  }
  const [formData, setFormData] = useState({
    cardNumber: "",
    expiryDate: "",
    CVC: "",
    nameOnCard: "",
  });

  const [validationErrors, setValidationErrors] = useState({});

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
    if (!/^\d{2}\/\d{2}$/.test(expiryDate)) {
      return false;
    }

    const [month, year] = expiryDate.split('/');

    const monthNum = parseInt(month, 10);
    if (monthNum < 1 || monthNum > 12) {
      return false;
    }

    const yearNum = parseInt(year, 10);
    if (yearNum < 23 || yearNum > 30) {
      return false;
    }
    return true;
  };


  const validateCVC = (cvc) => {
    return /^\d{3,4}$/.test(cvc);
  };

  const validateForm = () => {
    const errors = {};

    if (!validateCardNumber(formData.cardNumber)) {
      errors.cardNumber = "Invalid card number. Enter between 9 and 12 digits.";
    }

    if (!validateExpiryDate(formData.expiryDate)) {
      errors.expiryDate = "Invalid expiry date";
    }

    if (!validateCVC(formData.CVC)) {
      errors.CVC = "Invalid CVC/CVV";
    }

    setValidationErrors(errors);

    return Object.keys(errors).length === 0;
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (validateForm()) {
      setIsModalOpen(true);
    } else {
      console.log("Form has validation errors:", validationErrors);
    }
  };

  const handleModalClose = () => {
    setIsModalOpen(false);
    navigate("/");
    // Reset cartItems if needed: cartItems = [];
  };


  return (

    <div className="containerPayment" align='center'>
      <div align='left'>
        <BackButton />
      </div>
      <div className="center">Payment</div>
      <form onSubmit={handleSubmit}>
        <br></br><br></br>
        <table align="center" cellPadding={'10px'}>
          <tbody>
            <tr>
              <td align="left"><b>Total Amount</b></td>
              <td align="right"><b>Pay $ {(parseFloat(calculateTotal()) + deliveryFee).toFixed(2)}</b></td>
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
            {validationErrors.cardNumber && (
              <tr>
                <td className="left" colSpan={2}>
                  <div className="error">{validationErrors.cardNumber}</div>
                </td>
              </tr>
            )}

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
            {validationErrors.expiryDate && (
              <tr>
                <td className="left" colSpan={2}>
                  <div className="error">{validationErrors.expiryDate}</div>
                </td>
              </tr>
            )}
            {validationErrors.CVC && (
              <tr>
                <td className="left" colSpan={2}>
                  <div className="error">{validationErrors.CVC}</div>
                </td>
              </tr>
            )}

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
            </tr><br />

            {/* Pay Button */}
            <tr>
              <td align="center" colSpan={2}>
                <button className="checkoutButton" onClick={handleSubmit}>
                  <span>Pay $ {(parseFloat(calculateTotal()) + deliveryFee).toFixed(2)}</span>
                </button>

                <Popup open={isModalOpen} closeOnDocumentClick onClose={handleModalClose}>
                  <div className="popup">
                    <h1>Payment successful!</h1>
                    <h2>Thank you {location.state.firstName} {location.state.lastName} for purchasing</h2>
                    <h2>your order of ${(parseFloat(calculateTotal()) + deliveryFee).toFixed(2)} has gone through</h2>
                    <button className="default-home-button" onClick={handleModalClose}>
                      Close
                    </button>
                  </div>
                </Popup>
              </td>
            </tr>
          </tbody>
        </table>
      </form>
    </div>
  );
}
