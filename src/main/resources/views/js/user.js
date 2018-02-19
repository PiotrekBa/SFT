API_URL = "http://localhost:8080/";

$(document).ready(function () {

    function getAllAtTheWeek() {
        $.ajax({
            url: API_URL + "/training/user"
        }).done(function(object) {
            if (object === '') {
                window.location.replace(API_URL);
            } else {
                show(object);
                header(object);
                signLink();
            }
        })
    }

    function show(object) {
        var content = $('tbody');
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
        if(sign === true) {
            return '<a href="" class="sign">Sign off</a>';
        }
        return '<a href="" class="sign">Sign up</a>';
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

    function signLink() {
        var links = $('.sign');
        links.on('click', function(e) {
            e.preventDefault();
            var aText = $(e.target).text();
            var vac = $(e.target).parent().prev().text();

            if(aText === 'Sign up' & vac ==='0') {
                alert('No vacancies at this training');
            }

            var id = $(e.target).parent().parent().data('id');
            var myHeaders = new Headers({
                'Content-Type': 'application/json'
            });

            var myInit = {
                credentials: 'include',
                method: 'PUT',
                headers: myHeaders,
                cache: 'default'
            };

            fetch(API_URL + "training/user/"+ id, myInit).then(function (value) {
                clearTable();
                getAllAtTheWeek();
            })
        })
    }

    function clearTable() {
        $('tbody').children().remove();
    }

    getAllAtTheWeek();
})