$(function () {
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
                rangelength: [1, 32],
                required: true
            }
        },
        submitHandler: function (form, event) {
            event.preventDefault();
            $btn.button('loading');
            formSubmit($(form), $btn, function (response) {
                var user = response.user;
                $("#navFullname").html(user.realname);
                $form.find("input[type=password]").val("");
            });
        }
    });
});

