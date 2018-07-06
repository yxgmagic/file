/**
 * reportservice管理初始化
 */
var Reportservice = {
    id: "ReportserviceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Reportservice.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'id', visible: true, align: 'center', valign: 'middle',width:100
        	,formatter: function (value, row, index) {  
            return index+1;  
        }},
            {title: '对象名称', field: 'reportName', visible: true, align: 'center', valign: 'middle'},
            {title: '接口地址', field: 'reportAddress', visible: true, align: 'center', valign: 'middle'},
            {title: '端口', field: 'reportPort', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remarks', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Reportservice.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Reportservice.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加reportservice
 */
Reportservice.openAddReportservice = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加抄告对象',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/reportservice/reportservice_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看reportservice详情
 */
Reportservice.openReportserviceDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '修改抄告对象',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/reportservice/reportservice_update/' + Reportservice.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除reportservice
 */
Reportservice.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/reportservice/delete", function (data) {
	            Feng.success("删除成功!");
	            Reportservice.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("reportserviceId",Reportservice.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询reportservice列表
 */
Reportservice.search = function () {
    var queryData = {};
    queryData['reportName'] = $("#reportName").val();
    Reportservice.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Reportservice.initColumn();
    var table = new BSTable(Reportservice.id, "/reportservice/list", defaultColunms);
    table.setPaginationType("client");
    Reportservice.table = table.init({showColumns:false,showRefresh:false});
    
    //隐藏选择列
    $('#'+Reportservice.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );
});
