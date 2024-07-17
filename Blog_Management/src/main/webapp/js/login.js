/**
 * 
 */
function validateForm() {
    var username = document.forms["loginForm"]["username"].value;
    var password = document.forms["loginForm"]["password"].value;
    if (username === "" || password === "") {
        alert("Username and Password must be filled out");
        return false;
    }
    return true;
}
