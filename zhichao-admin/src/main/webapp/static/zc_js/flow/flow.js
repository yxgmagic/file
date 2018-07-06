 /**
 * 流程管理管理初始化
 */
var Flow = {
    id: "FlowTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Flow.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '流程ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '流程名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '流程创建json', field: 'flowJson', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人ID', field: 'createBy', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
            {title: '最后更新人ID', field: 'updataBy', visible: true, align: 'center', valign: 'middle'},
            {title: '最后更新时间', field: 'updataDate', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '状态 1 初始化 2 启动 3 废除', field: 'state', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Flow.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Flow.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加流程管理
 */
Flow.openAddFlow = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加流程管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/flow/flow_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看流程管理详情
 */
Flow.openFlowDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '流程管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/flow/flow_update/' + Flow.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除流程管理
 */
Flow.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/flow/delete", function (data) {
	            Feng.success("删除成功!");
	            Flow.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("flowId",Flow.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询流程管理列表
 */
Flow.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Flow.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Flow.initColumn();
    var table = new BSTable(Flow.id, "/flow/list", defaultColunms);
    table.setPaginationType("client");
    Flow.table = table.init();
    
    //隐藏选择列
    $('#'+Flow.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );
});
