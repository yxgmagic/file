function navBar(strData){
	var data;
	if(typeof(strData) == "string"){
		var data = JSON.parse(strData); //部分用户解析出来的是字符串，转换一下
	}else{
		data = strData;
	}	
	var ulHtml = '<ul class="layui-nav layui-nav-tree">';
	for(var i=0;i<data.length;i++){
		if(data[i].open){
			ulHtml += '<li class="layui-nav-item layui-nav-itemed">';
		}else{
			ulHtml += '<li class="layui-nav-item">';
		}
		if(data[i].list != undefined && data[i].list.length > 0){
			ulHtml += '<a href="javascript:;">';
			if(data[i].icon != undefined && data[i].icon != ''){
				if(data[i].icon.indexOf("icon-") != -1){
					ulHtml += '<i class="iconfont '+data[i].icon+'" data-icon="'+data[i].icon+'"></i>';
				}if(data[i].icon.indexOf("fa-")!=-1){
					ulHtml += '<i class="'+data[i].icon+'" data-icon="'+data[i].icon+'"></i>';
				}else{
					ulHtml += '<i class="layui-icon" data-icon="'+data[i].icon+'">'+data[i].icon+'</i>';
				}
			}
			ulHtml += '<cite>'+data[i].name+'</cite>';
			ulHtml += '<span class="layui-nav-more"></span>';
			ulHtml += '</a>';
			ulHtml += '<dl class="layui-nav-child">';
			for(var j=0;j<data[i].list.length;j++){
				if(data[i].list[j].target == "_blank"){
					ulHtml += '<dd><a href="javascript:;" data-url="'+data[i].list[j].url+'" target="'+data[i].list[j].target+'">';
				}else{
					ulHtml += '<dd><a href="javascript:;" data-url="'+data[i].list[j].url+'">';
				}
				if(data[i].list[j].icon != undefined && data[i].list[j].icon != ''){
					if(data[i].list[j].icon.indexOf("icon-") != -1){
						ulHtml += '<i class="iconfont '+data[i].list[j].icon+'" data-icon="'+data[i].list[j].icon+'"></i>';
					}if(data[i].list[j].icon.indexOf("fa-")!=-1){
						ulHtml += '<i class="'+data[i].list[j].icon+'" data-icon="'+data[i].list[j].icon+'"></i>';
					}else{
						ulHtml += '<i class="layui-icon" data-icon="'+data[i].list[j].icon+'">'+data[i].list[j].icon+'</i>';
					}
				}
				ulHtml += '<cite>'+data[i].list[j].name+'</cite></a></dd>';
			}
			ulHtml += "</dl>";
		}else{
			if(data[i].target == "_blank"){
				ulHtml += '<a href="javascript:;" data-url="'+data[i].url+'" target="'+data[i].target+'">';
			}else{
				ulHtml += '<a href="javascript:;" data-url="'+data[i].url+'">';
			}
			if(data[i].icon != undefined && data[i].icon != ''){
				if(data[i].icon.indexOf("icon-") != -1){
					ulHtml += '<i class="iconfont '+data[i].icon+'" data-icon="'+data[i].icon+'"></i>';
				}if(data[i].icon.indexOf("fa-")!=-1){
					ulHtml += '<i class="'+data[i].icon+'" data-icon="'+data[i].icon+'"></i>';
				}else{
					ulHtml += '<i class="layui-icon" data-icon="'+data[i].icon+'">'+data[i].icon+'</i>';
				}
			}
			ulHtml += '<cite>'+data[i].name+'</cite></a>';
		}
		ulHtml += '</li>';
	}
	ulHtml += '</ul>';
	return ulHtml;
}
