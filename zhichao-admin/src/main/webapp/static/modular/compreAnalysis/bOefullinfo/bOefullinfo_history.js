/**
 * queryVehicleCase管理初始化
 */
var BOefullinfoHistory = {
    id: "BOefullinfoHistoryTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BOefullinfoHistory.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'corpid', visible: true, align: 'center', formatter: function (value, row, index) {return  index + 1}},
            {title: '检测时间', field: 'ctime', visible: true, align: 'center', valign: 'middle'},
            {title: '检测站点', field: 'stationid', visible: true, align: 'center', valign: 'middle'},
            {title: '车辆总重（kg）', field: 'weightlimited', visible: true, align: 'center', valign: 'middle'},
            {title: '轴数', field: 'axlesum', visible: true, align: 'center', valign: 'middle'},
            {title: '超限率(%)', field: 'rate', visible: true, align: 'center', valign: 'middle'},
            {title: '超限数（kg）', field: 'overlimited', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'prostatus', visible: true, align: 'center', valign: 'middle'},
            {field: 'operate', title: '操作', align: 'center', valign: 'middle', events: openDetail,  formatter: detailFormatter}
    ];
};
window.openDetail = {
        'click #btn_detail': function(e, value, row, index) { BOefullinfoHistory.openDetail(row.id);}
}
function detailFormatter(value, row, index) {
	return ["<button id='btn_detail' class='btn btn-primary btn-xs'>查看明细</button>"].join('');
}

/**
 * 打开关联历史页面
 */
BOefullinfoHistory.openDetail = function (id) {
    var index = top.layer.open({
        type: 2,
        title: '关联历史',
        area: ['1000px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bOefullinfo/historyDetail/' + id
    });
    this.layerIndex = index;
};

$(function () {
	
    var defaultColunms = BOefullinfoHistory.initColumn();
    var table = new BSTable(BOefullinfoHistory.id, "/bOefullinfo/historyList/" + $("#vehicleid").val(), defaultColunms);
    table.setPaginationType("client");
    BOefullinfoHistory.table = table.init({showRefresh:false, showColumns:false, se: true,fn:$("#vehicleid").val()+'过磅记录'});
    
    //隐藏选择列
    $('#'+BOefullinfoHistory.id).on('all.bs.table', function (){
    		$(".bs-checkbox").hide();
    		$(".export").hide();
	});

});

 
