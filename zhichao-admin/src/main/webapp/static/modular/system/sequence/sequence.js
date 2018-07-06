/**
 * 编码规则器管理初始化
 */
var Sequence = {
    id: "SequenceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Sequence.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},		
        {  
			field : 'Number',  
			title : '序号',  
			formatter: function (value, row, index) {  
				

				return  index + 1;  
			}
            
        },    
            {title: '名称', field: 'seqname1', visible: true, align: 'center', valign: 'middle'},
            {title: '编码序列类型', field: 'seqtype', visible: true, align: 'center', valign: 'middle'},
            {title: '字符串', field: 'seqstr', visible: true, align: 'center', valign: 'middle'},
            {title: '流水生成日期', field: 'seqdate', visible: true, align: 'center', valign: 'middle'},
            {title: '当前最大流水号', field: 'seqmax', visible: true, align: 'center', valign: 'middle'},
            {title: '流水号长度', field: 'seqlen', visible: true, align: 'center', valign: 'middle'},
            {title: '取数列', field: 'seqcol', visible: true, align: 'center', valign: 'middle'},
            {title: '取数表', field: 'seqtab', visible: true, align: 'center', valign: 'middle'},
            {title: '站点/部门号', field: 'seqdept', visible: true, align: 'center', valign: 'middle'},
            {title: '车道号', field: 'roadnum', visible: true, align: 'center', valign: 'middle'},
            {title: '编码单号', field: 'sequence', visible: true, align: 'center', valign: 'middle'},
            {title: '流水号是否连续', field: 'isseriesname', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Sequence.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Sequence.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加编码规则器
 */
Sequence.openAddSequence = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加编码规则器',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sequence/sequence_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看编码规则器详情
 */
Sequence.openSequenceDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '编码规则器详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sequence/sequence_update/' + Sequence.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除编码规则器
 */
Sequence.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/sequence/delete", function (data) {
	            Feng.success("删除成功!");
	            Sequence.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("sequenceId",Sequence.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询编码规则器列表
 */
Sequence.search = function () {
    var queryData = {};
    queryData['seqname'] = $("#seqname").val();
    queryData['seqtype'] = $("#seqtype").val();
    queryData['seqcol'] = $("#seqcol").val();
    queryData['seqtab'] = $("#seqtab").val();
    queryData['seqdept'] = $("#seqdept").val();
    Sequence.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Sequence.initColumn();
    var table = new BSTable(Sequence.id, "/sequence/list", defaultColunms);
    table.setPaginationType("client");
    Sequence.table = table.init();
});
