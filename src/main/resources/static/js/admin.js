$(document).ready(function () {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    function getGroups() {
        $.ajax({
            type: 'POST',
            url: '/admin/getInfo'
        }).fail(function (error) {
            console.log(error);
            console.log('AJAX call failed :(');
        }).done(function (data) {
            append(data)
        });
    }

    getGroups();
    var DATA;
    var DISCIPLINE_DATA;

    function append(data) {
        console.log(data);
        var i = 0;
        for (i = 0; i < data[3].length; i++) {
            $('#add-person-group-name').append('<option value="' + data[3][i] + '" id="option1">' + data[3][i] + '</option>');
        }
        for (i = 0; i < data[2].length; i++) {
            $('#add-person-group-number').append('<option value="' + data[2][i] + '">' + data[2][i] + '</option>');
        }
        for (i = 0; i < data[0].length; i++) {
            $('#add-person-group-date').append('<option value="' + data[0][i] + '">' + data[0][i] + '</option>');
        }
        DATA = data;
        var groupDataTransferObject = {};
        groupDataTransferObject.groupName = $('#option1').val();
        getManyValues(groupDataTransferObject);
    }

    $("select.group-name").change(function () {
        $('#add-person-group-discipline').empty();
        var selectedGroup = $(this).children("option:selected").val();
        var groupDataTransferObject = {};
        groupDataTransferObject.groupName = selectedGroup;
        console.log(groupDataTransferObject);
        getManyValues(groupDataTransferObject);
    });

    function getManyValues(value) {
        $.ajax({
            type: 'POST',
            url: '/admin/getDisciplines',
            contentType: "application/json;charset=UTF-8",
            dataType: 'json',
            processData: false,
            data: JSON.stringify(value),
            cache: false,
            async: false
        }).fail(function (error) {
            console.log(error);
            console.log('AJAX call failed :(');
        }).done(function (data1) {
            appendDiscipline(data1);
        });
    }

    function appendDiscipline(data1) {
        $('#assessment-discipline-div').empty();
        DISCIPLINE_DATA = data1;
        for (var i = 0; i < data1.length; i++) {
            $('#assessment-discipline-div').append('<div>');
            $('#assessment-discipline-div').append('<input id="add-person-group-discipline' + i + '" value="' + data1[i] + '" readonly="readonly"/>');
            $('#assessment-discipline-div').append('<select id="add-person-group-assessment' + i + '"></select>');
            for (var k = 0; k < DATA[1].length; k++) {
                $('#add-person-group-assessment' + i + '').append('<option id="assessment' + k + '" value="' + DATA[1][k] + '">' + DATA[1][k] + '</option>');
            }
            $('#assessment-discipline-div').append('</div>');
        }

    }

    $('#insertData').click(function () {
        var studInsertDataTransformObject = {};
        studInsertDataTransformObject.firstName = $('#add-person-input-first-name').val();
        studInsertDataTransformObject.secondName = $('#add-person-input-last-name').val();
        studInsertDataTransformObject.date = $("#add-person-group-date option:selected").text();
        studInsertDataTransformObject.groupNum = $('#add-person-group-number option:selected').text();
        studInsertDataTransformObject.groupName = $('#add-person-group-name option:selected').text();
        var assessmentValues = [];
        console.log(DISCIPLINE_DATA);
        for (var i = 0; i < DISCIPLINE_DATA.length; i++) {
            assessmentValues.push({
                assessment: $('#add-person-group-assessment'+i+' option:selected').text(),
                discipline: $('#add-person-group-discipline'+i+'').val()
            })
        }
        studInsertDataTransformObject.assessmentValues = assessmentValues;
        console.log(studInsertDataTransformObject);
        $.ajax({
            type: 'POST',
            url: '/admin/insertData',
            contentType: "application/json;charset=UTF-8",
            dataType: 'json',
            processData: false,
            data: JSON.stringify(studInsertDataTransformObject),
            cache: false,
            async: false
        }).fail(function (error) {
            console.log(error);
            alert("Такой пользователь уже существует");
        }).done(function (result) {
            console.log(result);
        });
    });
});