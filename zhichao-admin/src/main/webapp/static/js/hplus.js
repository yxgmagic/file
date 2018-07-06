//自定义js

//公共配置

$(document).ready(function() {

	// MetsiMenu
	$('#side-menu').metisMenu();
	// 打开右侧边栏
	$('.right-sidebar-toggle').click(function() {
		$('#right-sidebar').toggleClass('sidebar-open');
	});

	// 右侧边栏使用slimscroll
	$('.sidebar-container').slimScroll({
		height : '100%',
		railOpacity : 0.4,
		wheelStep : 10
	});

	// 打开聊天窗口
	$('.open-small-chat').click(function() {
		$(this).children().toggleClass('fa-comments').toggleClass('fa-remove');
		$('.small-chat-box').toggleClass('active');
	});

	// 聊天窗口使用slimscroll
	$('.small-chat-box .content').slimScroll({
		height : '234px',
		railOpacity : 0.4
	});

	// Small todo handler
	$('.check-link').click(function() {
		var button = $(this).find('i');
		var label = $(this).next('span');
		button.toggleClass('fa-check-square').toggleClass('fa-square-o');
		label.toggleClass('todo-completed');
		return false;
	});

	// 固定菜单栏
	$(function() {
		$('.sidebar-collapse').slimScroll({
			height : '100%',
			railOpacity : 0.9,
			alwaysVisible : false
		});
	});

	// 菜单切换
	$('.navbar-minimalize').click(function() {
		$("body").toggleClass("mini-navbar");
		SmoothlyMenu();
	});

	// 侧边栏高度
	function fix_height() {
		var heightWithoutNavbar = $("body > #wrapper").height() - 61;
		$(".sidebard-panel").css("min-height", heightWithoutNavbar + "px");
	}
	fix_height();

	$(window).bind("load resize click scroll", function() {
		if (!$("body").hasClass('body-small')) {
			fix_height();
		}
	});

	// 侧边栏滚动
	$(window).scroll(function() {
		if ($(window).scrollTop() > 0 && !$('body').hasClass('fixed-nav')) {
			$('#right-sidebar').addClass('sidebar-top');
		} else {
			$('#right-sidebar').removeClass('sidebar-top');
		}
	});

	$('.full-height-scroll').slimScroll({
		height : '100%'
	});

	$('#side-menu>li').click(function() {
		if ($('body').hasClass('mini-navbar')) {
			NavToggle();
		}
	});
	$('#side-menu>li li a').click(function() {
		if ($(window).width() < 769) {
			NavToggle();
		}
	});

	// 点击菜单的时候高亮显示菜单
	$("a[name='tabMenuItem']").click(function() {
		clearTabMenuItem();
		$(this).addClass("tab-menu-selected");
	});

	$('.nav-close').click(NavToggle);

	// ios浏览器兼容性处理
	if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
		$('#content-main').css('overflow-y', 'auto');
	}

});

$(window).bind("load resize", function() {
	if ($(this).width() < 769) {
		$('body').addClass('mini-navbar');
		$('.navbar-static-side').fadeIn();
	}
});

function clearTabMenuItem() {
	$("a[name='tabMenuItem']").each(function() {
		$(this).removeClass("tab-menu-selected");
	});
}

function highLightMenuItem(hrefVal) {
	clearTabMenuItem();
	$("a[href='" + hrefVal + "']").addClass("tab-menu-selected");
}

function NavToggle() {
	$('.navbar-minimalize').trigger('click');
}

function SmoothlyMenu() {
	if (!$('body').hasClass('mini-navbar')) {
		$('#side-menu').hide();
		setTimeout(function() {
			$('#side-menu').fadeIn(500);
		}, 100);
	} else if ($('body').hasClass('fixed-sidebar')) {
		$('#side-menu').hide();
		setTimeout(function() {
			$('#side-menu').fadeIn(500);
		}, 300);
	} else {
		$('#side-menu').removeAttr('style');
	}
}

