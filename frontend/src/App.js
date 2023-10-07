import React, { useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Navbar from "./fragments/Navbar";
import Home from "./pages/Home";
import Login from "./pages/Login";
import MyProfile from "./pages/MyProfile";
import SignUp from "./pages/SignUp";
import Notifications from "./pages/Notifications";
import ProductPage from "./pages/components/ProductPage";
import Categories from "./pages/Categories";
import SubCategories from "./pages/SubCategories"
import SubCategoryProducts from "./pages/SubCategoryProducts";
import Checkout from "./pages/Checkout";
import Delivery from "./pages/Delivery";
import Payment from "./pages/Payment";
import ShoppingCart from "./pages/ShoppingCart";
import { getEmail, getPassword, removeUser } from "./data/repository";
import ProductSearch from "./pages/components/ProductSearch"
import './App.css';

function App() {
  const [email, setEmail] = useState(getEmail());
  const [password, setPassword] = useState(getPassword());

  const loginUser = (email, password) => {
    setEmail(email);
    setPassword(password);
  }

  const logoutUser = () => {
    removeUser();
    setEmail(null);
  }

  const [cartItems, setCartItems] = useState([]);

  const addToCart = (item) => {
    setCartItems((prevCartItems) => [...prevCartItems, item]);
  };

  const updateCartItems = (updatedCartItems) => {
    setCartItems(updatedCartItems);
  }

  return (
    <div className="d-flex flex-column min-vh-100">
      <Router>
        <Navbar email={email} password={password} logoutUser={logoutUser} />
        <p></p>

        <main role="main">
          <div className="container my-3">
            <Routes>
              <Route path="/" element={<Home email={email} />} />
              <Route path="/signup" element={<SignUp loginUser={loginUser} />} />
              <Route path="/login" element={<Login loginUser={loginUser} />} />
              <Route path="/profile" element={<MyProfile email={email} password={getPassword()} logoutUser={logoutUser} />} />
              <Route path="/notifications" element={<Notifications email={email} password={getPassword()} />} />
              <Route path="/checkout" element={<Checkout cartItems={cartItems} updateCartItems={updateCartItems} />} />
              <Route path="/payment" element={<Payment cartItems={cartItems} />} />
              <Route path="/shoppingcart" element={<ShoppingCart cartItems={cartItems} updateCartItems={updateCartItems} />} />
              <Route path="/delivery" element={<Delivery cartItems={cartItems} />} />
              <Route path="/categories" element={<Categories />} />
              <Route path="/categories/:subCategories" element={<SubCategories />} />
              <Route path="/categories/:subCategories/:SubCategoryProducts" element={<SubCategoryProducts />} />
              <Route path="/product/:productName" element={<ProductPage cartItems={cartItems} updateCartItems={updateCartItems} />} />
              <Route path="/ProductSearch/:searchTerm" element={<ProductSearch />} />
            </Routes>
          </div>
        </main>
        <p></p>
      </Router>
    </div>
  );
}


export default App;
