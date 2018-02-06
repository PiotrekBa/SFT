API_URL = "http://localhost:8080/";

$(document).ready(function () {

    function getAll() {
        $.ajax({
            url: API_URL + "user"
        }).done(function(object) {
            show(object);
        })
    }

    function show(object) {
        var content = $('tbody');


        object.forEach(function (user, i) {
            i++;
            var tr = $('<tr>' +
                '<th>'+i+'</th>' +
                '<td>'+ user.firstName +'</td>' +
                '<td>'+ user.lastName +'</td>' +
                '<td>'+ user.email +'</td>' +
                '<td>'+ user.enable +'</td>' +
                '<td><a href="user-details?id='+ user.id +'">Details</a></td>' +
                '</tr>')

            content.append(tr);
        })
    }

    getAll();
})