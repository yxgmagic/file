/**
 * 数据服务参数列表管理初始化
 */
var ParaList = {
    id: "ParaListTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ParaList.initColumn = function () {
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
ParaList.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ParaList.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加数据服务参数列表
 */
ParaList.openAddParaList = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加数据服务参数列表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/paraList/paraList_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看数据服务参数列表详情
 */
ParaList.openParaListDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '数据服务参数列表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/paraList/paraList_update/' + ParaList.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除数据服务参数列表
 */
ParaList.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/paraList/delete", function (data) {
	            Feng.success("删除成功!");
	            ParaList.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("paraListId",ParaList.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询数据服务参数列表列表
 */
ParaList.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ParaList.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ParaList.initColumn();
    var table = new BSTable(ParaList.id, "/paraList/list", defaultColunms);
    table.setPaginationType("client");
    ParaList.table = table.init();
});
