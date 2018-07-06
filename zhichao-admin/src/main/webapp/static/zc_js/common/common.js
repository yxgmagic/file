/* 自定义资源权限校验 */
function permissionsCheck(urlList){
	var result = "";
	 $.ajax({
         type: "post",
         url: "../permissions/permissionsCheck",
         data: {'urlList':JSON.stringify(urlList)},
         dataType: "json",
         async : false,//需设置同步请求，异步请求获取不到返回结果
         success: function(data){
        	 result = data;
         }
     });
	 return result;
}