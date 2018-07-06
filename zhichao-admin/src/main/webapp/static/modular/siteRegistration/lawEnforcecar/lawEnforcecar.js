/**
 * 执法车信息管理初始化
 */
var LawEnforcecar = {
    id: "LawEnforcecarTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
LawEnforcecar.initColumn = function () {
    return [
        	{field: 'selectItem', radio: true},
            {title: '序号', field: 'id', visible: true, align: 'center', valign: 'middle'
            	,formatter: function (value, row, index) {  
                return index+1;  
            }},
            {title: '执法车辆牌号', field: 'vehicleid', visible: true, align: 'center', valign: 'middle'},
            {title: '执法车编号', field: 'vehicleno', visible: true, align: 'center', valign: 'middle'},
            {title: '车型', field: 'cartypename', visible: true, align: 'center', valign: 'middle'},
            {title: '联系人', field: 'manager', visible: true, align: 'center', valign: 'middle'},
            {title: '联系方式', field: 'managertel', visible: true, align: 'center', valign: 'middle'},
            {title: '管理部门', field: 'deptname', visible: true, align: 'center', valign: 'middle'},
            {title: '所属区域', field: 'areaname', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
LawEnforcecar.check = function () {
	var selected = $('#' + this.id).bootstrapTable('getSelections');

	if(selected.length == 0){
		Feng.info("请先选中表格中的某一记录！");
		return false;
	}else{
		LawEnforcecar.seItem = selected[0];
		return true;
	}
};

/**
 * 点击添加执法车信息
 */
LawEnforcecar.openAddLawEnforcecar = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加执法车信息',
        area: ['910px', '400px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/lawEnforcecar/lawEnforcecar_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看执法车信息详情
 */
LawEnforcecar.openLawEnforcecarDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '执法车信息详情',
            area: ['910px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/lawEnforcecar/lawEnforcecar_update/' + LawEnforcecar.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除执法车信息
 */
LawEnforcecar.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/lawEnforcecar/delete", function (data) {
	            Feng.success("删除成功!");
	            LawEnforcecar.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("lawEnforcecarId",LawEnforcecar.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询执法车信息列表
 */
LawEnforcecar.search = function () {
    var queryData = {};
    queryData['deptName'] = $("#deptName").val();
    queryData['carNumber'] = $("#carNumber").val();
    LawEnforcecar.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = LawEnforcecar.initColumn();
    var table = new BSTable(LawEnforcecar.id, "/lawEnforcecar/list", defaultColunms);
    table.setPaginationType("client");
    LawEnforcecar.table = table.init({showColumns:false,showRefresh: false});
    
    //隐藏选择列
    $('#'+LawEnforcecar.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );
});
