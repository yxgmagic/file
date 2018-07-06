package com.zhichao.service.system.impl;

import static com.zhichao.service.common.constant.factory.MutiStrFactory.MUTI_STR_KEY;
import static com.zhichao.service.common.constant.factory.MutiStrFactory.MUTI_STR_VALUE;
import static com.zhichao.service.common.constant.factory.MutiStrFactory.parseKeyValue;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhichao.beans.exception.BizExceptionEnum;
import com.zhichao.beans.exception.BussinessException;
import com.zhichao.dal.mapper.DictMapper;
import com.zhichao.service.core.util.DictUtil;
import com.zhichao.dal.system.DictDao;
import com.zhichao.service.system.IDictService;
import com.zhichao.beans.guns.Dict;

@Service
@Transactional
public class DictServiceImpl implements IDictService {

    @Resource
    DictDao dictDao;

    @Resource
    DictMapper dictMapper;

    @Override
    public void addDict(String dictName, String dictValues,String pname) {
        //判断有没有该字典
        List<Dict> dicts = dictMapper.selectList(new EntityWrapper<Dict>().eq("name", dictName).and().eq("pid", 0));
        if(dicts != null && dicts.size() > 0){
            throw new BussinessException(BizExceptionEnum.DICT_EXISTED);
        }

        //解析dictValues
        List<Map<String, String>> items = parseKeyValue(dictValues);

        //添加字典
        Dict dict = new Dict();
        dict.setName(dictName);
        dict.setPname(pname);
        dict.setNum("");
        dict.setPid(0);
        this.dictMapper.insert(dict);

        //添加字典条目
        for (Map<String, String> item : items) {
            String num = item.get(MUTI_STR_KEY);
            String name = item.get(MUTI_STR_VALUE);
            Dict itemDict = new Dict();
            itemDict.setPid(dict.getId());
            itemDict.setName(name);
            itemDict.setPname(pname);
            try {
                itemDict.setNum(num);
            }catch (NumberFormatException e){
                throw new BussinessException(BizExceptionEnum.DICT_MUST_BE_NUMBER);
            }
            this.dictMapper.insert(itemDict);
        }
        DictUtil.reflashDictList("addDict===dictName="+dictName+"==dictValues=="+dictValues+"===pname=="+pname);
    }

    @Override
    public void editDict(Integer dictId, String dictName, String dicts,String pname) {
        //删除之前的字典
        this.delteDict(dictId);

        //重新添加新的字典
        this.addDict(dictName,dicts,pname);
      //  DictUtil.reflashDictList("editDict==dictId="+dictId);
    }

    @Override
    public void delteDict(Integer dictId) {
        //删除这个字典的子词典
        Wrapper<Dict> dictEntityWrapper = new EntityWrapper<>();
        dictEntityWrapper = dictEntityWrapper.eq("pid", dictId);
        dictMapper.delete(dictEntityWrapper);

        //删除这个词典
        dictMapper.deleteById(dictId);
        DictUtil.reflashDictList("delteDict dictId=="+dictId);
    }
}
