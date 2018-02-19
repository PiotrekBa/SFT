API_URL = "http://localhost:8080/";

$(document).ready(function () {

    function getAllAtTheWeek() {
        $.ajax({
            url: API_URL + "training/week"
        }).done(function(object) {
            if(object === '') {
                window.location.replace(API_URL);
            } else {
                show(object);
                header(object);
            }
        })
    }

    function show(object) {
        var content = $('tbody');
        console.log(content);
        object.trainings.forEach(function (training, i) {
            i++;
            var tr = $('<tr>' +
                '<th>'+ i +'</th>' +
                '<td>'+ training.name + '</td>'+
                '<td>'+ getDate(training.date) +'</td>' +
                '<td>'+ getTime(training.time)+'</td>' +
                '<td>'+ getTime(training.duration) +'</td>'+
                '<td>'+ training.capacity +'</td>'+
                '<td>'+ training.users.length +'</td>'+
                '<td><a href="training-details.html?id='+ training.id +'">Details</a></td>'+
                '</th>');
            content.append(tr);
        })
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

    function header(object) {
        var header = $('#header').children().children();
        header.text('The nearest trainings ('+
            getDate(object.startDate)+' - ' +
            getDate(object.finishDate) +')');
    }

    getAllAtTheWeek();
})