const USERS_KEY = "users";
const USER_KEY = "user";

let users = [];

// Initialise local storage "users" with data, if the data is already set this function returns immediately.
function initUsers() {
  localStorage.clear();

  // Set data into local storage.
  localStorage.setItem(USERS_KEY, JSON.stringify(users));
}

function saveUser(fullname, email, password, datejoined) {
  users[users.length] = { fullname, email, password, datejoined };
}

function getUsers() {
  // Extract user data from local storage.
  const data = localStorage.getItem(USERS_KEY);

  // Convert data to objects.
  return JSON.parse(data);
}

// NOTE: In this example the login is also persistent as it is stored in local storage.
function verifyUser(email, password) {
  const users = getUsers();
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
      usersname = user.fullname;
    }
   return(usersname);
  }
}



function verifySignUpUser(fullname, email, password) {
  if (fullname === "") {
    return "**Full name is required**";
  }
  if (email === "") {
    return "**Email is required**";
  } else if (!/\S+@\S+\.\S+/.test(email)) {
    return "**Email is invalid**";
  }
  if (password === "") {
    return "**Password is required**";
  } else if (password.length < 8) {
    return "**Password must contain 8 or more characters**";
  }



  return true;
}

function setUser(fullname) {
  localStorage.setItem(USER_KEY, fullname);
}

function getUser(fullname, email, password, datejoined) {
  return localStorage.getItem(fullname, email, password, datejoined);
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
  getFullName
}
