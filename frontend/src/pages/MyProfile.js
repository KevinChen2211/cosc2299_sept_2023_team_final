import React from "react";

import {getFullName} from "../data/repository";

function MyProfile(props) {
  return (
    <div
    style={{
        display: "flex",
        justifyContent: 'center',
        alignItems: "center"
    }}
  >
  
    <div>
      <p></p>
      <h1>My Profile</h1>
      <p></p>
    
      <hr/>
      <p></p>
      <p>Name: {getFullName(props.firstname)}</p>
      <p>Email: {props.email}</p>

    </div>
    </div>
  );
}

export default MyProfile;
