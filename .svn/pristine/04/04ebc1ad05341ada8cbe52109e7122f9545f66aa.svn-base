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
            {title: '序号', field: 'id', visible: true, align: 'center', valign: 'middle' ,formatter: function (value, row, index) { return index+1; }},
            {title: '执法车辆牌号', field: 'vehicleid', visible: true, align: 'center', valign: 'middle'},
            {title: '执法车编号', field: 'vehicleno', visible: true, align: 'center', valign: 'middle'},
            {title: '管理部门', field: 'deptname', visible: true, align: 'center', valign: 'middle'},
            {title: '所属区域', field: 'areaname', visible: true, align: 'center', valign: 'middle'}
    ];
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

/**
 * 选择执法车
 */
function determine(me) {
    	
    	parent.$("#vehicleno").val(me.vehicleno).change();
    	parent.$("#vehicleno_name").val(me.vehicleid).change();
    	
    	var index = parent.layer.getFrameIndex(window.name);//获取子窗口索引
        parent.layer.close(index);
        
};

$(function () {
    var defaultColunms = LawEnforcecar.initColumn();
    var table = new BSTable(LawEnforcecar.id, "/lawEnforcecar/list", defaultColunms);
    table.setPaginationType("client");
    LawEnforcecar.table = table.init({showColumns:false,showRefresh: false});
    
  //选择流动执法车的点击事件
    $('#LawEnforcecarTable').on('click-row.bs.table', function (e, row, element) {  
    	determine(row);
    });
    
    //隐藏选择列
    $('#'+LawEnforcecar.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );
});
