/**
 * 初始化区域站点权限详情对话框
 */
var DeptAreasiteInfoDlg = {
    deptAreasiteInfoData : {},zTreeArea : null
};

/**
 * 清除数据
 */
DeptAreasiteInfoDlg.clearData = function() {
    this.deptAreasiteInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DeptAreasiteInfoDlg.set = function(key, val) {
    this.deptAreasiteInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DeptAreasiteInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DeptAreasiteInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/deptAreasite");
	top.layer.close(winOpen.DeptAreasite.layerIndex);
}

/**
 * 收集数据
 */
DeptAreasiteInfoDlg.collectData = function() {
    this
    .set('id')
    .set('deptid')
    .set('areasitecode')
    .set('areasitetype')
    .set('authadd')
    .set('authdelete')
    .set('authselect')
    .set('authupdate')
    .set('crtdate')
    .set('crtuserid');
}

/**
 * 提交添加
 */
DeptAreasiteInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/deptAreasite/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/deptAreasite");
        winOpen.DeptAreasite.table.refresh();
        top.layer.close(winOpen.DeptAreasite.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.deptAreasiteInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DeptAreasiteInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/deptAreasite/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/deptAreasite");
        winOpen.DeptAreasite.table.refresh();
        top.layer.close(winOpen.DeptAreasite.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.deptAreasiteInfoData);
    ajax.start();
}
DeptAreasiteInfoDlg.onClickArea = function(e, treeId, treeNode) {
    $("#areaName").attr("value", DeptAreasiteInfoDlg.zTreeArea.getSelectedVal());
    $("#areasitecode").attr("value", treeNode.areaCode);
}
/**
 * 显示区域选择的树
 */
DeptAreasiteInfoDlg.showAreaSelectTree = function() {
	Feng.showInputTree("areaName", "parentAreaMenu");
}
$(function() {
	 var ztreeArea = new $ZTree("parentAreaMenuTree", "/area/tree");
	    ztreeArea.bindOnClick(DeptAreasiteInfoDlg.onClickArea);
	    ztreeArea.init();
	    DeptAreasiteInfoDlg.zTreeArea = ztreeArea;
});
