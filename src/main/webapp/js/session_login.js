// This calls our back-end Java program that sets our session info
function login() {

    var url = "api/login_servlet";

    // Grab data from the HTML form
    var sessionKey = "loginID";
    var sessionValue = $("#sessionValue").val();

    // Create a JSON request based on that data
    var dataToServer = {"loginID" : sessionValue};

    // Post
    $.post(url, dataToServer, function (dataFromServer) {
        // We are done. Write a message to our console
        console.log("Finished calling servlet.");
        console.log(dataFromServer);
        // Clear the form
        $("#sessionKey").val("");
        $("#sessionValue").val("");
        getLogin();
    });
}

getLogin();

// This gets session info from our back-end servlet.
function getLogin() {

    var url = "api/get_login_servlet";

    $.post(url, null, function (dataFromServer) {
        console.log("Finished calling servlet.");
        console.log(dataFromServer);
        // Update the HTML with our result
        if (dataFromServer === "null") {
            $('#logoutSection').hide();
            $('#getSessionResult').html("You are not logged in");
        }
        else {
            $('#logoutSection').show();
            $('#getSessionResult').html(dataFromServer);
        }
    });
}

// This method calls the servlet that invalidates our session
function invalidateSessionButton() {

    var url = "api/invalidate_session_servlet";

    $.post(url, null, function (dataFromServer) {
        console.log("Finished calling servlet.");
        console.log(dataFromServer);
        getLogin();
    });
}

// Hook the functions above to our buttons
button = $('#getLogin');
button.on("click", getLogin);

button = $('#login');
button.on("click", login);

button = $('#invalidateSession');
button.on("click", invalidateSessionButton);