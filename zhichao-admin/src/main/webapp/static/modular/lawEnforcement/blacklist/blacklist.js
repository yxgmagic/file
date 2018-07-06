/**
 * 黑白名单管理初始化
 */
var Blacklist = {
    id: "BlacklistTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Blacklist.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'indexid', visible: true, align: 'center', valign: 'middle'
        	,formatter: function (value, row, index) {  
            return index+1;  
        }},
            {title: '车牌号', field: 'vehicleid', visible: true, align: 'center', valign: 'middle'},
            {title: '驾驶人姓名', field: 'drivername', visible: true, align: 'center', valign: 'middle'},
            {title: '企业名称', field: 'corpname', visible: true, align: 'center', valign: 'middle'},
            {title: '超重站号', field: 'stationid', visible: false, align: 'center', valign: 'middle'},
            {title: '车辆类型', field: 'cartype', visible: false, align: 'center', valign: 'middle'},
            {title: '车轴数', field: 'caraxles', visible: false, align: 'center', valign: 'middle'},
            {title: '核定载重量(kg)', field: 'ratedloading', visible: false, align: 'center', valign: 'middle'},
            {title: '超重数(kg)', field: 'overload', visible: false, align: 'center', valign: 'middle'},
            {title: '罚金', field: 'fine', visible: false, align: 'center', valign: 'middle'},
            {title: '是否完成惩罚', field: 'isPunitiveName', visible: false, align: 'center', valign: 'middle'},
            {title: '是否进入黑名单', field: 'isBlackName', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remarks', visible: true, align: 'center', valign: 'middle'},
            {field: 'operate', title: '操作', align: 'center', valign: 'middle', events: openDetail,  formatter: detailFormatter}
    ];
};

/**
 * 处理表格按钮点击事件
 */
window.openDetail = {
	'click #btn_detail': function(e, value, row, index) {
		Blacklist.openBlacklistDetail(row.id)
	},

	'click #btn_add': function(e, value, row, index) {
		Blacklist.updateBlackStatus(row.id,1);
		Blacklist.search();
	},
	
	'click #btn_remove': function(e, value, row, index) {
		Blacklist.updateBlackStatus(row.id,0);
		Blacklist.search();
	}
}
function detailFormatter(value, row, index) {
	
	var result =  "<button id='btn_detail' class='btn btn-primary btn-xs'>查看违法记录</button>";
	var otherBtn = "";
    if(Blacklist.hasEdit == true) {
        if(row.isblack == "1") {
            otherBtn = "<button id='btn_remove' class='btn btn-primary btn-xs' >移出黑名单</button>";
        } else if (row.isblack == "0") {
            otherBtn = "<button id='btn_add' class='btn btn-primary btn-xs'>加入黑名单</button>";
        }
    }

	result = result + otherBtn;
    return result;
}


///blacklist/edit

//初始化权限
Blacklist.hasEdit = false;
Blacklist.initPermission =  function() {
    var urlList = new Array();
    urlList.push("/blacklist/edit");
    var data = Ning.Tools.permissionsCheck(urlList);
    for(var per in data){
        if(per=="/blacklist/edit" && data[per]){
            Blacklist.hasEdit = true;
        }
    }
}


//popup_select
/**
 * 弹出时间区间选择框
 */
Blacklist.openSelect = function () {
    var index = top.layer.open({
        type: 2,
        title: '请选择时间',
        area: ['320px', '410px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/blacklist/popup_select'
    });
    this.layerIndex = index;
};


/**
 * 点击添加黑白名单
 */
Blacklist.openAddBlacklist = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加黑名单',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/blacklist/blacklist_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看黑白名单详情
 */
Blacklist.openBlacklistDetail = function (id) {

        var index = top.layer.open({
            type: 2,
            title: '黑名单详情',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/blacklist/blacklist_update/' + id
        });
        this.layerIndex = index;

};

/**
 * 删除黑白名单
 */
Blacklist.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/blacklist/delete", function (data) {
	            Feng.success("删除成功!");
	            Blacklist.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("blacklistId",Blacklist.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询黑白名单列表
 */
Blacklist.search = function () {
    var queryData = {};
    queryData['vehicleid'] = $("#vehicleid").val();
    queryData['drivername'] = $("#drivername").val();
    queryData['corpname'] = $("#corpname").val();
    Blacklist.table.refresh({query: queryData});
};

/**
 * 改变某一条记录的黑名单状态
 */
Blacklist.updateBlackStatus = function(id, value) {
	
	//提交信息
    var ajax = new $ax(Feng.ctxPath + "/blacklist/update_black_status", function(data){
        Feng.success("修改成功!");
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    var date = {
    	'id': id,
    	'statusValue': value
    }
    ajax.set(date);
    ajax.start();
}


layui.use(['laydate'],function() {
	var laydate = layui.laydate;
	laydate.render({
	    elem: '#timeSelect'
	    ,type: 'year'
	    ,position: 'static'
	    ,show: true //直接显示
	    ,btns: ['confirm']
	   	,done: function(value, date, endDate){
	   		$("#timeSelect").fadeOut("fast");
	    	$("body").unbind("mousedown", onBodyDown);
	    	generate(value);
	   	  }
	});
});


/**
 * 点击生成黑名单时,
 * 并给body绑定单击事件,
 * 用来判断用户是否在时间选择
 */
$("#generate").click(function() {
	$("#timeSelect").slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
});

//body点击事件
/**
 * 判断点击的元素的父元素中是否含有#timeSelect,
 * 如果没有则代表用户点击到了时间选择组件以外的区域,
 * 需要将时间选择组件隐藏,且取消页面点击事件的绑定
 */
function onBodyDown(event) {
    if(!$(event.target).parents("#timeSelect").length == 1) {
    	$("#timeSelect").fadeOut("fast");
    	$("body").unbind("mousedown", onBodyDown);
    }
}

function generate(time) {
	var index = layer.load(1, {
		shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	
	$.ajax({
		type: 'POST',
		url: Feng.ctxPath + "/blacklist/generate_blacklist",
		data: {
			'time':time
		},
		success: function(data) {
			layer.close(index);

			Blacklist.table.refresh();
			
			layer.msg("新增" + data + "条记录");
			
		},
	});
}


$(function () {
    var defaultColunms = Blacklist.initColumn();
    var table = new BSTable(Blacklist.id, "/blacklist/list", defaultColunms);
    table.setPaginationType("client");
    Blacklist.table = table.init({showColumns:false,showRefresh: false});
    
    //隐藏选择列
    $('#'+Blacklist.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );

    //初始化权限
    Blacklist.initPermission();
    
});
