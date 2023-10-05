import React, { useState, useEffect } from "react";
import { getFirstName, getLastName, getPhone } from "../data/repository";
import { getAccount, deleteAccount } from "../data/repository";

function MyProfile({ email, password }) {
  // const history = useHistory();
  const [isEditing, setIsEditing] = useState(false);
  const [showConfirmation, setShowConfirmation] = useState(false);
  const [fields, setFields] = useState({ firstname: "", lastname: "", mobile: "", email: "", password: "" });

  const handleInputChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;

    // Copy fields.
    const temp = { firstname: fields.firstname, lastname: fields.lastname, mobile: fields.mobile, email: fields.email, password: fields.password };
    // OR use spread operator.
    // const temp = { ...fields };

    // Update field and state.
    temp[name] = value;
    setFields(temp);
  }

  const handleEditClick = () => {
    setIsEditing(true);
  };

  const handleCancelClick = () => {
    setIsEditing(false);
  };

  const handleUpdate = (event) => {
    setIsEditing(false);
  }

  const handleDelete = () => {
    setShowConfirmation(true);
  };

  const handleDeleteConfirmed = async () => {
    deleteAccount(email, password);
    setShowConfirmation(false); 
  };

 
  const [firstName, setFirstName] = useState('');

  const [lastName, setLastName] = useState('');

  const [phone, setPhone] = useState('');

  // Fetch user data and set the last name when the component mounts
  useEffect(() => {
    async function fetchData() {
      try {
        const userFirstName = await getFirstName(email, password);
        setFirstName(userFirstName);
        const userLastName = await getLastName(email, password);
        setLastName(userLastName);
        const userPhone = await getPhone(email, password);
        setPhone(userPhone);
      } catch (error) {
        console.error("Error fetching last name:", error);
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
                // Display User Information
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

                  <div className="form-group">
                    <label htmlFor="email">Email</label>
                    <input
                      name="email"
                      id="email"
                      className="form-control"
                      value={email}
                      readOnly
                    />
                  </div>
                </>
              ) : (
                // Edit Form
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
                  {/* Add more fields for lastName, email, mobile, and password */}
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
                      name="phone"
                      id="phone"
                      className="form-control"
                      onChange={handleInputChange}
                    />
                  </div>

                  <div className="form-group">
                    <label htmlFor="email">Email</label>
                    <input
                      name="email"
                      id="email"
                      className="form-control"
                      onChange={handleInputChange}
                    />
                  </div>
                </>
              )}
            </div>
          </div>
          {isEditing && (
            // "Cancel", "Update", and "Delete" buttons
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
        // Delete confirmation dialog
        <div className="modal fade show" style={{ display: "block" }}>
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title">Confirm Deletion</h5>
                <button
                  type="button"
                    className="close"
                    onClick={() => setShowConfirmation(false)} // Close the dialog when the close button is clicked
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
                    onClick={() => setShowConfirmation(false)} // Close the dialog when "Cancel" is clicked
                  >
                    Cancel
                  </button>
                  <button
                    type="button"
                    className="btn btn-danger"
                    onClick={handleDeleteConfirmed} // Trigger the delete action when "Delete" is clicked
                  >
                    Delete
                  </button>
                </div>
              </div>
            </div>
          </div>
        )}
      </div>
 
  );
}

export default MyProfile;




