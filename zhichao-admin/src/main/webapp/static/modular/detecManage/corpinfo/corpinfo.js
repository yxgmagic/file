/**
 * 源头企业检测数据管理管理初始化
 */
var Corpinfo = {
    id: "CorpinfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Corpinfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true,visible: false},
        {title: '序号', field: 'id', visible: true, align: 'center', valign: 'middle',formatter: function (value, row, index) {return Yang.Tools.serial(Corpinfo.id, index);}},
        {title: '源头企业', field: 'corpname', visible: true, align: 'center', valign: 'middle'},
        {title: '车牌号码', field: 'vehicleid', visible: true, align: 'center', valign: 'middle'},
        {title: '货车类型', field: 'vehicletype', visible: true, align: 'center', valign: 'middle'},
        {title: '轴数', field: 'axlesum', visible: true, align: 'center', valign: 'middle'},
        {title: '核定载重（kg）', field: 'approvedLoad', visible: true, align: 'center', formatter: approvedLoad},
        {title: '超限量（kg）', field: 'overlimited', visible: true, align: 'center', valign: 'middle'},
        {title: '超率（%）', field: 'superRate', visible: true, align: 'center', formatter: superRate},
        {title: '检测时间', field: 'corptime', visible: true, align: 'center', valign: 'middle'},
        {title: '总重', field: 'totalweight', visible: false, align: 'center', valign: 'middle'},
        {field: 'operate', title: '操作', width: '80px', events: openDetail,  formatter: detailFormatter}
    ];
};

window.openDetail = {
    'click .btn': function(e, value, row, index) {
        Corpinfo.openCorpinfoDetail(row.id)
    }
}
function detailFormatter(value, row, index) {
    return [
        "<button id='btn_detail' class='btn btn-primary btn-xs'>详情</button>",
    ].join('');
}
//核定载重
function approvedLoad(value, row, index) {
    return [ row.totalweight - row.overlimited ].join('');
}
//超率
function superRate(value, row, index) {
    return[Math.round(row.overlimited / (row.totalweight - row.overlimited) * 10000) / 100 ].join('');
}

/**
 * 打开查看源头企业检测数据管理详情
 */
Corpinfo.openCorpinfoDetail = function (id) {
    var index = top.layer.open({
        type: 2,
        title: '源头企业检测数据管理详情',
        area: ['1000px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/corpinfo/corpinfo_update/' + id
    });
    this.layerIndex = index;
};

/**
 * 查询源头企业检测数据管理列表
 */
Corpinfo.search = function () {
    var queryData = {};
    queryData['areacode'] = $("#areacode").val();
    queryData['vehicleid'] = $("#vehicleid").val();
    queryData['corptime'] = $("#corptime").val();
    Corpinfo.table.refresh({query: queryData});
};

Ning.Tree.bindOnClick(
    function(node) {
        update_right(node);
    }
);

function update_right(node){

    var index = layer.load(1, {
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });

    //左栏切换时清空搜索框的值
    $(".keyword").val('');

    //初始化状态值
    var areacode = node.areaCode;
    if (node.type == "corp") {
        areacode =  node.corpcode;
    }
    $("#areacode").val(areacode);

    //大屏数据获取
    var ajax = new $ax(Feng.ctxPath + "/corpinfo/calculateVehicle", function (data) {
        var text;
        for (var i = 0; i < data.length; i++) {
            text = data[i] != null && data[i] != 0 && data[i] != '' ? data[i] : '--';
            $("#right_top .col-md-3")[i].innerHTML = text;
        }
    }, function (data) {
        Feng.error("加载数据异常,请重试。");
    });
    ajax.set("areacode",areacode);
    ajax.start();

    //表格数据获取
    $("#CorpinfoTable").bootstrapTable('refresh', {
        query: { areacode: areacode, vehicleid: null, corptime: null}
    });

    layer.close(index);
}


$(function () {
    Ning.Tree.setStree("#iNickTree", "corp");

    var defaultColunms = Corpinfo.initColumn();
    var table = new BSTable(Corpinfo.id, "/corpinfo/list", defaultColunms);
    table.setPaginationType("server");
    Corpinfo.table = table.init({showRefresh:false, showColumns:false});
});