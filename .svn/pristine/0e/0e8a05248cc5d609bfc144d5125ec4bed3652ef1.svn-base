/**
 * 考评指标数据查询管理初始化
 */
var PeSiteIndic = {
    id: "PeSiteIndicTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PeSiteIndic.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'id', visible: true, align: 'center', valign: 'middle',formatter: function (value, row, index) {return  index + 1}},
        {title: '站点', field: 'sitename', visible: true, align: 'center', valign: 'middle'},
        {title: '指标名称', field: 'indicatorName', visible: true, align: 'center', valign: 'middle'},
        {title: '时间', field: 'time', visible: true, align: 'center', valign: 'middle'},
        {title: '分数', field: 'scores', visible: true, align: 'center', valign: 'middle'},
        {title: 'url', field: 'url', visible: false, align: 'center', valign: 'middle'},
        {title: '备注', field: 'notes', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PeSiteIndic.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PeSiteIndic.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加考评指标数据查询
 */
PeSiteIndic.openAddPeSiteIndic = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加考评指标数据查询',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/peSiteIndic/peSiteIndic_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看考评指标数据查询详情
 */
PeSiteIndic.openPeSiteIndicDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '考评指标数据查询详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/peSiteIndic/peSiteIndic_update/' + PeSiteIndic.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除考评指标数据查询
 */
PeSiteIndic.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/peSiteIndic/delete", function (data) {
	            Feng.success("删除成功!");
	            PeSiteIndic.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("peSiteIndicId",PeSiteIndic.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询考评指标数据查询列表
 */
PeSiteIndic.search = function () {
    var queryData = {};
    queryData['assessName'] = $("#assessName").val();
    queryData['indicType'] = $("#indicType").val();
    queryData['indicName'] = $("#indicName").val();
    queryData['sitecode'] = $("#stationid").val()
    PeSiteIndic.table.refresh({query: queryData});
};

/**
 * 生成下拉选择框
 */
function createSelElement() {
    var assessList = Yang.Util.AJAX('assessplan/list')
    if (assessList !== -1){
        $('#assessName').empty().append("<option value>考核名称</option>")
        for (var i in assessList){
            $('#assessName').append("<option value="+assessList[i].id+">"+assessList[i].assessName+"</option>")
        }
    }

    var indicList = Yang.Util.AJAX('indicator/list')
    if (indicList !== -1){
        $('#indicName').empty().append("<option value>指标</option>")
        for (var i in indicList){
            $('#indicName').append("<option value="+indicList[i].id+">"+indicList[i].indicatorDescription+"</option>")
        }
    }
}


$(function () {
    var defaultColunms = PeSiteIndic.initColumn();
    var table = new BSTable(PeSiteIndic.id, "/peSiteIndic/list", defaultColunms);
    table.setPaginationType("client");
    PeSiteIndic.table = table.init({showRefresh:false, showColumns:false});

    $('#'+PeSiteIndic.id).on('all.bs.table', function (){
        $(".bs-checkbox").attr('style',"display:none");
    });

    //当鼠标在table移动的时候,隐藏抓拍图片
    $('#PeSiteIndicTable').mouseout(function (e) {
        $('#photo').hide()
    });

    //表格渲染完成
    $('#'+PeSiteIndic.id).on('load-success.bs.table',function(){

        $('#'+PeSiteIndic.id).on("click", function(e) {
            var selected = $('#' + this.id).bootstrapTable('getSelections');
                //给表格的每一行绑定事件,鼠标点击后根据list中的url加入到鼠标右下角显示
                var pos = Yang.Tools.position(e);
                $('#photo').css('left',pos[0]).css('top', pos[1])
                $('#image').attr('src',selected[0].url)
                $('#photo').show()
        });
    });

    //创建下拉搜索条件
    createSelElement();
});
