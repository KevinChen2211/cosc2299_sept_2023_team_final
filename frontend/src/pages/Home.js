import React from "react";
import { useNavigate } from "react-router-dom";
import LoggedIn from "./LoggedIn";
import banner from "../assets/home_banner.jpg";


function Home(props) {
  const navigate = useNavigate();

  const handleSubmit = () => {
    navigate("/signup");
  };

  return (
    <div className="text-center">
      <br />
      <h1>Welcome to SuperPrice!</h1>
      <div className="form-group">

      </div>
      {props.email === null | props.email === "" ?
        <>
          <br />
          <img
            src={banner}
            className="home-banner"
          />
          <br /><br /><br />
          <p>
            This project is a collaborative effort aimed to develop a
            comprehensive price-matching and delivery application. With
            SuperPrice, we can help you find the best value for your money's
            worth by comparing prices across multiple retailers and also arrange
            for a seamless delivery.
          </p>
          <br />
          <br />
          <p>
            By integrating with various local supermarkets, this application
            uses real-time data to ensure accurate and up-to-date information
            about product prices and availability.
          </p>
          <br /><br />
          <p>
            Create an account with us today to improve your shopping experience,
            save both time and money, and simplify the grocery shopping process!
          </p>
          <br /><br />
          <p>You'll get exclusive access to source the best grocery products for incredibly low prices.</p>
          <button onClick={handleSubmit} className="button-interactive">
            Sign Up
          </button>
          <br />
          <br />
          <p>
            Already have an account? <a href="/login">Login here</a>
          </p>
        </>
        :
        // displays LoggedIn page if the user is logged in
        <>
          <LoggedIn />
        </>
      }

    </div>
  );
}

export default Home;
