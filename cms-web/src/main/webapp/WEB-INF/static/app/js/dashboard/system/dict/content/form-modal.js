$(function () {
    var $modal = $('.modal');
    var $form = $modal.find("form");
    var $btn = $modal.find("button[data-type=submit]");

    $form.validate({
        rules: {
            type: {
                required: true
            },
            code: {
                required: true,
                rangelength: [1, 64],
                remote: {
                    url: ctx + "/validate/dict",
                    type: 'post',
                    data: {
                        'type': function () {
                            return $('#type').val()
                        },
                        'oldType': function () {
                            return $('#old-type').val();
                        },
                        'code': function () {
                            return $('#code').val()
                        },
                        'oldCode': function () {
                            return $('#old-code').val();
                        }
                    }
                }
            },
            value: {
                required: true,
                rangelength: [1, 256]
            }
        },
        submitHandler: function (form, event) {
            event.preventDefault();
            $btn.button('loading');
            formSubmit($(form), $btn, function () {
                $modal.modal('hide');
                var params = $("#form").serializeForm();
                $('#table').bootstrapTable("refresh", {query: params});
            });
        }
    });
});