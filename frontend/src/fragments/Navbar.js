import React from "react";
import { Link } from "react-router-dom";
import logo from "../assets/logo.png";

// navigation bar component
function Navbar(props) {
  return (
    // nav bar colour bootstrap
    <nav className="navbar navbar-expand-sm bg-warning navbar-dark">
      <div className="container">
        <Link className="navbar-brand" to="/"><img src={logo} alt="logo" width="180" height="80" /></Link>
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
                  <Link className="nav-link" to="/profile">Profile</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/login" onClick={props.logoutUser}>Logout</Link>
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
