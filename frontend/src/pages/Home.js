import React from "react";
import { useNavigate } from "react-router-dom";
import LoggedIn from "./LoggedIn";

function Home(props) {

  const navigate = useNavigate();


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
          <LoggedIn />
        </>
      }

    </div>
  );
}

export default Home;
