$(function () {
    var $modal = $('.modal');
    var $form = $modal.find("form");
    var $btn = $modal.find("button[data-type=submit]");

    $form.validate({
        rules: {
            username: {
                required: true,
                isUsername: true,
                remote: {
                    url: ctx + "/validate/user",
                    type: 'post',
                    data: {
                        'username': function () {
                            return $('#username').val()
                        },
                        'oldUsername': function () {
                            return $('#old-username').val();
                        }
                    }
                }
            },
            realname: {
                required: true,
                isRealName: true
            },
            password: {
                required: true,
                isPassword: true
            },
            rePassword: {
                required: true,
                equalTo: "#password"
            }
        },
        submitHandler: function (form, event) {
            event.preventDefault();
            $btn.button('loading');
            formSubmit(form, $btn, function () {
                $modal.modal('hide');
                var params = $("#form").serializeForm();
                $('#table').bootstrapTable("refresh", {query: params});
            });
        }
    });
});