/**
 * 站点考核排名初始化
 */
var SiteAssessInfoDlg = {
    id: "SiteAssessInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 关闭此对话框
 */
SiteAssessInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/assessplan?page=siteAssess");
	if (winOpen!==undefined){
		top.layer.close(winOpen.SiteAssess.layerIndex);
	}
}

/**
 * 初始化表格的列
 */
SiteAssessInfoDlg.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'id', visible: true, align: 'center', valign: 'middle',formatter: function (value, row, index) {return  index + 1}},
        {title: '站点名称', field: 'siteName', visible: true, align: 'center', valign: 'middle'},
        {title: '制度指标得分', field: 'institution', visible: true, align: 'center', valign: 'middle'},
        {title: '站点量化指标得分', field: 'quantitative', visible: true, align: 'center', valign: 'middle'},
        {title: '总分', field: 'score', visible: true, align: 'center', valign: 'middle'},
        {title: '排序', field: 'id', visible: true, align: 'center', valign: 'middle',formatter: function (value, row, index) {return  index + 1}}
    ];
};


$(function() {

    var defaultColunms = SiteAssessInfoDlg.initColumn();
    var table = new BSTable(SiteAssessInfoDlg.id, "/assessplan/assessinfo/"+assId+'/'+start+'/'+end, defaultColunms);
    table.setPaginationType("client");
    SiteAssessInfoDlg.table = table.init({showRefresh:false, showColumns:false});

    //隐藏选择列
    $('#'+SiteAssessInfoDlg.id).on('all.bs.table', function (){
        $(".bs-checkbox").attr('style',"display:none");
    });

});
