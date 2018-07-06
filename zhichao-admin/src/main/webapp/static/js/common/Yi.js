/**
 * @author 易杰斌
 */
Yi = {};

Yi.components = {
    /**
     * 获取当月时间段
     * @returns {string}  ：2018-03-01 00:00:00 ~ 2018-03-31 00:00:00
     */
    getNowMonthArea: function(){
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth() < 10 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1);
        var isYear = (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ? true : false;
        if (isYear && month == "02") {
            return year + "-" + month + "-01 00:00:00 ~ " + year + "-" + month + "-29 00:00:00";
        }
        var isMonth = (month === '01' || month === '03'|| month === '05'|| month === '07'|| month === '08'|| month === '10'|| month === '12') ? true : false;
        if(isMonth){
            return year + "-" + month + "-01 00:00:00 ~ " + year + "-" + month + "-31 00:00:00";
        } else {
            return year + "-" + month + "-01 00:00:00 ~ " + year + "-" + month + "-30 00:00:00";
        }
    },

    /**
     * 地图标注选点
     * options:
     *      lng - 当前经度
     *      lat - 当前纬度
     *      src - iframe的src
     *      lngId - 经度的input Id
     *      latId - 纬度的input Id
     *      inputId - 填写坐标的inputId,如果存在lngId和latId，则inputId无效
     */
    setMapMarker: function (options) {
        var isLngLat = (options.hasOwnProperty('lng') && options.hasOwnProperty('lat')) ? true : false;
        var isSrc = options.src ? true : false;
        var isLngIdLatId = (options.lngId && options.latId) ? true : false;
        var isInputId = options.inputId ? true : false;
        if (isLngLat && isSrc && isLngIdLatId) {
            var url = "/gismonitor/coordinate?lng=" + encodeURI(options.lng) +
                "&lat=" + encodeURI(options.lat) + "&src=" + encodeURI(options.src) +
                "&lngId=" + encodeURI(options.lngId) + "&latId=" + encodeURI(options.latId);
            top.layer.open({
                type: 2,
                title: '选择坐标',
                area: [ '700px', '460px' ],
                fix: true,
                maxmin: false,
                content: Feng.ctxPath + url
            })
        } else if (isLngLat && isSrc && !isLngIdLatId && isInputId) {
            // 只有一个文本框保存坐标
            var urls = "/gismonitor/coordinate?lng=" + encodeURI(options.lng) +
                "&lat=" + encodeURI(options.lat) + "&src=" + encodeURI(options.src) +
                "&inputId=" + encodeURI(options.inputId) + "&lnglat=" + encodeURI(options.lnglat);
            top.layer.open({
                type: 2,
                title: '选择坐标',
                area: [ '700px', '460px' ],
                fix: true,
                maxmin: false,
                content: Feng.ctxPath + urls
            })
        }
    },

    /**
     * 获取路线坐标
     */
    getLineData: function (data, val) {
        if (typeof data !== 'object') {
            return console.error('data数据有误')
        }
        if (val !== '' && val !== null) {
            data = data && data !== '' ? JSON.stringify(data) : '';
            top.layer.open({
                type: 2,
                title: '选择路线',
                area: ['1000px', '600px'],
                fix: true,
                maxmin: false,
                content: Feng.ctxPath + '/gismonitor/getLine?data=' + data
            })
        }
    },


    /**
     * 随机生成车牌号  湘·A1234
     */
    carPlate: function () {
        var shen = ['京',
            '津', '冀', '晋', '蒙',
            '辽', '吉', '黑', '沪',
            '苏', '浙', '皖', '闽',
            '赣', '鲁', '豫', '鄂',
            '湘', '粤', '桂', '琼',
            '川', '贵', '云', '渝',
            '藏', '陕', '甘', '青',
            '宁', '新', '港', '澳'];
        var letter = ['Q', 'W', 'E', 'R', 'T',
            'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D',
            'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X',
            'C', 'V', 'B', 'N', 'M'];
        var letters = ['A', 'D','F', 'G', 'C',  'B', 'E'];
        var suiji = 0;
        var _carPlate1 = shen[Math.floor(Math.random()*(shen.length))];
        var _carPlate2 = letters[Math.floor(Math.random()*(letters.length))];
        var _carPlate3 = letter[Math.floor(Math.random()*(letter.length))];
        suiji = Math.ceil(Math.random()*10);
        var _carPlate4 = suiji > 9 ? 9 : suiji;
        suiji = Math.ceil(Math.random()*10);
        var _carPlate5 = suiji > 9 ? 9 : suiji;
        suiji = Math.ceil(Math.random()*10);
        var _carPlate6 = suiji > 9 ? 9 : suiji;
        suiji = Math.ceil(Math.random()*10);
        var _carPlate7 = suiji > 9 ? 9 : suiji;

        return ''+_carPlate1 + _carPlate2 + _carPlate3 + _carPlate4 + _carPlate5 + _carPlate6 + _carPlate7;

    },

    /**
     * @description 判断节点下是否存在子节点
     * @param node 需要判断的节点
     * @returns {boolean}
     */
    judgeNodeChildren: function (node) {
        if (node.children) {
            if (node.children.length) {
                return true
            }
            return false
        }
        return false
    }

};

Yi.config = {
    siteCode: []
};