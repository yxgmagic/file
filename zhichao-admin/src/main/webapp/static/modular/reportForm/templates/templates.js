/**
 * 自定义报表模块管理管理初始化
 */
var Templates = {
    id: "TemplatesTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Templates.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '报表代码', field: 'rfcode', visible: true, align: 'center', valign: 'middle'},
            {title: '报表名称', field: 'rfname', visible: true, align: 'center', valign: 'middle'},
            {title: '报表说明', field: 'rfdesc', visible: true, align: 'center', valign: 'middle'},
            {title: '报表模板', field: 'rfsrc', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Templates.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Templates.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加自定义报表模块管理
 */
Templates.openAddTemplates = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加自定义报表模块管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/templates/templates_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看自定义报表模块管理详情
 */
Templates.openTemplatesDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '自定义报表模块管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/templates/templates_update/' + Templates.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除自定义报表模块管理
 */
Templates.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/templates/delete", function (data) {
	            Feng.success("删除成功!");
	            Templates.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("templatesId",Templates.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询自定义报表模块管理列表
 */
Templates.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Templates.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Templates.initColumn();
    var table = new BSTable(Templates.id, "/templates/list", defaultColunms);
    table.setPaginationType("client");
    Templates.table = table.init();
});
