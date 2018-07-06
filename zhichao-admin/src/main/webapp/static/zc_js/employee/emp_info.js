 /**
 * 初始化员工信息详情对话框
 */
var EmpInfoDlg = {
    EmpInfoData : {}
};

/**
 * 清除数据
 */
EmpInfoDlg.clearData = function() {
    this.EmpInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EmpInfoDlg.set = function(key, val) {
    this.EmpInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EmpInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
EmpInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/employee");
	if (winOpen!=undefined){
		top.layer.close(winOpen.Emp.layerIndex);
	}
}

/**
 * 收集数据
 */
EmpInfoDlg.collectData = function() {
    this
    .set('empno')
    .set('ename')
    .set('job')
    .set('status')
    .set('hiredate');
}

/**
 * 提交添加
 */
EmpInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();
    alert(JSON.stringify(this));
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/employee/add", function(data){
        Feng.success("添加成功!");
        console.log("新增的data: " + JSON.stringify(data));
        var winOpen=Feng.GetFrame("/employee");
        if (winOpen!=undefined){
        	winOpen.Emp.table.refresh();
        	top.layer.close(winOpen.Emp.layerIndex);
        }
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.EmpInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
EmpInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    console.log("修改的this:　" + JSON.stringify(this));
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/employee/update", function(data){
    	console.log("修改的data: " + JSON.stringify(data));
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/employee");
        if (winOpen!=undefined){
        	winOpen.Emp.table.refresh();
        	top.layer.close(winOpen.Emp.layerIndex);
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.EmpInfoData);
    ajax.start();
}
/*
 * 文件上传
 */
EmpInfoDlg.ajaxFileUploadDoc= function() {
	this.clearData();
	this.collectData();
	if(this.get("ename")==''){
		$("#ename").focus();
		Feng.error("没有填写名称!");
		return false;
	}
	if(this.get("job")==''){
		$("#job").focus();
		Feng.error("没有填写职位!");
		return false;
	}
	if(this.get("status")==''){
		$("#status").focus();
		Feng.error("没有选择状态!");
		return false;
	}

	var index = layer.load(1, {
		shade : [ 0.1, '#fff' ] //0.1透明度的白色背景
	});

	Array.prototype.contains = function(item) {
		return RegExp("\\b" + item + "\\b").test(this);
	};
	var formData = new FormData();
	//从隐藏的file input中取值
	formData.append("file", document.getElementById('img_hidden').files[0]);

	formData.append("ename", this.get("ename"));
 

	formData.append("status", this.get("status"));

	formData.append("id", document.getElementById('id').value);
	console.log(JSON.stringify(formData));
	
	var fileName = $("#img_hidden").val();
	var suffix = fileName.substring(fileName.indexOf(".") + 1).toLowerCase();
	var ext = [ 'doc', 'docx', 'wps' ];

	//设置遮罩等待图片上传完成 ，暂时不限制文件类型
	//if (ext.contains(suffix)) {

		$.ajax({
			type : "POST",
			url : Feng.ctxPath + "/employee/empUpload",
			data : formData,
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			success : function(data) {

				if (data.code == 200) {

					var testid=document.getElementById('id').value
					if(testid!="-1"){
						document.getElementById('status').value = "2";
						$("#ensure").attr('disabled','true')
						var winOpen=Feng.GetFrame("/employee");
						winOpen.LawDoc.table.refresh();
					}else {document.getElementById('url').value = data.msg;}



				} else {

					Feng.info("报错了: " + data.msg);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown, data) {
				Feng.error("上传失败,请重试");
			}
		});
		//删除图片遮罩

//	} else {
//
//		Feng.info("仅支持doc,docx格式,请重新选择");
//
//		layer.close(index);
//		return false;
//	}

	layer.close(index);
}


$(function() {
});
