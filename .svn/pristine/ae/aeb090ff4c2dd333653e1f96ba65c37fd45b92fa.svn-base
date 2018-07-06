/**
 * 初始化考评指标数据查询详情对话框
 */
var PeSiteIndicInfoDlg = {
    peSiteIndicInfoData : {}
};

/**
 * 清除数据
 */
PeSiteIndicInfoDlg.clearData = function() {
    this.peSiteIndicInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PeSiteIndicInfoDlg.set = function(key, val) {
    this.peSiteIndicInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PeSiteIndicInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PeSiteIndicInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/peSiteIndic");
	if (winOpen!=undefined){
		top.layer.close(winOpen.PeSiteIndic.layerIndex);
	}
}

/**
 * 收集数据
 */
PeSiteIndicInfoDlg.collectData = function() {
    this
    .set('id')
    .set('siteId')
    .set('indicId')
    .set('startTime')
    .set('endTime')
    .set('notes');
}

/**
 * 提交添加
 */
PeSiteIndicInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/peSiteIndic/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/peSiteIndic");
        if (winOpen!=undefined){
        	winOpen.PeSiteIndic.table.refresh();
        	top.layer.close(winOpen.PeSiteIndic.layerIndex);
        }
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.peSiteIndicInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PeSiteIndicInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/peSiteIndic/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/peSiteIndic");
        if (winOpen!=undefined){
        	winOpen.PeSiteIndic.table.refresh();
        	top.layer.close(winOpen.PeSiteIndic.layerIndex);
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.peSiteIndicInfoData);
    ajax.start();
}

$(function() {

});
