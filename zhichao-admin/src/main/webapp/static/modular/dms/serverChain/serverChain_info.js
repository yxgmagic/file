/**
 * 初始化数据服务链与节点路径对应详情对话框
 */
var ServerChainInfoDlg = {
    serverChainInfoData : {}
};

/**
 * 清除数据
 */
ServerChainInfoDlg.clearData = function() {
    this.serverChainInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ServerChainInfoDlg.set = function(key, val) {
    this.serverChainInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ServerChainInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ServerChainInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/serverChain");
	top.layer.close(winOpen.ServerChain.layerIndex);
}

/**
 * 收集数据
 */
ServerChainInfoDlg.collectData = function() {
    this
    .set('id')
    .set('chainid')
    .set('serverid')
    .set('type')
    .set('nums');
}

/**
 * 提交添加
 */
ServerChainInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/serverChain/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/serverChain");
        winOpen.ServerChain.table.refresh();
        top.layer.close(winOpen.ServerChain.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.serverChainInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ServerChainInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/serverChain/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/serverChain");
        winOpen.ServerChain.table.refresh();
        top.layer.close(winOpen.ServerChain.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.serverChainInfoData);
    ajax.start();
}

$(function() {

});
