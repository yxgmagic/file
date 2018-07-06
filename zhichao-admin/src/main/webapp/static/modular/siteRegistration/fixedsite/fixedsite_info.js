/**
 * 初始化固定治超站详情对话框
 */
var FixedsiteInfoDlg = {
    fixedsiteInfoData : {},
    
};

/**
 * 清除数据
 */
FixedsiteInfoDlg.clearData = function() {
    this.fixedsiteInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FixedsiteInfoDlg.set = function(key, val) {
    this.fixedsiteInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FixedsiteInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
FixedsiteInfoDlg.close = function() {
    var winOpen=Feng.GetFrame("/fixedsite");
	top.layer.close(winOpen.Fixedsite.layerIndex);
}

/**
 * 收集数据
 */
FixedsiteInfoDlg.collectData = function() {
    this
    .set('id')
    .set('sitecode')
    .set('sitename')
    .set('sitelevel')
    .set('longitude')
    .set('latitude')
    .set('address')
    .set('planarea')
    .set('unloadarea')
    .set('areacode')
    .set('roadcode')
    .set('deptid')
    .set('deptpid')
    .set('lawperson')
    .set('lawpersontel')
    .set('setbegindate')
    .set('unloadcode')
    .set('unloadperson')
    .set('unloadpersontel')
    .set('manager')
    .set('managertel')
    .set('approves')
    .set('actuals')
    .set('checks')
    .set('contracts')
    .set('img_id')
    .set('imagetype')
		.set('certificate')
		.set('certificateValidityPeriod')
		.set('dueBank')
        .set('bankAddress')
        .set('bankAccountName')
        .set('bankAccount')
        .set('judicialOrgan')
    ;
    
}

/**
 * 提交添加
 */
FixedsiteInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    
    if (!this.validate()) {
        return;
    }


    //利用FormData进行上传信息
    var formData = new FormData();
    //将文件放到FormData对象中
    formData.append("file", certificateFile);
    //将前面获取到的值放进去
    var infoData = this.fixedsiteInfoData;
    for (var i in infoData) {
        formData.append(i, infoData[i]);
    }

    $.ajax({
        type : "POST",
        url : Feng.ctxPath + "/fixedsite/add?jstime=" + new Date().getTime(),
        data : formData,
        async : false,
        cache : false,
        contentType : false,
        processData : false,
        success : function(data) {
            Feng.success("添加成功!");
            var winOpen=Feng.GetFrame("/fixedsite");
            winOpen.Fixedsite.table.refresh();
            top.layer.close(winOpen.Fixedsite.layerIndex);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown, data) {
            Feng.error("添加失败!");
        }
    });

    // //提交信息
    // var ajax = new $ax(Feng.ctxPath + "/fixedsite/add", function(data){
    //     Feng.success("添加成功!");
    //     var winOpen=Feng.GetFrame("/fixedsite");
    //     winOpen.Fixedsite.table.refresh();
    //     top.layer.close(winOpen.Fixedsite.layerIndex);
    //
    // },function(data){
    //     Feng.error("添加失败!" + data.responseJSON.message + "!");
    // });
    // ajax.set(this.fixedsiteInfoData);
    // ajax.start();
}

/**
 * 提交修改
 */
FixedsiteInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }


    //利用FormData进行上传信息
    var formData = new FormData();

    //将文件放到FormData对象中
    formData.append("file", certificateFile);

    //将前面获取到的值放进去
    var infoData = this.fixedsiteInfoData;
    for (var i in infoData) {
        formData.append(i, infoData[i]);
    }

    $.ajax({
        type : "POST",
        url : Feng.ctxPath + "/fixedsite/update?jstime=" + new Date().getTime(),
        data : formData,
        async : false,
        cache : false,
        contentType : false,
        processData : false,
        success : function(data) {
            Feng.success("修改成功!");
            var winOpen=Feng.GetFrame("/fixedsite");
            winOpen.Fixedsite.table.refresh();
            top.layer.close(winOpen.Fixedsite.layerIndex);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown, data) {
            Feng.error("修改失败!");
        }
    });
    // //提交信息
    // var ajax = new $ax(Feng.ctxPath + "/fixedsite/update", function(data){
    //     Feng.success("修改成功!");
    //     var winOpen=Feng.GetFrame("/fixedsite");
    //     winOpen.Fixedsite.table.refresh();
    //     top.layer.close(winOpen.Fixedsite.layerIndex);
    // },function(data){
    //     Feng.error("修改失败!" + data.responseJSON.message + "!");
    // });
    // ajax.set(this.fixedsiteInfoData);
    // ajax.start();
}

