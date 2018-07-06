/**
 * 数据服务链与节点路径对应管理初始化
 */
var ServerChain = {
    id: "ServerChainTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ServerChain.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '数据服务链', field: 'chainid', visible: true, align: 'center', valign: 'middle'},
            {title: '关联服务节点', field: 'serverid', visible: true, align: 'center', valign: 'middle'},
            {title: '数据流向类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '路径顺序', field: 'nums', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ServerChain.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ServerChain.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加数据服务链与节点路径对应
 */
ServerChain.openAddServerChain = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加数据服务链与节点路径对应',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/serverChain/serverChain_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看数据服务链与节点路径对应详情
 */
ServerChain.openServerChainDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '数据服务链与节点路径对应详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/serverChain/serverChain_update/' + ServerChain.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除数据服务链与节点路径对应
 */
ServerChain.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/serverChain/delete", function (data) {
	            Feng.success("删除成功!");
	            ServerChain.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("serverChainId",ServerChain.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询数据服务链与节点路径对应列表
 */
ServerChain.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ServerChain.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ServerChain.initColumn();
    var table = new BSTable(ServerChain.id, "/serverChain/list", defaultColunms);
    table.setPaginationType("client");
    ServerChain.table = table.init();
});
