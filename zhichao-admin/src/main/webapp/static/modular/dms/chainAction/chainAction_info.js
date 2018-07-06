/**
 * 初始化数据服务链步骤关系维护详情对话框
 */
var ChainActionInfoDlg = {
    chainActionInfoData : {}
};

/**
 * 清除数据
 */
ChainActionInfoDlg.clearData = function() {
    this.chainActionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ChainActionInfoDlg.set = function(key, val) {
    this.chainActionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ChainActionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ChainActionInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/chainAction");
	top.layer.close(winOpen.ChainAction.layerIndex);
}

/**
 * 收集数据
 */
ChainActionInfoDlg.collectData = function() {
    this
    .set('id')
    .set('nums')
    .set('serverchainid')
    .set('actid');
}

/**
 * 提交添加
 */
ChainActionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/chainAction/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/chainAction");
        winOpen.ChainAction.table.refresh();
        top.layer.close(winOpen.ChainAction.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.chainActionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ChainActionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/chainAction/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/chainAction");
        winOpen.ChainAction.table.refresh();
        top.layer.close(winOpen.ChainAction.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.chainActionInfoData);
    ajax.start();
}

$(function() {

});
