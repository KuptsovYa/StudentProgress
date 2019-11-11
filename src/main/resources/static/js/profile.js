$(document).ready(function () {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    $('select[name=selector]').change(function() { alert($(this).val()); });
    alert( $("#task-priority option:selected").val());

    $('#addTask').click(function () {
        alert( $("#task-priority option:selected").val());
        $.ajax({
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            url: '/profile/addTask',
            dataType: 'json',
            data: JSON.stringify({
                taskName: $('#task-name').val(),
                taskPriority: $("#task-priority option:selected").val(),
                taskContent: $('#task-content').val(),
                taskPoints: $('#task-points').val()
            })
        }).fail(function (error) {
            console.log(error);
        }).done(function (data) {
            console.log(data);
        })
    });

});