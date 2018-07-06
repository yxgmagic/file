/**
 * JavaScript功能性插件.
 * @author by fengshuonan
 * @time 2017-12-1
 * @type {{}}
 */
var Yang = {};

//时间转换
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

Array.prototype.contains = function(item){
    return RegExp("\\b"+item+"\\b").test(this);
};

Yang.Tools = {
    /**
     * 计算罚款金额
     * 超限量,核定载质量
     */
    calcFined : function(overlimited,ratedloading) {
        var rate = Math.round((overlimited / ratedloading) * 100);
        var result = 0;
        if (rate === 0) {
            result = 0;
        } else if (rate > 0 && rate < 30) {
            result = 200;
        } else if (rate >= 30 && rate < 50) {
            result = 500;
        } else if (rate >= 50 && rate < 100) {
            result = 1000;
        } else if (rate > 100) {
            result = 2000;
        } else {
            result = -1;
        }
        return result;
    },
    /**
     * 时间日期范围选择控件
     * tp:类型，true为日期区间，year，month，time,datetime
     * rg:分隔符，默认为'~'
     * max,min:最大最小区间，默认'1970-01-01 00:00:00-当前时间'
     */
    date_interval:function(id,tp,rg,min,max){
        id = (tp === null) || (tp === 0) || (typeof $("#"+id) === "undefined") ? 'time' : id;
        tp = (typeof tp === "undefined") || (tp === null) || (tp === 0) ? true : tp;
        rg = (typeof rg === "undefined") || (rg === null) || (rg === 0) ? '~' : rg;
        max = (typeof max === "undefined") || (max === null) || (max === 0) ? new Date() : max;
        min = (typeof min === "undefined") || (min === null) || (min === 0) ? '1970-01-01 00:00:00' : min;
        layui.use('laydate', function(){
            var arr = tp === true ? {elem: '#'+id,range: rg,max: ''+max,min: ''+min} : {elem: '#'+id,type: tp,range: rg,max: ''+max,min: ''+min}
            var laydate = layui.laydate;
            laydate.render(arr);
        });
    },
    /**
     * 时间选择控件
     * @param id input id 默认time
     * @param tp 类型,默认带时分秒, == null 年月日时分秒
     * @param max 最大时间,默认当前
     * @param min 最小时间,默认1970-01-01 00:00:00
     */
    date:function (id,tp,max,min) {
        /**
         * 时间日期选择控件
         */
        id = typeof $("#"+id) === "undefined" ? 'time' : id;
        tp = (typeof tp === "undefined") || (tp === null) || (tp === 0) ? 'datetime' : tp;
        max = (typeof max === "undefined") || (max === null) || (max === 0) ? new Date() : max;
        min = (typeof min === "undefined") || (min === null) || (min === 0) ? '1970-01-01 00:00:00' : min;

        layui.use('laydate', function(){
            var laydate = layui.laydate;
            laydate.render({elem: '#'+id,type: tp,max: ''+max, min: ''+min});
        });
        return this;
    },
    /**
     * 轮播图
     * @param url 轮播图内容请求接口,返回base64数据
     * @param id 页面上显示的div
     */
    banner: function (url,id) {

        if (Yang.Util.isNotEmpty(url) || Yang.Util.isNotEmpty(id)){
            Feng.info('缺少必要参数!');
            return;
        }

        //初始化控件
        Yang.Util.initBannerHTML();


        //判断url是否以'/'开始,'/'结束
        url = /^\//.test(url) ? url : '/' + url;
        url = /\/$/.test(url) ? url : url + '/';

        // 初始化页面divid,生成banner图轮播
        layui.use([ 'carousel', 'form' ], function() {
            var carousel = layui.carousel, form = layui.form;
            $.ajax({
                type : "POST",
                url : Feng.ctxPath + url + id,
                success : function(data) {
                    if(data.length > 0) {
                        for (var i = 0; i < data.length; i++) {
                            $("#img_box").append("<img src='' alt='车头照片' id='img"+i+"'/>");
                            $("#img" + i)[0].src = "http://192.168.10.136:8080/"+data[i];
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
                    Feng.error("初始化图片数据失败,请重试!");
                }
            });

        });
    },
    /**
     * 将指定id文本框内容设置成当前时间
     * @param id 指定input
     * @param format 时间格式
     */
    setNowDate: function (id,format) {

        //判断是否传入id参数
        if (Yang.Util.isNotEmpty(id)){
            Feng.info('缺少必要参数!')
            return;
        }

        //如果格式为空,默认为yyyy-MM-dd HH:mm:ss
        format = Yang.Util.isNotEmpty(format) ? 'yyyy-MM-dd HH:mm:ss' : format;

        var now = new Date().Format(format);

        //设置时间为当前时间
        $("#"+id).val(now);

        return now;
    },
    /**
     * 为list内容添加逗号
     * @param arr
     * @returns {number}
     */
    addCommaToArr: function (arr) {

        var resultValue = -1;

        //对数组进行非空判断
        if (arr !== null){
            //清空resultValue,对其重新赋值
            resultValue = '';
            for (var i = 0; i < arr.length; i ++){
                if ('' != arr[i]){
                    resultValue += ',' + arr[i];
                }
            }
            resultValue = resultValue.substring(1);
        }
        return resultValue
    },
    /**
     * TODO 错误方法，请勿使用
     * 将Date格式的时间转化成指定format格式的字符串时间
     * @param date Date()
     * @param format 格式
     */
    dateFormat: function (date,format) {
                //判断是否传入date参数
        format = Yang.Util.isNotEmpty(format) ? 'yyyy-MM-dd HH:mm:ss' : format;
        if (null === date){
            Feng.info('缺少必要参数!');
            return;
        }
        return date.prototype.Format(format);
    },
    /**
     * 验证表单数据,正确返回true
     * @param id 表单id
     * @returns {*}
     */
    checkFormDate: function (id) {
        id = (typeof $("#"+id) === "undefined") ? 'form' : id;

        $('#'+id).bootstrapValidator('validate');
        var bootstrapValidator = $('#'+id).data('bootstrapValidator');

        //验证信息正确性
        return bootstrapValidator.isValid()
    },
    /**
     * 向指定接口发送请求.根据内容生成下拉框
     * @param id 页面元素id
     * @param url 指定接口,返回格式必须由id和name字段
     * @param defSel 默认选中
     */
    createSelElement: function (id,url,defSel) {
        id = (typeof $("#"+id) === "undefined") ? 'sel' : id
        if (Yang.Util.isNotEmpty(url)){
            Feng.info('缺少必要参数')
            return;
        }
        var date = Yang.Util.AJAX(url)
        if (date !== -1){
            $('#'+id).empty()
            for (var i in date){
                if (date[i].name != "") {
                    $('#' + id).append("<option value='" + date[i].id + "'>" + date[i].name + "</option>")
                }
            }
            if (defSel != null && defSel != ''){
                $('#'+id).val(defSel);
            }
        }
    },
    /**
     * 获取鼠标坐标
     * @param e
     * @returns {Array}
     */
    position: function (e) {
        var x = e.originalEvent.x || e.originalEvent.layerX || 0;
        var y = e.originalEvent.y || e.originalEvent.layerY || 0;

        var pos = [];
        pos[0] = x + 20;
        pos[1] = y + 20;

        return pos;
    },
    /**
     * bootstrap Validator
     * @param form 表单id
     * @returns {*}
     */
    bsValidator: function (form) {

        $('#'+form).bootstrapValidator('validate');
        var bootstrapValidator = $('#'+form).data('bootstrapValidator');
        var validate = bootstrapValidator.validate().getInvalidFields();
        if(validate.length > 0){
            validate[0].focus();
        }
        return bootstrapValidator.isValid();
    },
    /**
     * 检查文件格式是否符合
     * @param fileName 文件名
     * @param key 参数表key
     * @returns {boolean}
     */
    checkFileFormat: function (fileName, key) {

        var etx = Yang.Util.AJAX('/oefullinfo/getParaValue/' + key);
        var suffix = fileName.substring(fileName.indexOf('.') + 1);
        var etxarr = etx.split(',');
        for (var i in etxarr){
            if (etxarr[i] === suffix){
                return true;
            }
        }
        return etx === suffix;
    },
    /**
     * 关闭当前页面,仅适用于 {layer.open()} 方式打开的页面
     */
    closeCurrentWindow: function () {
        var index = parent.layer.getFrameIndex(window.name);//获取子窗口索引
        parent.layer.close(index);
    },
    /**
     * 后台分页翻页后使序号连续
     * @param id 表格id
     */
    serial: function (tableId,index) {
        var pageSize=$('#'+tableId).bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
        var pageNumber=$('#'+tableId).bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
        return pageSize * (pageNumber - 1) + index + 1;
    },
    /**
     * 文件下载(不打开新页面)
     * @param url 下载url
     * @param obj 下载传递参数
     * [
         {
             name: 'oeid',
             value: oeid
         },
         {
             name: 'ldtypes[]',
             value: arr
         }
     ];
     */
    download: function (url,obj) {
        var eleForm = $("<form method='get'></form>");

        eleForm.attr("action", url);

        if (obj != null && obj.length > 0){
            for (var i = 0; i < obj.length; i ++){
                var input = $("<input>");
                input.attr("type", "hidden");
                input.attr("name", obj[i].name);
                input.attr("value", obj[i].value);
                eleForm.append(input);
            }
        }

        $(document.body).append(eleForm);

        //提交表单，实现下载
        eleForm.submit();
    }

};

/**
 * 为Tools提供服务的Util对象
 * @type {{initBannerHTML: Yang.Util.initBannerHTML, isNotEmpty: Yang.Util.isNotEmpty}}
 */
Yang.Util = {
    initBannerHTML: function () {
        $("#banner").addClass('row row-bottom');
        $('#banner').append("<div class='layui-carousel' id='photo' style='margin: auto'>\n" +
            "                 <div carousel-item='' id='img_box'>\n" +
            "                 </div>\n" +
            "            </div>")
    },
    isNotEmpty: function (str) {
        return (typeof str == "undefined") || (str == null) || (str.trim() == '') ? true : false;
    },
    datePrototypeFormat: function () {

    },
    /**
     * 封装的AJAX方法.用于仅请求数据的AJAX请求.节省代码
     * @param url 请求地址
     * @returns {number} -1表示获取未成功
     * @returns {number} -2表示获取数据失败
     * @constructor
     */
    AJAX: function (url, reqParam) {
        reqParam = reqParam === null || typeof reqParam === 'undefined' ? [] : reqParam;
        var result = -1;
        if (!Yang.Util.isNotEmpty(url)) {
            //为url前后加 /
            url = /^\//.test(url) ? url : '/' + url;
            // url = /\/$/.test(url) ? url : url + '/';

            var ajax = new $ax(Feng.ctxPath + url, function (data) {
                result = data
            }, function (data) {
                result = -2;
            });
            if (reqParam.length > 0) {
                for (var i = 0; i < reqParam.length; i++) {
                    ajax.set(reqParam[i].name, reqParam[i].data);
                }
            }
            ajax.start();
        }
        return result
    },

}

Yang.Const = {
    /**
     * 案件相关状态的数组
     * 案件索引对应案件数组中的内容
     */
    CASEArr: [0, '未立案','立案待审批','已立案','已结案','驳回'],
    /**
     * 未立案
     */
    UNREGISTERED: 1,

    /**
     * 立案待审批
     */
    WAITAPPROVAL: 2,

    /**
     * 已立案
     */
    HASBEENREGISTERED: 3,

    /**
     * 已结案
     */
    CLOSED: 4,

    /**
     * 驳回
     */
    TURNDOWN: 5
}