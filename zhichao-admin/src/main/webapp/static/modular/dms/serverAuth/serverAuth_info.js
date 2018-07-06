/**
 * 初始化数据服务注册详情对话框
 */
var ServerAuthInfoDlg = {
    serverAuthInfoData : {}
};

/**
 * 清除数据
 */
ServerAuthInfoDlg.clearData = function() {
    this.serverAuthInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ServerAuthInfoDlg.set = function(key, val) {
    this.serverAuthInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ServerAuthInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ServerAuthInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/serverAuth");
	top.layer.close(winOpen.ServerAuth.layerIndex);
}

/**
 * 收集数据
 */
ServerAuthInfoDlg.collectData = function() {
    this
    .set('id')
    .set('alias')
    .set('aliasName')
    .set('url')
    .set('computerName')
    .set('diskno')
    .set('macno')
    .set('begindt')
    .set('enddt')
    .set('license')
    .set('cdkey')
    .set('status')
    .set('auditStatus')
    .set('usercode')
    .set('userpassword')
    .set('creatdt')
    .set('creatid')
    .set('auditdt')
    .set('auditid')
    .set('deptid')
    .set('updt')
    .set('upid');
}

/**
 * 提交添加
 */
ServerAuthInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/serverAuth/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/serverAuth");
        winOpen.ServerAuth.table.refresh();
        top.layer.close(winOpen.ServerAuth.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.serverAuthInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ServerAuthInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/serverAuth/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/serverAuth");
        winOpen.ServerAuth.table.refresh();
        top.layer.close(winOpen.ServerAuth.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.serverAuthInfoData);
    ajax.start();
}

$(function() {

});
