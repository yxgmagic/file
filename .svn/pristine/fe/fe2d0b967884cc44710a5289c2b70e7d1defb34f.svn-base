/**
 * 源头企业车辆管理管理初始化
 */
var Vehicle = {
    id: "VehicleTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Vehicle.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'corpid', visible: true, align: 'center', formatter: function (value, row, index) {return  index + 1}},
        {title: '车牌号码', field: 'vehicleid', visible: true, align: 'center', valign: 'middle'},
        {title: '所属企业', field: 'corpname', visible: true, align: 'center', valign: 'middle'},
        {title: '所有人名称', field: 'owername', visible: true, align: 'center', valign: 'middle'},
        {title: '所有人联系方式', field: 'owertel', visible: true, align: 'center', valign: 'middle'},
        {title: '道路运输证号', field: 'bizcertid', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Vehicle.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Vehicle.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加源头企业车辆管理
 */
Vehicle.openAddVehicle = function () {
    var index = parent.layer.open({
        type: 2,
        title: '添加源头企业车辆',
        area: ['1200px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/vehicle/vehicle_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看源头企业车辆管理详情
 */
Vehicle.openVehicleDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '源头企业车辆详情',
            area: ['1200px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/vehicle/vehicle_update/' + Vehicle.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除源头企业车辆管理
 */
Vehicle.delete = function () {
    if (this.check()) {
    	var operation = function(){
    		var ajax = new $ax(Feng.ctxPath + "/vehicle/delete", function (data) {
                Feng.success("删除成功!");
                Vehicle.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("vehicleId",Vehicle.seItem.id);
            ajax.start();
    	}
    	Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询源头企业车辆管理列表
 */
Vehicle.search = function () {
    var queryData = {};
    queryData['vehicleid'] = $("#vehicleid").val();
    queryData['corpname'] = $("#corpname").val();
    queryData['owername'] = $("#owername").val();
    queryData['owertel'] = $("#owertel").val();
    queryData['bizcertid'] = $("#bizcertid").val();
    Vehicle.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Vehicle.initColumn();
    var table = new BSTable(Vehicle.id, "/vehicle/list", defaultColunms);
    table.setPaginationType("client");
    Vehicle.table = table.init({showRefresh:false, showColumns:false});

    
    //隐藏选择列
    $('#'+Vehicle.id).on('all.bs.table', function (){
    	$(".bs-checkbox").attr('style',"display:none");
    });
});
