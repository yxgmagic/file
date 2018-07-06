package com.zhichao.admin.factory.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.beetl.core.GeneralVarTagBinding;

import com.zhichao.beans.constant.Enumeration;
import com.zhichao.service.common.constant.factory.ConstantFactory;
import com.zhichao.service.system.ICustomerDictService;
import com.zhichao.beans.guns.Dict;
import com.zhichao.core.util.SpringContextHolder;

/**
 * 下拉字典项
 *
 * @author lee
 * @version V1.0.0
 * @date 2017/12/18
 */
public class SelectDictTag extends GeneralVarTagBinding {
    // 默认选中项
    private String defaultSelected;
    // 显示“全部”的值
    private String needAll;
    // 显示“全部”的名称
    private String needAllName;
    // 是否禁用
    private String disabled;
    // 字典名称
    private String dictName;
    // 样式名称
    private String cssName;
    // 自定义参数
    private String params;

    private String id;

    private String name;
    // data-style样式
    private String dataStyle;

    // data-live-search 开启检索
    private String dataLiveSearch;

    private ICustomerDictService dictService = SpringContextHolder.getBean("customerDictService");

    private final String DB_DICT_PREFIX = "DB_";

    private final String ENUM_DICT_PREFIX = "ENUM_";

    @Override
    public void render() {
        Map attrs = (Map) args[1];
        dictName = (String) attrs.get("dictName");
        defaultSelected = String.valueOf(attrs.get("defaultSelected"));
        needAll = (String) attrs.get("needAll");
        needAllName = (String) attrs.get("needAllName");
        cssName = (String) attrs.get("cssName");
        params = (String) attrs.get("params");
        id = (String) attrs.get("id");
        name = (String) attrs.get("name");
        disabled = (String) attrs.get("disabled");
        dataStyle = (String) attrs.get("dataStyle");
        dataLiveSearch = (String) attrs.get("dataLiveSearch");

        StringBuffer sb = new StringBuffer();
        List<Enumeration> list = null;
        if (StringUtils.isNotBlank(dictName)) {
            if (dictName.startsWith(DB_DICT_PREFIX)) {
                list = dictService.searchDictByKey(dictName.substring(dictName
                        .indexOf(DB_DICT_PREFIX) + DB_DICT_PREFIX.length()), params);
            } else if (dictName.startsWith(ENUM_DICT_PREFIX)) {
                list = getDictEnums(dictName.substring(dictName.indexOf(ENUM_DICT_PREFIX) + ENUM_DICT_PREFIX.length()));
            } else {
                list = getDictList();
            }
        }

        if (list != null && list.size() != 0) {
            cssName = StringUtils.isBlank(cssName) ? "" : cssName;
            id = StringUtils.isBlank(id) ? "" : id;
            name = StringUtils.isBlank(name) ? "" : name;
            disabled = StringUtils.isBlank(disabled) ? "" : disabled;
            dataStyle = StringUtils.isBlank(dataStyle) ? "btn-default" : dataStyle;
            dataLiveSearch = StringUtils.isBlank(dataLiveSearch) ? "false" : dataLiveSearch;
            sb.append("<select data-live-search='" + dataLiveSearch + "' data-style='" + dataStyle + "' id='" + id + "' name='" + name + "' class='" + cssName + "' " + disabled + " >");

            if (StringUtils.isBlank(needAll)) {
                if (needAllName == null) {
                    needAllName = "全部";
                } else {
                    sb.append("<option value='" + needAll + "'>" + needAllName + "</option>");
                }
            }
            for (Enumeration element : list) {
                String code = element.getCode();
                String name = element.getName();
                sb.append("<option value=\"" + code + "\"");
                if (defaultSelected != null
                        && defaultSelected.toString().equals(code)) {
                    sb.append(" selected ");
                }
                sb.append(">" + name + "</option>");
            }
            sb.append("</select>");
        }

        try {
            ctx.byteWriter.writeString(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Enumeration> getDictEnums(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        List<Enumeration> result = null;
        List list = null;

        if (list != null && list.size() != 0) {
            result = new ArrayList<>();

        }
        return result;
    }

    private List<Enumeration> getDictList() {
        if (StringUtils.isBlank(dictName)) {
            return null;
        }
        List<Enumeration> result = null;
        List<Dict> list = ConstantFactory.me().findInDict(dictName);
        if (list != null && list.size() != 0) {
            result = new ArrayList<>();
            for (Dict dict : list) {
                Enumeration enumeration = new Enumeration(dict.getNum()
                        + "", dict.getName());
                result.add(enumeration);
            }
        }
        return result;
    }
}
