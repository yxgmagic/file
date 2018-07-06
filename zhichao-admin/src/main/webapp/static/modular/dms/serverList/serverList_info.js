/**
 * 初始化节点路径列表详情对话框
 */
var ServerListInfoDlg = {
    serverListInfoData : {}
};

/**
 * 清除数据
 */
ServerListInfoDlg.clearData = function() {
    this.serverListInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ServerListInfoDlg.set = function(key, val) {
    this.serverListInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ServerListInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ServerListInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/serverList");
	top.layer.close(winOpen.ServerList.layerIndex);
}

/**
 * 收集数据
 */
ServerListInfoDlg.collectData = function() {
    this
    .set('id')
    .set('sysalias')
    .set('sysdesc')
    .set('systype')
    .set('syssource')
    .set('host')
    .set('port')
    .set('dbname')
    .set('dbdriver')
    .set('dbuser')
    .set('dbpassword');
}

/**
 * 提交添加
 */
ServerListInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/serverList/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/serverList");
        winOpen.ServerList.table.refresh();
        top.layer.close(winOpen.ServerList.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.serverListInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ServerListInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/serverList/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/serverList");
        winOpen.ServerList.table.refresh();
        top.layer.close(winOpen.ServerList.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.serverListInfoData);
    ajax.start();
}

$(function() {

});
