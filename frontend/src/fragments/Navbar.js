import React from "react";
import { Link } from "react-router-dom";
// import logo from "../assets/logo.png";
import bwlogo from "../assets/bwlogo.png";
import logout from "../assets/logout.png";
import notification from "../assets/notification.png";
import profile from "../assets/profile.png";
import shoppingCart from "../assets/shoppingcart.png";

// navigation bar component
function Navbar(props) {
  return (
    // nav bar colour bootstrap
    <nav className="navbar navbar-expand-sm bg-warning navbar-dark">
      <div className="nav-bar-container">
        <Link className="navbar-brand" to="/"><img src={bwlogo} alt="bwlogo" width="250" /></Link>
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mr-auto">


          </ul>
          <ul className="navbar-nav">

            {props.email === null ?
              <>
                <div className="login-here"><a href="/login">Login</a></div>
              </>
              :
              <>
                <li className="nav-item">
                  <Link className="nav-link" to="/profile"><img src={profile} alt="profile" width="30" height="30" /></Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/notifications"><img src={notification} alt="notification" width="30" height="30" /></Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/shoppingcart"><img src={shoppingCart} alt="shoppingcart" width="30" height="30" /></Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/" onClick={props.logoutUser}><img src={logout} alt="logout" width="30" height="30" /></Link>
                </li>

              </>
            }
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
