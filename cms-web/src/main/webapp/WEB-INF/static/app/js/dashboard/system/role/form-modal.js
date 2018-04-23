$(function () {
    var $modal = $('.modal');
    var $form = $modal.find("form");
    var $btn = $modal.find("button[data-type=submit]");

    $form.validate({
        rules: {
            code: {
                required: true,
                isRoleCode: true,
                remote: {
                    url: ctx + "/validate/role",
                    type: 'post',
                    data: {
                        'code': function () {
                            return $('#code').val()
                        },
                        'oldCode': function () {
                            return $('#old-code').val();
                        }
                    }
                }
            },
            name: {
                required: true,
                rangelength: [1, 32]
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