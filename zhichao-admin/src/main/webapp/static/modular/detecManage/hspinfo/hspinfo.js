﻿/**
 * 不停车预检数据管理管理初始化
 */
var Hspinfo = {
    id: "HspinfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Hspinfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true,visible: false},
        {title: '序号', field: 'id', visible: true, align: 'center', valign: 'middle',formatter: function (value, row, index) {return Yang.Tools.serial(Hspinfo.id, index);}},
        {title: '检测站点', field: 'sitename', visible: true, align: 'center', valign: 'middle'},
        {title: '车牌号码', field: 'vehicleid', visible: true, align: 'center', valign: 'middle'},
        {title: '货车类型', field: 'vehicletypeString', visible: true, align: 'center', valign: 'middle'},
        {title: '轴数', field: 'axlesum', visible: true, align: 'center', valign: 'middle'},
        {title: '超限量（kg）', field: 'overlimited', visible: true, align: 'center', valign: 'middle'},
        {title: '超率（%）', field: 'superRate', visible: true, align: 'center', formatter: superRate},
        {title: '检测时间', field: 'hsptime', visible: true, align: 'center', valign: 'middle'},
        {title: '车货限重（kg）', field: 'approvedLoad', visible: true, align: 'center', formatter: approvedLoad},
        {title: '车货总重（kg）', field: 'totalweight', visible: true, align: 'center', valign: 'middle'},
        {field: 'operate', title: '操作', width: '80px', events: openDetail,  formatter: detailFormatter}
    ];
};


window.openDetail = {
		'click .btn': function(e, value, row, index) {      
			Hspinfo.openHspinfoDetail(row.id)
		}
}
function detailFormatter(value, row, index) {
	return [
		"<button id='btn_detail' class='btn btn-primary btn-xs'>详情</button>",
		].join('');
}
//车货限重
function approvedLoad(value, row, index) {
	return [
		row.totalweight - row.overlimited,
		].join('');
}
//超率
function superRate(value, row, index) {
	return[Math.round(row.overlimited / (row.totalweight - row.overlimited) * 10000) / 100 ].join('');
}

//全局变量
var stationid = "";
var depts = "";

/**
 * 打开查看不停车预检数据管理详情
 */
Hspinfo.openHspinfoDetail = function (id) {
	var index = top.layer.open({
		type: 2,
		title: '不停车预检数据管理详情',
		area: ['1000px', '600px'], //宽高
		fix: false, //不固定
		maxmin: true,
		content: Feng.ctxPath + '/hspinfo/hspinfo_update/' + id
	});
	this.layerIndex = index;
};

/**
 * 查询不停车预检数据管理列表
 */
Hspinfo.search = function () {
    var queryData = {};
    queryData['vehicleid'] = $("#vehicleid").val();
    queryData['hsptime'] = $("#hsptime").val();
    queryData['depts'] = depts;
    queryData['stationid'] = stationid;
    Hspinfo.table.refresh({query: queryData});
};


$(function () {
	
    var defaultColunms = Hspinfo.initColumn();
    var table = new BSTable(Hspinfo.id, "/hspinfo/list", defaultColunms);
    table.setPaginationType("server");
    Hspinfo.table = table.init({showRefresh:false, showColumns:false});

    var stationidValue = $("#statid").val();

    //初始化
    if(stationidValue == "") {
        Ning.Tree.setStree("#iNickTree", "presite");
    } else {
        Ning.Tree.setOneTree("#iNickTree", "presite", stationidValue);
        var node = {};
        node.type = "presite";
        node.sitecode = stationidValue;
        update_right(node);
    }

    Ning.Tree.bindOnClick(
        function(node) {
            update_right(node);
        }
    );

    //初始化状态值
    stationid = "";
    depts = "";
});

function showCertificate(uurl) {
    Ning.Tools.showPage(Feng.ctxPath + "/tempController/fileDownload?isDownload=0&fileUrl=" + uurl);

    return;
}

function update_right(node){

	var index = layer.load(1, {
		shade: [0.1,'#fff'] //0.1透明度的白色背景
	});

    if (node.type != "presite") {
        $("#certificateDiv").hide();
    } else {
        if(node.certificate == "") {
            $("#certificate").text("暂无");
            $("#certificate").attr("href", "javascript:;");
            $("#certificateValidityPeriod").text("暂无");
        } else {
            $("#certificate").text(node.certificate);
            $("#certificate").attr("href", 'javascript:showCertificate("'+node.certificate_url+'");');
            $("#certificateValidityPeriod").text(node.certificate_validity_period);
        }
        $("#certificateDiv").show();
    }
	
	//左栏切换时清空搜索框的值
	$("#vehicleid").val('');
	$("#hsptime").val('');

	//点击的是不停车站点
	if (node.type == "presite") {
    	stationid =  node.sitecode;
    	depts = [];
    	//点击的是部门
	} else if(node.type == "dept"){
		depts = Ning.Tree.getzNodes(node.id);
		stationid = "";
	}
    
	//大屏数据获取
	var ajax = new $ax(Feng.ctxPath + "/hspinfo/getStatistics", 
		function (data) {
			var text;
			for (var i = 0; i < data.length; i++) {
				text = data[i] != null && data[i] != 0 && data[i] != '' ? data[i] : "--"; 
				$("#right_top .col-md-3")[i].innerHTML = text;
			}
	    }, function (data) {
	    	Feng.info("加载数据异常,请重试。");
	    });
	ajax.set("stationid",stationid);
	ajax.set("depts",depts);
    ajax.start();

    //表格数据获取
    $("#HspinfoTable").bootstrapTable('refresh', {
    	query: { depts: depts, stationid: stationid,vehicleid: "",fctime: ""}
    });

    if(stationid != "") {
    	$("#HspinfoTable").on('load-success.bs.table',function(data){
            layer.close(index);
        });
    } else {
    	layer.close(index);
    }

    layer.close(index);
}
