API_URL = "http://localhost:8080/";

$(document).ready(function () {

    $.ajax({
        url: API_URL + "login/check-admin"
    }).done(function(object) {
        if(object === '') {
            window.location.replace(API_URL);
        }
    });

    var id = window.location.search.split('=')[1];

    function getTraining() {
        $.ajax({
            url: API_URL + "training/"+id
        }).done(function(object) {
            console.log(object);
            showDetails(object);
            showUsers(object.users);
        })
    }

    function showDetails(object) {
        var content = $('#training-details');
        var tr = $('<tr>' +
            '<td>'+ object.name + '</td>'+
            '<td>'+ getDate(object.date) +'</td>' +
            '<td>'+ getTime(object.time)+'</td>' +
            '<td>'+ getTime(object.duration) +'</td>'+
            '<td>'+ object.capacity +'</td>'+
            '<td>'+ object.users.length +'</td>'+
            '</th>');
        content.append(tr);
    }

    function getDate(date) {
        var day = date.dayOfMonth;
        var month = date.monthValue;
        var year = date.year;
        return day + "/" + month + "/" + year;
    }

    function getTime(time) {
        var hour = time.hour;
        var minute = "" + time.minute;
        if (minute.length === 1) {
            minute = "0" + minute;
        }
        return hour + " : " + minute;
    }

    function showUsers(users) {
        users.forEach(function(user, i) {
            var tr = ('<tr>' +
                '<td>' + (i+1) +'</td>' +
                '<td>'+ user.firstName +'</td>' +
                '<td>'+ user.lastName +'</td>' +
                '<td>'+ user.email+'</td>' +
                '</tr>');
            $('#users').append(tr);

        })
    }

    getTraining();
})