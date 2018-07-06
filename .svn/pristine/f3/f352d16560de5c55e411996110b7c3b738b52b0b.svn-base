/**
 * 初始化详情对话框
 */
var OefullLawdocInfoDlg = {
    oefullLawdocInfoData : {}
};

/**
 * 清除数据
 */
OefullLawdocInfoDlg.clearData = function() {
    this.oefullLawdocInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OefullLawdocInfoDlg.set = function(key, val) {
    this.oefullLawdocInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OefullLawdocInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OefullLawdocInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/oefullLawdoc");
	top.layer.close(winOpen.OefullLawdoc.layerIndex);
}

/**
 * 收集数据
 */
OefullLawdocInfoDlg.collectData = function() {
    this
    .set('id')
    .set('oefullid')
    .set('lawdocid')
    .set('lawdocjson')
    .set('inputjson')
    .set('procstatus')
    .set('ldtype');
}

/**
 * 提交添加
 */
OefullLawdocInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/oefullLawdoc/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/oefullLawdoc");
        winOpen.OefullLawdoc.table.refresh();
        top.layer.close(winOpen.OefullLawdoc.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.oefullLawdocInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
OefullLawdocInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/oefullLawdoc/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/oefullLawdoc");
        winOpen.OefullLawdoc.table.refresh();
        top.layer.close(winOpen.OefullLawdoc.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.oefullLawdocInfoData);
    ajax.start();
}

$(function() {

});
