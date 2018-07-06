/**
 * 初始化数据服务步骤参数详情对话框
 */
var ActionParaInfoDlg = {
    actionParaInfoData : {}
};

/**
 * 清除数据
 */
ActionParaInfoDlg.clearData = function() {
    this.actionParaInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ActionParaInfoDlg.set = function(key, val) {
    this.actionParaInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ActionParaInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ActionParaInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/actionPara");
	top.layer.close(winOpen.ActionPara.layerIndex);
}

/**
 * 收集数据
 */
ActionParaInfoDlg.collectData = function() {
    this
    .set('id')
    .set('actid')
    .set('paratype')
    .set('paraid')
    .set('paracode')
    .set('paravalue');
}

/**
 * 提交添加
 */
ActionParaInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/actionPara/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/actionPara");
        winOpen.ActionPara.table.refresh();
        top.layer.close(winOpen.ActionPara.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.actionParaInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ActionParaInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/actionPara/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/actionPara");
        winOpen.ActionPara.table.refresh();
        top.layer.close(winOpen.ActionPara.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.actionParaInfoData);
    ajax.start();
}

$(function() {

});
