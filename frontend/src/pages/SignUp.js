import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { setEmail, setPassword, verifySignUpUser } from "../data/repository";

function SignUp(props) {
    // initialize state variables
    const [fields, setFields] = useState({ firstname: "", lastname: "", mobile: "", email: "", password: "", isNotified: "false" });
    // intialize an error message state
    const [errorMessage, setErrorMessage] = useState(null);
    // initialize navigate function
    const navigate = useNavigate();

    // function to handle input field changes
    const handleInputChange = (event) => {
        const name = event.target.name;
        const value = event.target.type === 'checkbox' ? event.target.checked : event.target.value;

        // create temporary object to update the state based on changed input field
        const temp = { firstname: fields.firstname, lastname: fields.lastname, mobile: fields.mobile, email: fields.email, password: fields.password, isNotfied: fields.isNotified };

        // updates temporary object with new value and sets it as the new state
        temp[name] = value;
        setFields(temp);
    }

    // function to handle form submission
    const handleSubmit = (event) => {
        event.preventDefault();

        // verify user signup info 
        const verified = verifySignUpUser(fields.firstname, fields.lastname, fields.mobile, fields.email, fields.password, fields.isNotified);
        if (verified === true) {
            // Prepare the user data to be sent in the POST request
            const userData = {
                firstName: fields.firstname,
                lastName: fields.lastname,
                email: fields.email,
                password: fields.password,
                phone: fields.mobile,
                isNotified: fields.isNotified
            };

            // send a POST request to create a user account on the server
            fetch(`http://localhost:8081/v1/account/create`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(userData),
            })
                .then((response) => {
                    if (response.ok) {
                        // if request is successful, login the user, set email and password, navigate to LoggedIn page
                        props.loginUser(fields.email, fields.password);
                        setEmail(fields.email);
                        setPassword(fields.password);
                        navigate("/");
                        setErrorMessage('');
                    } else {

                        setErrorMessage(`Error creating account: Email already exists.`);
                    }
                })
                .catch((error) => {

                    setErrorMessage(`Error creating account: ${error.message}`);
                });
        } else {

            setErrorMessage(verified);
        }
    };

    // signup form
    return (
        <div>
            <h2>Create Account</h2>
            <hr />
            <div className="row">
                <div className="col-md-6 col-md-offset-6 align-center">
                    <form onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label htmlFor="firstname" className="control-label">First Name</label>
                            <input name="firstname" id="firstname" className="form-control"
                                value={fields.firstname} onChange={handleInputChange} />
                        </div>

                        <div className="form-group">
                            <label htmlFor="lastname" className="control-label">Last Name</label>
                            <input name="lastname" id="lastname" className="form-control"
                                value={fields.lastname} onChange={handleInputChange} />
                        </div>

                        <div className="form-group">
                            <label htmlFor="mobile" className="control-label">Mobile Number</label>
                            <input name="mobile" id="mobile" className="form-control"
                                value={fields.mobile} onChange={handleInputChange} />
                        </div>

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

                        <div className="form-group">
                            <label for="notifications">
                                <input type="checkbox" name="isNotified" value={fields.isNotified} onChange={handleInputChange} /> I would like to receive notifications from SuperPrice
                            </label>
                        </div>
                        <div className="form-group">
                            <input type="submit" className="btn btn-primary" value="Sign Up" />
                        </div>
                        <p></p><p>Already have an account?<a href="/login"> Login here</a></p>
                        {errorMessage !== null &&
                            <div className="form-group">
                                <span className="errorMessage">{errorMessage}</span>
                            </div>
                        }

                    </form>
                </div>
            </div>
        </div>
    );
}

export default SignUp;