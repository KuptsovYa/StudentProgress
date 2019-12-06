$(document).ready(function () {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });


    $('#getDataByName').click(function () {
        var studDataTransferObject ={};
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
            console.log('AJAX call was successfully executed ;)');
            console.log('data = ', data);
            console.log(data);
        })
    });

});