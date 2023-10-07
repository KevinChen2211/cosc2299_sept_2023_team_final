import React, { useState, useEffect } from "react";
import { getFirstName, getIsNotified, getLastName, getPhone, removeUser, setEmail } from "../data/repository";
import { getAccount, deleteAccount, verifySignUpUser } from "../data/repository";
import { useNavigate } from "react-router-dom";

function MyProfile({ email, password, logoutUser }) { // function receives email and password as props
  // initialize state variables
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [phone, setPhone] = useState('');
  const [isNotified, setIsNotified] = useState('');
  const [isEditing, setIsEditing] = useState(false);
  const [showConfirmation, setShowConfirmation] = useState(false);
  const [fields, setFields] = useState({ firstname: '', lastname: '', mobile: '' });
  const [errorMessage, setErrorMessage] = useState(null);
  const navigate = useNavigate();

  const handleInputChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;

    // update field and state
    setFields(prevState => ({
      ...prevState,
      [name]: value
    }));
  }

  // function to enable editing user info
  const handleEditClick = () => {
    setIsEditing(true);
  };

  // function to cancel editting user info
  const handleCancelClick = () => {
    setIsEditing(false);
  };

  // function to initiate delete account process
  const handleDelete = () => {
    setShowConfirmation(true);
  };

  // function to confirm and execute delete account
  const handleDeleteConfirmed = async () => {
    // delete the user's acc
    deleteAccount(email, password);
    setShowConfirmation(false);
    // remove user's data from the app
    removeUser();
    // clear user's email
    setEmail(null);
    logoutUser();
    // navigates to homepage upon deletion
    navigate("/");
  };

  // function to update user info
  const handleUpdate = (event) => {

    event.preventDefault();

    const verified = verifySignUpUser(fields.firstname, fields.lastname, fields.mobile, email, password);
    console.log(verified);
    if (verified === true) {
      // prep user data to be sent in the POST request
      const userData = {
        firstName: fields.firstname,
        lastName: fields.lastname,
        email: email,
        password: password,
        phone: fields.mobile,
        isNotified: isNotified // Use isNotified state directly
      };

      // sends PUT request to update user's account
      fetch(`http://localhost:8081/v1/account/update/${email}/${password}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData),
      })
        .then((response) => {
          if (response.ok) {
            // if the request is successful, update state and exit edit mode
            console.log(response.ok);
            setErrorMessage('');
            setIsEditing(false);
            setFirstName(fields.firstname);
            setLastName(fields.lastname);
            setPhone(fields.mobile);
          } else {
            setErrorMessage(`Error updating account: ${response.statusText}`);
          }
        })
        .catch((error) => {
          setErrorMessage(`Error updating account: ${error.message}`);
        });
    } else {
      setErrorMessage(verified);
    }
  };

  // fetch user data and set the state when the component mounts
  useEffect(() => {
    async function fetchData() {
      try {
        const userFirstName = await getFirstName(email, password);
        const userLastName = await getLastName(email, password);
        const userPhone = await getPhone(email, password);
        const userIsNotified = await getIsNotified(email, password);

        setFirstName(userFirstName);
        setLastName(userLastName);
        setPhone(userPhone);
        setIsNotified(userIsNotified);

        // initialize fields state after fetching data
        setFields({ firstname: userFirstName, lastname: userLastName, mobile: userPhone });
      } catch (error) {
        console.error("Error fetching user data:", error);
      }
    }

    fetchData();
  }, [email, password]);

  return (
    <div className="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
      <div className="card h-100">
        <div className="card-body">
          <div className="row gutters">
            <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
              <div className="d-flex justify-content-between align-items-center">
                <h2>Profile Settings</h2>
                {!isEditing ? (
                  <button
                    className="btn btn-primary"
                    onClick={handleEditClick}
                  >
                    Edit
                  </button>
                ) : null}
              </div>
            </div>
            <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
              {!isEditing ? (
                // display user account infomation
                <>
                  <div className="form-group">
                    <label htmlFor="firstName">First Name</label>
                    <input
                      name="firstname"
                      id="firstname"
                      className="form-control"
                      value={firstName}
                      readOnly
                    />
                  </div>

                  <div className="form-group">
                    <label htmlFor="lastname">Last Name</label>
                    <input
                      name="lastname"
                      id="lastname"
                      className="form-control"
                      value={lastName}
                      readOnly
                    />
                  </div>

                  <div className="form-group">
                    <label htmlFor="phone">Phone</label>
                    <input
                      name="phone"
                      id="phone"
                      className="form-control"
                      value={phone}
                      readOnly
                    />
                  </div>
                </>
              ) : (
                // edit form 
                <>
                  <div className="form-group">
                    <label htmlFor="firstName">First Name</label>
                    <input
                      name="firstname"
                      id="firstname"
                      className="form-control"

                      onChange={handleInputChange}
                    />
                  </div>

                  <div className="form-group">
                    <label htmlFor="lastName">Last Name</label>
                    <input
                      name="lastname"
                      id="lastname"
                      className="form-control"
                      onChange={handleInputChange}
                    />
                  </div>

                  <div className="form-group">
                    <label htmlFor="phone">Mobile Number</label>
                    <input
                      name="mobile"
                      id="mobile"
                      className="form-control"
                      onChange={handleInputChange}
                    />
                  </div>
                </>
              )}
            </div>
          </div>
          {isEditing && (
            // cancel + update + delete buttons
            <div className="row gutters">
              <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                <button
                  type="button"
                  className="btn btn-danger"
                  onClick={handleDelete}
                >
                  Delete Account
                </button>
              </div>
              <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 text-right">
                <button
                  type="button"
                  className="btn btn-secondary mr-2"
                  onClick={handleCancelClick}
                >
                  Cancel
                </button>
                <button
                  type="button"
                  className="btn btn-primary"
                  onClick={handleUpdate}
                >
                  Update
                </button>
              </div>
            </div>
          )}
        </div>
      </div>

      {showConfirmation && (
        // delete confirmation dialog
        <div className="modal fade show" style={{ display: "block" }}>
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title">Confirm Deletion</h5>
                <button
                  type="button"
                  className="close"
                  // close dialog when the exit button is clicked
                  onClick={() => setShowConfirmation(false)}
                >
                  <span>&times;</span>
                </button>
              </div>
              <div className="modal-body">
                <p>Are you sure you want to delete your account?</p>
              </div>
              <div className="modal-footer">
                <button
                  type="button"
                  className="btn btn-secondary"
                  // close dialog when "Cancel" is clicked
                  onClick={() => setShowConfirmation(false)}
                >
                  Cancel
                </button>
                <button
                  type="button"
                  className="btn btn-danger"
                  // trigger delete action when "Delete" is clicked
                  onClick={handleDeleteConfirmed}
                >
                  Delete
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
      <div>{errorMessage}</div>
    </div>

  );
}

export default MyProfile;




