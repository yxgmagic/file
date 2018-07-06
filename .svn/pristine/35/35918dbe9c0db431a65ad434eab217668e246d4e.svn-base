/**
 * 初始化数据服务终端与链关系配置详情对话框
 */
var ServerAuthChainInfoDlg = {
    serverAuthChainInfoData : {}
};

/**
 * 清除数据
 */
ServerAuthChainInfoDlg.clearData = function() {
    this.serverAuthChainInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ServerAuthChainInfoDlg.set = function(key, val) {
    this.serverAuthChainInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ServerAuthChainInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ServerAuthChainInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/serverAuthChain");
	top.layer.close(winOpen.ServerAuthChain.layerIndex);
}

/**
 * 收集数据
 */
ServerAuthChainInfoDlg.collectData = function() {
    this
    .set('id')
    .set('chainid')
    .set('authid');
}

/**
 * 提交添加
 */
ServerAuthChainInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/serverAuthChain/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/serverAuthChain");
        winOpen.ServerAuthChain.table.refresh();
        top.layer.close(winOpen.ServerAuthChain.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.serverAuthChainInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ServerAuthChainInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/serverAuthChain/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/serverAuthChain");
        winOpen.ServerAuthChain.table.refresh();
        top.layer.close(winOpen.ServerAuthChain.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.serverAuthChainInfoData);
    ajax.start();
}

$(function() {

});