// 主题设置
$(function() {
	getPointSite1();
	getPointSite2();
//	text();
	// 顶部菜单固定
	$('#fixednavbar').click(
			function() {
				if ($('#fixednavbar').is(':checked')) {
					$(".navbar-static-top").removeClass('navbar-static-top')
							.addClass('navbar-fixed-top');
					$("body").removeClass('boxed-layout');
					$("body").addClass('fixed-nav');
					$('#boxedlayout').prop('checked', false);

					if (localStorageSupport) {
						localStorage.setItem("boxedlayout", 'off');
					}

					if (localStorageSupport) {
						localStorage.setItem("fixednavbar", 'on');
					}
				} else {
					$(".navbar-fixed-top").removeClass('navbar-fixed-top')
							.addClass('navbar-static-top');
					$("body").removeClass('fixed-nav');

					if (localStorageSupport) {
						localStorage.setItem("fixednavbar", 'off');
					}
				}
			});

	// 收起左侧菜单
	$('#collapsemenu').click(function() {
		if ($('#collapsemenu').is(':checked')) {
			$("body").addClass('mini-navbar');
			SmoothlyMenu();

			if (localStorageSupport) {
				localStorage.setItem("collapse_menu", 'on');
			}

		} else {
			$("body").removeClass('mini-navbar');
			SmoothlyMenu();

			if (localStorageSupport) {
				localStorage.setItem("collapse_menu", 'off');
			}
		}
	});

	// 固定宽度
	$('#boxedlayout').click(
			function() {
				if ($('#boxedlayout').is(':checked')) {
					$("body").addClass('boxed-layout');
					$('#fixednavbar').prop('checked', false);
					$(".navbar-fixed-top").removeClass('navbar-fixed-top')
							.addClass('navbar-static-top');
					$("body").removeClass('fixed-nav');
					if (localStorageSupport) {
						localStorage.setItem("fixednavbar", 'off');
					}

					if (localStorageSupport) {
						localStorage.setItem("boxedlayout", 'on');
					}
				} else {
					$("body").removeClass('boxed-layout');

					if (localStorageSupport) {
						localStorage.setItem("boxedlayout", 'off');
					}
				}
			});

	// 默认主题
	$('.s-skin-0').click(function() {
		$("body").removeClass("skin-1");
		$("body").removeClass("skin-2");
		$("body").removeClass("skin-3");
		return false;
	});

	// 蓝色主题
	$('.s-skin-1').click(function() {
		$("body").removeClass("skin-2");
		$("body").removeClass("skin-3");
		$("body").addClass("skin-1");
		return false;
	});

	// 黄色主题
	$('.s-skin-3').click(function() {
		$("body").removeClass("skin-1");
		$("body").removeClass("skin-2");
		$("body").addClass("skin-   3");
		return false;
	});

	if (localStorageSupport) {
		var collapse = localStorage.getItem("collapse_menu");
		var fixednavbar = localStorage.getItem("fixednavbar");
		var boxedlayout = localStorage.getItem("boxedlayout");

		if (collapse == 'on') {
			$('#collapsemenu').prop('checked', 'checked')
		}
		if (fixednavbar == 'on') {
			$('#fixednavbar').prop('checked', 'checked')
		}
		if (boxedlayout == 'on') {
			$('#boxedlayout').prop('checked', 'checked')
		}
	}

	if (localStorageSupport) {

		var collapse = localStorage.getItem("collapse_menu");
		var fixednavbar = localStorage.getItem("fixednavbar");
		var boxedlayout = localStorage.getItem("boxedlayout");

		var body = $('body');

		if (collapse == 'on') {
			if (!body.hasClass('body-small')) {
				body.addClass('mini-navbar');
			}
		}

		if (fixednavbar == 'on') {
			$(".navbar-static-top").removeClass('navbar-static-top').addClass(
					'navbar-fixed-top');
			body.addClass('fixed-nav');
		}

		if (boxedlayout == 'on') {
			body.addClass('boxed-layout');
		}
	}
});

