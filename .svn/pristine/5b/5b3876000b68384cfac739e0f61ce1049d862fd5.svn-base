/**
 * 数据服务步骤管理管理初始化
 */
var ActionList = {
    id: "ActionListTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ActionList.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '步骤别名', field: 'actalias', visible: true, align: 'center', valign: 'middle'},
            {title: '步骤描述', field: 'actdesc', visible: true, align: 'center', valign: 'middle'},
            {title: '步骤实现类', field: 'actclass', visible: true, align: 'center', valign: 'middle'},
            {title: '入口方法', field: 'actmethod', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ActionList.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ActionList.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加数据服务步骤管理
 */
ActionList.openAddActionList = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加数据服务步骤管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/actionList/actionList_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看数据服务步骤管理详情
 */
ActionList.openActionListDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '数据服务步骤管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/actionList/actionList_update/' + ActionList.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除数据服务步骤管理
 */
ActionList.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/actionList/delete", function (data) {
	            Feng.success("删除成功!");
	            ActionList.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("actionListId",ActionList.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询数据服务步骤管理列表
 */
ActionList.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ActionList.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ActionList.initColumn();
    var table = new BSTable(ActionList.id, "/actionList/list", defaultColunms);
    table.setPaginationType("client");
    ActionList.table = table.init();
});
