/**
 * 流动执法车数据管理管理初始化
 */
var Meinfo = {
    id: "MeinfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Meinfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'id', visible: true, align: 'center', valign: 'middle',formatter: function (value, row, index) {return Yang.Tools.serial(Meinfo.id, index);}},
            {title: '执法车车牌号', field: 'enforcecar', visible: true, align: 'center', valign: 'middle'},
            {title: '检测时间', field: 'fctime', visible: true, align: 'center', valign: 'middle'},
            {title: '车牌号', field: 'vehicleid', visible: true, align: 'center', valign: 'middle'},
            {title: '轴数', field: 'axlesum', visible: true, align: 'center', valign: 'middle'},
            {title: '实重(kg)', field: 'fctotalweight', visible: true, align: 'center', valign: 'middle'},
            {title: '核定载重量(kg)', field: 'ratedloading', visible: true, align: 'center', valign: 'middle'},
            {title: '超限量(kg)', field: 'overlimited', visible: true, align: 'center', valign: 'middle'},
            {title: '超率(%)', field: '', align: 'center', valign: 'middle', formatter: getrate},
            {field: 'operate', title: '操作', align: 'center', valign: 'middle', events: openDetail,  formatter: detailFormatter},
            {title: '检测单号', field: 'checkno', visible: false, align: 'center', valign: 'middle'},
            {title: '车货限重', field: 'weightlimited', visible: false, align: 'center', valign: 'middle'},
            {title: '车辆标记总质量', field: 'ratedtotalweight', visible: false, align: 'center', valign: 'middle'},
            {title: '超限量', field: 'overlimited', visible: false, align: 'center', valign: 'middle'},
    ];
};

window.openDetail = {
        'click #btn_detail': function(e, value, row, index) {      
        	Meinfo.openMeinfoDetail(row.id)
            },
        'click #btn_enforcement': function(e, value, row, index) {      
            layer.msg("执法")
          }
      }
function detailFormatter(value, row, index) {
    return [
        "<button id='btn_detail' class='btn btn-primary btn-xs'>详情</button>" +
        "<button id='btn_enforcement' class='btn btn-primary btn-xs'>执法</button>",
    ].join('');
}

function getrate(value, row, index) {
    if (row.weightlimited <= 0 ||     row.weightlimited == null){
        return '-';
    }
	return[Math.round(row.overlimited / row.weightlimited * 10000) / 100 ].join('');
}

//全局变量
var stationid = "";
var areacode = "";
var depts = "";

/**
 * 点击添加流动执法车数据管理
 */
Meinfo.openAddMeinfo = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加流动执法车数据管理',
        area: ['1000px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/meinfo/meinfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看流动执法车数据管理详情
 */
Meinfo.openMeinfoDetail = function (id) {
    var index = top.layer.open({
        type: 2,
        title: '流动执法车数据管理详情',
        area: ['1000px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/meinfo/meinfo_update/' + id
    });
    this.layerIndex = index;
};

/**
 * 查询流动执法车数据管理列表
 */
Meinfo.search = function () {
	var queryData = {};
	queryData['vehicleid'] = $("#vehicleid").val();
    queryData['fctime'] = $("#fctime").val();
    queryData['areacode'] = areacode;
    Meinfo.table.refresh({query: queryData});
};

$(function () {
	var stationidValue = $("#statid").val();
	
	//初始化
	if(stationidValue == "") {
		Ning.Tree.setStree("#iNickTree", "enforcecar");
	} else {
		Ning.Tree.setStree("#iNickTree", "enforcecar", stationidValue, true);
	}

	//给左侧的部门树绑定点击事件
    Ning.Tree.bindOnClick(
        function(node) {
        	update_right(node);
        }
    );
    
	//初始化状态值
	stationid = "";
	areacode = "";
	
    var defaultColunms = Meinfo.initColumn();
    var table = new BSTable(Meinfo.id, "/meinfo/list", defaultColunms);
    table.setPaginationType("server");
    Meinfo.table = table.init({showRefresh:false, showColumns:false});
    
    //隐藏选择列
    $('#'+Meinfo.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );
});

function update_right(node){

	var index = layer.load(1, {
		shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	
	//左栏切换时清空搜索框的值
	$("#vehicleid").val('');
	$("#fctime").val('');
	
	if (node.type == "enforcecar") {
    	stationid =  node.sitecode;
    	depts = [];
	} else if(node.type == "dept"){
		depts = Ning.Tree.getzNodes(node.id);
		stationid = "";
	}
    
    //大屏数据获取
	var ajax = new $ax(Feng.ctxPath + "/meinfo/getStatistics", 
		function (data) {
	    console.log(data)
			var text;
			for (var i = 0; i < data.length; i++) {
				text = data[i] != null && data[i] != 0 && data[i] != '' ? data[i] : "--"; 
				$("#right_top .col-md-3")[i].innerHTML = text;
			}
	    }, function (data) {
	    	Feng.info("加载数据异常,请重试。");
	    });
	ajax.set("depts",depts);
    ajax.start();
    
    //清空表格
    $("#MeinfoTable").bootstrapTable('removeAll');
    
    //表格数据获取
    $("#MeinfoTable").bootstrapTable('refresh', {
    	query: { depts: depts, vehicleid: null,fctime: null}
    });
    
    if(stationid != "") {
    	$("#MeinfoTable").on('load-success.bs.table',function(data){
            layer.close(index);
        });
    } else {
    	layer.close(index);
    }
}