// 判断浏览器是否支持html5本地存储
function localStorageSupport() {
	return (('localStorage' in window) && window['localStorage'] !== null)
}
// 计算元素集合的总宽度
function calSumWidth(elements) {
	var width = 0;
	$(elements).each(function() {
		width += $(this).outerWidth(true);
	});
	return width;
}
// 滚动到指定选项卡
function scrollToTab(element) {
	var marginLeftVal = calSumWidth($(element).prevAll()), marginRightVal = calSumWidth($(
			element).nextAll());
	// 可视区域非tab宽度
	var tabOuterWidth = calSumWidth($(".content-tabs").children().not(
			".J_menuTabs"));
	// 可视区域tab宽度
	var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
	// 实际滚动宽度
	var scrollVal = 0;
	if ($(".page-tabs-content").outerWidth() < visibleWidth) {
		scrollVal = 0;
	} else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(
			element).next().outerWidth(true))) {
		if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
			scrollVal = marginLeftVal;
			var tabElement = element;
			while ((scrollVal - $(tabElement).outerWidth()) > ($(
					".page-tabs-content").outerWidth() - visibleWidth)) {
				scrollVal -= $(tabElement).prev().outerWidth();
				tabElement = $(tabElement).prev();
			}
		}
	} else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(
			element).prev().outerWidth(true))) {
		scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
	}
	$('.page-tabs-content').animate({
		marginLeft : 0 - scrollVal + 'px'
	}, "fast");
}

function menuItem(longitude, latitude, siteType) {
	// 获取标识数据
	var dataUrl = purl + "/gismonitor?longitude=" + longitude + "&latitude="
			+ latitude + "&siteType=" + siteType, dataIndex = $(this).data(
			'index'), menuName = "GIS地图监控", flag = true;
	if (dataUrl == undefined || $.trim(dataUrl).length == 0)
		return false;
	// 选项卡菜单已存在
	$('.J_menuTab').each(
			function() {
				if ($(this).data('id') == dataUrl) {
					if (!$(this).hasClass('active')) {
						$(this).addClass('active').siblings('.J_menuTab')
								.removeClass('active');
						scrollToTab(this);
						// 显示tab对应的内容区
						$('.J_mainContent .J_iframe').each(function() {
							if ($(this).data('id') == dataUrl) {
								$(this).show().siblings('.J_iframe').hide();
								$(this).attr('src', $(this).attr('src'));
								return false;
							}
						});
					}
					flag = false;
					return false;
				}
			});

	// 选项卡菜单不存在
	if (flag) {
		var str = '<a href="javascript:;" class="active J_menuTab" data-id="'
				+ dataUrl + '">' + menuName
				+ ' <i class="fa fa-times-circle"></i></a>';
		$('.J_menuTab').removeClass('active');
		// 添加选项卡对应的iframe
		var str1 = '<iframe class="J_iframe" name="iframe' + dataIndex
				+ '" width="100%" height="100%" src="' + dataUrl
				+ '" frameborder="0" data-id="' + dataUrl
				+ '" seamless></iframe>';
		$('.J_mainContent').find('iframe.J_iframe').hide().parents(
				'.J_mainContent').append(str1);
		$('.J_menuTabs .page-tabs-content').append(str);
		scrollToTab($('.J_menuTab.active'));
	}
	return false;
}

/**
 * 配置项
 */
