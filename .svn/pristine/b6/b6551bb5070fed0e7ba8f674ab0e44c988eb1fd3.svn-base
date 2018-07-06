/**
 * 抄告信息反馈管理初始化
 */
var Reportsend = {
    id: "ReportsendTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Reportsend.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '关联抄告ID', field: 'reportId', visible: true, align: 'center', valign: 'middle'},
            {title: '部门编号', field: 'departmentId', visible: true, align: 'center', valign: 'middle'},
            {title: '反馈内容', field: 'feedback', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remarks', visible: true, align: 'center', valign: 'middle'},
            {title: '时间', field: 'addtime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Reportsend.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Reportsend.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加抄告信息反馈
 */
Reportsend.openAddReportsend = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加抄告信息反馈',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/reportsend/reportsend_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看抄告信息反馈详情
 */
Reportsend.openReportsendDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '抄告信息反馈详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/reportsend/reportsend_update/' + Reportsend.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除抄告信息反馈
 */
Reportsend.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/reportsend/delete", function (data) {
	            Feng.success("删除成功!");
	            Reportsend.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("reportsendId",Reportsend.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询抄告信息反馈列表
 */
Reportsend.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Reportsend.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Reportsend.initColumn();
    var table = new BSTable(Reportsend.id, "/reportsend/list", defaultColunms);
    table.setPaginationType("client");
    Reportsend.table = table.init();
    
    //隐藏选择列
    $('#'+Reportsend.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );
});
