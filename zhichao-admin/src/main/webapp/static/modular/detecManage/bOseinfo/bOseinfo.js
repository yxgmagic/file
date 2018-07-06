﻿/**
 * 非现场执法数据管理管理初始化
 */
var BOseinfo = {
    id: "BOseinfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 检查是否选中
 */
BOseinfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BOseinfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加非现场执法数据管理
 */
BOseinfo.openAddBOseinfo = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加非现场执法数据管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bOseinfo/bOseinfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看非现场执法数据管理详情
 */
BOseinfo.openBOseinfoDetail = function (id) {
    var index = top.layer.open({
        type: 2,
        title: '数据详情',
        area: ['1000px', '620px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bOseinfo/deteil/' + id
    });
    this.layerIndex = index;
};

BOseinfo.oefull = function (id) {
    var index = top.layer.open({
        type: 2,
        title: '立案',
        area: ['1600px', '900px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bOseinfo/addOseCasePage/'+id
    });
    this.layerIndex = index;
};

BOseinfo.lawdoc = function (id) {
    var index = layer.open({
        type: 2,
        title: '文书下载',
        area: ['800px', '300px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/oefullinfo/lawdocPage'
    });
    this.layerIndex = index;
};

BOseinfo.annex = function (id) {
    var index = layer.open({
        type: 2,
        title: '上传附件',
        area: ['800px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/oefullinfo/annexPage'
    });
    this.layerIndex = index;
};

//下一个页面从此处获取值
var msg = {"data":-1,"flag":true,"checkno": -1};
$('#BOseinfoTable').on("click", function(e) {
    var selected = $('#BOseinfoTable').bootstrapTable('getSelections');
    msg.checkno = selected[0].oseid;
});

/**
 * 查询非现场执法数据
 */
BOseinfo.search = function () {
    var queryData = {};
    queryData['vehicleid'] = $("#vehicleid").val();
    queryData['osetime'] = $("#osetime").val();
    queryData['depts'] = depts;
    queryData['stationid'] = stationid;
    BOseinfo.table.refresh({query: queryData});
};

//全局变量
var stationid = "";
var depts = "";
$(function () {
    var defaultColunms = BOseinfo.initColumn();
    var table = new BSTable(BOseinfo.id, "/bOseinfo/list", defaultColunms);
    table.setPaginationType("server");
    BOseinfo.table = table.init({showColumns:false,showRefresh: false,se: false});
    
    //隐藏选择列
    $('#'+BOseinfo.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );

    var stationidValue = $("#statid").val();

    //初始化
    if(stationidValue == "") {
        Ning.Tree.setStree("#iNickTree", "ose");
    } else {
        Ning.Tree.setOneTree("#iNickTree", "ose", stationidValue);
        var node = {};
        node.type = "ose";
        node.sitecode = stationidValue;
        update_right(node);
    }

    //左侧部门选择组件
    Ning.Tree.bindOnClick(function(node) {
        update_right(node);
    });

    //初始化状态值
    stationid = "";
    depts = "";

    BOseinfo.initPermission();
});


function showCertificate(uurl) {
    Ning.Tools.showPage(Feng.ctxPath + uurl);
    return false;
}

function update_right(node){

    var index = layer.load(1, {
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });

    if (node.type != "ose") {
        $("#certificateDiv").hide();
    } else {
        if(node.certificate == "") {
            $("#certificate").text("暂无");
            $("#certificate").attr("href", "javascript:return false;");
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

    if (node.type == "ose") {
        stationid =  node.sitecode;
        depts = [];
    } else if(node.type == "dept"){
        depts = Ning.Tree.getzNodes(node.id);
        stationid = "";
    }

    //大屏数据获取
    var ajax = new $ax(Feng.ctxPath + '/bOseinfo/getStatistics',
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
    $("#BOseinfoTable").bootstrapTable('refresh', {
        query: { depts: depts, stationid: stationid,vehicleid: "",osetime: ""}
    });

    if(stationid != "") {
        $("#BOseinfoTable").on('load-success.bs.table',function(data){
            layer.close(index);
        });
    } else {
        layer.close(index);
    }

    layer.close(index);
}


/**
 * 初始化表格的列
 */
BOseinfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'id', visible: true, align: 'center', valign: 'middle',formatter: function (value, row, index) {return Yang.Tools.serial(BOseinfo.id, index);}},
        {title: '检测站点', field: 'stationid', visible: true, align: 'center', valign: 'middle'},
        {title: '车牌号码', field: 'vehicleid', visible: true, align: 'center', valign: 'middle'},
        {title: '轴数', field: 'axlesum', visible: true, align: 'center', valign: 'middle'},
        {title: '超限量（kg）', field: 'overlimited', visible: true, align: 'center', valign: 'middle'},
        {title: '超率（%）', field: 'superRate', visible: true, align: 'center', formatter: superRate},
        {title: '检测时间', field: 'osetime', visible: true, align: 'center', valign: 'middle'},
        {title: '车货限重（kg）', field: 'approvedLoad', visible: true, align: 'center', formatter: approvedLoad},
        {title: '车货总重（kg）', field: 'totalweight', visible: true, align: 'center', valign: 'middle'},
        {title: '处理状态', field: 'prostatus', visible: true, align: 'center', valign: 'middle', formatter: props},
        {field: 'operate', title: '操作', width: '250px', events: openDetail,align: 'center',  formatter: detailFormatter}
    ];
};

window.openDetail = {
    'click #btn_detail': function(e, value, row, index) {
        BOseinfo.openBOseinfoDetail(row.id)
    },
    'click #btn_oefull': function(e, value, row, index) {
        BOseinfo.oefull(row.id)
    },
    'click #btn_lawdoc': function(e, value, row, index) {
        BOseinfo.lawdoc(row.id)
    },
    'click #btn_annex': function(e, value, row, index) {
        BOseinfo.annex(row.id)
    }
};

//初始化权限
BOseinfo.hasDetail = false;
BOseinfo.hasAnnex = false;
BOseinfo.hasLawdoc = false;
BOseinfo.hasOefull = false;
BOseinfo.initPermission =  function() {
    var urlList = ["/bOseinfo/addOseCasePage", "/bOseinfo/deteil", "/bOseinfo/lawdocPage", "/bOseinfo/annexPage"];
    var data = Ning.Tools.permissionsCheck(urlList);
    for(var per in data){
        if(per=="/bOseinfo/addOseCasePage" && data[per]){
            BOseinfo.hasOefull=true;
        }
        if(per=="/bOseinfo/deteil" && data[per]){
            BOseinfo.hasDetail = true;
        }
        if(per=="/bOseinfo/lawdocPage" && data[per]){
            BOseinfo.hasLawdoc = true;
        }
        if(per=="/bOseinfo/annexPage" && data[per]){
            BOseinfo.hasAnnex = true;
        }
    }
}
function detailFormatter(value, row, index) {
    var annex = "";
    var lawdoc = "";
    var detail = "";
    var oefull = "";

    if ((row.prostatus == Yang.Const.UNREGISTERED) || (row.prostatus == Yang.Const.TURNDOWN)){
        if (BOseinfo.hasAnnex){
            lawdoc = "<a id='btn_lawdoc' class='btn btn-primary btn-xs disabled'>文书下载</a>";
        }

        if (BOseinfo.hasLawdoc){
            annex = "<a id='btn_annex' class='btn btn-primary btn-xs disabled'>上传附件</a>";
        }
    }else {
        if (BOseinfo.hasAnnex){
            annex = "<a id='btn_annex' class='btn btn-primary btn-xs'>上传附件</a>";
        }

        if (BOseinfo.hasLawdoc){
            lawdoc = "<a id='btn_lawdoc' class='btn btn-primary btn-xs'>文书下载</a>";
        }
    }

    if (BOseinfo.hasDetail){
        detail = "<button id='btn_detail' class='btn btn-primary btn-xs'>详情</button>";
    }

    if (BOseinfo.hasOefull){
        oefull = "<button id='btn_oefull' class='btn btn-primary btn-xs'>立案</button>";
    }

    var format = (detail + oefull + lawdoc + annex) == '' ? '无操作权限' : (detail + oefull + lawdoc + annex);
    return [format].join('');
}
//核定载重
function approvedLoad(value, row, index) {
    return [
        row.totalweight - row.overlimited,
    ].join('');
}
//超率
function superRate(value, row, index) {
    var ratedloadingValue = row.totalweight - row.overlimited
    if(ratedloadingValue == 0) {
        return "--"
    } else {
        return Math.round(row.overlimited / ratedloadingValue * 10000) / 100;
    }

}
function props(value, row, index) {
	return Yang.Const.CASEArr[row.prostatus];
}

/*BOseinfo.props = function (value, row, index) {
    return Yang.Const.CASEArr[row.prostatus];

}*/