/**
 * 获取地图坐标
 */
$(".getcoor").on("click", function () {
	// lng, lat, src, lngId, latId
	var lng = $("#longitude").val() || "";
    var lat = $("#latitude").val() || "";
    var src = "/fixedsite/fixedsite_add";
    var lngId = "longitude";
    var latId = "latitude";
    var options = {
		lng: lng,
		lat: lat,
		src: src,
		lngId: lngId,
		latId: latId
    };
    Yi.components.setMapMarker(options);
});

/**
 * 获取地图坐标
 */
$(".getcoor2").on("click", function () {
    // lng, lat, src, lngId, latId
    var lng = $("#longitude").val() || "";
    var lat = $("#latitude").val() || "";
    var src = "/fixedsite/fixedsite_update";
    var lngId = "longitude";
    var latId = "latitude";
    var options = {
        lng: lng,
        lat: lat,
        src: src,
        lngId: lngId,
        latId: latId
    };
    Yi.components.setMapMarker(options);
});


$(function() {
	getSiteLevelList();
    getUnloadingList();
	myInit();
	
	initValidate();
    //设置证书有效期限时间框
    Yang.Tools.date_interval('certificateValidityPeriod','date','~','1970-01-01 00:00:00','2099-12-12 23:59:59');


});

//下面是我加的代码

//当上传完文件之后改变左边检定证书的名字
var certificateFile = null;	//需要上传的证书文件
$("#file_input").change(function() {
    if(this.files.length > 0) {
        var file = this.files[0];
        certificateFile = file;
        var certificateName = file.name.replace(/(\.\w+$)/,"");
        $("#certificate").val(certificateName).change();
        $("#certificateA").text(certificateName);
        $("#certificateA").css("color","#00B83F");
    } else {
        $("#certificate").val("").change();
        $("#certificateA").text("请点击上传按钮上传");
        $("#certificateA").css("color","#00B83F");
    }
});
//展示检定证书
function showCertificate() {
    var files = $("#file_input")[0].files;
    var fileCount = $("#file_input")[0].files.length
    if(fileCount > 0) {
        Ning.Tools.showPage(Ning.Tools.getObjectURL(files[0]));
        return ;
    }
    if(certificateUrl.length > 0) {
        Ning.Tools.showPage(Feng.ctxPath + "/tempController/fileDownload?isDownload=0&fileUrl=" + certificateUrl);
        return ;
    }
    return ;
}


/**
 * 初始化表单验证
 */
function initValidate() {
	$('#idFixedSiteForm').bootstrapValidator({
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields : {
	    	sitename: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        sitelevel: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        sitecode: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        longitude: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        latitude: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        areacode: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        lawperson: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        lawpersontel: {
				validators: {
					notEmpty: {
						message: '不能为空!'
					},
					regexp: {
						regexp: '^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,2,5-9]))\\d{8}$',
						message: '手机号码格式错误!'
					}
				}
			},
	        pName: {
	        	trigger:"change",
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        manager: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        managertel: {
				validators: {
					notEmpty: {
						message: '不能为空!'
					},
					regexp: {
						regexp: /^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,2,5-9]))\d{8}$/,
						message: '手机号码格式错误!'
					}
				}
			},
			Select2: {
				validators: {
					notEmpty: {
						message: '请选择行政区域'
					}
				}
			},
			ulloadname: {
				validators: {
					notEmpty: {
						message: '请选择卸货场'
					}
				}
			},
            certificate: {
                trigger:"change",
	    		validators: {
	    			notEmpty: {
	    				message: '请上传检定证书'
					}
				}
			},
            dueBank: {
                validators: {
                    notEmpty: {
                        message: '请输入收款银行'
                    }
                }
            },
            bankAddress: {
                validators: {
                    notEmpty: {
                        message: '请输入银行地址'
                    }
                }
            },
            bankAccountName: {
                validators: {
                    notEmpty: {
                        message: '请输入银行户名'
                    }
                }
            },
            bankAccount: {
                validators: {
                    notEmpty: {
                        message: '请输入银行账号'
                    }
                }
            },
            judicialOrgan: {
                validators: {
                    notEmpty: {
                        message: '请输入审判机关'
                    }
                }
            }

	    }
	});
}

