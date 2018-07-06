/**
 * 初始化黑白名单详情对话框
 */
var BlacklistInfoDlg = {
    blacklistInfoData : {}
};

/**
 * 清除数据
 */
BlacklistInfoDlg.clearData = function() {
    this.blacklistInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BlacklistInfoDlg.set = function(key, val) {
    this.blacklistInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BlacklistInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BlacklistInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/blacklist");
	if (winOpen!=undefined){
		top.layer.close(winOpen.Blacklist.layerIndex);
	}
}

/**
 * 收集数据
 */
BlacklistInfoDlg.collectData = function() {
    this
    .set('id')
    .set('vehicleid')
    .set('drivername')
    .set('corpname')
    .set('stationid')
    .set('cartype')
    .set('caraxles')
    .set('ratedloading')
    .set('overload')
    .set('fine')
    .set('ispunitive')
    .set('isblack')
    .set('remarks');
}

/**
 * 提交添加
 */
var imgFiles = new Array();
BlacklistInfoDlg.addSubmit = function() {

    var index = layer.load(1, {
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });

    if (!this.validate()) {
        layer.close(index);
        return;
    }
    var formData = new FormData();

    //将文件放到FormData对象中
    if(imgFiles.length > 0) {
    	var length = imgFiles.length;
    	for(var i = 0; i < length; i++) {
            formData.append("image" + i, imgFiles[i]);
		}
	}
    formData.append("vehicleid",$("#vehicleid").val());
    formData.append("drivername",$("#drivername").val());
    formData.append("corpname",$("#corpname").val());
    formData.append("stationid",$("#stationid").val());
    formData.append("cartype",$("#cartype").val());
    formData.append("caraxles",$("#caraxles").val());
    formData.append("ratedloading",$("#ratedloading").val());
    formData.append("overload",$("#overload").val());
    formData.append("fine",$("#fine").val());
    formData.append("ispunitive",$("#ispunitive").val());
    formData.append("isblack",$("#isblack").val());
    formData.append("remarks",$("#remarks").val());


    $.ajax({
        type : "POST",
        url : Feng.ctxPath + "/blacklist/add?jstime=" + new Date().getTime(),
        data : formData,
        async : false,
        cache : false,
        contentType : false,
        processData : false,
        success : function(data) {
            layer.close(index);
            if (data.code == 200) {
                layer.msg("上传数据成功");
				var winOpen=Feng.GetFrame("/blacklist");
				if (winOpen!=undefined){
				   winOpen.Blacklist.table.refresh();
				   top.layer.close(winOpen.Blacklist.layerIndex);
				}
            } else {
                Feng.error(data.data);
            }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown, data) {
            layer.close(index);
            Feng.error("上传失败,请检查文件数据是否损坏");
        }
    });
    layer.close(index);

}

/**
 * 提交修改
 */
BlacklistInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/blacklist/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/blacklist");
        if (winOpen!=undefined){
        	winOpen.Blacklist.table.refresh();
        	top.layer.close(winOpen.Blacklist.layerIndex);
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.blacklistInfoData);
    ajax.start();
}


var table = layui.table;


/**
 * 渲染表格
 * @returns
 */
function renderTable() {
	table.render({
		  elem: '#idHistoryRecordTable'
		  ,height: 315
		  ,loading: true
		  ,url: Feng.ctxPath + '/lscinfo/getHistoryRecord?vehicleid=' + $("#vehicleid").text() //数据接口
		  ,text: {
			    none: '暂无相关数据' 
		  }
		  ,page: true //开启分页
		  ,cols: [[ //表头
		    {field: 'sitename', title: '超重站点',align: 'center'}
		    ,{field: 'rctime', title: '时间', align: 'center'}
		    ,{field: 'overlimited', title: '超限量(kg)', align: 'center'} 
		    ,{field: 'reta', title: '超重率(%)', align: 'center'}
		  ]]
		});
}

function getCarImage() {
	/**
	 * 获取需要显示的车头信息
	 */


	layui.use([ 'carousel', 'form' ], function() {
		var carousel = layui.carousel, form = layui.form;
		var vehicleidValue = $("#vehicleid")[0].innerText;
		$.ajax({
			type : "POST",
			url : Feng.ctxPath + "/blacklist/getVehicleImages",
			data : {"vehicleid" : vehicleidValue},

			success : function(data) {

				if(data.length > 0) {
					for (var i = 0; i < data.length; i++) {
						$("#img_box").append("<img src='' alt='车头照片' id='img"+i+"'/>");
						$("#img" + i)[0].src = "data:image/png;base64,"+data[i];
					}
				} else {
					for (var i = 0; i < 2; i++) {
						$("#img_box").append("<img src='' alt='车头照片' id='img"+i+"'/>");
						$("#img" + i)[0].src = Feng.ctxPath + "/static/Resource/Images/truck.jpg";
					}
				}

				carousel.render({
					elem : '#photo',
					arrow : 'always'
				});
			},
			error : function() {
				Feng.error("初始化数据出错,请重试!");
			}
		});

	});
}

//idBlackListForm
BlacklistInfoDlg.initValidate = function() {
	
	$("#idBlackListForm").bootstrapValidator({
		feedbackIcons : {
			valid: 'glyphicon glyphicon-ok',
			validating: 'glyphicon glyphicon-refresh'
		},
		excluded:[":disabled"],
		fields : {
	        vehicleid: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        drivername: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        stationidInput: {
	        	trigger:"change",
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        cartype: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        isblack: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        caraxles: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        }
		}
	});
	
}

/**
 * 验证数据是否为空
 */
BlacklistInfoDlg.validate = function () {
    $('#idBlackListForm').data("bootstrapValidator").resetForm();
    $('#idBlackListForm').bootstrapValidator('validate');
    return $("#idBlackListForm").data('bootstrapValidator').isValid();
}


/**
 * 上传图片
 */
BlacklistInfoDlg.uploadImgs = function(files) {
	var length = files.length;
	var i = 0;
	console.log(files);
	if(length > 3) {
        layer.msg('文件太多了', {icon: 5});
        return "文件太多";
	}


	if($("#img_box").children().length > 0) {
        $("#img_box").empty();
	}
    imgFiles = new Array();
    /**
     * 显示需要提交的车头信息
     */
    layui.use([ 'carousel', 'form' ], function() {
        var carousel = layui.carousel, form = layui.form;
		for (i = 0; i < length; i++) {
			$("#img_box").append("<img src='' alt='车头照片' id='id_img"+(i===0?"":i)+"'/>");
            var srcs = Ning.Tools.getObjectURL(files[i]);
			$("#id_img" + (i===0?"":i))[0].src = srcs;
            imgFiles.push(files[i]);

		}
		carousel.render({
			elem : '#photo',
			arrow : 'always'
		});
    });

}

$(function() {
	
	if(isAdd == 0) {
		renderTable();
		getCarImage();
	}
	
	BlacklistInfoDlg.initValidate();

	
});



