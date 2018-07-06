/**
 * 初始化抄告内容信息详情对话框
 */
var ReportinfoInfoDlg = {
    reportinfoInfoData : {}
};

/**
 * 清除数据
 */
ReportinfoInfoDlg.clearData = function() {
    this.reportinfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ReportinfoInfoDlg.set = function(key, val) {
    this.reportinfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ReportinfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ReportinfoInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/reportinfo");
	if (winOpen!=undefined){
		top.layer.close(winOpen.Reportinfo.layerIndex);
	}
}

/**
 * 收集数据
 */
ReportinfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('reportTitle')
    .set('reportContent')
}

/**
 * 提交添加
 */
ReportinfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/reportinfo/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/reportinfo");
        if (winOpen!=undefined){
        	winOpen.Reportinfo.table.refresh();
        	top.layer.close(winOpen.Reportinfo.layerIndex);
        }
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.reportinfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ReportinfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/reportinfo/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/reportinfo");
        if (winOpen!=undefined){
        	winOpen.Reportinfo.table.refresh();
        	top.layer.close(winOpen.Reportinfo.layerIndex);
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.reportinfoInfoData);
    ajax.start();
}

$(function() {

});
