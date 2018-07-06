/**
 * 精检站治超数据管理初始化
 */
var Lscinfo = {
    id: "LscinfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Lscinfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'indexid', visible: true, align: 'center', valign: 'middle'
            ,formatter: function (value, row, index) {
                var pageSize=$('#LscinfoTable').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                var pageNumber=$('#LscinfoTable').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;
            }},
        {title: '检测站点', field: 'stationid', visible: true, align: 'center', valign: 'middle'},
        {title: '检测时间', field: 'fctime', visible: true, align: 'center', valign: 'middle'},
        {title: '车牌号码', field: 'vehicleid', visible: true, align: 'center', valign: 'middle'},
        {title: '轴数', field: 'axlesum', visible: true, align: 'center', valign: 'middle'},
        {title: '车货限重(kg)', field: 'weightlimited', visible: true, align: 'center', valign: 'middle'},
        {title: '车货总重(kg)', field: 'fctotalweight', visible: true, align: 'center', valign: 'middle'},

        {title: '超限量(kg)', field: 'overlimited', visible: true, align: 'center', valign: 'middle'},
        {title: '超率(%)', field: 'reta', align: 'center', valign: 'middle', formatter: getrate},
        {field: 'status', title: '状态', align: 'center', valign: 'middle',   formatter: statusFormatter},
        {field: 'operate', title: '操作', align: 'center', valign: 'middle', events: openDetail,  formatter: detailFormatter}
    ];
};

//绑定按钮点击事件
window.openDetail = {
    'click #btn_detail': function(e, value, row, index) {
        Lscinfo.openLscinfoDetail(row)
    },
    'click #btn_detail1': function(e, value, row, index) {
        Lscinfo.openLscinfoDetail(row)
    }
}
//每一条记录的操作按钮,利用js动态添加
function detailFormatter(value, row, index) {
    var resultStr = "";

    if(Lscinfo.hasRecheck == true) {    //复检权限
        if (row.rctotalweight > 0) {
            resultStr = "<button id='btn_detail' class='btn btn-primary btn-xs' disabled='disabled'>复检</button>";
        } else {
            resultStr = "<button id='btn_detail' class='btn btn-primary btn-xs' >复检</button>";
        }
    }
    if(Lscinfo.hasDetail == true) {     //查看详情权限
        resultStr  = resultStr + "<button id='btn_detail1' class='btn btn-primary btn-xs' >查看</button>";
    }

    if(resultStr.length < 1) {
        resultStr = "---";
    }

    return resultStr;
}

//用来标志这条记录的状态
function statusFormatter(value, row, index) {
    if(row.rctotalweight > 0) {
        return '已复检';
    } else {
        return '已初检';
    }
}


//用于初始化权限,记得在下面页面加载完之后初始化
Lscinfo.hasRecheck = false;
Lscinfo.hasFcheck = false;
Lscinfo.hasDetail = false;
Lscinfo.initPermission =  function() {
    var urlList = new Array();
    urlList.push("/lscinfo/recheck");
    urlList.push("/lscinfo/fcheck");
    urlList.push("/lscinfo/detail");


    var data = Ning.Tools.permissionsCheck(urlList);
    for(var per in data){
        if(per=="/lscinfo/recheck" && data[per]){
            Lscinfo.hasRecheck=true;
        }
        if(per=="/lscinfo/fcheck" && data[per]){
            Lscinfo.hasFcheck = true;
        }
        if(per=="/lscinfo/detail" && data[per]){
            Lscinfo.hasDetail = true;
        }
    }
}


/**
 * 检查是否选中
 */
Lscinfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Lscinfo.seItem = selected[0];
        return true;
    }
};


//用来计算这条记录的超率(计算公式:超限量/车货限重 ,暂定于此,具体修改问一下上面)
function getrate(value, row, index) {
    var weightlimitedValue = row.weightlimited;
    if(weightlimitedValue == 0) {
        return "--"
    } else {
        return Math.round(row.overlimited / weightlimitedValue * 10000) / 100;
    }

}
/**
 * 点击添加精检站治超数据
 */
