import React, { useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Navbar from "./fragments/Navbar";
import Home from "./pages/Home";
import Login from "./pages/Login";
import MyProfile from "./pages/MyProfile";
import SignUp from "./pages/SignUp";
import Location from "./pages/Location";
import Notifications from "./pages/Notifications";
// import shoppingCart from "./pages/shoppingCart";
// import shoppingCart from "./pages/shoppingCart"
import ProductPage from "./pages/components/ProductPage";
import Categories from "./pages/Categories";
import SubCategories from "./pages/SubCategories"
import SubCategoryProducts from "./pages/SubCategoryProducts"
import { getUser, removeUser } from "./data/repository";
import './App.css';

function App() {
  const [email, setEmail] = useState(getUser());

  const loginUser = (email) => {
    setEmail(email);
  }

  const logoutUser = () => {
    removeUser();
    setEmail(null);
  }

  return (
    <div className="d-flex flex-column min-vh-100">
      <Router>
        <Navbar email={email} logoutUser={logoutUser} />
        <p></p>

        <main role="main">
          <div className="container my-3">
            <Routes>
              <Route path="/" element={<Home email={email} />} />
              <Route path="/signup" element={<SignUp loginUser={loginUser} />} />
              <Route path="/login" element={<Login loginUser={loginUser} />} />
              <Route path="/profile" element={<MyProfile email={email} />} />
              <Route path="/location" element={<Location email={email} />} />
              <Route path="/notifications" element={<Notifications email={email} />} />
              <Route path="/shoppingcart" element={<shoppingCart email={email} />} />
              <Route path="/product/:productName" element={<ProductPage />} />
              <Route path="/categories" element={<Categories />} />
              <Route path="/categories/:subCategories" element={<SubCategories />} />
              <Route path="/categories/:subCategories/:SubCategoryProducts" element={<SubCategoryProducts />} />
            </Routes>
          </div>
        </main>
        <p></p>
      </Router>
    </div>
  );
}

export default App;
