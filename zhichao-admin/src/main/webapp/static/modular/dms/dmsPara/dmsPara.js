/**
 * 数据服务器日志上传管理管理初始化
 */
var DmsPara = {
    id: "DmsParaTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DmsPara.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '参数别名', field: 'paracode', visible: true, align: 'center', valign: 'middle'},
            {title: '参数名称', field: 'paraname', visible: true, align: 'center', valign: 'middle'},
            {title: '参数值', field: 'paravalue', visible: true, align: 'center', valign: 'middle'},
            {title: '参数描述', field: 'ramark', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
DmsPara.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DmsPara.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加数据服务器日志上传管理
 */
DmsPara.openAddDmsPara = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加数据服务器日志上传管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/dmsPara/dmsPara_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看数据服务器日志上传管理详情
 */
DmsPara.openDmsParaDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '数据服务器日志上传管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/dmsPara/dmsPara_update/' + DmsPara.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除数据服务器日志上传管理
 */
DmsPara.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/dmsPara/delete", function (data) {
	            Feng.success("删除成功!");
	            DmsPara.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("dmsParaId",DmsPara.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询数据服务器日志上传管理列表
 */
DmsPara.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    DmsPara.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = DmsPara.initColumn();
    var table = new BSTable(DmsPara.id, "/dmsPara/list", defaultColunms);
    table.setPaginationType("client");
    DmsPara.table = table.init();
});
