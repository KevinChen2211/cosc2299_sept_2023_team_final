import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { saveUser } from "../data/repository";
import { initUsers } from "../data/repository";
import { verifySignUpUser } from "../data/repository";

function SignUp(props) {
    const [fields, setFields] = useState({fullname: "",  email: "", password: "" });
    const [errorMessage, setErrorMessage] = useState(null);
    const navigate = useNavigate();
   

    const handleInputChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
    
        // Copy fields.
        const temp = { fullname: fields.fullname, email: fields.email, password: fields.password };
        // OR use spread operator.
        // const temp = { ...fields };
    
        // Update field and state.
        temp[name] = value;
        setFields(temp);
      }

    const handleSubmit = (event) => {
        event.preventDefault();

        const verified = verifySignUpUser(fields.fullname, fields.email, fields.password);

        // If verified login the user.
        if (verified === true) {
            props.loginUser(fields.email);
            const dateJoined = new Date().toLocaleDateString();
            saveUser(fields.fullname, fields.email, fields.password, dateJoined);
            initUsers();
            // Navigate to the home page.
            navigate("/profile");
            return;
        }

        // Set error message.
        setErrorMessage(verified);
    }

    // Sign Up form
    return (
        <div>
            <h2>Sign Up</h2>
            <hr />
            <div className="row">
                <div className="col-md-6">
                    <form onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label htmlFor="fullname" className="control-label">Full Name</label>
                            <input name="fullname" id="fullname" className="form-control"
                                value={fields.fullname} onChange={handleInputChange} />
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
                            <input type="submit" className="btn btn-primary" value="Submit" />
                        </div>
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