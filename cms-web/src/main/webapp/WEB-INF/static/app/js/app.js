$(function () {
    // 异步加载界面
    var $ajaxContent = $(".page-content-area");
    $ajaxContent.ace_ajax({
        'default_url': '#index',
        'content_url': function (hash) {
            return window.location.protocol + "//" + window.location.hostname + ":" + window.location.port + window.location.pathname + "/" + hash;
        },
        'update_active': updateMenuActive,
        'update_breadcrumbs': updateBreadcrumbs,
        'update_title': updateTitle,
        'loading_text': '<span class="loading">正在加载, 请稍等...</span>'
    });

    // 监听异步加载失败事件
    $ajaxContent.on("ajaxloaderror", function (e, data) {
        window.location.href = ctx + '/404';
    });

    // modal form 提交
    $('.modal').on('click', '[data-type=submit]', function (e) {
        e.preventDefault();
        $($(this).parents('.modal').find("form")).submit();
    });

    // 查询。<@c.link type="submit" table_id="xxx" .../>
    $(document).on("click", "[data-type='submit']", function (e) {
        e.preventDefault();
        var $this = $(this);
        var $table = $("#" + $this.data("table-id"));

        var params = $this.parents("form").serializeForm();
        $table.bootstrapTable("refresh", {query: params});
        return false;
    });

    // 清除。<@c.link type="reset" .../>
    $(document).on("click", "[data-type='reset']", function (e) {
        e.preventDefault();
        var $this = $(this);
        var $form = $this.parents("form");

        $form.find("input").val("");
        $form.find("select").val("");
        return false;
    });
});
