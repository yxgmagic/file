/**
 * 考核计划管理初始化
 */
var PeAssessplan = {
    id: "PeAssessplanTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PeAssessplan.initColumn = function () {
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
        PeAssessplan.openPeAssessplanDetail(row.id)
    },
    'click #btn_del': function(e, value, row, index) {
        PeAssessplan.delete(row.id)
    }
}
function detailFormatter(value, row, index) {
    return [
        "<button id='btn_edit' class='btn btn-primary btn-xs'>编辑</button>"+
        "<button id='btn_del' class='btn btn-primary btn-xs'>删除</button>"
    ].join('');
}


/**
 * 点击添加考核计划
 */
PeAssessplan.openAddPeAssessplan = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加考核计划',
        area: ['900px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/assessplan/peAssessplan_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看考核计划详情
 */
PeAssessplan.openPeAssessplanDetail = function (id) {
    var index = top.layer.open({
        type: 2,
        title: '考核计划详情',
        area: ['900px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/assessplan/peAssessplan_update/' + id
    });
    this.layerIndex = index;
};

/**
 * 删除考核计划
 */
PeAssessplan.delete = function (id) {
    var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/assessplan/delete", function (data) {
            Feng.success("删除成功!");
            PeAssessplan.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("peAssessplanId",id);
        ajax.start();
    }
    Feng.confirm("确认要删除吗?", operation);
};

/**
 * 查询考核计划列表
 */
PeAssessplan.search = function () {
    var queryData = {};
    queryData['assessName'] = $("#assessName").val();
    queryData['assessTime'] = $("#assessTime").val();
    PeAssessplan.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PeAssessplan.initColumn();
    var table = new BSTable(PeAssessplan.id, "/assessplan/list", defaultColunms);
    table.setPaginationType("client");
    PeAssessplan.table = table.init({showRefresh:false, showColumns:false});

    //设置搜索框为时间区间选择器
    Yang.Tools.date_interval('assessTime','datetime')

    //隐藏选择列
    $('#'+PeAssessplan.id).on('all.bs.table', function (){
        $(".bs-checkbox").attr('style',"display:none");
    });

});
