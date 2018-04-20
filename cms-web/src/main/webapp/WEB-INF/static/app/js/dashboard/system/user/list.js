$(function () {
    // 更新面包屑
    updateState("system/user");

    // 表格初始化
    $('#table').bootstrapTable();

    // 日期框
    $('.date-picker').datepicker();
});