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
        $('#main-table-frame').append('<tr id="grade-to-stud' + 1 + '">');
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
        $('#grade-to-stud1').prepend('<th>' + $('#firstName-input').val() + ' ' + $('#lastName-input').val() + '</th>');
    }

    function appendDefault() {
        $('#main-table-frame').append('<tr id="table-frame-date">');
        $('#table-frame-date').append('<th>Дата</th>');
        $('#main-table-frame').append('<tr id="table-frame-discipline">');
        $('#table-frame-discipline').append('<th>Предметы</th>');
    }

    $('#getDataByGroup').click(function () {
        var groupDataTransferObject = {};
        groupDataTransferObject.groupName = $('#groupName-input').val();
        groupDataTransferObject.groupNumber = $('#groupNumber-input').val();

        console.log(JSON.stringify(groupDataTransferObject));
        $.ajax({
            type: 'POST',
            url: '/profile/getByGroup',
            contentType: "application/json;charset=UTF-8",
            dataType: 'json',
            processData: false,
            data: JSON.stringify(groupDataTransferObject),
            cache: false,
            async: false
        }).fail(function (error) {
            console.log('AJAX call failed :(');
        }).done(function (data) {
            $('#main-table-frame tr,th').remove();
            appendDefault();
            appendTOGroup(data);
        })
    });


    function appendTOGroup(data) {
        var k = 0;
        var colspanValue = 0;
        for (var i = 0; i < Object.keys(data).length; i++) {
            var name = data[Object.keys(data)[i]][0].name;
            var j = 0;
            for (j; j < data[Object.keys(data)[i]].length; j++) {
                var newName = data[Object.keys(data)[i]][j].name;
                if (name != newName) {
                    $('#main-table-frame').append('<tr id="grade-to-stud' + k + '">');
                    $('#grade-to-stud' + k + '').append('<th>' + name + '</th>');
                    name = newName;
                    if (colspanValue == 0) colspanValue = j;
                    k++;
                }
            }
            $('#main-table-frame').append('<tr id="grade-to-stud' + k + '">');
            $('#grade-to-stud' + k + '').append('<th>' + newName + '</th>');
            j = 0;
        }
        console.log(colspanValue);
        k = 0;
        for (i = 0; i < Object.keys(data).length; i++) {
            $('#table-frame-date').append('<th id="date' + i + '" colspan="' + colspanValue + '"> ' + Object.keys(data)[i] + ' </th>');
        }
        for (i = 0; i < colspanValue; i++) {
            var disc = data[Object.keys(data)[k]][i].discipline;
            $('#table-frame-discipline').append('<th id="discipline' + i + '">' + disc + '</th>');
        }

        for (i = 0; i < Object.keys(data).length; i++) {
            name = data[Object.keys(data)[i]][0].name;
            j = 0;
            for (j; j < data[Object.keys(data)[i]].length; j++) {
                var newName = data[Object.keys(data)[i]][j].name;
                var assessment = data[Object.keys(data)[i]][j].assessment;
                if (name != newName) {
                    name = newName;
                    k++;
                }
                $('#grade-to-stud' + k + '').append('<td id="assessment' + j + '">' + assessment + '</td>');
            }
        }
    }
});