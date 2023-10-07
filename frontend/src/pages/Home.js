import React from "react";
import { useNavigate } from "react-router-dom";
import LoggedIn from "./LoggedIn";

function Home(props) {

  // initialize navigate function
  const navigate = useNavigate();

  // function to handle form submission
  const handleSubmit = (event) => {
    // signup button will navigate user to the signup page
    navigate("/signup");
    return;
  }

  return (
    <div className="text-center">
      <h1>Welcome to SuperPrice!</h1>
      <div className="form-group">

      </div>
      {props.email === null | props.email === "" ?
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
        // displays LoggedIn page if the user is logged in
        <>
          <LoggedIn />
        </>
      }

    </div>
  );
}

export default Home;
