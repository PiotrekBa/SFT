API_URL = "http://localhost:8080/";

$(document).ready(function () {

    function getAll(page) {
        $.ajax({
            url: API_URL + "training/all/"+page
        }).done(function(object) {
            $('tbody').children().remove();
            show(object, page);
            pages(object.pages);
            linksToPages();
        })
    }

    function show(object, page) {
        var content = $('tbody');
        var elementOnPage = object.elementsOnPage;
        object.trainings.forEach(function (training, i) {
            var counter = elementOnPage*(page-1)+i+1;
            var tr = $('<tr>' +
                '<th>'+ counter +'</th>' +
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

    function pages(amountOfPages) {
        var pagesLinks = $('#pages').children().children();
        if (pagesLinks.children().length > 0) {
            pagesLinks.children().remove();
        }
        for(var i = 1; i <= amountOfPages; i++) {
            pagesLinks.append('<a href ="" class="pagesLink">'+i+'</a>  ');
        }
    }

    function linksToPages() {
        var pagesLinks = $('.pagesLink');

        pagesLinks.on('click', function (e) {
            e.preventDefault();
            var page = $(e.target).text();
            getAll(page);
        })
    }


    getAll(1);
})