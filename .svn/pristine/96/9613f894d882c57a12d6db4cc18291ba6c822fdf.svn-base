/**
 * 货物转运处理管理初始化
 */
var MakeCargo = {
    id: "MakeCargoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MakeCargo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'indexid', visible: true, align: 'center', valign: 'middle'
        	,formatter: function (value, row, index) {  
            return index+1;  
        }},
            {title: '接货单号', field: 'makecargono', visible: true, align: 'center', valign: 'middle'},
            {title: '车牌号', field: 'makevehicleid', visible: true, align: 'center', valign: 'middle'},
            {title: '接货车辆联系人', field: 'makevehicleman', visible: true, align: 'center', valign: 'middle'},
            {title: '接货车辆联系方式', field: 'makevehicletel', visible: true, align: 'center', valign: 'middle'},
            {title: '接货重量(kg)', field: 'makecargoweight', visible: true, align: 'center', valign: 'middle'},
            {title: '接货日期', field: 'makecargodate', visible: true, align: 'center', valign: 'middle'},
            {title: '暂扣单号', field: 'withholdno', visible: false, align: 'center', valign: 'middle'},
            {
            	title: '操作',
            	field: 'MakeCargo', 
            	visible: true, 
            	align: 'center', 
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
			window.open(Feng.ctxPath + '/makeCargo/preview/' + row.id);
		},
		'click #btn_detail': function(e, value, row, index) {
			MakeCargo.openMakeCargoDetail(row.id);
		},

		'click #btn_delete': function(e, value, row, index) {
			MakeCargo.delete(row.id);
		},
		
}
/**
 * 添加按钮到表格
 */
function detailsFormatter() {

    var result = "";

    if(MakeCargo.hasPrint == true) {
        result = result + "<button id='btn_print' class='btn btn-primary btn-xs'>打印</button>";
    }
    if(MakeCargo.hasEdit == true) {
        result = result + "<button id='btn_detail' class='btn btn-primary btn-xs'>编辑</button>";
    }
    if(MakeCargo.hasDelete == true) {
        result = result + "<button id='btn_delete' class='btn btn-primary btn-xs'>删除</button>";
    }

    if(result.length < 1) {
        result = "--"
    }

    return result;
}

//初始化权限
MakeCargo.hasPrint = false;
MakeCargo.hasEdit = false;
MakeCargo.hasDelete = false;
MakeCargo.initPermission =  function() {
    var urlList = new Array();
    urlList.push("/makeCargo/print");
    urlList.push("/makeCargo/edit");
    urlList.push("/makeCargo/delete");
    var data = Ning.Tools.permissionsCheck(urlList);
    for(var per in data){
        if(per=="/makeCargo/print" && data[per]){
            MakeCargo.hasPrint=true;
        }
        if(per=="/makeCargo/edit" && data[per]){
            MakeCargo.hasEdit = true;
        }
        if(per=="/makeCargo/delete" && data[per]){
            MakeCargo.hasDelete = true;
        }
    }
}

/**
 * 检查是否选中
 */
MakeCargo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MakeCargo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加货物转运处理
 */
MakeCargo.openAddMakeCargo = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加货物转运处理',
        area: ['800px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/makeCargo/makeCargo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看货物转运处理详情
 */
MakeCargo.openMakeCargoDetail = function (id) {
        var index = top.layer.open({
            type: 2,
            title: '货物转运处理详情',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/makeCargo/makeCargo_update/' + id
        });
        this.layerIndex = index;
};

/**
 * 删除货物转运处理
 */
MakeCargo.delete = function (id) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/makeCargo/delete", function (data) {
	            Feng.success("删除成功!");
	            MakeCargo.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("makeCargoId",id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
};

/**
 * 查询货物转运处理列表
 */
MakeCargo.search = function () {
    var queryData = {};
    queryData['withholdno'] = $("#withholdno").val();
    queryData['makecargono'] = $("#makecargono").val();
    queryData['stationid'] = $("#stationid").val();
    queryData['makevehicleid'] = $("#makevehicleid").val();
    queryData['makecargodate'] = $("#makecargodate").val();
    MakeCargo.table.refresh({query: queryData});
};

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


$(function () {
    var defaultColunms = MakeCargo.initColumn();
    var table = new BSTable(MakeCargo.id, "/makeCargo/list", defaultColunms);
    table.setPaginationType("client");
    MakeCargo.table = table.init({showColumns:false,showRefresh: false});
    
    //隐藏选择列
    $('#'+MakeCargo.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );

    //初识权限
    MakeCargo.initPermission();
});
