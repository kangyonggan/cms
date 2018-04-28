<div class="ace-settings-container" id="ace-settings-container">
    <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
        <i class="ace-icon fa fa-cog bigger-130"></i>
    </div>

    <div class="ace-settings-box clearfix" id="ace-settings-box">
        <div class="pull-left width-50">
            <div class="ace-settings-item">
                <div class="pull-left">
                    <select id="skin-colorpicker" class="hide">
                        <option data-skin="no-skin" value="#438EB9">#438EB9</option>
                        <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                        <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                        <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                    </select>
                </div>
                <span>&nbsp; 选择皮肤</span>
            </div>
            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar"/>
                <label class="lbl" for="ace-settings-navbar">固定导航条</label>
            </div>

            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar"/>
                <label class="lbl" for="ace-settings-sidebar">固定侧栏</label>
            </div>

            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs"/>
                <label class="lbl" for="ace-settings-breadcrumbs">固定面包屑</label>
            </div>

            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl"/>
                <label class="lbl" for="ace-settings-rtl">左右颠倒</label>
            </div>

            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container"/>
                <label class="lbl" for="ace-settings-add-container">
                    内容器
                </label>
            </div>
        </div>

        <div class="pull-left width-50">
            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover"/>
                <label class="lbl" for="ace-settings-hover">悬浮子菜单</label>
            </div>

            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact"/>
                <label class="lbl" for="ace-settings-compact">简洁侧栏</label>
            </div>

            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight"/>
                <label class="lbl" for="ace-settings-highlight">侧栏高亮</label>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        var skins = {"#438EB9": "no-skin", "#222A2D": "skin-1", "#C6487E": "skin-2", "#D0D0D0": "skin-3"};

        // 初始化皮肤
        var skin = $("body").attr("class");
        var color = findColor(skin);
        $(".ace-settings-item .btn-colorpicker").css("background", color);
        $(".ace-settings-item .dropdown-colorpicker li a").removeClass("selected");
        var as =  $(".ace-settings-item .dropdown-colorpicker li a");
        for (var i = 0; i < as.length; i++) {
            if ($(as[i]).data("color") == color) {
                $(as[i]).addClass("selected");
                break;
            }
        }

        // 切换皮肤
        $(".dropdown-colorpicker .colorpick-btn").click(function () {
            var color = $(this).data("color");
            var skin = skins[color];
            $.get(ctx + "/dashboard/preference/update?type=ace&name=skin&value=" + skin, function (data, status) {
                if (status == "success") {
                    data = eval('(' + data + ')');
                    if ("0000" == data.respCo) {
                        Message.success(data.respMsg);
                    } else {
                        Message.error(data.respMsg);
                    }
                } else {
                    Message.error("服务器内部错误，请稍后再试。");
                }
            });
        });

        /**
         * 查找皮肤颜色
         *
         * @param skin
         * @returns {*}
         */
        function findColor(skin) {
            for (var color in skins) {
                if (skins[color] == skin) {
                    return color;
                }
            }

            return "#438EB9";
        }
    })
</script>