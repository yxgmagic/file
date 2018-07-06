/**
 * 案件处理初始化
 */
var CaseHand = {
    id: "CaseHandTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 打开查看案件详情
 */
CaseHand.openCaseHandDetail = function (id) {
    var index = top.layer.open({
        type: 2,
        title: '案件办理',
        area: ['1600px', '900px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/casehand/oefullinfo_update/' + id
    });
    this.layerIndex = index;
};

/**
 * 查询案件处理列表
 */
CaseHand.search = function () {
    var queryData = {};
    queryData['casetime'] = $("#casetime").val();
    queryData['vehicleid'] = $("#vehicleid").val();
    queryData['caseno'] = $("#caseno").val();
    queryData['prostatus'] = $("#prostatus").val();
    CaseHand.table.refresh({query: queryData});
};

/**
 * 审核
 */
CaseHand.Audit = function(id,checkno,caseSource) {
	layer.confirm('确认要通过审核吗？', {
		btn: ['通过','驳回','取消'] //按钮
	}, function(){
		
		//通过
		var ajax = new $ax(Feng.ctxPath + "/casehand/via/" + id + "/" + checkno + "/" + caseSource, function(data){
			Feng.success("已通过!");
		},function(data){
			Feng.error("操作失败，请重试!");
		});
		ajax.start();
		
		refushtable();
	}, function(){
		//驳回
		var ajax = new $ax(Feng.ctxPath + "/casehand/turndown/" + id + "/" + checkno + "/" + caseSource, function(data){
			Feng.success("已驳回!");
		},function(data){
			Feng.error("操作失败，请重试!" + data.responseJSON.message + "!");
		});
		ajax.start();
		
		refushtable();
	}, function() {

	});
};

/**
 * 刷新表格
 * @returns
 */
function refushtable() {
	
	layer.closeAll();
	
	var winOpen=Feng.GetFrame("/casehand");
    if (winOpen!=undefined){
    	winOpen.CaseHand.table.refresh();
    }
}


/**
 * 附件
 * @constructor
 */
CaseHand.Annex = function () {
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
CaseHand.Lawdoc = function () {
    var index = layer.open({
        type: 2,
        title: '文书',
        area: ['800px', '300px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/oefullinfo/lawdocPage'
    });
    this.layerIndex = index;
};

/**
 * 短消息发送
 * @constructor
 */
CaseHand.sendMsg = function () {
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

var msg = {"data":-1,"flag":false,"checkno": -1,"oeid":-1};
$('#CaseHandTable').on("click", function(e) {
    var selected = $('#CaseHandTable').bootstrapTable('getSelections');
    msg.checkno = selected[0].id;
    msg.oeid = msg.checkno;
});


$(function () {
    var defaultColunms = CaseHand.initColumn();
    var table = new BSTable(CaseHand.id, "/casehand/list", defaultColunms);
    table.setPaginationType("client");
    CaseHand.table = table.init({showRefresh:false, showColumns:false});
    
  //隐藏选择列
    $('#'+CaseHand.id).on('all.bs.table', function (){
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

    CaseHand.initPermission();
});


/**
 * 初始化表格的列
 */
CaseHand.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'corpid', visible: true, align: 'center', formatter: function (value, row, index) {return  index + 1}},
            {title: '案号', field: 'caseno', visible: true, align: 'center', valign: 'middle'},
            {title: '检测站点', field: 'sitename', visible: true, align: 'center', valign: 'middle'},
            {title: '检测时间', field: 'fctime', visible: true, align: 'center', valign: 'middle'},
            {title: '车牌号码', field: 'vehicleid', visible: true, align: 'center', valign: 'middle'},
            {title: '车货总重（kg）', field: 'fctotalweight', visible: true, align: 'center', valign: 'middle'},//初检总重量
            {title: '车货限重（kg）', field: 'weightlimited', visible: true, align: 'center', valign: 'middle'},
            {title: '超限量（kg）', field: 'overlimited', visible: true, align: 'center', valign: 'middle'},
            {title: '审核结果', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {field: 'operate', title: '操作', align: 'center', valign: 'middle', events: openDetail,  formatter: detailFormatter}
    ];
};

function GetQueryString() {
	var r = window.location.href;
 
	var mentutype=r.substring(r.lastIndexOf("/")+1 ,r.length);
	 
	    var h5= document.getElementsByTagName("h5")[0];
	    if('1'==mentutype){
	    h5.innerHTML = "执法文书管理";
	    }
	return mentutype;
}

window.openDetail = {
    'click #btn_audit': function(e, value, row, index) {
        CaseHand.Audit(row.id,row.checkno, row.caseSource);
    },
    'click #btn_detail': function(e, value, row, index) {
        CaseHand.openCaseHandDetail(row.id,row.checkno);
    },
    'click #annex': function(e, value, row, index) {
        CaseHand.Annex();
    },
    'click #btn_lawdoc': function(e, value, row, index) {
        CaseHand.Lawdoc();
    },
    'click #sendMsg': function(e, value, row, index) {
        CaseHand.sendMsg();
    }
};

//初始化权限
CaseHand.hasAudit = false;
CaseHand.hasDetail = false;
CaseHand.hasAnnex = false;
CaseHand.hasLawdoc = false;
CaseHand.hasSendMsg = false;
CaseHand.initPermission =  function() {
    var urlList = ["/casehand/oefullinfo_update/", "/casehand/audit", "/casehand/annexPage", "/casehand/lawdocPage", "/oefullinfo/sendMsgPage"];
    var data = Ning.Tools.permissionsCheck(urlList);
    for(var per in data){
        if(per=="/casehand/lawdocPage" && data[per]){
            CaseHand.hasLawdoc = true;
        }
        if(per=="/oefullinfo/sendMsgPage" && data[per]){
            CaseHand.hasSendMsg = true;
        }
        if(per=="/casehand/annexPage" && data[per]){
            CaseHand.hasAnnex = true;
        }
        if(per=="/casehand/audit" && data[per]){
            CaseHand.hasAudit = true;
        }
        if(per=="/casehand/oefullinfo_update/" && data[per]){
            CaseHand.hasDetail = true;
        }
    }
}
function detailFormatter(value, row, index) {

    var lawdoc = "";
    var annex = "";
    var sendMsg = "";
    var detail = "";
    var audit = "";
    if (CaseHand.hasLawdoc){
        lawdoc = "<a id='btn_lawdoc' class='btn btn-primary btn-xs'>文书下载</a>";
    }
    if (CaseHand.hasAnnex){
        annex = "<a id='annex' class='btn btn-primary btn-xs'>附件查看</a>";
    }
    if (CaseHand.hasSendMsg){
        sendMsg = "<a id='sendMsg' class='btn btn-primary btn-xs'>短消息发送</a>";
    }
    if (CaseHand.hasDetail){
        detail = "<button id='btn_detail' class='btn btn-primary btn-xs'>查看</button>";
    }
    if (CaseHand.hasAudit){
        audit = "<button id='btn_audit' class='btn btn-primary btn-xs'>审核</button>";
        if(row.prostatus >= Yang.Const.HASBEENREGISTERED && CaseHand.hasAudit){
            audit = "<button id='btn_audit' disabled='disabled' class='btn btn-primary btn-xs'>审核</button>";
        }
    }

    var menutype=GetQueryString();
    if('1'==menutype){
        return [
            "<button id='btn_detail' class='btn btn-primary btn-xs'>查看</button>" +
            "<button id='btn_detail' class='btn btn-primary btn-xs'>提交省平台</button>",
        ].join('');
        //已立案，不可点击审核
    }
    var format = (audit + detail + lawdoc + annex + sendMsg) == '' ? '无操作权限' : audit + detail + lawdoc + annex + sendMsg;
    return [format].join('');
}

function getrate(value, row, index) {
	return[Math.round((row.fctotalweight - row.weightlimited) / row.ratedloading * 10000) / 100 ].join('');
}