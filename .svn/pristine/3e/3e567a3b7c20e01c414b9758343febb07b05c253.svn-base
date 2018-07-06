/**
 * 初始化数据服务步骤管理详情对话框
 */
var ActionListInfoDlg = {
    actionListInfoData : {}
};

/**
 * 清除数据
 */
ActionListInfoDlg.clearData = function() {
    this.actionListInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ActionListInfoDlg.set = function(key, val) {
    this.actionListInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ActionListInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ActionListInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/actionList");
	top.layer.close(winOpen.ActionList.layerIndex);
}

/**
 * 收集数据
 */
ActionListInfoDlg.collectData = function() {
    this
    .set('id')
    .set('actalias')
    .set('actdesc')
    .set('actclass')
    .set('actmethod');
}

/**
 * 提交添加
 */
ActionListInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/actionList/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/actionList");
        winOpen.ActionList.table.refresh();
        top.layer.close(winOpen.ActionList.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.actionListInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ActionListInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/actionList/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/actionList");
        winOpen.ActionList.table.refresh();
        top.layer.close(winOpen.ActionList.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.actionListInfoData);
    ajax.start();
}

$(function() {

});
