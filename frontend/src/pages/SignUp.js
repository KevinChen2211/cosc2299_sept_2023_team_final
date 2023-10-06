import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { verifySignUpUser } from "../data/repository";
import axios from 'axios';

function SignUp(props) {
    const [fields, setFields] = useState({ firstname: "", lastname: "", mobile: "", email: "", password: "", isNotified: "false"});
    const [errorMessage, setErrorMessage] = useState(null);
    const navigate = useNavigate();
    

    const handleInputChange = (event) => {
        const name = event.target.name;
        const value = event.target.type === 'checkbox' ? event.target.checked : event.target.value;

        // Copy fields.
        const temp = { firstname: fields.firstname, lastname: fields.lastname, mobile: fields.mobile, email: fields.email, password: fields.password, isNotfied: fields.isNotified };
        // OR use spread operator.
        // const temp = { ...fields };

        // Update field and state.
        temp[name] = value;
        setFields(temp);
    }

    // const handleSubmit = (event) => {
    //     event.preventDefault();

    //     const verified = verifySignUpUser(fields.firstname, fields.lastname, fields.mobile, fields.email, fields.password);

    //     // If verified signup the user.
    //     if (verified === true) {
    //         // storing user account
    //         axios.post('http://localhost:8081/v1/account/create')
    //             .then(response => {
    //                 props.loginUser(fields.email);
    //                 // saveUser(fields.firstname, fields.lastname, fields.mobile, fields.email, fields.password);
    //                 // initUsers();
    //                 // Navigate to the home page.
    //                 navigate("/");
    //                 setErrorMessage('');
    //             })
    //             .catch(error => {

    //                 setErrorMessage(`Error creating account: ${error.message}`);
    //             });


    //         return;
    //     }

    //     // Set error message.
    //     setErrorMessage(verified);
    // }
    const handleSubmit = (event) => {
        event.preventDefault();

        const verified = verifySignUpUser(fields.firstname, fields.lastname, fields.mobile, fields.email, fields.password, fields.isNotfied);
        if (verified === true) {
            // Prepare the user data to be sent in the POST request
            const userData = {
                firstName: fields.firstname,
                lastName: fields.lastname,
                // address: "123 main st",
                email: fields.email,
                password: fields.password,
                phone: fields.mobile,
                isNotified: fields.isNotified
            };

            fetch('http://localhost:8081/v1/account/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(userData),
            })
                .then((response) => {
                    if (response.ok) {

                        props.loginUser(fields.email);
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

    // Sign Up form
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
                                <input type="checkbox" name= "isNotified" value={fields.isNotified} onChange={handleInputChange} /> I would like to receive notifications from SuperPrice
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