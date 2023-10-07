import '../styling.css';
import { useNavigate } from "react-router-dom";
import React, { useState } from "react";
import Popup from "reactjs-popup";
import "reactjs-popup/dist/index.css";


export default function ShoppingCart({ cartItems, updateCartItems }) {

  const [isPopupOpen, setIsPopupOpen] = useState(false);

  const calculateTotal = () => {
    let total = 0;
    for (const item of cartItems) {
      total += item.price * item.boughtQuantity;
    }
    return total.toFixed(2);
  }

  const handleQuantityChange = (index, amount) => {
    const updatedData = [...cartItems];
    updatedData[index].boughtQuantity += amount;
    updateCartItems(updatedData);
  }

  const handleRemoveItem = (indexToRemove) => {
    const updatedFormData = cartItems.filter((_, index) => index !== indexToRemove);
    updateCartItems(updatedFormData);
  };

  const navigate = useNavigate();

  const handleCheckout = () => {
    if (calculateTotal() === "0.00") {
      setIsPopupOpen(true);
    } else {
      navigate("/Checkout");
    }
  };

  return (
    <div>
      <div className='center'>Shopping cart</div>
      <br />

      {cartItems.map((item, index) => (
        <div align='center'>
          {/* ---------------  Product row ----------------- */}
          {/*   Cancel Item  */}
          <td className='setPadding'>
            <button className='cancelButton' onClick={() => handleRemoveItem(index)}>
              X </button> </td>

          {/*   Item Image  */}
          <td align='center' className='setPadding'>
            <img src={item.imageLocation} alt={item.name} width={70} height={70} /> </td>

          {/*   Item Name  */}
          <td className='setPadding' key={item.id}> {item.name} </td>

          {/*   Item Unit Price  */}
          <td> $ {item.price} </td>

          {/*   Item quantity  */}
          <td align='center' width={'140px'}>
            <div className="quantityBar">
              <button
                className='quantityButton'
                onClick={() => handleQuantityChange(index, -1)}>
                - </button>
              <span className='quantityContainer'>
                {/* {item.quantity} */}
                {item.boughtQuantity}
              </span>
              <button className='quantityButton'
                onClick={() => handleQuantityChange(index, 1)}>
                + </button>
            </div>
          </td>
          {/*   Item total price  */}
          <td width="100px" align='center'> <span>$ {(item.price * item.boughtQuantity).toFixed(2)}</span> </td>
        </div>
      ))}

      <div>
        <hr></hr>
        <table align='center' width="60%">
          {/* ---------------  Total price ----------------- */}
          <tr>
            <td align='right' width="60%"><span className='grayColor'><b>Total:</b></span></td>
            <td className='totalAmount' align='center'> <b>$ {calculateTotal()}</b> </td>
          </tr>
        </table>
      </div>
      <br></br><br></br>
      {/* ---------------  Check out button ----------------- */}
      <div className='button'>
        <table align='center'>
          <td align='right'>
            <button className='checkoutButton' onClick={handleCheckout} >
              <span >
                Continue to Checkout
              </span>
            </button>
          </td>
        </table>
      </div>
      <Popup open={isPopupOpen} closeOnDocumentClick onClose={() => setIsPopupOpen(false)}>
        <div className="popup">
          <h2>Zero Total Warning</h2>
          <p>Your total is $0.00. You cannot proceed to checkout with an empty cart.</p>
          <button className='default-home-button' onClick={() => setIsPopupOpen(false)}>Close</button>
        </div>
      </Popup>
    </div>
  );
}
