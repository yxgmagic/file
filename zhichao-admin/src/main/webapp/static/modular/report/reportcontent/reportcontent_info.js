/**
 * 初始化抄告报表关联详情对话框
 */
var ReportcontentInfoDlg = {
    reportcontentInfoData : {}
};

/**
 * 清除数据
 */
ReportcontentInfoDlg.clearData = function() {
    this.reportcontentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ReportcontentInfoDlg.set = function(key, val) {
    this.reportcontentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ReportcontentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ReportcontentInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/reportcontent");
	if (winOpen!=undefined){
		top.layer.close(winOpen.Reportcontent.layerIndex);
	}
}

/**
 * 收集数据
 */
ReportcontentInfoDlg.collectData = function() {
    this
    .set('id')
    .set('reportId')
    .set('reportInfoId');
}

/**
 * 提交添加
 */
ReportcontentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/reportcontent/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/reportcontent");
        if (winOpen!=undefined){
        	winOpen.Reportcontent.table.refresh();
        	top.layer.close(winOpen.Reportcontent.layerIndex);
        }
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.reportcontentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ReportcontentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/reportcontent/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/reportcontent");
        if (winOpen!=undefined){
        	winOpen.Reportcontent.table.refresh();
        	top.layer.close(winOpen.Reportcontent.layerIndex);
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.reportcontentInfoData);
    ajax.start();
}

$(function() {

});
