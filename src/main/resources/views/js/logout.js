API_URL = "http://localhost:8080/";

$(document).ready(function () {
    $('#log-out').on('click', function(e) {
        e.preventDefault();

        var myHeaders = new Headers({
            'Content-Type': 'application/json'
        });

        var myInit = {
            credentials: 'include',
            method: 'POST',
            headers: myHeaders,
            cache: 'default'
        };

        fetch(API_URL + "login/logout", myInit).then(function (value) {
            window.location.replace(value.url);
        })
    })
})