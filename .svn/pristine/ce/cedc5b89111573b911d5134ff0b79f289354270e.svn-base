/**
 * 源头企业管理管理初始化
 */
var BsCorp = {
    id: "BsCorpTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BsCorp.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'corpid', visible: true, align: 'center', formatter: function (value, row, index) {return  index + 1}},
        {title: '营业执照', field: 'corpcode', visible: true, align: 'center', valign: 'middle'},
        {title: '企业名称', field: 'corpname', visible: true, align: 'center', valign: 'middle'},
        {title: '所属区域', field: 'areacode', visible: true, align: 'center', valign: 'middle'},
        {title: '企业负责人/联系人', field: 'manager', visible: true, align: 'center', valign: 'middle'},
        {title: '身份证号码', field: 'managerid', visible: true, align: 'center', valign: 'middle'},
        {title: '联系方式', field: 'managertel', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BsCorp.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BsCorp.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加源头企业管理
 */
BsCorp.openAddBsCorp = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加源头企业',
        area: ['1100px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bsCorp/bsCorp_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看源头企业管理详情
 */
BsCorp.openBsCorpDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '源头企业详情',
            area: ['1100px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bsCorp/bsCorp_update/' + BsCorp.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除源头企业管理
 */
BsCorp.delete = function () {
    if (this.check()) {
    	var operation = function(){
    		var ajax = new $ax(Feng.ctxPath + "/bsCorp/delete", function (data) {
                Feng.success("删除成功!");
                BsCorp.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("bsCorpId",BsCorp.seItem.id);
            ajax.start();
    	}
    	Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询源头企业管理列表
 */
BsCorp.search = function () {
	var queryData = {};
	queryData['corpname'] = $("#corpname").val();
	queryData['areacode'] = $("#areacode").val();
	BsCorp.table.refresh({query: queryData});
	
	Feng.initValidator()
};

$(function () {
    var defaultColunms = BsCorp.initColumn();
    var table = new BSTable(BsCorp.id, "/bsCorp/list", defaultColunms);
    table.setPaginationType("client");
    BsCorp.table = table.init({showRefresh:false, showColumns:false});
    
    $('#'+BsCorp.id).on('all.bs.table', function (){
		$(".bs-checkbox").attr('style',"display:none");
	});
});
