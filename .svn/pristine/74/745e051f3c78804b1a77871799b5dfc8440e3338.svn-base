/**
 * 节点路径列表管理初始化
 */
var ServerList = {
    id: "ServerListTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ServerList.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '数据终端别名', field: 'sysalias', visible: true, align: 'center', valign: 'middle'},
            {title: '数据终端描述', field: 'sysdesc', visible: true, align: 'center', valign: 'middle'},
            {title: '数据服务类型', field: 'systype', visible: true, align: 'center', valign: 'middle'},
            {title: '数据来源去向', field: 'syssource', visible: true, align: 'center', valign: 'middle'},
            {title: '网络地址', field: 'host', visible: true, align: 'center', valign: 'middle'},
            {title: '端口', field: 'port', visible: true, align: 'center', valign: 'middle'},
            {title: '数据库名或服务名', field: 'dbname', visible: true, align: 'center', valign: 'middle'},
            {title: '数据驱动类名', field: 'dbdriver', visible: true, align: 'center', valign: 'middle'},
            {title: '访问用户', field: 'dbuser', visible: true, align: 'center', valign: 'middle'},
            {title: '访问密码', field: 'dbpassword', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ServerList.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ServerList.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加节点路径列表
 */
ServerList.openAddServerList = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加节点路径列表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/serverList/serverList_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看节点路径列表详情
 */
ServerList.openServerListDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '节点路径列表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/serverList/serverList_update/' + ServerList.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除节点路径列表
 */
ServerList.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/serverList/delete", function (data) {
	            Feng.success("删除成功!");
	            ServerList.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("serverListId",ServerList.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询节点路径列表列表
 */
ServerList.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ServerList.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ServerList.initColumn();
    var table = new BSTable(ServerList.id, "/serverList/list", defaultColunms);
    table.setPaginationType("client");
    ServerList.table = table.init();
});
