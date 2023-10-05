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

function getAccount(email, password) {
  console.log(email + "Hello WORLD" + password);
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

async function getLastName(email, password){
  const user = await getAccount(email, password);
  console.log("LASTNAME" + user.lastName);
  return user.lastName;
}

async function getFirstName(email, password){
  const user = await getAccount(email, password);
  console.log("FIRSTNAME" + user.firstName);
  return user.firstName;
}

async function getPhone(email, password){
  const user = await getAccount(email, password);
  console.log("PHONE" + user.phone);
  return user.phone;
}

function getAccountByEmail(email) {
  console.log("Hello WORLD" + email)
  return axios.get(`http://localhost:8081/v1/account/${email}`)
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

// function setUser(firstname) {
//   localStorage.setItem(USER_KEY, firstname);
// }

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

export {
  initUsers,
  verifyUser,
  getEmail,
  getPassword,
  setEmail,
  setPassword,
  removeUser,
  getAccountByEmail,
  // saveUser,
  verifySignUpUser,
  // getFullName,
  getAccount,
  getFirstName,
  getPhone,
  getLastName
}