var overrunId = $(".overrunId").text();
var _checkno = "";
var text = function() {
	var overrunId = $(".overrunId").text();
	// console.log(overrunId);
	$
			.ajax({
				method : "POST",
				url : purl + "/gismonitor/selOverRun",
				data : {
					checkno : overrunId
				},
				success : function(result) {

					if (result != "" && result != null && result) {

						if (overrunId.toString() !== result.checkno.toString()) {
							console.log('result', result.checkno);
							$(".overrunId").text(result.checkno);
							var siteType;
							for ( var i in sitePoint1) {
								if (result.longitude == sitePoint1[i].lng
										&& result.latitude == sitePoint1[i].lat) {
									siteType = 1;
								}
							}
							for ( var i in sitePoint2) {
								if (result.longitude == sitePoint2[i].lng
										&& result.latitude == sitePoint2[i].lat) {
									siteType = 2;
								}
							}
							top.layer
									.open({
										title : false,
										content : '<div class="topLayBox"><span style="font-weight: 800">'
												+ result.sitename
												+ '站点</span>有超限情况，超限车的车牌号为'
												+ result.vehicleid
												+ '<div style="text-align:right;"><a class="J_menuItem btn btn-primary " style="width: 50px;height: 26px;line-height: 10px;"'
												+ 'onclick="toGIS('
												+ result.longitude
												+ ','
												+ result.latitude
												+ ','
												+ siteType
												+ ')" '
												+ 'name="tabMenuItem">详情</a></div></div>',
										type : 1,
										skin : 'layui-layer-rim',
										area : [ '220px', '92px' ],
										offset : 'rb',
										shade : 0,
										time : 5000
									});
						}
						// text();
					}
					setTimeout(function() {
						text();
					}, 5000);
				},
				error : function(err) {
					setTimeout(function() {
						text();
					}, 50000);
				}
			})
};

function toGIS(longitude, latitude, siteType) {
	menuItem(longitude, latitude, siteType);
};
// function togis() {
// var mapaddress = {
// "lng": "113.087539",
// "lat": "27.833865",
// "siteType": 2
// }
// document.cookie = "mapaddress=" + JSON.stringify(mapaddress);
// menuItem();
// }

// 保存精检站点坐标
var sitePoint1 = [];
function getPointSite1() {
	sitePoint1 = [];
	$.ajax({
		url : purl + "/gismonitor/list",
		method : "GET",
		success : function(result) {
			if (result) {
				for ( var i in result) {
					var temp = {};
					temp.lng = result[i].longitude;
					temp.lat = result[i].latitude;
					sitePoint1.push(temp);
				}
			}
		}
	})
}
// 保存预检站点坐标
var sitePoint2 = [];
function getPointSite2() {
	sitePoint2 = [];
	$.ajax({
		url : purl + "/gismonitor/lists",
		method : "GET",
		success : function(results) {
			if (results) {
				for ( var j in results) {
					var temp = {};
					temp.lng = results[j].longitude;
					temp.lat = results[j].latitude;
					sitePoint2.push(temp);
				}
			}
		}
	})
}

/**
 * gis地图监控跳转数据管理
 */
function toLcsInfo() {
	layer.closeAll();
	var code = $(".sitecode")[0].textContent || '';
	var type = $(".sitetype")[0].textContent || '';
	menuItems(code, type);
}

// 跳转到立案办理
function menuItem1() {
	layer.closeAll();
	// 获取标识数据
	var dataUrl = purl + "/oefullinfo", dataIndex = 999, menuName = "立案办理", flag = true;
	if (dataUrl == undefined || $.trim(dataUrl).length == 0)
		return false;
	// 选项卡菜单已存在
	$('.J_menuTab').each(
			function() {
				if ($(this).data('id') == dataUrl) {
					if (!$(this).hasClass('active')) {
						$(this).addClass('active').siblings('.J_menuTab')
								.removeClass('active');
						scrollToTab(this);
						// 显示tab对应的内容区
						$('.J_mainContent .J_iframe').each(function() {
							if ($(this).data('id') == dataUrl) {
								$(this).show().siblings('.J_iframe').hide();
								$(this).attr('src', $(this).attr('src'));
								return false;
							}
						});
					}
					flag = false;
					return false;
				}
			});

	// 选项卡菜单不存在
	if (flag) {
		var str = '<a href="javascript:;" class="active J_menuTab" data-id="'
				+ dataUrl + '">' + menuName
				+ ' <i class="fa fa-times-circle"></i></a>';
		$('.J_menuTab').removeClass('active');
		// 添加选项卡对应的iframe
		var str1 = '<iframe class="J_iframe" name="iframe' + dataIndex
				+ '" width="100%" height="100%" src="' + dataUrl
				+ '" frameborder="0" data-id="' + dataUrl
				+ '" seamless></iframe>';
		$('.J_mainContent').find('iframe.J_iframe').hide().parents(
				'.J_mainContent').append(str1);
		$('.J_menuTabs .page-tabs-content').append(str);
		scrollToTab($('.J_menuTab.active'));
	}
	return false;
}

