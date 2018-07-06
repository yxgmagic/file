﻿/**
 * 初始化立案办理详情对话框
 */
var CaseHand = {
    oefullinfoInfoData : {}
};

/**
 * 清除数据
 */
CaseHand.clearData = function() {
    this.oefullinfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CaseHand.set = function(key, val) {
    this.oefullinfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CaseHand.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CaseHand.close = function() {
	var winOpen=Feng.GetFrame("/casehand");
	if (winOpen!=undefined){
		top.layer.close(winOpen.Oefullinfo.layerIndex);
	}
};


/**
 * 通过
 */
CaseHand.via = function() {

	var open = function() {
		var id = $("#id").val();
		var checkno = $("#checkno").val();
		//提交信息
		var ajax = new $ax(Feng.ctxPath + "/casehand/via/" + id + "/" + checkno + "/" + $("#caseSource").val(), function(data){
			Feng.success("已通过!");

			refushtable();
		},function(data){
			Feng.error("未成功通过，请重试!");
		});
		ajax.start();
	}
	Feng.confirm("确认要通过审核吗？",open);
}

/**
 * 驳回
 */
CaseHand.turndown = function() {

	var open = function() {
		var id = $("#id").val();
		var checkno = $("#checkno").val();
		//驳回
		var ajax = new $ax(Feng.ctxPath + "/casehand/turndown/" + id + "/" + checkno + "/" + $("#caseSource").val(), function(data){
			Feng.success("已驳回!");
			
			refushtable();
		},function(data){
			Feng.error("驳回失败，请重试!" + data.responseJSON.message + "!");
		});
		ajax.start();
	}
	Feng.confirm("确认要驳回审核吗？",open);
}

/**
 * 关闭并刷新
 * @returns
 */
function refushtable() {
	var winOpen=Feng.GetFrame("/casehand");
    if (winOpen!=undefined){
    	winOpen.CaseHand.table.refresh();
    	top.layer.close(winOpen.CaseHand.layerIndex);
    }
}

$(function() {
	
	//已立案不可改变状态
	var prostatus = $("#prostatus").val();
	if (prostatus >= 3) {
		$(".row-margin-tb30").empty()
	}

    Yang.Tools.createSelElement('enforcername1','oefullinfo/getLawEnforceMan',$("#enforce1").val())
    Yang.Tools.createSelElement('enforcername2','oefullinfo/getLawEnforceMan',$("#enforce2").val())
});