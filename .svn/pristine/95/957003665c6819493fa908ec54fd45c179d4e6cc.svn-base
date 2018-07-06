/**
 * 初始化数据服务参数列表详情对话框
 */
var ParaListInfoDlg = {
    paraListInfoData : {}
};

/**
 * 清除数据
 */
ParaListInfoDlg.clearData = function() {
    this.paraListInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ParaListInfoDlg.set = function(key, val) {
    this.paraListInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ParaListInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ParaListInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/paraList");
	top.layer.close(winOpen.ParaList.layerIndex);
}

/**
 * 收集数据
 */
ParaListInfoDlg.collectData = function() {
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
ParaListInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paraList/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/paraList");
        winOpen.ParaList.table.refresh();
        top.layer.close(winOpen.ParaList.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.paraListInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ParaListInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paraList/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/paraList");
        winOpen.ParaList.table.refresh();
        top.layer.close(winOpen.ParaList.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.paraListInfoData);
    ajax.start();
}

$(function() {

});
