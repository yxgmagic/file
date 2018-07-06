/**
 * 检测记录管理初始化
 */
var Hlcminfo = {
    id: "HlcminfoTable",//表格id
    zTreeArea : null,

    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Hlcminfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'indexid', visible: true, align: 'center', valign: 'middle',
            formatter: function (value, row, index) {
                var pageSize=$('#HlcminfoTable').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                var pageNumber=$('#HlcminfoTable').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;
            }
        },


        {title: '站点编号', field: 'stationid', visible: false, align: 'center', valign: 'middle'},
        {title: '检测站点', field: 'sitename', visible: true, align: 'center', valign: 'middle'},
        {title: '检测时间', field: 'fctime', visible: true, align: 'center', valign: 'middle'},
        {title: '车牌号', field: 'vehicleid', visible: true, align: 'center', valign: 'middle'},
        {title: '车轴', field: 'axlesum', visible: true, align: 'center', valign: 'middle'},
        {title: '车货总重(kg)', field: 'fctotalweight', visible: true, align: 'center', valign: 'middle'},
        {title: '车货限重(kg)', field: 'ratedloading', visible: true, align: 'center', valign: 'middle'},
        {title: '超限量(kg)', field: 'overlimited', visible: true, align: 'center', valign: 'middle'},
        {title: '超限率(%)', field: 'overrate', visible: true, align: 'center', valign: 'middle'},
        // {title: '是否超限', field: 'isoverlimit', visible: true, align: 'center', valign: 'middle'},
        {field: 'operate', title: '操作', align: 'center', valign: 'middle', events: openDetail,  formatter: detailFormatter}
    ];
};

window.openDetail = {
    'click #btn_detail': function(e, value, row, index) {

        Hlcminfo.openHlcminfoDetail(row.id,row.sitetype)
    },


}
function detailFormatter(value, row, index) {
    return [
        "<button id='btn_detail' class='btn btn-primary btn-xs'>查看</button>"
    ].join('');
}
Hlcminfo.showAreaSelectTree = function() {
    Feng.showInputTree('areaName','parentAreaMenu');

}
/**
 * 检查是否选中
 */
Hlcminfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Hlcminfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加检测记录
 */
Hlcminfo.openAddHlcminfo = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加检测记录',
        area: ['1000px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/hlcminfo/hlcminfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看检测记录详情
 */
Hlcminfo.openHlcminfoDetail = function (iid,ttype) {

        var index = top.layer.open({
            type: 2,
            title: '检测记录详情',
            area: ['1000px', '620px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/hlcminfo/detail/' + iid+'_'+ ttype
        });
        this.layerIndex = index;

};

/**
 * 删除检测记录
 */
Hlcminfo.delete = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/hlcminfo/delete", function (data) {
                Feng.success("删除成功!");
                Hlcminfo.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("hlcminfoId",Hlcminfo.seItem.id);
            ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};
Hlcminfo.onClickArea = function(e, treeId, treeNode) {
    $("#areaName").attr("value", Hlcminfo.zTreeArea.getSelectedVal());
    $("#areacode").attr("value", treeNode.areaCode);
}
/**
 * 查询检测记录列表
 */
Hlcminfo.search = function () {
    var queryData = {};
    queryData['sitename'] = $("#sitename").val();
    queryData['areacode'] = $("#areacode").val();

    if($("#timeArea").length > 0) {
        var temArea = $("#timeArea").val();
        var temparr = temArea.split('~');

        queryData['begindt'] = temparr[0];
        queryData['enddt'] = temparr[1];

    }

    // queryData['begindt'] = $("#begindt").val();
    // queryData['enddt'] = $("#enddt").val();

    queryData['sitetype'] = $("#sitetype").val();
    queryData['checkmode'] = $("#checkmode").val();
    queryData['vehicleid'] = $("#vehicleid").val();
    Hlcminfo.table.refresh({query: queryData});
};
//当站点类别被改变的时候,改变右边的站点内容
$("#sitetype").change(function(){
    var selectObj = document.getElementById("sitename");
    if($("#sitetype").val() == 1) {
        Ning.Tree.createTreeSelectt(selectObj, "presite", function() {});
    } else if ($("#sitetype").val() == 3) {
        Ning.Tree.createTreeSelectt(selectObj, "fixedsite", function() {});
    } else {
        Ning.Tree.createTreeSelectt(selectObj, "corp", function() {});
    }
});

$(function () {
    var ztreeArea = new $ZTree("parentAreaMenuTree", "/area/tree");
    ztreeArea.bindOnClick(Hlcminfo.onClickArea);
    ztreeArea.init();
    Hlcminfo.zTreeArea = ztreeArea;

    var defaultColunms = Hlcminfo.initColumn();
    var table = new BSTable(Hlcminfo.id, "/hlcminfo/list", defaultColunms);
    table.setPaginationType("server");
    Hlcminfo.table = table.init({showColumns:false,showRefresh: false});

    //隐藏选择列
    $('#'+Hlcminfo.id).on('all.bs.table', function (){
            $(".bs-checkbox").attr('style',"display:none");
        }
    );
});
