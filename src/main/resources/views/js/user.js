API_URL = "http://localhost:8080/";

$(document).ready(function () {

    function getAllAtTheWeek() {
        $.ajax({
            url: API_URL + "training/user"
        }).done(function(object) {
            console.log(object);
            show(object);
            header(object);
            
        })
    }

    function show(object) {
        var content = $('tbody');
        console.log(content);
        object.trainings.forEach(function (training, i) {
            i++;
            var tr = $('<tr data-id="'+ training.id +'">' +
                '<th>'+ i +'</th>' +
                '<td>'+ training.name + '</td>'+
                '<td>'+ getDate(training.date) +'</td>' +
                '<td>'+ getTime(training.time)+'</td>' +
                '<td>'+ getTime(training.duration) +'</td>'+
                '<td>'+ training.capacity +'</td>'+
                '<td>'+ training.vacancies +'</td>'+
                '<td>'+ getSign(training.sign) +'</td>'+
                '</th>');
            content.append(tr);

        })
    }

    function getSign(sign) {
        console.log(sign);
        if(sign === true) {
            return '<a href="" data-sign="t" class="sign">Sign off</a>';
        }
        return '<a href="" data-sign="f" class="sign">Sign in</a>';
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