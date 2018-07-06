/**
 * 初始化数据服务器参数管理详情对话框
 */
var ServerParaInfoDlg = {
    serverParaInfoData : {}
};

/**
 * 清除数据
 */
ServerParaInfoDlg.clearData = function() {
    this.serverParaInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ServerParaInfoDlg.set = function(key, val) {
    this.serverParaInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ServerParaInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ServerParaInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/serverPara");
	top.layer.close(winOpen.ServerPara.layerIndex);
}

/**
 * 收集数据
 */
ServerParaInfoDlg.collectData = function() {
    this
    .set('id')
    .set('authid')
    .set('paraid');
}

/**
 * 提交添加
 */
ServerParaInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/serverPara/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/serverPara");
        winOpen.ServerPara.table.refresh();
        top.layer.close(winOpen.ServerPara.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.serverParaInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ServerParaInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/serverPara/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/serverPara");
        winOpen.ServerPara.table.refresh();
        top.layer.close(winOpen.ServerPara.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.serverParaInfoData);
    ajax.start();
}

$(function() {

});
