/**
 * 站点考核排名初始化
 */
var SiteAssess = {
    id: "SiteAssessTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SiteAssess.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'id', visible: true, align: 'center', valign: 'middle',formatter: function (value, row, index) {return  index + 1}},
        {title: '考核名称', field: 'assessName', visible: true, align: 'center', valign: 'middle'},
        {title: '开始时间', field: 'startTime', visible: true, align: 'center', valign: 'middle'},
        {title: '结束时间', field: 'endTime', visible: true, align: 'center', valign: 'middle'},
        {title: '考评模板对象数', field: 'objNum', visible: true, align: 'center', valign: 'middle'},
        {title: '指标数', field: 'indicNum', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'notes', visible: true, align: 'center', valign: 'middle'},
        {field: 'operate', title: '操作', width: '140px', events: openDetail,  formatter: detailFormatter}

    ];
};

window.openDetail = {
    'click #btn_edit': function(e, value, row, index) {
        SiteAssess.openSiteAssessDetail(row.id, row.startTime, row.endTime)
    },
    'click #btn_del': function(e, value, row, index) {
        SiteAssess.delete(row.id)
    }
}
function detailFormatter(value, row, index) {
    return [
        "<button id='btn_edit' class='btn btn-primary btn-xs'>查看</button>"+
        "<button id='btn_del' class='btn btn-primary btn-xs'>删除</button>"
    ].join('');
}

/**
 * 打开查看考核计划详情
 */
SiteAssess.openSiteAssessDetail = function (id,start,end) {
    var index = top.layer.open({
        type: 2,
        title: '考核详情',
        area: ['900px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/assessplan/siteAssess_info/' + id + '/' + start + '/' + end
    });
    this.layerIndex = index;
};

/**
 * 删除考核计划
 */
SiteAssess.delete = function (id) {
    var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/assessplan/delete", function (data) {
            Feng.success("删除成功!");
            SiteAssess.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("SiteAssessId",id);
        ajax.start();
    }
    Feng.confirm("确认要删除吗?", operation);
};

/**
 * 查询站点考核列表
 */
SiteAssess.search = function () {
    var queryData = {};
    queryData['assessName'] = $("#assessName").val();
    queryData['assessTime'] = $("#assessTime").val();
    SiteAssess.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = SiteAssess.initColumn();
    var table = new BSTable(SiteAssess.id, "/assessplan/list", defaultColunms);
    table.setPaginationType("client");
    SiteAssess.table = table.init({showRefresh:false, showColumns:false});

    //设置搜索框为时间区间选择器
    Yang.Tools.date_interval('assessTime','datetime')

    //隐藏选择列
    $('#'+SiteAssess.id).on('all.bs.table', function (){
        $(".bs-checkbox").attr('style',"display:none");
    });

});
