$(function () {
    updateState("user/info");

    var $form = $('#form');
    var $btn = $form.find("button");

    $form.validate({
        rules: {
            password: {
                required: true,
                isPassword: true
            },
            rePassword: {
                required: true,
                equalTo: "#password"
            },
            realname: {
                isRealName: true,
                required: true
            }
        },
        submitHandler: function (form, event) {
            event.preventDefault();
            $btn.button('loading');
            $(form).ajaxSubmit({
                dataType: 'json',
                success: function (response) {
                    if (response.respCo == '0000') {
                        Message.success("修改成功");
                        var user = response.user;
                        $("#navFullname").html(user.realname);
                        $form.find("input[type=password]").val("");
                    } else {
                        Message.error(response.respMsg);
                    }
                    $btn.button('reset');
                },
                error: function () {
                    Message.error("服务器内部错误，请稍后再试。");
                    $btn.button('reset');
                }
            });
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent());
        },
        errorElement: "div",
        errorClass: "error"
    });
});