function menuItems(code, type) {
	if (type == "1") {
		var dataUrl = purl + "/lscinfo?stationid=" + code;
		var menuName = "精简站治超数据管理";
	} else {
		var dataUrl = purl + "/hspinfo?stationid=" + code;
		var menuName = "不停车预检数据管理";
	}
	// 获取标识数据
	var dataIndex = 45, flag = true;
	if (dataUrl == undefined || $.trim(dataUrl).length == 0)
		return false;
	// 选项卡菜单已存在
	$('.J_menuTab').each(
			function() {
				if ($(this).data('id') == dataUrl) {
					if (!$(this).hasClass('active')) {
						$(this).addClass('active').siblings('.J_menuTab')
								.removeClass('active');
						scrollToTabs(this);
						// 显示tab对应的内容区
						$('.J_mainContent .J_iframe').each(function() {
							if ($(this).data('id') == dataUrl) {
								$(this).show().siblings('.J_iframe').hide();
								$(this).attr('src', $(this).attr('src'));
								return false;
							}
						});
					}
					flag = false;
					return false;
				}
			});
	// 选项卡菜单不存在
	if (flag) {
		var str = '<a href="javascript:;" class="active J_menuTab" data-id="'
				+ dataUrl + '">' + menuName
				+ ' <i class="fa fa-times-circle"></i></a>';
		$('.J_menuTab').removeClass('active');
		// 添加选项卡对应的iframe
		var str1 = '<iframe class="J_iframe" name="iframe' + 45
				+ '" width="100%" height="100%" src="' + dataUrl
				+ '" frameborder="0" data-id="' + dataUrl
				+ '" seamless></iframe>';
		$('.J_mainContent').find('iframe.J_iframe').hide().parents(
				'.J_mainContent').append(str1);
		$('.J_menuTabs .page-tabs-content').append(str);
		scrollToTabs($('.J_menuTab.active'));
	}
	return false;
}
// 滚动到指定选项卡
function scrollToTabs(element) {
	var marginLeftVal = calSumWidths($(element).prevAll()), marginRightVal = calSumWidth($(
			element).nextAll());
	// 可视区域非tab宽度
	var tabOuterWidth = calSumWidths($(".content-tabs").children().not(
			".J_menuTabs"));
	// 可视区域tab宽度
	var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
	// 实际滚动宽度
	var scrollVal = 0;
	if ($(".page-tabs-content").outerWidth() < visibleWidth) {
		scrollVal = 0;
	} else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(
			element).next().outerWidth(true))) {
		if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
			scrollVal = marginLeftVal;
			var tabElement = element;
			while ((scrollVal - $(tabElement).outerWidth()) > ($(
					".page-tabs-content").outerWidth() - visibleWidth)) {
				scrollVal -= $(tabElement).prev().outerWidth();
				tabElement = $(tabElement).prev();
			}
		}
	} else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(
			element).prev().outerWidth(true))) {
		scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
	}
	$('.page-tabs-content').animate({
		marginLeft : 0 - scrollVal + 'px'
	}, "fast");
}
// 计算元素集合的总宽度
function calSumWidths(elements) {
	var width = 0;
	$(elements).each(function() {
		width += $(this).outerWidth(true);
	});
	return width;
}
/**
 * 读取cookie
 */
function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	if (arr = document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}
/**
 * 删除cookie
 */
function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if (cval != null)
		document.cookie = name + "=" + cval + ";expires=" + exp.toUTCString();
}