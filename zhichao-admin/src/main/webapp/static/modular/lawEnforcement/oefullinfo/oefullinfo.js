﻿/**
 * 立案办理管理初始化
 */
var Oefullinfo = {
    id: "OefullinfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 开具执法文书
 * @param id
 * @param vid
 * @param checkno
 * @param source
 */
Oefullinfo.openOefullinfoDetail = function (id,source) {

    var url = source == 'lsc' ? '/oefullinfo/oefullinfo_update/' + id : '/bOseinfo/addOseCasePage/'+id;

    var index = top.layer.open({
        type: 2,
        title: '立案办理',
        area: ['1600px', '900px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + url
    });
    this.layerIndex = index;
};
/*
 * 浏览信息
 */
Oefullinfo.browse = function(id,source) {
	var url = '/oefullinfo/oefullinfo_info/' + id + "/" + source;
	var index = top.layer.open({
		type: 2,
		title: '案件信息',
		area: ['1600px', '900px'], //宽高
		fix: false, //不固定
		manmin: true,
		content: Feng.ctxPath + url
	});
	this.layerIndex = index;
}


/**
 * 查询立案办理列表
 */
Oefullinfo.search = function () {
    var queryData = {};
    queryData['casetime'] = $("#casetime").val();
    queryData['vehicleid'] = $("#vehicleid").val();
    queryData['caseno'] = $("#caseno").val();
    queryData['prostatus'] = $("#prostatus").val();
    Oefullinfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Oefullinfo.initColumn();
    var table = new BSTable(Oefullinfo.id, "/oefullinfo/list", defaultColunms);
    table.setPaginationType("server");
    Oefullinfo.table = table.init({showRefresh:false, showColumns:false});

    //隐藏选择列
    $('#'+Oefullinfo.id).on('all.bs.table', function (){
            $(".bs-checkbox").attr('style',"display:none");
        }
    );

    layui.use('laydate', function(){
        var laydate = layui.laydate;
        laydate.render({
            elem: '#casetime'
            ,type: 'datetime'
            ,range: '~' //或 range: '~' 来自定义分割字符
            ,max: ''+new Date()
        });
    });

    Oefullinfo.initPermission();
});


/**
 * 附件
 * @constructor
 */
Oefullinfo.Annex = function () {
    var index = layer.open({
        type: 2,
        title: '附件',
        area: ['800px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/oefullinfo/annexPage'
    });
    this.layerIndex = index;
};

/**
 * 文书
 * @constructor
 */
Oefullinfo.Lawdoc = function () {
    var index = layer.open({
        type: 2,
        title: '文书',
        area: ['800px', '300px'], //宽高
        fix: true, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/oefullinfo/lawdocPage'
    });
    this.layerIndex = index;
};

/**
 * 短消息发送
 * @constructor
 */
Oefullinfo.sendMsg = function () {
    var index = layer.open({
        type: 2,
        title: '短消息发送',
        area: ['600px', '300px'], //宽高
        fix: true, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/oefullinfo/sendMsgPage'
    });
    this.layerIndex = index;
};

//下一个页面从此处获取值
var msg = {"data":-1,"flag":true,"checkno":-1,"oeid": -1};
$('#OefullinfoTable').on("click", function(e) {
    var selected = $('#OefullinfoTable').bootstrapTable('getSelections');
    msg.checkno = selected[0].checkno;
});

/**
 * 初始化表格的列
 */
Oefullinfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'corpid', visible: true, align: 'center', formatter: function (value, row, index) {return  index + 1}},
        {title: '检测站点', field: 'stationid', visible: true, align: 'center', valign: 'middle'},
        {title: '检测时间', field: 'time', visible: true, align: 'center', valign: 'middle'},
        {title: '车牌号码', field: 'vehicleid', visible: true, align: 'center', valign: 'middle'},
        {title: '轴数', field: 'axlesum', visible: true, align: 'center', valign: 'middle'},
        {title: '车货总重(kg)', field: 'totalweight', visible: true, align: 'center', valign: 'middle'},
        {title: '超限量(kg)', field: 'overlimited', visible: true, align: 'center', valign: 'middle'},
        {title: '车货限重(kg)', field: 'weightlimited', visible: true, align: 'center', valign: 'middle'},
        {title: '超载量(kg)', field: 'overload', visible: true, align: 'center', valign: 'middle'},
        {title: '超率(%)', field: '', align: 'center', valign: 'middle', formatter: getrate},
        {title: '处理状态', field: 'prostatus', visible: true, align: 'center', valign: 'middle', formatter: props},
        {field: 'operate', title: '操作', align: 'center', valign: 'middle', events: openDetail,  formatter: detailFormatter}
    ];
};

