$(document).ready(function () {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    $('a#reg').click(function (event) {
        event.preventDefault();
        $('#overlay_reg').fadeIn(400,
            function () {
                $('#modal_form_reg')
                    .css('display', 'block')
                    .animate({opacity: 1, top: '50%'}, 300);
            });
    });

    $('#modal_close_reg, #overlay_reg').click(function () {
        $('#modal_form_reg')
            .animate({opacity: 0, top: '45%'}, 300,
                function () {
                    $(this).css('display', 'none');
                    $('#overlay_reg').fadeOut(400);
                }
            );
    });

    $('a#loginhref').click(function (event) {
        event.preventDefault();
        $('#overlay_login').fadeIn(400,
            function () {
                $('#modal_form_login')
                    .css('display', 'block')
                    .animate({opacity: 1, top: '50%'}, 300);
            });
    });

    $('#modal_close_login, #overlay_login').click(function () {
        $('#modal_form_login')
            .animate({opacity: 0, top: '45%'}, 300,
                function () {
                    $(this).css('display', 'none');
                    $('#overlay_login').fadeOut(400);
                }
            );
    });

    var blockReg = true;

    $('#vpassword_reg').focusout(
        function () {
            if ($('#vpassword_reg').val() != $('#vpasswordRepeat_reg').val() && $('#vpasswordRepeat_reg').val() != "") {
                blockReg = true;
                $('#vinvalidpass_reg').css('display', 'block');
            } else {
                blockReg = false;
                $('#vinvalidpass_reg').css('display', 'none');
            }
        }
    );

    $("#vlogin_reg").focusout(
        function f() {
            var login = $('#vlogin_reg').val();

            var url = '/loginCheck/' + login;
            $.ajax
            (
                {
                    type: 'GET',
                    url: url,
                    async: true,
                    success: function (result) {
                        if (result == false) {
                            blockReg = true;
                            $('#vinvalidpass_reg').css('display', 'block');
                        } else {
                            blockReg = false;
                            $('#vinvalidpass_reg').css('display', 'none');
                        }
                    },
                    error: function (request, status, error) {
                        var statusCode = request.status;
                        console.log(statusCode);
                    }
                }
            );
        }
    );

    $("#submitbtn_reg").click(
        function regFunction() {
            if (blockReg == true) {
                event.preventDefault();
            } else {
                $.ajax
                (
                    {
                        type: 'PUT',
                        url: '/registration',
                        contentType: "application/json",
                        dataType: 'json',
                        data: JSON.stringify({login: $('#vlogin_reg').val(),
                                                    password: $('#vpassword_reg').val(),
                                                    roleId: 0}),
                        cache: false,
                        async: false,
                        success: function (result) {
                            if (result == true) {
                                alert("Success");
                            } else {
                            }
                        },
                        error: function (request, status, error) {
                            blockReg = true;
                            var statusCode = request.status; // вот он код ответа
                            alert(message());
                        }
                    }
                )
            }
        });
});