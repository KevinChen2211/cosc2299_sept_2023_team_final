import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { verifyUser, setEmail, setPassword } from "../data/repository";

function Login(props) {
  // initialize state variables
  const [fields, setFields] = useState({ email: "", password: "" });
  // initialize an error message state
  const [errorMessage, setErrorMessage] = useState(null);
  //initialize navigate function
  const navigate = useNavigate();

  // function to handle input field changes
  const handleInputChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;

    // creating a temporary object to updcate the state based on changed input field
    const temp = { email: fields.email, password: fields.password };
    temp[name] = value;
    setFields(temp);
  }

  // function to handle form submit
  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      // verify the user login info using verifyUser function
      const verified = await verifyUser(fields.email, fields.password);

      // If verified, login the user.
      if (verified === true) {
        props.loginUser(fields.email);
        setEmail(fields.email);
        setPassword(fields.password);
        // Navigate to the home page.
        navigate("/");
        return;
      }

      // Reset password field to blank.
      const temp = { ...fields };
      temp.password = "";
      setFields(temp);

      // Set error message.
      setErrorMessage("Email and/or password invalid, please try again.");
    } catch (error) {
      console.error("Error during form submission:", error);
      setErrorMessage("An error occurred during verification.");
    }
  };

  // Sign In form
  return (
    <div>
      <h2>Log In</h2>
      <hr />
      <div className="row">
        <div className="col-md-6">
          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label htmlFor="email" className="control-label">Email</label>
              <input name="email" id="email" className="form-control"
                value={fields.email} onChange={handleInputChange} />
            </div>


            <div className="form-group">
              <label htmlFor="password" className="control-label">Password</label>
              <input type="password" name="password" id="password" className="form-control"
                value={fields.password} onChange={handleInputChange} />
            </div>
            <p></p><p>Don't have an account?<a href="/signup"> Sign up here</a></p>
            <div className="form-group">
              <button type="submit" className="button-interactive">Login</button>
            </div>
            {errorMessage !== null &&
              <div className="form-group">
                <span className="text-danger">{errorMessage}</span>
              </div>
            }
          </form>
        </div>
      </div>
    </div>
  );
}

export default Login;
