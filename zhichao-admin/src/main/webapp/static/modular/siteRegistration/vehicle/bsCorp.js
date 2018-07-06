/**
 * 源头企业管理管理初始化
 */
var BsCorp = {
    id: "BsCorpTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BsCorp.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        	{title: '序号', field: 'corpid', visible: true, align: 'center', formatter: function (value, row, index) {return  index + 1}},
            {title: '营业执照', field: 'corpcode', visible: true, align: 'center', valign: 'middle'},
            {title: '企业名称', field: 'corpname', visible: true, align: 'center', valign: 'middle'},
            {title: '所属区域', field: 'areaname', visible: true, align: 'center', valign: 'middle'}
    ];
};


/**
 * 检查是否选中
 */
BsCorp.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BsCorp.seItem = selected[0];
        return true;
    }
};

/**
 * 选择源头企业
 */
function determine(corp) {
    	
    	parent.$("#corpcode").val(corp.corpcode).change();
    	parent.$("#corp_code").val(corp.corpname).change();
    	
    	var index = parent.layer.getFrameIndex(window.name);//获取子窗口索引
        parent.layer.close(index);
        
};

/**
 * 查询源头企业管理列表
 */
BsCorp.search = function () {
	var queryData = {};
	queryData['corpname'] = $("#corpname").val();
	queryData['areacode'] = $("#areacode").val();
	BsCorp.table.refresh({query: queryData});
	
	Feng.initValidator()
};

$(function () {
    var defaultColunms = BsCorp.initColumn();
    var table = new BSTable(BsCorp.id, "/bsCorp/list", defaultColunms);
    table.setPaginationType("client");
    BsCorp.table = table.init({showRefresh:false, showColumns:false});

    //选择源头企业的点击事件
    $('#BsCorpTable').on('click-row.bs.table', function (e, row, element) {  
    	determine(row);
    });  
    
    //隐藏选择列
    $('#'+BsCorpTable.id).on('all.bs.table', function (){
    	$(".bs-checkbox").attr('style',"display:none");
    });
});