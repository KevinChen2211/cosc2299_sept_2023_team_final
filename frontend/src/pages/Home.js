import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import SearchBar from "./components/SearchBar";
import supermarket from "../assets/supermarket.png";
import axios from 'axios';

function Home(props) {
  const [products, setProducts] = useState([]);
  useEffect(() => {
    axios.get(`http://localhost:8080/product/subcate/apples`)
      .then(response => {
        setProducts(response.data);
      })
      .catch(error => {
        console.error("There was an error fetching products for the subcategory:", error);
      });
  }, []);


  const navigate = useNavigate();

  const handleImageClick = (product, productIdentify) => {
    // Replace with the actual URLs with the navigate 
    navigate("/product/" + product, { state: { productID: productIdentify } });
    return;
  }

  const routeChange = () => {
    let path = `/categories`;
    navigate(path);
  }

  const handleSubmit = (event) => {
    navigate("/signup");
    return;
  }

  return (
    <div className="text-center">
      {/* content describing website purpose */}

      <h1>Welcome to SuperPrice!</h1>
      <div className="form-group">

      </div>
      {props.email === null ?
        <>
          <p>[INSERT CONTENT IMAGE]</p>
          <br></br>
          <p>This project is a collaborative effort aimed to develop a comprehensive price-matching and delivery application.
            With SuperPrice, we can help you find the best value for your money's worth by comparing prices across multiple retailers and also arrange for a seamless delivery.</p>
          <br></br>
          <p>By integrating with various local supermarkets, this application uses real-time data to ensure accurate and up-to-date information about product prices and availability.</p>
          <br></br>
          <br></br>
          <p>Create an account with us today to improve your shopping experience, save both time and money, and simplify the grocery shopping process!</p>
          <p>You'll get exclusive access to source the best grocery products for incredibly low prices.</p>
          <input onClick={handleSubmit} className="btn btn-primary" value="Sign Up" />
          <p></p><p>Already have an account?<a href="/login"> Login here</a></p>
        </>
        :
        <>
          <div className="home-page-search-categories">
            {/* TODO: add feature to broswe tab */}
            <button onClick={routeChange}>Categories</button>
            <SearchBar />
          </div>
          <img
            src={supermarket}
            className="main-page-image"
          />
          <h1>Popular Items</h1>
          <div className="specials-container">
            {products.map(product => (
              <div
                className="home-popular-items"
                key={product.productID}
                onClick={() => handleImageClick(product.name, product.productID)}
              >
                <img
                  src={product.imageLocation}
                  alt={product.name} width="100"
                />
                <br />
                <strong>{product.name}</strong> - ${product.price.toFixed(2)}
              </div>
            ))}

          </div>
        </>
      }

    </div>
  );
}

export default Home;
