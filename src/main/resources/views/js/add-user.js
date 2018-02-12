var API_URL = 'http://localhost:8080/user';

$(document).ready(function () {


    var button = $('button');

    button.on('click', function (e) {
        e.preventDefault();

        var firstName = $('#firstname').val();
        var lastName = $('#lastname').val();
        var email = $('#email').val();
        var password = $('#password').val();
        var confirmPassword = $('#confirm-pass').val();

        var text = '';

        if (firstName.length < 2) {
            text = 'Firstname must have more than one letter';
        } else if (lastName.length < 2) {
            text = 'Lastname must have more than one letter';
        } else if (password.length < 4) {
            text = 'Password must have more than three letter';
        } else if (password !== confirmPassword) {
            text = 'Passwords must be the same';
        } else {
            var objToSave = {
                firstName: firstName,
                lastName: lastName,
                email: email,
                password: password
            }

            var myHeaders = new Headers({
                'Content-Type': 'application/json'
            });

            var myInit = {
                method: 'POST',
                headers: myHeaders,
                cache: 'default',
                body: JSON.stringify(objToSave)
            };

            fetch(API_URL, myInit).then(function (response) {
                window.location.replace(response.url);
            });
        }

        $('#error').text(text).css('color', 'red');

    })
})