/**
 * 验证数据是否为空
 */
FixedsiteInfoDlg.validate = function () {
    $('#idFixedSiteForm').data("bootstrapValidator").resetForm();
    $('#idFixedSiteForm').bootstrapValidator('validate');
    if(this.get("certificate").length==0) {
        $("#certificateA").text("请上传检定证书!!!");
        $("#certificateA").css("color","red");
        return false;
    }
    if(this.get("certificateValidityPeriod").length==0){
        $("#certificateValidityPeriod").focus();
        Feng.error("没有填写检定证书有效期!");

        return false;
    }
    if(this.get("setbegindate").length==0){
        $("#setbegindate").focus();
        Feng.error("没有填写设置日期!");
        return false;
    }

    return $("#idFixedSiteForm").data('bootstrapValidator').isValid();
}


//当站点唯一标识被改变的时候，改变站点顺序码的值
$('#sitecode').bind('input propertychange', function() {
	var str = $(this).val();
  $('#siteorder').val(str.slice(2));  
});


//初始化部门对象
var DeptInfoDlg = {};

/**
 * 点击部门ztree列表的选项时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
DeptInfoDlg.onClickDept = function(e, treeId, treeNode) {
    $("#pName").attr("value", DeptInfoDlg.zTreeInstance.getSelectedVal()).change();
    $("#deptpid").attr("value", treeNode.id).change();
}

/**
 * 显示部门选择的树
 *
 * @returns
 */
DeptInfoDlg.showDeptSelectTree = function() {
    var pName = $("#pName");
    var pNameOffset = $("#pName").offset();
    $("#parentDeptMenu").css({
        left : "0px",
        top : pName.outerHeight() + "px"
    }).slideDown("fast");
    $("body").bind("mousedown", onBodyDown);
}

/**
 * 隐藏部门选择的树
 */
