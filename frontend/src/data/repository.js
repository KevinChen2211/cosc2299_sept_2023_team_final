import axios from 'axios';

// constants for local storage keys
const PASSWORD = "password";
const EMAIL = "email";

// function to initialize local storage 'users' with data
function initUsers() {
}

// function to fetch user account information from server
function getAccount(email, password) {
  // HTTP GET request to retrieve user data based on email and password
  return axios.get(`http://localhost:8081/v1/account/${email}/${password}`)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      if (error.response && error.response.status === 404) {
        return null; // email not found
      }
      console.error("There was an error fetching account by email.", error);
      throw error;
    });
}

// function to fetch user last name
async function getLastName(email, password) {
  const user = await getAccount(email, password);
  console.log("LASTNAME" + user.lastName);
  return user.lastName;
}

// function to fetch whether user receives notifications or not
async function getIsNotified(email, password) {
  const user = await getAccount(email, password);
  console.log("ISNOTIFIED" + user.isNotified);
  return user.isNotified;
}

// function to fetch user first name
async function getFirstName(email, password) {
  const user = await getAccount(email, password);
  console.log("FIRSTNAME" + user.firstName);
  return user.firstName;
}

// function to fetch user phone number
async function getPhone(email, password) {
  const user = await getAccount(email, password);
  console.log("PHONE" + user.phone);
  return user.phone;
}

// function to verify user's email and password
async function verifyUser(email, password) {
  try {
    const user = await getAccount(email, password);
    if (email === user.email && password === user.password) {

      return true; // user is verified
    }

    return false; // user is NOT verified
  } catch (error) {
    console.error("There was an error verifying the user.", error);
    return false;
  }
}

// function to verify user input during signup
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
  if (password === "") {
    return "**Password is required**";
  }

  return true; // input is valid
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

// function to remove user data from local storage when logging out
function removeUser() {
  localStorage.removeItem(EMAIL);
  localStorage.removeItem(PASSWORD);
}

// function to delete user account
const deleteAccount = async (email, password) => {
  try {
    // make an API request to delete the user account
    const response = await fetch(`http://localhost:8081/v1/account/delete/${email}/${password}`, {
      method: "DELETE",
    });

    if (response.ok) {
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
