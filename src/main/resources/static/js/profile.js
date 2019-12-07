$(document).ready(function () {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });


    $('#getDataByName').click(function () {
        var studDataTransferObject = {};
        studDataTransferObject.personFirstName = $('#firstName-input').val();
        studDataTransferObject.personSecondName = $('#lastName-input').val();

        console.log($('#firstName-input').val() + $('#lastName-input').val());
        console.log(JSON.stringify(studDataTransferObject));
        $.ajax({
            type: 'POST',
            url: '/profile/getByName',
            contentType: "application/json;charset=UTF-8",
            dataType: 'json',
            processData: false,
            data: JSON.stringify(studDataTransferObject),
            cache: false,
            async: false
        }).fail(function (error) {
            console.log('AJAX call failed :(');
        }).done(function (data) {
            $('#main-table-frame tr,th').remove();
            appendDefault();
            appendTOSingleOne(data);
        })
    });

    function appendTOSingleOne(data) {
        console.log(data);
        console.log(Object.keys(data));
        $('#main-table-frame').append('<tr id="grade-to-stud'+ 1 +'">');
        for (var i = 0; i < Object.keys(data).length; i++) {
            $('#table-frame-date').append('<th id="date' + i + '" colspan="' + data[Object.keys(data)[i]].length + '"> ' + Object.keys(data)[i] + ' </th>');
        }
        console.log(Object.keys(data).length);
        for (i = 0; i < Object.keys(data).length; i++) {
            for (var j = 0; j < data[Object.keys(data)[i]].length; j++) {
                var dis = data[Object.keys(data)[i]][j].discipline;
                var assess = data[Object.keys(data)[i]][j].assessment;
                $('#table-frame-discipline').append('<th id="discipline' + j + '">' + dis + '</th>');
                $('#grade-to-stud1').append('<td id="assessment' + j + '">' + assess + '</td>');
            }
        }
        $('#grade-to-stud1').prepend('<th>'+ $('#firstName-input').val() +' '+ $('#lastName-input').val() +'</th>');
    }

    function appendDefault() {
        $('#main-table-frame').append('<tr id="table-frame-date">');
        $('#table-frame-date').append('<th>Дата</th>');
        $('#main-table-frame').append('<tr id="table-frame-discipline">');
        $('#table-frame-discipline').append('<th>Предметы</th>');
    }
});