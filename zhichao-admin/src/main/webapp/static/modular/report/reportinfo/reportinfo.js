/**
 * 抄告内容信息管理初始化
 */
var Reportinfo = {
    id: "ReportinfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Reportinfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '标题', field: 'reportTitle', visible: true, align: 'center', valign: 'middle'},
            {title: '菜单编号', field: 'code', align: 'center', valign: 'middle', sortable: true, width: '12%'},
            {title: '菜单父编号', field: 'pcode', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Reportinfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Reportinfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加抄告内容信息
 */
Reportinfo.openAddReportinfo = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加抄告内容信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/reportinfo/reportinfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看抄告内容信息详情
 */
Reportinfo.openReportinfoDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '抄告内容信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/reportinfo/reportinfo_update/' + Reportinfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除抄告内容信息
 */
Reportinfo.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/reportinfo/delete", function (data) {
	            Feng.success("删除成功!");
	            Reportinfo.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("reportinfoId",Reportinfo.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询抄告内容信息列表
 */
Reportinfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Reportinfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Reportinfo.initColumn();
    //var table = new BSTable(Reportinfo.id, "/reportinfo/list", defaultColunms);
    //table.setPaginationType("client");
    
    var table =new BSTreeTable(Reportinfo.id, "/reportinfo/list", defaultColunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("code");
    table.setParentCodeField("pcode");
    table.setExpandAll(true);
    
    table.init();
    
    Reportinfo.table = table;
    
    /*//隐藏选择列
    $('#'+Reportinfo.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );*/
});
