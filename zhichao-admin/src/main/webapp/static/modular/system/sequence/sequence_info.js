/**
 * 初始化编码规则器详情对话框
 */
var SequenceInfoDlg = {
    sequenceInfoData : {}
};

/**
 * 清除数据
 */
SequenceInfoDlg.clearData = function() {
    this.sequenceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SequenceInfoDlg.set = function(key, val) {
    this.sequenceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SequenceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SequenceInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/sequence");
	top.layer.close(winOpen.Sequence.layerIndex);
}

/**
 * 收集数据
 */
SequenceInfoDlg.collectData = function() {
    this
    .set('id')
    .set('seqname')
    .set('seqtype')
    .set('seqstr')
    .set('seqdate')
    .set('seqmax')
    .set('seqlen')
    .set('seqcol')
    .set('seqtab')
    .set('seqdept')
    .set('roadnum').set('isseries')
    .set('sequence');
}

/**
 * 提交添加
 */
SequenceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
	if(this.get("isseries")==''){
		$("#isseries").focus();
		Feng.error("没有选择是否允许连续!");
		return false;
	}
//    if(this.sequenceInfoData["seqdept"]!=""||this.sequenceInfoData["seqdept"]!=null){
//    	this.sequenceInfoData["seqname"]+='_'+ this.sequenceInfoData["seqdept"];
//    }
// 
//    if(this.sequenceInfoData["roadnum"]!=""||this.sequenceInfoData["roadnum"]!=null&&this.sequenceInfoData["roadnum"]>'0'){
//    	this.sequenceInfoData["seqname"]+='_'+ this.sequenceInfoData["roadnum"];
//    }
   
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sequence/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/sequence");
        winOpen.Sequence.table.refresh();
        top.layer.close(winOpen.Sequence.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sequenceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SequenceInfoDlg.editSubmit = function() {
	//$("#seqname").val("${item.seqname}");
    this.clearData();
    this.collectData();
	if(this.get("isseries")==''){
		$("#isseries").focus();
		Feng.error("没有选择是否允许连续!");
		return false;
	}
//    if(this.sequenceInfoData["seqdept"]!=""||this.sequenceInfoData["seqdept"]!=null){
//    	this.sequenceInfoData["seqname"]+='_'+ this.sequenceInfoData["seqdept"];
//    }
//    
//    if(this.sequenceInfoData["roadnum"]!=""||this.sequenceInfoData["roadnum"]!=null&&this.sequenceInfoData["roadnum"]>='0'){
//    	this.sequenceInfoData["seqname"]+='_'+ this.sequenceInfoData["roadnum"];
//    }
 
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sequence/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/sequence");
        winOpen.Sequence.table.refresh();
        top.layer.close(winOpen.Sequence.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sequenceInfoData);
    ajax.start();
}
SequenceInfoDlg.getseq =function() {
    this.clearData();
    this.collectData();
	var areajson = null;
	if(this.get("isseries")==''){
		$("#isseries").focus();
		Feng.error("没有选择是否允许连续!");
		return false;
	}
	var ajax = new $ax(Feng.ctxPath + "/sequence/getseq", function(data) {
		 
		areajson = data;
    	if( "undefined"==areajson.sequence){Feng.error("失败！"+areajson.ERROR);}
    	else {
    		$("#sequence").val(areajson.sequence);
    		alert("试算生成的编码为："+areajson.sequence);
    	}
		
	}, function(data) {
		Feng.error("失败!" + data.responseJSON.message + "!");

	});

	ajax.set("seqtype", this.get("seqtype"));
	ajax.set("seqdept", this.get("seqdept"));
 
	ajax.set("roadnum", this.get("roadnum"));
	ajax.set("seqcol", this.get("seqcol"));
	ajax.set("seqtab", this.get("seqtab"));
	ajax.start();
	

	return areajson;

} ;
$(function() {

});
