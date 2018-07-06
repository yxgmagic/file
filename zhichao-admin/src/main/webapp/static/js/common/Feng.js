var Feng = {
    ctxPath: "",
    /**
     * options: lng - 当前经度， lat - 当前纬度， src - iframe的src， lngId - 经度的input ID， latId - 纬度的input ID
     */
    GetCoor: function(options){
    	if(options.lng === null || options.lng === 'undefined' || options.lat === null || options.lat === 'undefined' || options.src === null || options.src === '' || options.src === 'undefined' || options.lngId === null || options.lngId === '' || options.lngId === 'undefined' || options.latId === null || options.latId === '' || options.latId === 'undefined' ){
    		return;
    	} else {
    		var url = "/gismonitor/coordinate?lng=" + encodeURI(options.lng) + "&lat=" + encodeURI(options.lat) + "&src=" + encodeURI(options.src) +
            "&lngId=" + encodeURI(options.lngId) + "&latId=" + encodeURI(options.latId);
	        top.layer.open({
	            type: 2,
	            title: '选择坐标',
	            area: ['700px', '460px'],
	            fix: true,
	            maxmin: false,
	            content: Feng.ctxPath + url
	        })
    	}
    },
    yd:function(id,tp,rg,min,max){
    	/**
    	 * 时间日期范围选择控件
    	 * tp:类型，true为日期区间，year，month，time,datetime
    	 * rg:分隔符，默认为'~'
    	 * max,min:最大最小区间，默认'1970-01-01 00:00:00-当前时间'
    	 */
    	id = (tp == null) || (tp == 0) || (typeof $("#"+id) == "undefined") ? 'time' : id;
    	tp = (typeof tp == "undefined") || (tp == null) || (tp == 0) ? true : tp;
    	rg = (typeof rg == "undefined") || (rg == null) || (rg == 0) ? '~' : rg;
    	max = (typeof max == "undefined") || (max == null) || (max == 0) ? new Date() : max;
    	min = (typeof min == "undefined") || (min == null) || (min == 0) ? '1970-01-01 00:00:00' : min;
    	layui.use('laydate', function(){
    		var arr = tp == true ? {elem: '#'+id,range: rg,max: ''+max,min: ''+min} : {elem: '#'+id,type: tp,range: rg,max: ''+max,min: ''+min}
        	var laydate = layui.laydate;
    	  	laydate.render(arr);
    	});	
    },
    showVideo:function(title,idx,flag,ptz,site,code){//标题,视频窗口(23),标记（0实时,1历史),云台(0有,1无),站点(0站点,1设备),代码(站点或者设备编码)
    	top.layer.open({
            type: 2,
            title: title,
            area: ['990px', '580px'], //宽高
            fix: false, //不固定
            maxmin: false,
            content: Feng.ctxPath + '/comm/showSiteVideo'+"?title="+encodeURI(encodeURI(title))+"&idx="+idx+"&flag="+flag+"&ptz="+ptz+"&site="+site+"&code="+encodeURI(encodeURI(code))
        });
    },
    playVideo:function(title,flag,st,et,site,code){//标题,标记（0历史,1文件),开始时间,结束时间,站点(0站点,1设备),代码(站点或者设备编码，或者视频U)
    	top.layer.open({
            type: 2,
            title: title,
            area: ['990px', '580px'], //宽高
            fix: false, //不固定
            maxmin: false,
            content: Feng.ctxPath + '/comm/play'+"?title="+encodeURI(encodeURI(title))+"&flag="+flag+"&st="+st+"&et="+et+"&site="+site+"&code="+encodeURI(encodeURI(code))
        });
    },
    GetFrame:function(src){
    	for(var i=0;i<parent.frames.length;i++){
    		var str=parent.frames[i].location.href;
    		if(str.indexOf(src)>0)
    			return parent.frames[i];
    	}
    	
    	for(var i=0;i<top.frames.length;i++){
    		var str=top.frames[i].location.href;
    		if(str.indexOf(src)>0)
    			return top.frames[i];
    	}
    },
    ajaxFileUploadEntityImage:function(){
    	
    	Array.prototype.contains = function(item){
    		return RegExp("\\b"+item+"\\b").test(this);
    	};
    	var formData = new FormData();
    	//从隐藏的file input中取值
    	formData.append("file", document.getElementById('img_hidden').files[0]);
    	
    	var fileName = $("#img_hidden").val();
    	var suffix = fileName.substring(fileName.indexOf(".") + 1).toLowerCase();
    	var ext =  ['jpg','png','jpeg'];
    	var cPath = this.ctxPath;
    	
    	if (ext.contains(suffix)) {
    		
    		//设置遮罩等待图片上传完成
        	var index = layer.load(1, {
    			shade: [0.1,'#fff'] //0.1透明度的白色背景
    		});
    		
    		$.ajax({
        		type : "POST",
        		url : this.ctxPath+"/image/upload",
        		data : formData,
        		async : false,
        		cache : false,
        		contentType : false,
        		processData : false,
        		success : function(data) {
        			
        			var reader=new FileReader();
        			if(data.code == 200){
        				//图片上传成功.
        				if(window.FileReader){//支持FileReader的时候
        	                
        	                reader.readAsDataURL(document.getElementById("img_hidden").files[0]);
        	                reader.onload=function(evt){
        	                	$("#img_show")[0].src = evt.target.result
        	                	$('#img_id').val(data.msg);
        	                }
        	            }else{//兼容ie9-
        	                Feng.info("图片上传成功, IE9以下浏览器不支持预览")
        	            }
        			}else{
        				$('#img_id').val("0");
        				Feng.info(data.msg);
        			}
        		},
        		error : function(XMLHttpRequest, textStatus, errorThrown, data) {
        			
        			Feng.error("图片上传失败,请重试");
        		}
        	});
    		
    		//删除图片遮罩
    		layer.close(index);
        }else{
    		$('#img_id').val("0");
    		$("#img_show")[0].src = cPath+'/static/img/notfoundbl.png';
    		Feng.info("仅支持jpg,jpeg,png图片格式,请重新选择");
    		
            return false;
        }
    },
    //页面加载时向后台找图片数据.
    getImage:function(id,itype){
    	var path=this.ctxPath;
    	if (id > 0) {
    		$.ajax({
    			type : "POST",
    			url : this.ctxPath+"/image/findImg",
    			data : {
    				"entityId":id,
    				"imagetype":itype
    			},
    			async : false,
    			cache : false,
    			success : function(data) {
    				if(data.code == 200){
    					$("#img_show")[0].src = "data:image/png;base64,"+data.msg.img;
    				}else{
    					$("#img_show")[0].src = path+'/static/img/notfoundbl.png';
    				}
    			},
    			error : function(XMLHttpRequest, textStatus, errorThrown, data) {
    				Feng.error("初始化数据出错,请重试!");
    			}
    		});
    	}else{
    		//新增页面,无需寻找id
    	}
    },
    addCtx: function (ctx) {
        if (this.ctxPath == "") {
            this.ctxPath = ctx;
        }
    },
    confirm: function (tip, ensure) {//询问框
        parent.layer.confirm(tip, {
            btn: ['确定', '取消']
        }, function (index) {
            ensure();
            parent.layer.close(index);
        }, function (index) {
            parent.layer.close(index);
        });
    },
    log: function (info) {
        console.log(info);
    },
    alert: function (info, iconIndex) {
        parent.layer.msg(info, {
            icon: iconIndex
        });
    },
    info: function (info) {
        Feng.alert(info, 0);
    },
    success: function (info) {
        Feng.alert(info, 1);
    },
    error: function (info) {
        Feng.alert(info, 2);
    },
    infoDetail: function (title, info) {
        var display = "";
        if (typeof info == "string") {
            display = info;
        } else {
            if (info instanceof Array) {
                for (var x in info) {
                    display = display + info[x] + "<br/>";
                }
            } else {
                display = info;
            }
        }
        parent.layer.open({
            title: title,
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['950px', '600px'], //宽高
            content: '<div style="padding: 20px;">' + display + '</div>'
        });
    },
    writeObj: function (obj) {
        var description = "";
        for (var i in obj) {
            var property = obj[i];
            description += i + " = " + property + ",";
        }
        layer.alert(description, {
            skin: 'layui-layer-molv',
            closeBtn: 0
        });
    },
    showInputTree: function (inputId, inputTreeContentId, leftOffset, rightOffset) {
        var onBodyDown = function (event) {
            if (!(event.target.id == "menuBtn" || event.target.id == inputTreeContentId || $(event.target).parents("#" + inputTreeContentId).length > 0)) {
                $("#" + inputTreeContentId).fadeOut("fast");
                $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
            }
        };

         if(leftOffset == undefined && rightOffset == undefined){
            var inputDiv = $("#" + inputId);
            var inputDivOffset = $("#" + inputId).offset();
            $("#" + inputTreeContentId).css({
                left: inputDivOffset.left + "px",
                top: inputDivOffset.top + inputDiv.outerHeight() + "px"
            }).slideDown("fast");
        }else{
            $("#" + inputTreeContentId).css({
                left: leftOffset + "px",
                top: rightOffset + "px"
            }).slideDown("fast");
        }

        $("body").bind("mousedown", onBodyDown);
    },
    baseAjax: function (url, tip) {
        var ajax = new $ax(Feng.ctxPath + url, function (data) {
            Feng.success(tip + "成功!");
        }, function (data) {
            Feng.error(tip + "失败!" + data.responseJSON.message + "!");
        });
        return ajax;
    },
    changeAjax: function (url) {
        return Feng.baseAjax(url, "修改");
    },
    zTreeCheckedNodes: function (zTreeId) {
        var zTree = $.fn.zTree.getZTreeObj(zTreeId);
        var nodes = zTree.getCheckedNodes();
        var ids = "";
        for (var i = 0, l = nodes.length; i < l; i++) {
            ids += "," + nodes[i].id;
        }
        return ids.substring(1);
    },
    eventParseObject: function (event) {//获取点击事件的源对象
        event = event ? event : window.event;
        var obj = event.srcElement ? event.srcElement : event.target;
        return $(obj);
    },
    sessionTimeoutRegistry: function () {
        $.ajaxSetup({
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            complete: function (XMLHttpRequest, textStatus) {
                //通过XMLHttpRequest取得响应头，sessionstatus，
                var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
                if (sessionstatus == "timeout") {
                    //如果超时就处理 ，指定要跳转的页面
                    window.location = Feng.ctxPath + "/global/sessionError";
                }
            }
        });
    },
    initValidator: function(formId,fields){
        $('#' + formId).bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: fields,
            live: 'enabled',
            message: '该字段不能为空'
        });
    },
    underLineToCamel: function (str) {
        var strArr = str.split('_');
        for (var i = 1; i < strArr.length; i++) {
            strArr[i] = strArr[i].charAt(0).toUpperCase() + strArr[i].substring(1);
        }
        var result = strArr.join('');
        return result.charAt(0).toUpperCase() + result.substring(1);
    },
    // -----------------------------------zhichao--- 暂时没有解决权限问题,弃用 --------------------------------------------
    /**
     * @description 把删除和修改按钮移到表格中的封装
     * @author zhichao
     * @param infoInit 当前对象			Unloading
     * @param updateUrl 修改的接口url		/unloading/unloading_update/
     * @param deleteUrl 删除的接口url		/unloading/delete
     * @param itemName 删除时需要的Id		unloadingId
     */
    btnToLine: function(infoInit, updateUrl, deleteUrl, itemName){
    	window.openDetail = {
			'click #btn_update': function(e, value, row, index) {
		    	infoInit.openUpdate(row.id);
		    },
		    'click #btn_delete': function(e, value, row, index) {
		    	infoInit.openDelete(row.id, itemName);
		    }
    	},
    	infoInit.openUpdate = function(id) {
    		var index = top.layer.open({
    			type: 2,
    			title: '卸货场数据管理详情',
    			area: ['800px', '600px'], //宽高
    			fix: false, //不固定
    			maxmin: true,
    			content: Feng.ctxPath + updateUrl + id
    		});
    		this.layerIndex = index;
    	},
    	infoInit.openDelete = function(id, itemName){
    		var operation = function(){
    			var ajax = new $ax(Feng.ctxPath + deleteUrl, function (data) {
    	            Feng.success("删除成功!");
    	            infoInit.table.refresh();
    	        }, function (data) {
    	            Feng.error("删除失败!" + data.responseJSON.message + "!");
    	        });
    	        ajax.set(itemName, id);
    	        ajax.start();
    		}
    		Feng.confirm("确认要删除吗?", operation);
    	}
    },
    detailFormatter:function(){
    	 return [
    	        "<button id='btn_update' class='btn btn-primary btn-xs'>修改</button>", 
    	        "<button id='btn_delete' class='btn btn-primary btn-xs'>删除</button>",
    	    ].join('');
    }
    
    
};


