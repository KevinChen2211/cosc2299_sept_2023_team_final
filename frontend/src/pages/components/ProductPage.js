import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Popup from "reactjs-popup";
import "reactjs-popup/dist/index.css";
import '../../styling.css'
import axios from "axios";

export default function ProductPage({ cartItems, updateCartItems }) {
  const [cartItem, setCartItem] = useState([{
    boughtQuantity: 0,
  }]);
  const location = useLocation();
  const navigate = useNavigate();
  const [isPopupOpen, setIsPopupOpen] = useState(false);

  const [clickCount, setCount] = useState(1);

  useEffect(() => {
    // Fetch products for the given subcategory from the backend
    axios.get(`http://localhost:8080/product/id/${location.state.productID}`)
      .then(response => {
        setCartItem({ ...response.data, boughtQuantity: 0 });
      })
      .catch(error => {
        console.error("There was an error fetching products for the subcategory:", error);
      });
  }, []);

  const handleAddToCart = () => {
    let updatedCartItems = [...cartItems];

    const itemIndex = updatedCartItems.findIndex((item) => item.productID === cartItem.productID);

    if (itemIndex !== -1) {
      updatedCartItems[itemIndex].boughtQuantity++;
    } else {
      updatedCartItems.push({ ...cartItem, boughtQuantity: 1 });
    }
    updateCartItems(updatedCartItems);
    setCount(clickCount + 1);
    setIsPopupOpen(true);
  };

  return (
    <tbody>
      <td align='right'>
        <button className="default-home-button" onClick={() => navigate('/shoppingCart')}>
          <span >
            Shopping Cart
          </span>
        </button>
      </td>
      <br />
      <td className="itemInformation" align="center" width={'50%'}>  <br></br><br></br>
        <div style={{ display: 'flex' }}>
          <div>
            <span><img src={cartItem.imageLocation} alt={cartItem.name}
              width={'250px'} height={'250px'} ></img> </span>
            <span width={'10px'}></span>
          </div>
          <div className="product-info">
            <h3><span className='productName'>{cartItem.name}</span></h3><br></br>
            <h4><span className='productPrice'>Price ${cartItem.price}</span></h4><br></br>
            Category: {cartItem.category}<br />
            Chain: {cartItem.chain}<br />
            <br />
            <button className="default-home-button" onClick={handleAddToCart}> Add to cart</button>
            <br /><br />
          </div>
        </div>
      </td>
      <br></br>
      <Popup open={isPopupOpen} closeOnDocumentClick onClose={() => setIsPopupOpen(false)}>
        <div className="popup">
          <p>Item has been added to the shopping cart!</p>
        </div>
      </Popup>
    </tbody>
  );
}