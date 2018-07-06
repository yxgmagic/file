 

function getArealist(str1,str2,url) {
	var areajson =null;
    var ajax = new $ax(Feng.ctxPath + url, function (data) {
       // Feng.success("成功!");
    	areajson=data;
    }, function (data) {
        Feng.error("失败!" + data.responseJSON.message + "!");
        
    });
 ajax.set("pid",str1);
 ajax.set("areatype",str2);
 ajax.set("roadcode",str1);
 ajax.set("areacode",str1);
 ajax.start();
return areajson;
 
};

/*
function test2() {
     $.ajax({
     	method: "GET",
     	url: "/road/roadlist",
     	success: function(data){
     		console.log(data)
     	}
     })
       
};
*/
 
 

	var json;
	var roadjson;
    window.onload = function() {  
	json= getArealist(null,null,"/area/arealist");
	
	roadjson= getArealist(null,null,"/road/roadlist");
        initArea("Select1","Select2","areacode");   
        
    }  
      
   
    function initArea( s1,s2,objname) { 
    	var areacode=document.getElementById(objname).value
       var cvalue="";
    	var avalue="";
        /*获取，引用三个下拉框 */  
        var dropElement1=document.getElementById(s1);   
        var dropElement2=document.getElementById(s2);   
     
      
        /*自定义一个方法 把传进来的对象清除 这里清除了三个下拉所有框的选项*/    
        removeDropDownList(dropElement1);  
        removeDropDownList(dropElement2);  
        var cOption = document.createElement("option");  
        cOption.value = "";  
        cOption.text = "所有地市";  
        dropElement1.add(cOption); 
    
        var cOption = document.createElement("option");  
        cOption.value = "";  
        cOption.text = "所有区县";  
        dropElement2.add(cOption);  
        if($("#Select3").length > 0){
            
        	  
        	var dropElement3=document.getElementById("Select3");  
            var cOption = document.createElement("option");  
            cOption.value = "";  
            cOption.text = "所有路网";  
            dropElement3.add(cOption); 
        }else{ }
        
         
        doDict(null,null,null,dropElement1,dropElement2,null);      
        
 
         for(var p = 0; p < json.length; p++){//遍历json数组时，这么写p为索引，0,1
				   var acode=json[p].areacode;
				   var acode_sub=areacode.substring(0,4)+'00';
               	   if(acode_sub==acode){
	               	  
	               	 doDict(null,acode_sub,null,dropElement1,dropElement2,null); 
	               	  for(i=0;i<dropElement1.length;i++){
					  if(dropElement1[i].value == acode_sub)
					    dropElement1[i].selected = true;
					    cvalue=acode_sub;
					}      
               	   } 
     
               
               }//for
         var flag=0;
         
        for(var p = 0; p < json.length; p++){//遍历json数组时，这么写p为索引，0,1
				   var acode=json[p].areacode;
				   var aname=json[p].areaname;
			 
 
               	  if(acode==areacode){
					for(i=0;i<dropElement2.length;i++){
					  if(dropElement2[i].value == areacode){
					    dropElement2[i].selected = true;
					    avalue=areacode;
	
						  flag=1;
					  }
					}

					
               	   }
               
               }//for
		  if($("#Select3").length > 0&& flag==1){
			    var dropElement3=document.getElementById("Select3");  
			    var rc=document.getElementById("roadcode");
			  //  removeDropDownList(dropElement3); 
			    
          	doDict(null,cvalue,avalue,dropElement1,dropElement2,dropElement3); 
          	for(i=0;i<dropElement3.length;i++){
          		
          		if(dropElement3[i].value == rc.value ){
          			dropElement3[i].selected = true;
          			
          		}
          	}
		  }
        if(flag==0){
        	selectCity("Select1","Select2",objname,flag);
        }
             
    }  
      
    function selectRoad(obj){
 
     
    	var se3=document.getElementById("Select3");
    	 
    	obj.value=se3.value;
 
    	

    }
    
      
    /** 
     * 当选择一个市的时候自动的添加该市下的区县
     */      
    function selectCity(s1,s2,obj,qureyflag) {  
    	var dropElement1=document.getElementById(s1);   
    	//获取选中项的内容  
    	var cityvalue=dropElement1.options[dropElement1.selectedIndex].value;  
    	var dropElement2=document.getElementById(s2);   
    	removeDropDownList(dropElement2);  
    	var cOption = document.createElement("option");  
    	cOption.value = "";  
    	cOption.text = "所有区县";  
    	dropElement2.add(cOption);  
    	if($("#Select3").length > 0){
    		var dropElement3=document.getElementById("Select3");  
    		removeDropDownList(dropElement3); 
    		var cOption = document.createElement("option");  
    		cOption.value = "";  
    		cOption.text = "所有路网";  
    		dropElement3.add(cOption); 
    		document.getElementById('roadcode').value= "";

    	}
    	if(cityvalue!=null&&cityvalue!=""){
    		doDict(null,cityvalue,null,dropElement1,dropElement2,null);  

    		obj.value=cityvalue.substring(0, 4);
    	}else{
    		obj.value="";
    	}
    }  
      
    /** 
     * 当选择一个区县的时候自动的添加该省下的路网
     */   
    function selectArea(s1,s2,obj,qureyflag) {  
    	  var ds1=document.getElementById(s1); 
        var ds2=document.getElementById(s2); 
          
            obj.value=ds2.value;
         
            if(""==obj.value&&qureyflag==0){
            	obj.value=ds1.value.substring(0, 4);
            }
            if($("#Select3").length > 0){
            	var dropElement3=document.getElementById("Select3");  
            	removeDropDownList(dropElement3); 
                var cOption = document.createElement("option");  
                cOption.value = "";  
                cOption.text = "所有路网";  
                dropElement3.add(cOption); 
            	doDict(null,ds1.value,ds2.value,ds1,ds2,dropElement3); 
            	document.getElementById('roadcode').value= "";
 
           }else{ }
    }  
      
      
    
    function doDict(obj,cityvalue,areavalue,dropElement1,dropElement2,dropElement3) {  
   	           
               if(cityvalue == null || cityvalue == ""){
               for(var p = 0; p < json.length; p++){//遍历json数组时，这么写p为索引，0,1
				   var areacode1=json[p].areacode;
				   var areaname1=json[p].areaname;
				   var citycode=areacode1.substring(4,6);
               	   if(citycode=='00'&&areacode1.substring(3,6)!='000'){
               	   var pOption = document.createElement("option");  
			          pOption.value = areacode1;  
			          pOption.text = areaname1;  
			          dropElement1.add(pOption);
               	   }
                }//for
               }else  if(areavalue == null || areavalue == ""){
                for(var p = 0; p < json.length; p++){//遍历json数组时，这么写p为索引，0,1
				   var areacode2=json[p].areacode;
				   var areaname2=json[p].areaname;
				   var areacode_temp=areacode2.substring(0,4)+'00';
               	   if(areacode_temp==cityvalue&&areacode2.substring(4,6)!='00'){
	               	  var pOption = document.createElement("option");  
				          pOption.value = areacode2;  
				          pOption.text = areaname2;  
				          dropElement2.add(pOption);
               	   }
               }//for
               }else  if(areavalue != null && areavalue != ""&&$("#Select3").length > 0){
          
                   for(var p = 0; p < roadjson.length; p++){//遍历json数组时，这么写p为索引，0,1
    				   var areacode3=roadjson[p].areacode;
    				   var roadcode3=roadjson[p].roadcode;
    				   var roadname3=roadjson[p].roadname;
    				  
                   	   if(areacode3==areavalue ){
                   		    
    	               	  var pOption = document.createElement("option");  
    				          pOption.value = roadcode3;  
    				          pOption.text = roadname3;  
    				          dropElement3.add(pOption);
                   	   }
                   }//for
               }
          
            
            
    }  
      
      
    /* 清除下拉框的选项 */  
    function removeDropDownList(obj) {  
        //如果obj不为空的时候  
        if(obj) {  
            var length = obj.options.length;  
            if(length > 0) {  
                for(var i=length;i >= 0;i--) {  
                    obj.remove(i);  
                }  
            }  
        }  
    }  
      
 