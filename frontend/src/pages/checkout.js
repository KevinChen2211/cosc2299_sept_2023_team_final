import React, { useState } from "react";
import '../styling.css';
import { useNavigate } from "react-router-dom"; 

export default function Checkout({cartItems, updateCartItems}) {
  const [formData, setFormData] = useState({
    email: "",
    firstName: "",
    lastName: "",
    address: "",
    city: "",
    postcode: "",
    phone: "",
  });

  const [formErrors, setFormErrors] = useState({});

  const handleRemoveItem = (indexToRemove) => {
    const updatedFormData = cartItems.filter((_, index) => index !== indexToRemove);
    updateCartItems(updatedFormData);
  };  

  const navigate = useNavigate();

  const handleCheckout = () => {
    // Validate the contact information inputs
    const errors = {};
    if (!formData.email || !/^\S+@\S+\.\S+$/.test(formData.email)) {
      errors.email = "Invalid email format";
    }
    if (!formData.firstName) {
      errors.firstName = "First Name is required";
    }
    if (!formData.lastName) {
      errors.lastName = "Last Name is required";
    }
    if (!formData.address) {
      errors.address = "Address is required";
    }
    if (!formData.city) {
      errors.city = "City is required";
    }
    if (!formData.state) {
      errors.state = "State is required";
    }
    if (!formData.postcode || !/^\d{4}$/.test(formData.postcode)) {
      errors.postcode = "Postcode must be 4 digits";
    }
    if (!formData.phone) {
      errors.phone = "Phone is required";
    }

    // If there are validation errors, set them in the state and prevent navigation
    if (Object.keys(errors).length > 0) {
      setFormErrors(errors);
    } else {
      // If there are no validation errors, navigate to the payment page
      navigate("/payment");
    }
  };

  const calculateTotal = () => {
    let total = 0;
    for (const item of cartItems) {
      total += item.price;
    }
    return total;
  }

  const shippingFee = 7;

  return (  
    <main>
      <button onClick={() => navigate('/shoppingCart')} >
        <span>
          Back to shopping cart
        </span>
      </button>

      <div className='center'>Checkout</div>  <br/>

      <div style={{display:'flex'}}>
        {/* Left container contains "my order" */}
        <div className='containerCheckout' style={{display:'flex',flexDirection:'column'}}>

          <div className='subtitle'><b>My Order</b></div>
          <hr />
          {/* Render items in a loop */}
          {cartItems.map((item, index) => (
            <div key={index} style={{display:'flex'}}>
              {/* Cancel Item */}
              <button className='cancelButton' onClick={() => handleRemoveItem(index)}>
                X
              </button>
              <img src={`../assets/${item.image}`} alt="itemImage" width={70} height={70} />
              <div style={{display:'flex',flexDirection:'column'}}>
                <span className='productName'>{item.name}</span>
                <span className='productPrice'>Price ${item.price}</span>
              </div>
            </div>
          ))}
          <hr />
          {/* Subtotal */}
          <div className="textColumn">
            <div>
              <span className="textColumnLeft" ><i>Subtotal: </i></span>
              <span className="textColumnRight">$ {calculateTotal()}</span>
            </div>
          </div>
          <div className="textColumn">
            {/* Shipping fee */}
            <div>
              <span className="textColumnLeft"><i>Shipping fee:</i></span>
              <span className="textColumnRight">$ {shippingFee}</span>
            </div>
            <br></br>
          </div>
          <hr />
          <div className="textColumn">
            {/* Grand Total */}
            <div>
              <span className="textColumnLeft"><b><i>Total Amount:</i></b></span>
              <span className="textColumnRight"><b>$ {calculateTotal() + shippingFee}</b></span>
            </div>
            <br/>
          </div>
        </div>

        {/* Right container contains contact information */}
        <div className='containerCheckout' style={{display:'flex',flexDirection:'column'}}>
          <div className='subtitle'><b>Contact Information</b></div>
          <hr />

          {/* Email */}
          <div className='form-group'>
            <label className="setPadding">Email</label>
            <input type="text" name="email" value={formData.email} onChange={(e) => setFormData({ ...formData, email: e.target.value })} />
            {formErrors.email && <div className="error">{formErrors.email}</div>}
          </div>

          {/* Delivery Information */}
          <div className='subtitle'><b>Delivery Information</b></div>
          <hr />

          {/* First Name and Last Name */}
          <div className='form-group'>
            <label className="setPadding">First Name</label>
            <input type="text" name="firstName" value={formData.firstName} onChange={(e) => setFormData({ ...formData, firstName: e.target.value })} />
            {formErrors.firstName && <div className="error">{formErrors.firstName}</div>}
          </div>
          <div className='form-group'>
            <label className="setPadding">Last Name</label>
            <input type="text" name="lastName" value={formData.lastName} onChange={(e) => setFormData({ ...formData, lastName: e.target.value })} />
            {formErrors.lastName && <div className="error">{formErrors.lastName}</div>}
          </div>

          {/* Address */}
          <div className='form-group'>
            <label className="setPadding">Address</label>
            <input type="text" name="address" value={formData.address} onChange={(e) => setFormData({ ...formData, address: e.target.value })} />
            {formErrors.address && <div className="error">{formErrors.address}</div>}
          </div>

          {/* City and State */}
          <div className='form-group'>
            <label className="setPadding">City</label>
            <input type="text" name="city" value={formData.city} onChange={(e) => setFormData({ ...formData, city: e.target.value })} />
            {formErrors.city && <div className="error">{formErrors.city}</div>}
          </div>

          <div className='form-group'>
            <label className="setPadding">State</label>
            <input type="text" name="state" value={formData.state} onChange={(e) => setFormData({ ...formData, state: e.target.value })} />
            {formErrors.state && <div className="error">{formErrors.state}</div>}
          </div>

          {/* Postcode and Phone */}
          <div className='form-group'>
            <label className="setPadding">Postcode</label>
            <input type="text" name="postcode" value={formData.postcode} onChange={(e) => setFormData({ ...formData, postcode: e.target.value })} />
            {formErrors.postcode && <div className="error">{formErrors.postcode}</div>}
          </div>
          <div className='form-group'>
            <label className="setPadding">Phone</label>
            <input type="text" name="phone" value={formData.phone} onChange={(e) => setFormData({ ...formData, phone: e.target.value })} />
            {formErrors.phone && <div className="error">{formErrors.phone}</div>}
          </div>

          {/* Button for continue shipping */}
          <div className='form-group'>
            <button className='checkoutButton' onClick={handleCheckout}>
              Continue to Payment
            </button>
          </div>
        </div>
      </div>
    </main>
  );
}
