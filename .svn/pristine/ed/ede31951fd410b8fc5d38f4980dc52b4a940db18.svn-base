/**
 * 系统参数表管理初始化
 */
var Para = {
    id: "ParaTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Para.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '参数别名', field: 'paracode', visible: true, align: 'center', valign: 'middle'},
            {title: '参数名称', field: 'paraname', visible: true, align: 'center', valign: 'middle'},
            {title: '参数值', field: 'paravalue', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'ramark', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Para.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Para.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加系统参数表
 */
Para.openAddPara = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加系统参数表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/para/para_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看系统参数表详情
 */
Para.openParaDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '系统参数表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/para/para_update/' + Para.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除系统参数表
 */
Para.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/para/delete", function (data) {
	            Feng.success("删除成功!");
	            Para.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("paraId",Para.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询系统参数表列表
 */
Para.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Para.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Para.initColumn();
    var table = new BSTable(Para.id, "/para/list", defaultColunms);
    table.setPaginationType("client");
    Para.table = table.init();
});
