/**
 * 管理初始化
 */
var OefullLawdoc = {
    id: "OefullLawdocTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OefullLawdoc.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: 'oefullid', field: 'oefullid', visible: true, align: 'center', valign: 'middle'},
            {title: 'lawdocid', field: 'lawdocid', visible: true, align: 'center', valign: 'middle'},
            {title: '业务数据', field: 'lawdocjson', visible: true, align: 'center', valign: 'middle'},
            {title: '补充手打输入数据', field: 'inputjson', visible: true, align: 'center', valign: 'middle'},
            {title: '处理状态', field: 'procstatusname', visible: true, align: 'center', valign: 'middle'},
            {title: '文书类型', field: 'ldtypename', visible: true, align: 'center', valign: 'middle'},
            {title: '文书路径', field: 'fileurl', visible: true, align: 'center', valign: 'middle'},
            {title: '文档中以下字段未取到值', field: 'unfilled', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
OefullLawdoc.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        OefullLawdoc.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
OefullLawdoc.openAddOefullLawdoc = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/oefullLawdoc/oefullLawdoc_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
OefullLawdoc.openOefullLawdocDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/oefullLawdoc/oefullLawdoc_update/' + OefullLawdoc.seItem.id
        });
        this.layerIndex = index;
    }
};

OefullLawdoc.addnewOefullLawdoc = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/oefullLawdoc/addnewOefullLawdoc/"+ OefullLawdoc.seItem.id, function(data){
          
        	 OefullLawdoc.table.refresh();
             window.open(Feng.ctxPath + data); },function(data){
                 Feng.error("失败!" + data.responseJSON.message + "!");
             });
         
             ajax.start();
        this.layerIndex = index;
    }
};
OefullLawdoc.getOefullLawdoc = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/oefullLawdoc/getOefullLawdoc/"+ OefullLawdoc.seItem.id, function(data){
          
             
             window.open(Feng.ctxPath + data); },function(data){
                 Feng.error("失败!" + data.responseJSON.message + "!");
             });
         
             ajax.start();
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
OefullLawdoc.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/oefullLawdoc/delete", function (data) {
	            Feng.success("删除成功!");
	            OefullLawdoc.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("oefullLawdocId",OefullLawdoc.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询列表
 */
OefullLawdoc.search = function () {
    var queryData = {};
    queryData['ldtype'] = $("#ldtype").val();
    queryData['procstatus'] = $("#procstatus").val();
    
    OefullLawdoc.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = OefullLawdoc.initColumn();
    var table = new BSTable(OefullLawdoc.id, "/oefullLawdoc/list", defaultColunms);
    table.setPaginationType("client");
    OefullLawdoc.table = table.init();
});
