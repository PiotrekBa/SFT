var API_URL = 'http://localhost:8080/';

$(document).ready(function(){

    $.ajax({
        url: API_URL + "login/check-admin"
    }).done(function(object) {
        if(object === '') {
            window.location.replace(API_URL);
        }
    })


    var button = $('button');

    button.on('click', function (e) {
        e.preventDefault();

        var trainingName = $('#name').val();
        var date = $('#date').val();
        var time = $('#time').val()+':00';
        var duration = $('#duration').val()+':00';
        var capacity = $('#capacity').val();


        var objToSave = {
            name: trainingName,
            time: time,
            date: date,
            duration: duration,
            capacity: capacity,
            visibility: true
        }

        console.log(JSON.stringify(objToSave))

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

        fetch(API_URL+"training", myInit).then(function (response) {
            console.log(response)
        });

    })
})