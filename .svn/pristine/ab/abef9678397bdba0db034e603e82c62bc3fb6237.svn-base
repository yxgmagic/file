/**
 * queryVehicleCase管理初始化
 */
var BOefullinfo = {
    id: "BOefullinfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BOefullinfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'corpid', visible: true, align: 'center', formatter: function (value, row, index) {return  index + 1}},
        {title: '车牌号', field: 'vehicleid', visible: true, align: 'center', valign: 'middle'},
        {title: '车主', field: 'drivername', visible: true, align: 'center', valign: 'middle'},
        {title: '登记时间', field: 'caseTimeString', visible: true, align: 'center', valign: 'middle'},
        {title: '登记人', field: 'enforcername1', visible: true, align: 'center', valign: 'middle'},
        {title: '核载质量（kg）', field: 'ratedloading', visible: true, align: 'center', valign: 'middle'},
        {title: '违章未处理次', field: 'notprocessed', visible: true, align: 'center', valign: 'middle'},
        {title: '违章次数', field: 'processed', visible: true, align: 'center', valign: 'middle'},
        {field: 'operate', title: '操作', align: 'center', valign: 'middle', events: openDetail,  formatter: detailFormatter}
    ];
};


window.openDetail = {
        'click #btn_detail': function(e, value, row, index) {      
        	BOefullinfo.openVehicleDetail(row.vehicleid);
        },
        'click #btn_history': function(e, value, row, index) {      
        	BOefullinfo.openHistoryDetail(row.vehicleid);
        }
}
function detailFormatter(value, row, index) {
	var result = "";
	if(historyPageFlag){
		result = result + "<button id='btn_detail' class='btn btn-primary btn-xs'>查看车辆信息</button>";
	}
	if(vehicleDetailFlag){
		result = result + "<button id='btn_history' class='btn btn-primary btn-xs'>关联历史</button>";
	}
	return [
	        result,
		].join('');
}

/**
 * 打开关联历史页面
 */
BOefullinfo.openHistoryDetail = function (vehicleid) {
    var index = top.layer.open({
        type: 2,
        title: '超限详情',
        area: ['900px', '400px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bOefullinfo/historyPage/' + vehicleid
    });
    this.layerIndex = index;
};

/**
 * 打开车辆信息页面
 */
BOefullinfo.openVehicleDetail = function (vehicleid) {
    var index = top.layer.open({
        type: 2,
        title: '车辆信息',
        area: ['900px', '520px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bOefullinfo/vehicleDetail/' + vehicleid
    });
    this.layerIndex = index;
};


/**
 * 查询queryVehicleCase列表
 */
BOefullinfo.search = function () {
    var queryData = {};
    queryData['vehicleid'] = $("#vehicleid").val();
    queryData['casetime'] = $("#casetime").val();
    queryData['overlimited'] = $("#overlimited").val();
    queryData['processed'] = $("#processed").val();
    queryData['notprocessed'] = $("#notprocessed").val();
    queryData['areacode'] = $("#areacode").val();
    BOefullinfo.table.refresh({query: queryData});
};

//自定义权限控制
var historyPageFlag = false;
var vehicleDetailFlag = false;
$(function () {
	var urlList = new Array();
	urlList.push("/bOefullinfo/historyPage");
	urlList.push("/bOefullinfo/vehicleDetail");
	var data = permissionsCheck(urlList);
	for(var per in data){
		if(per=="/bOefullinfo/historyPage" && data[per]){
			historyPageFlag=true;
		}
		if(per=="/bOefullinfo/vehicleDetail" && data[per]){
			vehicleDetailFlag = true;
		}
	}
	
	Feng.yd('casetime','datetime');
	
    var defaultColunms = BOefullinfo.initColumn();
    var table = new BSTable(BOefullinfo.id, "/bOefullinfo/list", defaultColunms);
    table.setPaginationType("client");
    BOefullinfo.table = table.init({showRefresh:false, showColumns:false});
    
  //隐藏选择列
    $('#'+BOefullinfo.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );
});
