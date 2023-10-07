import React, { useState, useEffect } from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";

function Notifications({ email, password }) {

  const [isNotified, setIsNotified] = useState({});
  const navigate = useNavigate();
  const [promotedItems, setPromotedItems] = useState([]);
  const handleImageClick = (product, productIdentifier) => {
    navigate("/product/" + product, { state: { productID: productIdentifier } });
  };

  useEffect(() => {
    // Fetch user data from the backend
    axios.get(`http://localhost:8081/v1/account/${email}/${password}`)
      .then(response => {
        setIsNotified(response.data.isNotified); // Extract isNotified from the response data
      })
      .catch(error => {
        console.error("There was an error fetching user data:", error);
      });
  }, []);

  useEffect(() => {
    axios.get(`http://localhost:8080/product/promotion`)
      .then(response => {
        setPromotedItems(response.data);
      })
      .catch(error => {
        console.error("There was an error fetching products for the subcategory:", error);
      });
  }, []);

  return (
    <div className="text-center">
      <h1>Notifications</h1>
      {isNotified === true ?
        <div className='product-list'>
          {promotedItems.map(product => (
            <div
              key={product.productID}
              className='products'
              onClick={() => handleImageClick(product.name, product.productID)}
            >
              <img src={product.imageLocation} alt={product.name} width="100" />
              <br />
              <strong>{product.name}</strong>
              <br />
              ${product.price.toFixed(2)} AUD
              <br />
              Sold by: {product.chain}
            </div>
          ))}
        </div>

        :
        <div>Notifications are not available as you have opt out of receiving any.</div>
      }
    </div>
  );
}

export default Notifications;