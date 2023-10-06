import axios from 'axios';
const PASSWORD = "password";
const EMAIL = "email";

// let users = [];

// // Initialise local storage "users" with data, if the data is already set this function returns immediately.
function initUsers() {
}

// function saveUser(firstname, lastname, mobile, email, password, datejoined) {
//   users[users.length] = { firstname, lastname, mobile, email, password, datejoined };
// }

function update(firstName, lastName, phone, email, password) {
  const userData = {
    firstName: firstName,
    lastName: lastName,
    phone: phone,
    email: email,
    password: password,
  };
  return axios.put(`http://localhost:8081/v1/account/update/${email}/${password}`, userData)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      console.error("There was an error updating the account information.", error);
      throw error;
    });
}

function getAccount(email, password) {
  return axios.get(`http://localhost:8081/v1/account/${email}/${password}`)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      if (error.response && error.response.status === 404) {
        return null; // Email not found
      }
      console.error("There was an error fetching account by email.", error);
      throw error;
    });
}

async function getLastName(email, password) {
  const user = await getAccount(email, password);
  console.log("LASTNAME" + user.lastName);
  return user.lastName;
}

async function getIsNotified(email, password) {
  const user = await getAccount(email, password);
  console.log("ISNOTIFIED" + user.isNotified);
  return user.isNotified;
}

async function getFirstName(email, password) {
  const user = await getAccount(email, password);
  console.log("FIRSTNAME" + user.firstName);
  return user.firstName;
}

async function getPhone(email, password) {
  const user = await getAccount(email, password);
  console.log("PHONE" + user.phone);
  return user.phone;
}

async function verifyUser(email, password) {
  try {
    const user = await getAccount(email, password);
    if (email === user.email && password === user.password) {
      // setUser(email);

      return true;
    }

    return false;
  } catch (error) {
    console.error("There was an error verifying the user.", error);
    return false;
  }
}


function verifySignUpUser(firstname, lastname, mobile, email, password) {
  if (firstname === "") {
    return "**First name is required**";
  }
  if (lastname === "") {
    console.log(lastname);
    return "**Last name is required**";
  } if (mobile === "") {
    return "**Mobile number is required**";
  }
  else if (!/^04\d{8}$/.test(mobile)) {
    return "Mobile format must be 04XXXXXXXX"
  }
  if (email === "") {
    return "**Email is required**";
  } else if (!/\S+@\S+\.\S+/.test(email)) {
    return "**Email is invalid**";
  }
  // if (getAccount(email)) {
  //   return "**Email already exist**";
  // }
  if (password === "") {
    return "**Password is required**";
  }



  return true;
}

function setEmail(email) {
  localStorage.setItem(EMAIL, email);
}


function setPassword(password) {
  localStorage.setItem(PASSWORD, password);
}

function getEmail() {
  return localStorage.getItem(EMAIL);
}

function getPassword() {
  var password = localStorage.getItem(PASSWORD);
  return password;
}

function removeUser() {
  localStorage.removeItem(EMAIL);
  localStorage.removeItem(PASSWORD);
}

const deleteAccount = async (email, password) => {
  try {
    // Make an API request to delete the user account
    const response = await fetch(`http://localhost:8081/v1/account/delete/${email}/${password}`, {
      method: "DELETE",
    });

    if (response.ok) {
      // If the deletion was successful, you can handle further actions here
      console.log("User account deleted successfully");
    } else {
      console.error("Failed to delete user account");
    }
  } catch (error) {
    console.error("Error deleting user account:", error);
  }
};


export {
  initUsers,
  verifyUser,
  getEmail,
  getPassword,
  setEmail,
  setPassword,
  removeUser,
  verifySignUpUser,
  getAccount,
  getFirstName,
  getPhone,
  getLastName,
  deleteAccount,
  getIsNotified
}
