/**
 * 卸货场管理管理初始化
 */
var Unloading = {
    id: "UnloadingTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Unloading.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'corpid', visible: true, align: 'center', formatter: function (value, row, index) {return  index + 1}},
//            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '编码', field: 'ulcode', visible: true, align: 'center', valign: 'middle'},
            {title: '名称', field: 'ulname', visible: true, align: 'center', valign: 'middle'},
            {title: '位置', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '负责人', field: 'manager', visible: true, align: 'center', valign: 'middle'},
            {title: '联系电话', field: 'managertel', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Unloading.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Unloading.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加卸货场管理
 */
Unloading.openAddUnloading = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加卸货场管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/unloading/unloading_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看卸货场管理详情
 */
Unloading.openUnloadingDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '卸货场管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/unloading/unloading_update/' + Unloading.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除卸货场管理
 */
Unloading.delete = function () {
    if (this.check()) {
    	var operation = function(){
    		var ajax = new $ax(Feng.ctxPath + "/unloading/delete", function (data) {
                Feng.success("删除成功!");
                Unloading.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("unloadingId",Unloading.seItem.id);
            ajax.start();
    	}
    	Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询卸货场管理列表
 */
Unloading.search = function () {
    var queryData = {};
    queryData['ulname'] = $("#ulname").val() || "";
    queryData['address'] = $("#address").val() || "";
    queryData['manager'] = $("#manager").val() || "";
    queryData['managertel'] = $("#managertel").val() || "";
    queryData['sitecode'] = $("#sitecode").val() || "";
    Unloading.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Unloading.initColumn();
    var table = new BSTable(Unloading.id, "/unloading/list", defaultColunms);
    table.setPaginationType("client");
    Unloading.table = table.init({showRefresh:false, showColumns:false});
    
    $('#'+Unloading.id).on('all.bs.table', function (){
		$(".bs-checkbox").attr('style',"display:none");
	});
    
});
