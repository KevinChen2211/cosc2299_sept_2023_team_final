
import React, { useState, useEffect } from "react";
import axios from 'axios';

function Notifications({ email, password }) {

  const [isNotified, setIsNotified] = useState({});

  useEffect(() => {
    // Fetch products for the given subcategory from the backend
    axios.get("http://localhost:8081/v1/account/${email}/${password}")
      .then(response => {
        // user = response.data;
        setIsNotified(response.data);
      })
      .catch(error => {
        console.error("There was an error fetching products for the subcategory:", error);
      });
  }, []);

  return (
    <div className="text-center">
      <h1>Notifications</h1>
      {isNotified.isNotified === true ?
        <>
          <div>isNotifed</div>
        </>
        :
        <>
          <div>isNotNotifed</div>
        </>
      }
    </div>
  );
}

export default Notifications;