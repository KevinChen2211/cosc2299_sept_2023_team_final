import React from "react";
import { Link } from "react-router-dom";
// import logo from "../assets/logo.png";
import bwlogo from "../assets/bwlogo.png";
import logout from "../assets/logout.png";
import location from "../assets/location.png";
import notification from "../assets/notification.png";
import profile from "../assets/profile.png";
// import shoppingcart from "../assets/shoppingcart.png";

// navigation bar component
function Navbar(props) {
  return (
    // nav bar colour bootstrap
    <nav className="navbar navbar-expand-sm bg-warning navbar-dark">
      <div className="container">
        <Link className="navbar-brand" to="/"><img src={bwlogo} alt="bwlogo" width="200" height="70" /></Link>
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mr-auto">


          </ul>
          <ul className="navbar-nav">

            {/* if a user is not logged in, they will see a Sign Up and Sign In page available in nav bar */}
            {props.email === null ?
              <>

              </>
              :
              <>
                <li className="nav-item">
                  <Link className="nav-link" to="/location"> <img src={location} alt="location" width="30" height="30"/> </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/profile"><img src={profile} alt="profile" width="30" height="30"/></Link>
                </li>
                {/* <li className="nav-item">
                  <Link className="nav-link" to="/shoppingcart"><img src={shoppingcart} alt="shoppingcart" width="30" height="30"/></Link>
                </li> */}
                <li className="nav-item">
                  <Link className="nav-link" to="/notifications"><img src={notification} alt="notification" width="30" height="30"/></Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/login" onClick={props.logoutUser}><img src={logout} alt="logout" width="30" height="30"/></Link>
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
