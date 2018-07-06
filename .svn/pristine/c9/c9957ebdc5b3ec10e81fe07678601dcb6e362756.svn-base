/**
 * 数据服务链定义管理初始化
 */
var ChainList = {
    id: "ChainListTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ChainList.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '数据服务链别名', field: 'chainalias', visible: true, align: 'center', valign: 'middle'},
            {title: '数据服务链描述', field: 'chaindesc', visible: true, align: 'center', valign: 'middle'},
            {title: '接收服务', field: 'inchainclass', visible: true, align: 'center', valign: 'middle'},
            {title: '推送服务', field: 'outchainclass', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ChainList.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ChainList.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加数据服务链定义
 */
ChainList.openAddChainList = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加数据服务链定义',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/chainList/chainList_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看数据服务链定义详情
 */
ChainList.openChainListDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '数据服务链定义详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/chainList/chainList_update/' + ChainList.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除数据服务链定义
 */
ChainList.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/chainList/delete", function (data) {
	            Feng.success("删除成功!");
	            ChainList.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("chainListId",ChainList.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询数据服务链定义列表
 */
ChainList.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ChainList.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ChainList.initColumn();
    var table = new BSTable(ChainList.id, "/chainList/list", defaultColunms);
    table.setPaginationType("client");
    ChainList.table = table.init();
});