DeptInfoDlg.hideDeptSelectTree = function() {
    $("#parentDeptMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
}

//
function onBodyDown(event) {
	
    if (!(event.target.id == "pName" || event.target.id == "parentDeptMenu" || $(
            event.target).parents("#parentDeptMenu").length > 0)) {
        DeptInfoDlg.hideDeptSelectTree();
    }
}


//我进行的一些初始化
function myInit() {
	var str = $('#sitecode').val();
	if(str != "") {
		$('#siteorder').val(str.slice(2));  
	}
	
	
	
	//和树形下拉菜单相关的初始化
	var ztree = new $ZTree("parentDeptMenuTree", "/dept/tree");
    ztree.bindOnClick(DeptInfoDlg.onClickDept);
    ztree.init();
    DeptInfoDlg.zTreeInstance = ztree;
    
    
    
    //设置所属部门
  	if(typeof(deptpidValue) != "undefined") {
  		//console.log(deptpidValue);
  		//将部门id赋值给相对应的input
  		$("#deptpid").attr("value", deptpidValue);
  		
  		var zNodes = initNodes();
  		var i = 0;
  		var length = zNodes.length;
  		var temp = null;
  		for(i = 0; i < length; i++) {
  			temp = zNodes[i];
  			if(temp.id == deptpidValue) {
  				$("#pName").attr("value", temp.name);
  				break;
  			}
  		}
  		
  	}
	
	//sitelevel
	
	//初始化治超站等级下拉菜单
	var tempData = siteLevelListData;
	var length = tempData.length;
	var i;
	var tempArr = new Array();
	for(i = 0; i < length; i++) {
		var temp = tempData[i];
		tempArr.push([temp.num, temp.name]);
	}
	appendToEmnu(tempArr, $("#sitelevel"));
	//设置治超站等级下拉菜单选中项
	seclectOnEmnu(sitelevelValue, $("#sitelevel"));
	
	
	//初始化卸货场信息下拉列表
	//console.log(unloadingListData); 
//	tempData = unloadingListData;
//	length = tempData.length;
//	tempArr.length = 0;
//
//	for(i = 0; i < length; i++) {
//		var temp = tempData[i];
//		tempArr.push([temp.ulcode, temp.ulname]);
//	}
//	appendToEmnu(tempArr, $("#unloadcode"));
//	seclectOnEmnu(unloadcodeValue, $("#unloadcode"));
//	
	if(unloadcodeValue != "") {
		var tempItem = getObjOfVal(unloadingListData,"ulcode", unloadcodeValue);
		$("#ulloadname").val(tempItem.ulname);
	}
	
	Feng.getImage($("#id").val(), $("#imagetype").val());
	//监听上传按钮的改变事件
	$("#img_hidden").change(function() {
		//隐藏图片(input=file)id,显示图片id,上传图片后图片id
		Feng.ajaxFileUploadEntityImage();
	});
}

/**
 * 卸货场被改变时将卸货场联系人信息个填写好
 */
var unloadItem = {
		manager:"",
		managertel:""
};
// $('#unloadcode').change(function(){
// 	console.log(unloadItem)
// 	$("#unloadperson").val(unloadItem.manager);
// 	$("#unloadpersontel").val(unloadItem.managertel);
//
// });
FixedsiteInfoDlg.autoFill = function() {
    console.log(unloadItem);
}

/**
 * 根据json的名字和属性名和属性值，返回一个相对应的json（map？）对象
 * tempData : json的名字
 * attr ： 属性名
 * value ： 属性值
 */
function getObjOfVal(tempData, attr, value) {
	var tempItem;
	for(var i in tempData) {
		tempItem = tempData[i];
		for(var item in tempItem) {
			if(item == attr && value == tempItem[item]) {
				return tempItem;
				break;
			}
		}
	}
	return "";
}

/**
 * 获取部门Nodes
 * 
 */

function initNodes(){
	var zNodes = null;
	var ajax = new $ax(Feng.ctxPath + "/dept/tree", function(data) {
		zNodes = data;
	}, function(data) {
		Feng.error("加载ztree信息失败!");
	});
	ajax.start();
	return zNodes;
}

/**
 * 将选中的值设置到下拉菜单中
 */
function seclectOnEmnu(optionValue, downEmnu) {

	downEmnu.val(optionValue);
	
}

/**
 * //根据数组将数组添加到下拉菜单
 * arrData = [菜单值, 菜单名]
 * downEmnu = 用来接收数据的下拉菜单对象（引用）
 */
function appendToEmnu(arrData, downMenu) {
	var tempData = arrData;
	var length = tempData.length;
	var i = 0;
	for(i = 0; i < length; i++) {
		var temp = tempData[i];
		var option = "<option value='"+tempData[i][0]+"'>"+tempData[i][1]+"</option>";
		downMenu.append(option);
	}
}

//获取治超站等级列表用于动态显示在下拉框
var siteLevelListData = null;
function getSiteLevelList() {
	var ajax = new $ax(Feng.ctxPath + "/fixedsite/getSiteLevelList", function (data) {
		siteLevelListData = data;
    }, function (data) {
        Feng.error("从后台获取数据失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
}

//获取卸货场信息列表，用于显示在下拉菜单/getUnloadingList
var unloadingListData = null;
function getUnloadingList() {
	var ajax = new $ax(Feng.ctxPath + "/fixedsite/getUnloadingList", function (data) {
		unloadingListData = data;
    }, function (data) {
        Feng.error("从后台获取数据失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
}

//图片预览
	$("#img_show").click(function() {
		layer.open({
			  type: 1,
			  title: '图片预览',
			  closeBtn: 1,
			  area: ['700px', '450px'],
			  skin: 'layui-layer-nobg', //没有背景色
			  shadeClose: true,
			  content: "<img id='openImg' width='694px' heigth='500px' src='"+$("#img_show")[0].src+"'>"
			});
	});


	/**
	 * 绑定卸货场弹框
	 */
	$("#ulloadname").click(function() {
		var index = layer.open({
	        type: 2,
	        title: '选择卸货场',
	        area: ['790px', '490px'], //宽高
	        fix: true, //不固定
	        content: Feng.ctxPath + '/cargoHandling/unloading_popups'
	    });
		
	});
	