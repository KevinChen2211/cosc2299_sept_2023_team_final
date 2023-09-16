import React, { useState } from "react";
import '../styling.css';
import { useNavigate } from "react-router-dom"; 

export default function Checkout() {
  const [cartItems, setCartItems] = useState([
    { id: 1, name: "Product 1", price: 10 , image: '../assets/milk.jpeg'},
    { id: 2, name: "Product 2", price: 15 , image: '../assets/milk.jpeg'},
    { id: 3, name: "Product 3", price: 20 , image: '../assets/milk.jpeg'},
  ]);

  const handleRemoveItem = (indexToRemove) => {
    const updatedFormData = cartItems.filter((_, index) => index !== indexToRemove);
    setCartItems(updatedFormData);
  };  

  const navigate = useNavigate();

  const handleCheckout = () => {
    navigate("/payment"); // Navigate to the checkout page
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

    <div className='center' >Checkout</div>  <br/>

    <div style={{display:'flex'}}>
      {/* Left container contains "my order" */}
      <div className='containerCheckout' style={{display:'flex',flexDirection:'column'}}>

      <div className='subtitle'><b>My Order</b></div>
        <hr />
        {/* Render items in a loop */}
        {cartItems.map((item, index) => (
          <div key={index} style={{display:'flex'}}>
          
            <button className='cancelButton'  onClick={() => handleRemoveItem(index)}>
               X </button>
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
        <input type="text" name="email" />
      </div>

      {/* Delivery Information */}
      <div className='subtitle'><b>Delivery Information</b></div>
      <hr />

      {/* First Name and Last Name */}
      <div className='form-group'>
        <label className="setPadding">First Name</label>
        <input type="text" name="firstName" />
      </div>
      <div className='form-group'>
        <label className="setPadding">Last Name</label>
        <input type="text" name="lastName"/>
      </div>

      {/* Address */}
      <div className='form-group'>
        <label className="setPadding">Address</label>
        <input type="text" name="address" />
      </div>

      {/* City and State */}
      <div className='form-group'>
        <label className="setPadding">City</label>
        <input type="text" name="city"/>
      </div>
      <div className='form-group'>
        <label className="setPadding">State</label>
        <input type="text" name="state" />
      </div>

      {/* Postcode and Phone */}
      <div className='form-group'>
        <label className="setPadding">Postcode</label>
        <input type="text" name="postcode" />
      </div>
      <div className='form-group'>
        <label className="setPadding">Phone</label>
        <input type="text" name="phone" />
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
