/**
 * 数据服务链步骤关系维护管理初始化
 */
var ChainAction = {
    id: "ChainActionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ChainAction.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '步骤执行顺序', field: 'nums', visible: true, align: 'center', valign: 'middle'},
            {title: '服务链节点路径', field: 'serverchainid', visible: true, align: 'center', valign: 'middle'},
            {title: '步骤', field: 'actid', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ChainAction.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ChainAction.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加数据服务链步骤关系维护
 */
ChainAction.openAddChainAction = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加数据服务链步骤关系维护',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/chainAction/chainAction_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看数据服务链步骤关系维护详情
 */
ChainAction.openChainActionDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '数据服务链步骤关系维护详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/chainAction/chainAction_update/' + ChainAction.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除数据服务链步骤关系维护
 */
ChainAction.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/chainAction/delete", function (data) {
	            Feng.success("删除成功!");
	            ChainAction.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("chainActionId",ChainAction.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询数据服务链步骤关系维护列表
 */
ChainAction.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ChainAction.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ChainAction.initColumn();
    var table = new BSTable(ChainAction.id, "/chainAction/list", defaultColunms);
    table.setPaginationType("client");
    ChainAction.table = table.init();
});
