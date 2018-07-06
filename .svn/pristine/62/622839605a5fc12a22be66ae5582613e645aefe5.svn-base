/**
 * 初始化数据服务器日志上传管理详情对话框
 */
var ServerLogInfoDlg = {
    serverLogInfoData : {}
};

/**
 * 清除数据
 */
ServerLogInfoDlg.clearData = function() {
    this.serverLogInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ServerLogInfoDlg.set = function(key, val) {
    this.serverLogInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ServerLogInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ServerLogInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/serverLog");
	top.layer.close(winOpen.ServerLog.layerIndex);
}

/**
 * 收集数据
 */
ServerLogInfoDlg.collectData = function() {
    this
    .set('id')
    .set('authid')
    .set('classname')
    .set('method')
    .set('logtime')
    .set('logtext');
}

/**
 * 提交添加
 */
ServerLogInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/serverLog/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/serverLog");
        winOpen.ServerLog.table.refresh();
        top.layer.close(winOpen.ServerLog.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.serverLogInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ServerLogInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/serverLog/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/serverLog");
        winOpen.ServerLog.table.refresh();
        top.layer.close(winOpen.ServerLog.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.serverLogInfoData);
    ajax.start();
}

$(function() {

});
