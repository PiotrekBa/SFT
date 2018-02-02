var API_URL = "http://localhost:8080";

$(document).ready(function () {
    var button = $('button');

    button.on('click', function (e) {
        e.preventDefault();

        var email = $('#email').val();
        var password = $('#password').val();

        var objToSave = {
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
        }

        console.log(objToSave);

        fetch(API_URL+"/checkLogin", myInit).then(function (value) {
            console.log(value);
        })
    })
})