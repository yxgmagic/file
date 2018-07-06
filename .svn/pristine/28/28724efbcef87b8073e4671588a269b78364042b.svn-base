/**
 * 超载货物处理管理初始化
 */
var CargoHandling = {
    id: "CargoHandlingTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CargoHandling.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'indexid', visible: true, align: 'center', valign: 'middle'
            ,formatter: function (value, row, index) {
            return index+1;
        }},
        {title: '暂扣单号', field: 'withholdno', visible: true, align: 'center', valign: 'middle'},
        {title: '检测站点', field: 'fixedsitename', visible: true, align: 'center', valign: 'middle'},
        {title: '车牌号', field: 'vehicleid', visible: true, align: 'center', valign: 'middle'},
        {title: '卸货重量(kg)', field: 'unloadweight', visible: true, align: 'center', valign: 'middle'},
        {title: '卸货日期', field: 'unloadtime', visible: true, align: 'center', valign: 'middle'},
        {title: '卸货场', field: 'ulloadname', visible: true, align: 'center', valign: 'middle'}
    ];
};


/**
 * 查询超载货物处理列表
 */
CargoHandling.search = function () {
    var queryData = {};
    // queryData['withholdno'] = $("#withholdno").val();
    queryData['stationid'] = $("#stationid").val();
    // queryData['vehicleid'] = $("#vehicleid").val();
    queryData['unloadtime'] = $("#unloadtime").val();
    CargoHandling.table.refresh({query: queryData});
};

/**
 * 选择卸货单
 */
function determine(withhold) {
    parent.$("#withholdno").val(withhold.withholdno).change();

    var index = parent.layer.getFrameIndex(window.name);//获取子窗口索引
    parent.layer.close(index);

};

$(function () {
    var defaultColunms = CargoHandling.initColumn();
    var vehicleid = encodeURIComponent(parent.$("#vehicleid").val());
    var table = new BSTable(CargoHandling.id, "/cargoHandling/list?vehicleid="+vehicleid, defaultColunms);
    table.setPaginationType("client");
    CargoHandling.table = table.init({showColumns:false,showRefresh: false});

    //选择卸货单的点击事件
    $('#CargoHandlingTable').on('click-row.bs.table', function (e, row, element) {
        determine(row);
    });


    //隐藏选择列
    $('#'+CargoHandling.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );
    
    
});


//站点input被点击时的事件
/**
 * 被点击时展示站点的树形菜单,
 * 并给body绑定单击事件,
 * 用来判断用户是否在操作树形菜单
 */
$("#stationidInput").click(function() {
	Ning.Tree.setStree("#idStationStree", "fixedsite");
	Ning.Tree.bindOnClick(
     		function(node) {

     			if(node.type == "fixedsite") {
     				$("#stationidInput").val(node.name);
         			$("#stationid").val(node.sitecode);
     			} else {
     				$("#stationidInput").val("");
         			$("#stationid").val("");
     			}
     			
     		}
     	);
	$("#idStationStree").slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
});

//body点击事件
/**
 * 判断点击的元素的父元素中是否含有#idStationStree,
 * 如果没有则代表用户点击到了树形菜单以外的区域,
 * 需要将树形菜单隐藏,且取消页面点击事件的绑定
 */
function onBodyDown(event) {
    if(!$(event.target).parents("#idStationStree").length == 1) {
    	$("#idStationStree").fadeOut("fast");
    	$("body").unbind("mousedown", onBodyDown);
    }
}