//初始化权限
Oefullinfo.hasAudit = false;
Oefullinfo.hasSendMsg = false;
Oefullinfo.hasLawdoc = false;
Oefullinfo.hasAnnex = false;
Oefullinfo.bewoseInfo = false;
Oefullinfo.initPermission =  function() {
    var urlList = ["/oefullinfo/oefullinfo_update/", "/oefullinfo/sendMsgPage", "/oefullinfo/lawdocPage", "/oefullinfo/annexPage","/"];
    var data = Ning.Tools.permissionsCheck(urlList);
    for(var per in data){
        if(per=="/oefullinfo/sendMsgPage" && data[per]){
            Oefullinfo.hasSendMsg=true;
        }
        if(per=="/oefullinfo/oefullinfo_update/" && data[per]){
            Oefullinfo.hasAudit = true;
        }
        if(per=="/oefullinfo/lawdocPage" && data[per]){
            Oefullinfo.hasLawdoc = true;
        }
        if(per=="/oefullinfo/annexPage" && data[per]){
            Oefullinfo.hasAnnex = true;
        }
    }
};
function detailFormatter(value, row, index) {
    var lawdoc = "";
    var annex = "";
    var sendMsg = "";
    var audit = "";
    var browseInfo ="";
    if ((row.prostatus == Yang.Const.UNREGISTERED) || (row.prostatus == Yang.Const.TURNDOWN)){

        if (Oefullinfo.hasLawdoc){
            lawdoc = "<a id='btn_lawdoc' class='btn btn-primary btn-xs disabled'>文书下载</a>";
        }
        if (Oefullinfo.hasAnnex){
            annex = "<a id='annex' class='btn btn-primary btn-xs disabled'>上传附件</a>";
        }
        if (Oefullinfo.hasSendMsg){
            sendMsg = "<a id='sendMsg' class='btn btn-primary btn-xs disabled'>短消息发送</a>";
        }
        
    }else{
        if (Oefullinfo.hasLawdoc){
            lawdoc = "<a id='btn_lawdoc' class='btn btn-primary btn-xs'>文书下载</a>";
        }
        if (Oefullinfo.hasAnnex){
            annex = "<a id='annex' class='btn btn-primary btn-xs'>上传附件</a>";
        }
        if (Oefullinfo.hasSendMsg){
            sendMsg = "<a id='sendMsg' class='btn btn-primary btn-xs'>短消息发送</a>";
        }
        
    }
    if(row.prostatus > Yang.Const.WAITAPPROVAL){
    	browseInfo = "<button id='browseInfo' class='btn btn-primary btn-xs'>浏览</button>"
    }else{
    	browseInfo = "<button id='browseInfo' class='btn btn-primary btn-xs disabled'>浏览</button>";
    }

    if (Oefullinfo.hasAudit){
        audit = "<button id='btn_detail' class='btn btn-primary btn-xs'>开具执法文书</button>";
    }
    
    var format = (audit + lawdoc + annex + sendMsg + browseInfo) == '' ? '无操作权限' : audit + lawdoc + annex + sendMsg + browseInfo;

    return [format].join('');
}
window.openDetail = {
    'click #btn_detail': function(e, value, row, index) {
        Oefullinfo.openOefullinfoDetail(row.id, row.source)
    },
    'click #annex': function(e, value, row, index) {
        Oefullinfo.Annex();
    },
    'click #btn_lawdoc': function(e, value, row, index) {
        Oefullinfo.Lawdoc();
    },
    'click #sendMsg': function(e, value, row, index) {
        Oefullinfo.sendMsg();
    },
    'click #browseInfo': function(e, value, row, index){
    	Oefullinfo.browse(row.id,row.source);
    }
};
function props(value, row, index) {
    return Yang.Const.CASEArr[row.prostatus];
}

function getrate(value, row, index) {
    if(row.overlimited <= 0) {
        return "未超限"
    } else {
        var num = row.overlimited / row.weightlimited;
        num = parseFloat(num);
        return Math.round(num * 10000) / 100;
    }
}