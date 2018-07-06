/**
 * 货运从业人员管理管理初始化
 */
var VehicleDriver = {
    id: "VehicleDriverTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
VehicleDriver.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'corpid', visible: true, align: 'center', formatter: function (value, row, index) {return  index + 1}},
        {title: '企业名称', field: 'corpcode', visible: true, align: 'center', valign: 'middle'},
        {title: '姓名', field: 'drivername', visible: true, align: 'center', valign: 'middle'},
        {title: '驾驶证号', field: 'driverid', visible: true, align: 'center', valign: 'middle'},
        {title: '身份证号', field: 'idcard', visible: true, align: 'center', valign: 'middle'},
        {title: '性别', field: 'gender', visible: true, align: 'center', valign: 'middle'},
        {title: '从业资格证', field: 'qualificationid', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
VehicleDriver.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        VehicleDriver.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加货运从业人员管理
 */
VehicleDriver.openAddVehicleDriver = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加货运从业人员管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/vehicleDriver/vehicleDriver_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看货运从业人员管理详情
 */
VehicleDriver.openVehicleDriverDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '货运从业人员管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/vehicleDriver/vehicleDriver_update/' + VehicleDriver.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除货运从业人员管理
 */
VehicleDriver.delete = function () {
    if (this.check()) {
    	var operation = function(){
    		var ajax = new $ax(Feng.ctxPath + "/vehicleDriver/delete", function (data) {
                Feng.success("删除成功!");
                VehicleDriver.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("vehicleDriverId",VehicleDriver.seItem.id);
            ajax.start();
    	}
    	Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询货运从业人员管理列表
 */
VehicleDriver.search = function () {
    var queryData = {};
    queryData['corpcode'] = $("#corpcode").val();
    queryData['drivername'] = $("#drivername").val();
    queryData['idcard'] = $("#idcard").val();
    queryData['sex'] = $('#sex').val();
    queryData['driverid'] = $('#driverid').val();
    queryData['qualificationid'] = $('#qualificationid').val();
//    console.log(queryData);
    VehicleDriver.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = VehicleDriver.initColumn();
    var table = new BSTable(VehicleDriver.id, "/vehicleDriver/list", defaultColunms);
    table.setPaginationType("client");
    VehicleDriver.table = table.init({showRefresh:false, showColumns:false});
    
    
    
    $('#'+VehicleDriver.id).on('all.bs.table', function (){
		$(".bs-checkbox").attr('style',"display:none");
	});
});
