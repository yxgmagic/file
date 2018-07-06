/**
 * 初始化行政区域管理详情对话框
 */
var AreaInfoDlg = {
		areaInfoData : {}
};

/**
 * 清除数据
 */
AreaInfoDlg.clearData = function() {
	this.areaInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AreaInfoDlg.set = function(key, val) {
	this.areaInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
	return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AreaInfoDlg.get = function(key) {
	return $("#" + key).val();
}

/**
 * 关闭此对话框
 * parent.layer.close(window.parent.Area.layerIndex);
 */
AreaInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/arc");
	top.layer.close(winOpen.Area.layerIndex);
}

/**
 * 收集数据
 */
AreaInfoDlg.collectData = function() {
	this
	.set('id')
	.set('pid')
	.set('areacode')
	.set('areaname')
	.set('arealetter')
	.set('areatype')
	.set('address');
}

/**
 * 提交添加
 */
AreaInfoDlg.addSubmit = function() {

	this.clearData();
	this.collectData();

	$('#areaForm').bootstrapValidator('validate');
	var bootstrapValidator = $("#areaForm").data('bootstrapValidator');

	//验证信息正确性
	if(bootstrapValidator.isValid()){
		//提交信息
		var ajax = new $ax(Feng.ctxPath + "/arc/add", function(data){
			Feng.success("添加成功!");
			
			var winOpen=Feng.GetFrame("/arc");
			winOpen.Area.table.refresh();
			top.layer.close(winOpen.Area.layerIndex);
			
		},function(data){
			Feng.error("添加失败!" + data.responseJSON.message + "!");
		});
		ajax.set(this.areaInfoData);
		ajax.start();
	}else{
		return false;
	}
}

/**
 * 提交修改
 */
AreaInfoDlg.editSubmit = function() {

	this.clearData();
	this.collectData();

	$('#areaForm').bootstrapValidator('validate');
	var bootstrapValidator = $("#areaForm").data('bootstrapValidator');

	//验证信息正确性
	if(bootstrapValidator.isValid()){
		//提交信息
		var ajax = new $ax(Feng.ctxPath + "/arc/update", function(data){
			Feng.success("修改成功!");
			
			var winOpen=Feng.GetFrame("/arc");
			winOpen.Area.table.refresh();
			top.layer.close(winOpen.Area.layerIndex);
			
		},function(data){
			Feng.error("修改失败!" + data.responseJSON.message + "!");
		});
		ajax.set(this.areaInfoData);
		ajax.start();
	}else{
		return false;
	}
}


$(function() {

	var p_id = $("#hpid").val() ;
	var ajax = new $ax(Feng.ctxPath + "/arc/selByPid", function(data){
		for(var i = 0; i < data.length; i ++){
			$("#pid").append("<option value="+data[i].id+">"+data[i].areaname+"</option>");
		}
		if(typeof p_id != "undefined"){
			$("#pid").val(''+p_id);
		}else{
			$("#pid").val(''+data[0].id);
		}
	},function(data){
		Feng.error("初始化失败,请刷新!");
	});
	ajax.set("pid",p_id);
	ajax.start();
	
	
	$('#areaForm').bootstrapValidator({
		message: '这个值没有被验证',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			areacode: {
				validators: {
					notEmpty: {
						message: '该项为必填项'
					},
					regexp: {
						regexp: /^\d{6}$/,
						message: '行政区域代码格式不正确'
					}
				}
			},
			areaname: {
				validators: {
					notEmpty: {
						message: '该项为必填项'  //行政区域名称
					}
				}
			},
			arealetter: {
				validators: {
					notEmpty: {
						message: '该项为必填项'
					},
					regexp: {
						regexp: /^[A-Z]+$/,
						message: '行政区域字母码格式不正确'
					}
				}
			},
			managerid: {
				validators: {
					notEmpty: {
						message: '该项为必填项'
					},
					regexp: {
						regexp: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}[0-9Xx]$)/,
						message: '身份证不合法'
					}
				}
			},
			address: {
				validators: {
					notEmpty: {
						message: '该项为必填项'
					}
				}
			}, 
			areatype: {
				validators: {
					notEmpty: {
						message: '请选择行政区类别'
					}
				}
			}
		}
	});

});

