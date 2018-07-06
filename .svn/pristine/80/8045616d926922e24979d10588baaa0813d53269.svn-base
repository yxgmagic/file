/**
 * 初始化系统参数表详情对话框
 */
var ParaInfoDlg = {
    paraInfoData : {}
};

/**
 * 清除数据
 */
ParaInfoDlg.clearData = function() {
    this.paraInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ParaInfoDlg.set = function(key, val) {
    this.paraInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ParaInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ParaInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/para");
	top.layer.close(winOpen.Para.layerIndex);
}

/**
 * 收集数据
 */
ParaInfoDlg.collectData = function() {
    this
    .set('id')
    .set('paracode')
    .set('paraname')
    .set('paravalue')
    .set('ramark');
}

/**
 * 提交添加
 */
ParaInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/para/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/para");
        winOpen.Para.table.refresh();
        top.layer.close(winOpen.Para.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.paraInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ParaInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/para/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/para");
        winOpen.Para.table.refresh();
        top.layer.close(winOpen.Para.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.paraInfoData);
    ajax.start();
}

$(function() {

});
