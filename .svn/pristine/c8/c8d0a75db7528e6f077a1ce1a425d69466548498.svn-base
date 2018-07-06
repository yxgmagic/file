$(function () {
    /**
     * 省级信息获取和修改
     */
    $.ajax({
    	method: "GET",
    	url: Feng.ctxPath + "/area/arealist?pid=0",
    	success: function(data){
    		$("#areaName").val(data[0].areaname);
    		$("#areaNo").val(data[0].areacode);
    		$("#areaCode").val(data[0].arealetter)
    	}
    })
    
    $("#btnSubmit").on('click', function(){
    	var areaname = $("#areaName").val();
    	var areacode = $("#areaNo").val();
    	var arealetter = $("#areaCode").val();
    	if (areaname == '' || areacode == '' || arealetter == '') {
    		return Feng.error("请填写完整...o！")
    	} 
    	$.ajax({
        	method: "GET",
        	url: Feng.ctxPath + "/area/arealist?pid=1",
        	success: function(data){
        		if (areaname == data[0].areaname && areacode == data[0].areacode && arealetter == data[0].arealetter){
        			return Feng.info("你没有进行更改哦...")
        		}
        		$.ajax({
                	method: "POST",
                	url: Feng.ctxPath + "/area/update",
                	data: {
                		"areaname": areaname,
                		"areacode": areacode,
                		"arealetter": arealetter,
                		"areatype": 1,
                		"id": 1
                	},
                	success: function(data){
                		if(data.code == 200){
                			Feng.info("修改成功!");
                		}
                	}
                })
        	}
        })
    	
    	
    })
    
    
});
