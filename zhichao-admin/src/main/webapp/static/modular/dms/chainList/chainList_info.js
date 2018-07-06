/**
 * 初始化数据服务链定义详情对话框
 */
var ChainListInfoDlg = {
    chainListInfoData : {}
};

/**
 * 清除数据
 */
ChainListInfoDlg.clearData = function() {
    this.chainListInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ChainListInfoDlg.set = function(key, val) {
    this.chainListInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ChainListInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ChainListInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/chainList");
	top.layer.close(winOpen.ChainList.layerIndex);
}

/**
 * 收集数据
 */
ChainListInfoDlg.collectData = function() {
    this
    .set('id')
    .set('chainalias')
    .set('chaindesc')
    .set('inchainclass')
    .set('outchainclass');
}

/**
 * 提交添加
 */
ChainListInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/chainList/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/chainList");
        winOpen.ChainList.table.refresh();
        top.layer.close(winOpen.ChainList.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.chainListInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ChainListInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/chainList/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/chainList");
        winOpen.ChainList.table.refresh();
        top.layer.close(winOpen.ChainList.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.chainListInfoData);
    ajax.start();
}

$(function() {

});
