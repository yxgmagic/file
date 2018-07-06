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
            {title: '卸货场', field: 'ulloadname', visible: true, align: 'center', valign: 'middle'},

            {
            	title: '操作',
            	field: 'operation',
            	visible: true, align: 'center', 
            	valign: 'middle',
            	events: openDetail,
            	formatter: detailsFormatter
            	
            }
    ];
};

/**
 * 添加按钮的点击事件
 */
window.openDetail = {
		'click #btn_print': function(e, value, row, index) {
			window.open(Feng.ctxPath + '/cargoHandling/preview/' + row.id);
		},
		'click #btn_detail': function(e, value, row, index) {
			CargoHandling.openCargoHandlingDetail(row.id);
		},
		'click #btn_delete': function(e, value, row, index) {
			CargoHandling.delete(row.id);
		},
		
}
/**
 * 添加按钮到表格
 */
function detailsFormatter() {
    var result = "";

    if(CargoHandling.hasPrint == true) {
        result = result + "<button id='btn_print' class='btn btn-primary btn-xs'>打印</button>";
    }
    if(CargoHandling.hasEdit == true) {
        result = result + "<button id='btn_detail' class='btn btn-primary btn-xs'>编辑</button>";
    }
    if(CargoHandling.hasDelete == true) {
        result = result + "<button id='btn_delete' class='btn btn-primary btn-xs'>删除</button>";
    }

    if(result.length < 1) {
        result = "--"
    }
	
	return result;
}

//初始化权限
CargoHandling.hasPrint = false;
CargoHandling.hasEdit = false;
CargoHandling.hasDelete = false;
CargoHandling.initPermission =  function() {
    var urlList = new Array();
    urlList.push("/cargoHandling/print");
    urlList.push("/cargoHandling/edit");
    urlList.push("/cargoHandling/delete");
    var data = Ning.Tools.permissionsCheck(urlList);
    for(var per in data){
        if(per=="/cargoHandling/print" && data[per]){
            CargoHandling.hasPrint=true;
        }
        if(per=="/cargoHandling/edit" && data[per]){
            CargoHandling.hasEdit = true;
        }
        if(per=="/cargoHandling/delete" && data[per]){
            CargoHandling.hasDelete = true;
        }
    }
}

/**
 * 点击添加超载货物处理
 */
CargoHandling.openAddCargoHandling = function () {
    var index = top.layer.open({
        type: 2,
        title: '新增卸货单',
        area: ['1000px', '550px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/cargoHandling/cargoHandling_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看超载货物处理详情
 */
CargoHandling.openCargoHandlingDetail = function (id) {
        var index = top.layer.open({
            type: 2,
            title: '超载货物处理详情',
            area: ['1000px', '550px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/cargoHandling/cargoHandling_update/' + id
        });
        this.layerIndex = index;
};

/**
 * 删除超载货物处理
 */
CargoHandling.delete = function (id) {

     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/cargoHandling/delete", function (data) {
	            Feng.success("删除成功!");
	            CargoHandling.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("cargoHandlingId",id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);

};

/**
 * 查询超载货物处理列表
 */
CargoHandling.search = function () {
    var queryData = {};
    queryData['withholdno'] = $("#withholdno").val();
    queryData['stationid'] = $("#stationid").val();
    queryData['vehicleid'] = $("#vehicleid").val();
    queryData['unloadtime'] = $("#unloadtime").val();
    CargoHandling.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CargoHandling.initColumn();
    var table = new BSTable(CargoHandling.id, "/cargoHandling/list", defaultColunms);
    table.setPaginationType("client");
    CargoHandling.table = table.init({showColumns:false,showRefresh: false});
    
    //隐藏选择列
    $('#'+CargoHandling.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );
    
    //初始化权限鉴权
    CargoHandling.initPermission();
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
