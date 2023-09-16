import axios from 'axios';
const USERS_KEY = "users";
const USER_KEY = "user";

let users = [];

// Initialise local storage "users" with data, if the data is already set this function returns immediately.
function initUsers() {
  localStorage.clear();

  // Set data into local storage.
  localStorage.setItem(USERS_KEY, JSON.stringify(users));
}

function saveUser(firstname, lastname, mobile, email, password, datejoined) {
  users[users.length] = { firstname, lastname, mobile, email, password, datejoined };
}

function getUsers() {
  // Extract user data from local storage.
  const data = localStorage.getItem(USERS_KEY);

  // Convert data to objects.
  return JSON.parse(data);
}

// NOTE: In this example the login is also persistent as it is stored in local storage.
function verifyUser(email, password) {
  const users = getAccount(email);
  for (const user of users) {
    if (email === user.email && password === user.password) {
      setUser(email);
      return true;
    }
  }
  return false;
}

// loop finds user's name
function getFullName(currentEmail){
 let usersname = "";
  for (const user of users){
    if (currentEmail === user.email){
      usersname = user.firstname;
    }
   return(usersname);
  }
}

function getAccount (email){
  axios.get(`http://localhost:8080/v1/account/${email}`)
      .then(response => {
       return response.data;
      })
      .catch(error => {
        console.error("There was an error fetching account by email.", error);
      });
}

function verifySignUpUser(firstname, lastname, mobile, email, password) {
  if (firstname === "") {
    return "**First name is required**";
  }
  if (lastname === "") {
    return "**Last name is required**";
  }if (mobile === "") {
    return "**Mobile number is required**";
  }
  else if (!/^04\d{8}$/.test(mobile)){
    return "Mobile format must be 04XXXXXXXX"
  }
  if (email === "") {
    return "**Email is required**";
  } else if (!/\S+@\S+\.\S+/.test(email)) {
    return "**Email is invalid**";
  }
  if (getAccount(email)){
    return "**Email already exist**";
  }
  if (password === "") {
    return "**Password is required**";
  }



  return true;
}

function setUser(firstname) {
  localStorage.setItem(USER_KEY, firstname);
}

function getUser(firstname, lastname, mobile, email, password, datejoined) {
  return localStorage.getItem(firstname, lastname, mobile, email, password, datejoined);
}

function removeUser() {
  localStorage.removeItem(USER_KEY);
}

export {
  initUsers,
  verifyUser,
  getUser,
  removeUser,
  saveUser,
  verifySignUpUser,
  getFullName, 
  getAccount
}
