API_URL = "http://localhost:8080/";

$(document).ready(function () {

    function getAllAtTheWeek() {
        $.ajax({
            url: API_URL + "training/week"
        }).done(function(object) {
            console.log(object);
            show(object);
            header(object);
        })
    }

    // function draw(object) {
    //     var content = $('#content');
    // }

    function show(object) {
        var content = $('#content');

        object.trainings.forEach(function (training) {
            var div = $('<div id="training" data-id="'+ training.id +'">' +
                '<h4>'+ getDate(training.date) +'</h4>' +
                '<h5>'+ getTime(training.time)+'</h5>' +
                '<h5>Name: '+ training.name +'</h5>'+
                '<h5>Capacity: '+ training.capacity +'</h5>'+
                '<h5>Duration: '+ getTime(training.duration) +'</h5>'+
                '<h5>Volunteers: '+ training.users.length+'</h5>'+
                '<a href="/edit.html?id='+ training.id +'">Edit</a>'+
                '</div>');
            div.css("padding", "10px")
                .css("background", "white")
                .css("borderRadius", "10px")
                .css("margin", "10px");
            content.append(div);
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