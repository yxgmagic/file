// 【初始化操作对象】
var Module = {
	// 模态框
	Box: { }
}
// -----------------------------Box 模态框------------------------------------
// 【弹出错误信息】
/** 参数：
* msg 		: 要显示的错误信息
* autoClose : 是否自动关闭
* callback  : 回调函数
*/
Module.Box.ShowErr = function(msg, autoClose, callback){
	if (autoClose == undefined || autoClose == null) {
		autoClose = true;
	}
	var CallBack = { };
	if (!callback) {
		CallBack = function () {
			Module.Box.Close();
		}
	} else {
		CallBack = function () {
			callback();
			Module.Box.Close();
		}
	}
	if (autoClose) {
		return layer.msg(msg, { icon: 2, anim: 6, time: 2000, end: CallBack})
	} else {
		return layer.alert(msg, { 'title': '提示', closeBtn: 0, shade: [0, '#fff'], icon: 2 }, CallBack)
	}
}
// 【弹出错误信息框】
/** 参数：
* msg 		: 要显示的错误信息
* autoClose : 是否自动关闭
* callback  : 回调函数
*/
Module.Box.AlertErr = function(msg, autoClose, callback){
	if (autoClose == undefined || autoClose == null) {
		autoClose = true;
	}
	var CallBack = { };
	if (!callback) {
		CallBack = function () {
			Module.Box.Close();
		}
	} else {
		CallBack = function () {
			callback();
			Module.Box.Close();
		}
	}
	console.log(autoClose)
	if (autoClose) {
		return layer.alert(msg, { 'title': '提示', closeBtn: 0, shade: [0, '#fff'], time: 2000, icon: 2 }, CallBack)
	} else {
		return layer.alert(msg, { 'title': '提示', closeBtn: 0, shade: [0, '#fff'], icon: 2 }, CallBack)
	}
}
// 【弹出成功消息】
/** 参数：
* msg 		: 要显示的成功信息
* autoClose : 是否自动关闭
* callback  : 回调函数
* isTop     : 是否置顶
*/
Module.Box.ShowSuccess = function(msg, autoClose, callback, isTop){
	if (autoClose == undefined || autoClose == null) {
		autoClose = true;
	}
	var CallBack = { };
	if (!callback) {
		CallBack = function(){
			Module.Box.Close();
		}
	} else {
		CallBack = function () {
			callback();
			Module.Box.Close();
		}
	}
	if (autoClose) {
		if (isTop) {
			return top.layer.msg(msg, { icon: 1, anim: 0, time: 2000, end: CallBack });
		} else {
			return layer.msg(msg, { icon: 1, anim: 0, time: 2000, end: CallBack });
		}
	} else {
		return layer.alert(msg, { 'title': '提示', closeBtn: '1', shade: [0, '#fff'], icon: 1 }, CallBack)
	}
}
// 【加载动画】
/** 参数：
* msg 		: 要显示的信息
* callback  : 回调函数
*/
Module.Box.ShowLoading = function(msg, callback){
	if (!callback) {
		callback = null;
	}
	return layer.msg(msg, {
        icon: 16,
        shade: 0.01,
        time: '',
        end: callback
    });
}
// 【询问提示】
/** 参数：
* msg 		: 要显示的信息
* callback  : 点击确定的事件
* btn       : 确定按钮的名称(可选参数)
*/
Module.Box.Confirm = function (msg, callback, btn) {
    if (!btn) {
        btn = '确定';
    }
    layer.confirm(msg, {
        btn: [btn, '取消'],
        title: '提示',
        shade: [0.1, '#fff']
    }, function () {
        callback();
    });
}
//【弹出指定页面，以iframe方式弹出层】
/** 参数：
* width 	 : 打开窗口的宽度
* height     : 打开窗口的高度
* href       : 要打开的链接
* title      :显示的标题
* endCallback: 回调函数
*/
Module.Box.OpenDialog = function (width, height, href, title, endCallback) {
    data = {
        IsSuccess: 0
    };
    if (!endCallback) {
        endCallback = null;
    }
    console.log(href)
    layer.open({
        type: 2,
        area: [width + 'px', height + 'px'],
        fixed: true, //不固定
        content: href,
        resize: false,
        title: title,
        anim: 5,
        end: endCallback
    });
}

//【关闭弹出层，以非iframe方式弹层的】
/** 参数：
* index 	 : 若未指定index则关闭所有层
*/
Module.Box.Close = function (index) {
    if (index) {
        layer.close(index);
    } else {
        layer.closeAll();
    }
};

//【关闭弹出层，以iframe方式弹层的，与Module.Box.OpenDialog对应】
Module.Box.CloseDialog = function () {
    window.parent.layer.closeAll();
}