/**
 * 数据服务终端与链关系配置管理初始化
 */
var ServerAuthChain = {
    id: "ServerAuthChainTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ServerAuthChain.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '数据服务链', field: 'chainid', visible: true, align: 'center', valign: 'middle'},
            {title: '数据服务服务端', field: 'authid', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ServerAuthChain.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ServerAuthChain.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加数据服务终端与链关系配置
 */
ServerAuthChain.openAddServerAuthChain = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加数据服务终端与链关系配置',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/serverAuthChain/serverAuthChain_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看数据服务终端与链关系配置详情
 */
ServerAuthChain.openServerAuthChainDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '数据服务终端与链关系配置详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/serverAuthChain/serverAuthChain_update/' + ServerAuthChain.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除数据服务终端与链关系配置
 */
ServerAuthChain.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/serverAuthChain/delete", function (data) {
	            Feng.success("删除成功!");
	            ServerAuthChain.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("serverAuthChainId",ServerAuthChain.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询数据服务终端与链关系配置列表
 */
ServerAuthChain.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ServerAuthChain.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ServerAuthChain.initColumn();
    var table = new BSTable(ServerAuthChain.id, "/serverAuthChain/list", defaultColunms);
    table.setPaginationType("client");
    ServerAuthChain.table = table.init();
});
