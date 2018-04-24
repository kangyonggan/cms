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

            var zTree = $.fn.zTree.getZTreeObj("tree");
            var nodes = zTree.getCheckedNodes(true);
            var arr = [];
            for (var i = 0; i < nodes.length; i++) {
                arr.push(nodes[i].code);
            }
            $("#menus").val(arr);

            $btn.button('loading');
            formSubmit($(form), $btn, function () {
                $modal.modal('hide');
            });
        }
    });

    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };

    $.fn.zTree.init($("#tree"), setting, zNodes);
});