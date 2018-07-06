/**
 * 非现场执法检测站管理初始化
 */
var BsOsesite = {
    id: "BsOsesiteTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BsOsesite.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {field : '序号', title : '序号', formatter: function (value, row, index) {return	index + 1}},
        {title: '管理部门', field: 'simplename', visible: true, align: 'center', valign: 'middle'},
        {title: '检测站名称', field: 'sitename', visible: true, align: 'center', valign: 'middle'},
        {title: '负责人', field: 'manager', visible: true, align: 'center', valign: 'middle'},
        {title: '联系方式', field: 'managertel', visible: true, align: 'center', valign: 'middle'},
        {title: '桩号', field: 'stake_number', visible: true, align: 'center', valign: 'middle'},
        {title: '所属道路', field: 'roadname', visible: true, align: 'center', valign: 'middle'},
        {title: '建站时间', field: 'buildTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BsOsesite.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BsOsesite.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加非现场执法检测站
 */
BsOsesite.openAddBsOsesite = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加非现场执法检测站',
        area: ['1200px', '700px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bsOsesite/bsOsesite_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看非现场执法检测站详情
 */
BsOsesite.openBsOsesiteDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '非现场执法检测站详情',
            area: ['1200px', '700px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bsOsesite/bsOsesite_update/' + BsOsesite.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除非现场执法检测站
 */
BsOsesite.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/bsOsesite/delete", function (data) {
	            Feng.success("删除成功!");
	            BsOsesite.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("bsOsesiteId",BsOsesite.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询非现场执法检测站列表
 */
BsOsesite.search = function () {
    var queryData = {};
    queryData['roadcode'] = $("#roadcode").val();
    queryData['areacode'] = $("#areacode").val();
    queryData['sitename'] = $("#sitename").val();
    BsOsesite.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BsOsesite.initColumn();
    var table = new BSTable(BsOsesite.id, "/bsOsesite/list", defaultColunms);
    table.setPaginationType("client");
    BsOsesite.table = table.init();
    
    //隐藏选择列
    $('#'+BsOsesite.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );
});
