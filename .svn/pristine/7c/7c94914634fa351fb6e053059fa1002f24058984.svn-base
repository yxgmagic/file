/**
 * 初始化抄告信息反馈详情对话框
 */
var ReportsendInfoDlg = {
    reportsendInfoData : {}
};

/**
 * 清除数据
 */
ReportsendInfoDlg.clearData = function() {
    this.reportsendInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ReportsendInfoDlg.set = function(key, val) {
    this.reportsendInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ReportsendInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ReportsendInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/reportsend");
	if (winOpen!=undefined){
		top.layer.close(winOpen.Reportsend.layerIndex);
	}
}

/**
 * 收集数据
 */
ReportsendInfoDlg.collectData = function() {
    this
    .set('id')
    .set('reportId')
    .set('departmentId')
    .set('feedback')
    .set('remarks')
    .set('addtime');
}

/**
 * 提交添加
 */
ReportsendInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/reportsend/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/reportsend");
        if (winOpen!=undefined){
        	winOpen.Reportsend.table.refresh();
        	top.layer.close(winOpen.Reportsend.layerIndex);
        }
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.reportsendInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ReportsendInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/reportsend/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/reportsend");
        if (winOpen!=undefined){
        	winOpen.Reportsend.table.refresh();
        	top.layer.close(winOpen.Reportsend.layerIndex);
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.reportsendInfoData);
    ajax.start();
}

$(function() {

});
