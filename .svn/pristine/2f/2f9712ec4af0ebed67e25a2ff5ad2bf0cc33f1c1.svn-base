/**
 * 初始化详情对话框
 */
var SeqGenerateInfoDlg = {
    seqGenerateInfoData : {}
};

/**
 * 清除数据
 */
SeqGenerateInfoDlg.clearData = function() {
    this.seqGenerateInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SeqGenerateInfoDlg.set = function(key, val) {
    this.seqGenerateInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SeqGenerateInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SeqGenerateInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/seqGenerate");
	top.layer.close(winOpen.SeqGenerate.layerIndex);
}

/**
 * 收集数据
 */
SeqGenerateInfoDlg.collectData = function() {
    this
    .set('id')
    .set('seqtype')
    .set('seqnum')
    .set('seqcol')
    .set('seqlen');
}

/**
 * 提交添加
 */
SeqGenerateInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/seqGenerate/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/seqGenerate");
        winOpen.SeqGenerate.table.refresh();
        top.layer.close(winOpen.SeqGenerate.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.seqGenerateInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SeqGenerateInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/seqGenerate/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/seqGenerate");
        winOpen.SeqGenerate.table.refresh();
        top.layer.close(winOpen.SeqGenerate.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.seqGenerateInfoData);
    ajax.start();
}

$(function() {

});
