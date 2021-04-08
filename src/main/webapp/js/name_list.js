// Main Javascript File
function htmlSafe(data) {
    return data.replace(/&/g, "&amp;").replace(/>/g, "&gt;").replace(/</g, "&lt;");
}
function updateTable() {
    // Here's where your code is going to go.
    console.log("updateTable called");
    // Define a URL
    var url = "api/name_list_get";
    // Start a web call. Specify:
    // URL
    // Data to pass (nothing in this case)
    // Function to call when we are done
    $.getJSON(url, null, function(json_result) {
        $('#datatable tbody tr').remove();
        // List all the names
            for (let i = 0; i < json_result.length; i++) {
                // Print the first name
                console.log(json_result[i].first, json_result[i].last);
                $('#datatable tbody').append('<tr><td>'
                    +json_result[i].id
                    +'</td><td>'
                    +htmlSafe(json_result[i].first)
                    +'</td><td>'
                    +htmlSafe(json_result[i].last)
                    +'</td><td>'
                    +htmlSafe(json_result[i].email)
                    +'</td><td>'
                    +htmlSafe(json_result[i].phone)
                    +'</td><td>'
                    +htmlSafe(json_result[i].birthday)
                    +'</td></tr>');
            }
            console.log("Done");
        }
    );
}

// Call your code.
updateTable();

// Called when "Add Item" button is clicked
function showDialogAdd() {
    console.log("ADD ITEM");

    // Show the hidden dialog
    $('#myModal').modal('show');

    $('#id').val("");
    $('#firstName').val("");
    $('#lastName').val("");
    $('#email').val("");
    $('#phone').val("");
    $('#birthday').val("");

    $('#firstName').removeClass("is-valid");
    $('#lastName').removeClass("is-valid");
    $('#email').removeClass("is-valid");
    $('#phone').removeClass("is-valid");
    $('#birthday').removeClass("is-valid");

    $('#firstName').removeClass("is-invalid");
    $('#lastName').removeClass("is-invalid");
    $('#email').removeClass("is-invalid");
    $('#phone').removeClass("is-invalid");
    $('#birthday').removeClass("is-invalid");
}

let addItemButton = $('#addItem');
addItemButton.on("click", showDialogAdd);

function saveChanges() {
    console.log("Save changes");
    let isValid = true;
    let firstName = $('#firstName').val();
    let lastName = $('#lastName').val();
    let email = $('#email').val();
    let phone = $('#phone').val();
    let birthday = $('#birthday').val();

    // Create the regular expression
    let reg = /^[A-Za-z]{1,10}$/;

    let regEmail = /^.+@.+$/;

    let regPhone = /^[0-9]{3}-[0-9]{3}-[0-9]{4}$/;

    let regBirth = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;

    // Test the regular expression to see if there is a match
    if (reg.test(firstName)) {
        $('#firstName').removeClass("is-invalid");
        $('#firstName').addClass("is-valid");
    } else {
        $('#firstName').removeClass("is-valid");
        $('#firstName').addClass("is-invalid");
    }

    if (reg.test(lastName)) {
        $('#lastName').removeClass("is-invalid");
        $('#lastName').addClass("is-valid");
    } else {
        $('#lastName').removeClass("is-valid");
        $('#lastName').addClass("is-invalid");
    }

    if (regEmail.test(email)) {
        $('#email').removeClass("is-invalid");
        $('#email').addClass("is-valid");
    } else {
        $('#email').removeClass("is-valid");
        $('#email').addClass("is-invalid");
    }

    if (regPhone.test(phone)) {
        $('#phone').removeClass("is-invalid");
        $('#phone').addClass("is-valid");
    } else {
        $('#phone').removeClass("is-valid");
        $('#phone').addClass("is-invalid");
    }

    if (regBirth.test(birthday)) {
        $('#birthday').removeClass("is-invalid");
        $('#birthday').addClass("is-valid");
    } else {
        $('#birthday').removeClass("is-valid");
        $('#birthday').addClass("is-invalid");
    }
    if (isValid) {
        console.log("Valid form");
        // Code to submit your form will go here.
        let firstName = $('#firstName').val();
        let lastName = $('#lastName').val();
        let email = $('#email').val();
        let phone = $('#phone').val();
        let birthday = $('#birthday').val();

        let user = {
            first: firstName,
            last: lastName,
            email,
            phone,
            birthday,
        }
        console.log(user);

        let userJSONObject = JSON.stringify(user);

        $.ajax({
            type: 'POST',
            url: 'api/name_list_edit',
            data: userJSONObject,
            success: function(dataFromServer) {
                console.log(dataFromServer);
                updateTable();
            },
            contentType: "application/json",
            dataType: 'JSON'
        });
    }
}
let saveChangesButton = $('#saveChanges');
saveChangesButton.on("click", saveChanges);

