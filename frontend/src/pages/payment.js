import React, { useState } from "react";
import '../styling.css';
import { useNavigate } from "react-router-dom"; 

export default function Payment() {
  const [formData, setFormData] = useState({
    cardNumber: "",
    expiryDate: "",
    CVC: "",
    nameOnCard: "",
  });
  const [validationErrors, setValidationErrors] = useState({});
  const navigate = useNavigate();

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
      errors.cardNumber = "Invalid card number";
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

  const totalAmount = 100;

  const handleSubmit = (e) => {
    e.preventDefault();

    if (validateForm()) {
      navigate("/login");
    } else {
      console.log("Form has validation errors:", validationErrors);
    }
  };

  return (
    
    <div>
      <div align='left'>
        <button onClick={() => navigate('/checkout')} >
            <span>  Back to checkout  </span>
        </button>
      </div>
      <div className="center">Payment</div>
        <br/><br/>

          <div align='center'>
            <span><b>Total Amount: </b></span>
            <span><b>$ {totalAmount}</b></span>
            <span><i>  Credit Card</i></span> <br/><br/>

            <label style={{padding: '10px'}}>Card Number</label> 
                <input
                  type="text"
                  name="cardNumber"
                  value={formData.cardNumber}
                  onChange={handleInputChange}
                  placeholder="Card Number"
                />
            {validationErrors.cardNumber && (
              <div>{validationErrors.cardNumber}</div>
            )}<br/>

            <label style={{padding: '10px'}}>Expiry date</label>
            <input
              type="text"
              name="expiryDate"
              value={formData.expiryDate}
              onChange={handleInputChange}
              placeholder="Expiry date"
            />
            {validationErrors.expiryDate && (
              <div className="error">{validationErrors.expiryDate}</div>
            )}<br/>

            <label style={{padding: '10px'}}>CVC/CVV</label>
            <input
              type="text"
              name="CVC"
              value={formData.CVC}
              onChange={handleInputChange}
              placeholder="CVC/CVV"
            />
            {validationErrors.CVC && (
              <div className="error">{validationErrors.CVC}</div>
            )}<br/>

            <label style={{padding: '10px'}}>Name on card  </label>
            <input
              type="text"
              name="nameOnCard"
              value={formData.nameOnCard}
              onChange={handleInputChange}
              placeholder="Name on Card"
            /><br/><br/>

            <button className="checkoutButton" onClick={handleSubmit}>
              <span>Pay $ {totalAmount}</span>
            </button>
          </div>
    </div>
  );
}
