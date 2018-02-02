var API_URL = 'http://localhost:8080/training';

$(document).ready(function(){

    $.ajax({
        url: API_URL
    }).done(function () {
    })

    var button = $('button');

    button.on('click', function (e) {
        e.preventDefault();

        var trainingName = $('#name').val();
        var date = $('#date').val();
        var time = $('#time').val()+":00";
        var duration = $('#duration').val()+":00";
        var capacity = $('#capacity').val();


        var objToSave = {
            name: trainingName,
            time: time,
            date: date,
            duration: duration,
            capacity: capacity,
            visibility: true
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
            console.log(response)
        });

    })
})