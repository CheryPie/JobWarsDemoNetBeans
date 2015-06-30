function isDataValid(password, username) {
    if (password == "" || username == "") {
        alert("Username and password should not be empty.");
        return false;
    }
    return true;
}

