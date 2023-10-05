import React, { useState, useEffect } from "react";
import { getFirstName, getLastName, getPhone } from "../data/repository";
import { getAccount } from "../data/repository";

function MyProfile({email, password}) {

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
    // <div
    //   style={{
    //     display: "flex",
    //     justifyContent: 'center',
    //     alignItems: "center"
    //   }}
    // >

    //   <div>
    //     <p></p>
    //     <h1>My Profile</h1>
    //     <p></p>

    //     <hr />
    //     <p></p>
    //     <p>Name: {getFullName(props.firstname)}</p>
    //     <p>Email: {props.email}</p>
    //   </div>
    // </div>


    <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
      <div class="card h-100">
        <div class="card-body">
          <div class="row gutters">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
              <h6 class="mb-2 text-primary">Personal Details</h6>
            </div>
            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
              <div class="form-group">
                <label for="firstName">First Name</label>
                <input name="firstname" id="firstname" className="form-control"
                  value={firstName} onChange={handleInputChange} />
              </div>
            </div>
            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
              <div class="form-group">
                <label for="lastName">Last Name</label>
                <input name="lastname" id="lastname" className="form-control"
                // const user = await getAccount(email, password);
                  value={lastName} onChange={handleInputChange} />
              </div>
            </div>
            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
              <div class="form-group">
                <label for="email">Email</label>
                <input name="email" id="email" className="form-control"
                  value={email} onChange={handleInputChange} />
              </div>
            </div>
            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
              <div class="form-group">
                <label for="phone">Phone</label>
                <input name="mobile" id="mobile" className="form-control"
                  value={phone} onChange={handleInputChange} />
              </div>
            </div>

          </div>
          <div class="row gutters">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
              <div class="text-right">
                <button type="button" id="submit" name="submit" class="btn btn-secondary">Cancel</button>
                <button type="button" id="submit" name="submit" class="btn btn-primary">Update</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default MyProfile;
