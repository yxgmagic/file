/**
 * 指标管理管理初始化
 */
var Indicator = {
    id: "IndicatorTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Indicator.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: ' 序号', field: 'id', visible: true, align: 'center', valign: 'middle',formatter: function (value, row, index) {return  index + 1}},
            {title: '指标名称', field: 'indicatorName', visible: true, align: 'center', valign: 'middle'},
            {title: '指标类别', field: 'indicatorCategory', visible: true, align: 'center', valign: 'middle'},
            {title: '指标要素', field: 'indicatorDescription', visible: true, align: 'center', valign: 'middle'},
            {title: '评分规则', field: 'judgeRules', visible: true, align: 'center', valign: 'middle'},
            {title: '分值', field: 'score', visible: true, align: 'center', valign: 'middle'},
            {field: 'operate', title: '操作', width: '140px', events: openDetail,  formatter: detailFormatter}
    ];
};

window.openDetail = {
    'click #btn_edit': function(e, value, row, index) {
        Indicator.openIndicatorDetail(row.id)
    },
    'click #btn_del': function(e, value, row, index) {
        Indicator.delete(row.id)
    }
}
function detailFormatter(value, row, index) {
    return [
        "<button id='btn_edit' class='btn btn-primary btn-xs'>编辑</button>"+
        "<button id='btn_del' class='btn btn-primary btn-xs'>删除</button>"
    ].join('');
}


/**
 * 点击添加指标
 */
Indicator.openAddIndicator = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加指标',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/indicator/indicator_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看指标详情
 */
Indicator.openIndicatorDetail = function (id) {
        var index = top.layer.open({
            type: 2,
            title: '指标详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/indicator/indicator_update/' + id
        });
        this.layerIndex = index;
};

/**
 * 删除指标
 */
Indicator.delete = function (id) {
    var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/indicator/delete/" + id, function (data) {
            Feng.success("删除成功!");
            Indicator.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    }
    Feng.confirm("确认要删除吗?", operation);
};

/**
 * 查询指标列表
 */
Indicator.search = function () {
    var queryData = {};
    queryData['indicName'] = $("#indicName").val();
    queryData['indicType'] = $("#indicType").val();
    Indicator.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Indicator.initColumn();
    var table = new BSTable(Indicator.id, "/indicator/list", defaultColunms);
    table.setPaginationType("client");
    Indicator.table = table.init({showRefresh:false, showColumns:false});

    //隐藏选择列
    $('#'+Indicator.id).on('all.bs.table', function (){
        $(".bs-checkbox").attr('style',"display:none");
    });
});
