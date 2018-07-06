/**
 * 数据服务器日志上传管理管理初始化
 */
var ServerLog = {
    id: "ServerLogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ServerLog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '数据服务', field: 'authid', visible: true, align: 'center', valign: 'middle'},
            {title: '日志触发类', field: 'classname', visible: true, align: 'center', valign: 'middle'},
            {title: '触发方法', field: 'method', visible: true, align: 'center', valign: 'middle'},
            {title: '日志时间', field: 'logtime', visible: true, align: 'center', valign: 'middle'},
            {title: '日志内容', field: 'logtext', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ServerLog.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ServerLog.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加数据服务器日志上传管理
 */
ServerLog.openAddServerLog = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加数据服务器日志上传管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/serverLog/serverLog_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看数据服务器日志上传管理详情
 */
ServerLog.openServerLogDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '数据服务器日志上传管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/serverLog/serverLog_update/' + ServerLog.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除数据服务器日志上传管理
 */
ServerLog.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/serverLog/delete", function (data) {
	            Feng.success("删除成功!");
	            ServerLog.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("serverLogId",ServerLog.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询数据服务器日志上传管理列表
 */
ServerLog.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ServerLog.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ServerLog.initColumn();
    var table = new BSTable(ServerLog.id, "/serverLog/list", defaultColunms);
    table.setPaginationType("client");
    ServerLog.table = table.init();
});