Lscinfo.openAddLscinfo = function () {

    if(stationid.length == 0 || depts.length > 0) {
        layer.msg("请在站点一级操作");
        return;
    }

    var index = top.layer.open({
        type: 2,
        title: '添加固定治超站站治超数据',
        area: ['1000px', '550px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/lscinfo/lscinfo_add?stationid=' + stationid
    });
    this.layerIndex = index;
};

/**
 * 打开查看精检站治超数据详情
 */
Lscinfo.openLscinfoDetail = function (row) {
    //在详情页面,会根据精检站数据的状态,动态显示或隐藏相对应的按钮
    var index = top.layer.open({
        type: 2,
        title: '固定治超站治超数据详情',
        area: ['1000px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/lscinfo/lscinfo_recheck/' + row.id
    });
    this.layerIndex = index;
};


/**
 * 删除精检站治超数据
 */
Lscinfo.delete = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/lscinfo/delete", function (data) {
                Feng.success("删除成功!");
                Lscinfo.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("lscinfoId",Lscinfo.seItem.id);
            ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

//全局变量,用来到后台查询数据记录
var stationid = "";
var depts = "";

/**
 * 查询精检站治超数据列表
 */
Lscinfo.search = function () {
    var queryData = {};
    queryData['vehicleid'] = $("#vehicleid").val();
    queryData['fctime'] = $("#fctime").val();
    queryData['chekStatus'] = $("#chekStatus").val();

    queryData['depts'] = depts;
    queryData['stationid'] = stationid;

    Lscinfo.table.refresh({query: queryData});
};

//页面初始化
$(function () {

    //初始化状态值
    stationid = "";
    depts = "";

    var defaultColunms = Lscinfo.initColumn();
    var table = new BSTable(Lscinfo.id, "/lscinfo/list", defaultColunms);
    table.setPaginationType("server");
    Lscinfo.table = table.init({showColumns:false,showRefresh: false});

    //隐藏选择列
    $('#'+Lscinfo.id).on('all.bs.table', function (){
            $(".bs-checkbox").attr('style',"display:none");
        }
    );

    //初始化
    if(stationidValue == "") {
        Ning.Tree.setStree("#iNickTree", "fixedsite");
    } else {
        Ning.Tree.setOneTree("#iNickTree", "fixedsite", stationidValue);
        var node = {};
        node.type = "fixedsite";
        node.sitecode = stationidValue;
        update_right(node);
    }

    //给左边的tree添加点击事件
    Ning.Tree.bindOnClick(
        function(node) {
            update_right(node);
        }
    );

    //初始化权限
    Lscinfo.initPermission();
});

//用于展示检定证书
function showCertificate(uurl) {
    Ning.Tools.showPage(Feng.ctxPath + "/tempController/fileDownload?isDownload=0&fileUrl=" + uurl);
    return ;
}

//update_reight,根据左边的树目录,来动态更新右边的数据
function update_right(node){
    var index = layer.load(1, {
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });

    if (node.type != "fixedsite") { //如果当前选中的不是站点,则不显示检定证书
        $("#certificateDiv").hide();
    } else {

        //设置检定证书的点击事件和名字
        if(node.certificate == "") {
            $("#certificate").text("暂无");
            $("#certificate").attr("href", "javascript:;");
            $("#certificateValidityPeriod").text("暂无");
        } else {
            $("#certificate").text(node.certificate);
            $("#certificate").attr("href", 'javascript:showCertificate("'+node.certificateUrl+'");');
            $("#certificateValidityPeriod").text(node.certificateValidityPeriod);
        }
        $("#certificateDiv").show();

    }

    //左栏切换时清空搜索框的值
    $("#vehicleid").val('');
    $("#fctime").val('');
    $("#chekStatus").val('');

    if (node.type == "fixedsite") {
        stationid =  node.sitecode;
        depts = [];
    } else if(node.type == "dept"){
        depts = node.id;
        stationid = "";
    }

    //大屏数据获取
    var ajax = new $ax(Feng.ctxPath + "/lscinfo/getStatistics",
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

    //清空表格
    //$("#LscinfoTable").bootstrapTable('removeAll');

    //表格数据获取
    $("#LscinfoTable").bootstrapTable('refresh', {
        query: { depts: depts, stationid: stationid,vehicleid: "",fctime: ""}
    });

    //当右边表格加载完成之后,将加载动画关闭
    $("#LscinfoTable").on('load-success.bs.table',function(data){
        layer.close(index);
    }).on('load-error.bs.table', function (e, status) {
        layer.close(index);
    });

}

