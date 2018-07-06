/**
 * 抄告报表关联管理初始化
 */
var Reportcontent = {
    id: "ReportcontentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Reportcontent.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '关联抄告ID', field: 'reportId', visible: true, align: 'center', valign: 'middle'},
            {title: '报表编号ID', field: 'reportInfoId', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Reportcontent.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Reportcontent.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加抄告报表关联
 */
Reportcontent.openAddReportcontent = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加抄告报表关联',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/reportcontent/reportcontent_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看抄告报表关联详情
 */
Reportcontent.openReportcontentDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '抄告报表关联详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/reportcontent/reportcontent_update/' + Reportcontent.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除抄告报表关联
 */
Reportcontent.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/reportcontent/delete", function (data) {
	            Feng.success("删除成功!");
	            Reportcontent.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("reportcontentId",Reportcontent.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询抄告报表关联列表
 */
Reportcontent.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Reportcontent.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Reportcontent.initColumn();
    var table = new BSTable(Reportcontent.id, "/reportcontent/list", defaultColunms);
    table.setPaginationType("client");
    Reportcontent.table = table.init();
    
    //隐藏选择列
    $('#'+Reportcontent.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );
});
