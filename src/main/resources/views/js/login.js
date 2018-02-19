var API_URL = "http://localhost:8080/login/check";

$(document).ready(function () {
    var button = $('button');

    button.on('click', function (e) {
        e.preventDefault();

        var email = $('#email').val();
        var password = $('#password').val();

        var objToSave = {
            email: email,
            password: password
        };

        var myHeaders = new Headers({
            'Content-Type': 'application/json'
        });

        var myInit = {
            credentials: 'include',
            method: 'POST',
            headers: myHeaders,
            cache: 'default',
            body: JSON.stringify(objToSave)
        };

        fetch(API_URL , myInit).then(function (response) {
            window.location.replace(response.url);
        })
    })
})