package com.zhichao.service.platformConfig.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.service.core.util.DictUtil;
import com.zhichao.dal.platformConfig.LawEnforceManDao;
import com.zhichao.service.platformConfig.ILawEnforceManService;
import com.zhichao.beans.guns.Area;
import com.zhichao.beans.guns.Dict;
import com.zhichao.beans.guns.LawEnforceMan;
import com.zhichao.common.util.ToolUtil;
import com.zhichao.dal.mapper.AreaMapper;
import com.zhichao.dal.mapper.LawEnforceManMapper;

/**
 * <p>
 * 执法人员信息表 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-08
 */
@Service
public class LawEnforceManServiceImpl extends ServiceImpl<LawEnforceManMapper, LawEnforceMan> implements ILawEnforceManService {

	@Autowired
    private LawEnforceManDao lawEnforceManDao;

    @Autowired
    AreaMapper areaMapper;
	
	
	//用于录入匹配的字典列表
	//性别
	List<Dict> sexDictList = null;
	
	//学历
	List<Dict> edbgDictList = null;
	
	//职务
	List<Dict> dutyDictList = null;
		
	//政治面貌
	List<Dict> polDictList = null;
		
	//等级
	List<Dict> gradeDictList = null;

	
	{
		sexDictList = DictUtil.selectListByColname("sex");
		edbgDictList = DictUtil.selectListByColname("eduBg");
		dutyDictList = DictUtil.selectListByColname("lemDuty");
		polDictList = DictUtil.selectListByColname("PolCode");
		gradeDictList = DictUtil.selectListByColname("lemGrade");
	}
	
	/**
	 * 检查每行数据的是否正确
	 */
	private Map<String, Object> checkDataOfRow(HSSFRow row) {
		HSSFCell cell = null;  //单元格
		String cellString = null;  //单元格内容
		int rowNum = row.getRowNum();  //当前这一行的行号
		StringBuilder errData = new StringBuilder("第"+rowNum+"行:");  //错误信息
		LawEnforceMan lawEnforceMan = new LawEnforceMan();  //如果成功,用于返回的对象
		boolean pass = true;  //用于标记是否通过验证的变量
		int temp = 0;  //临时变量,记录查询条数,来判断数据库是否有重复的记录
		int i = 0;  //用于循环的变量
		int length = 0;  //记录字典的长度
		
		
		//工作编号
		cell = row.getCell(0);
		cellString = cell.getStringCellValue();
		if("工作编号".equals(cellString)) {
			return msgMap(2,"表头","工作编号");
		}
		temp = lawEnforceManDao.check("", cellString, "0");
		if(temp > 0) {
			errData.append("工作编号重复,");
			pass = false;
		} else {
			lawEnforceMan.setLemNum(cellString);
		}
		
		//姓名
		cell = row.getCell(1);
		cellString = cell.getStringCellValue();
		if(ToolUtil.isEmpty(cellString)) {
			errData.append("姓名为空,");
			pass = false;
		} else {
			lawEnforceMan.setLemName(cellString);
		}
		
		//性别
		cell = row.getCell(2);
		cellString = cell.getStringCellValue();
		length = sexDictList.size();
		//对字典列表进行遍历
		for(i = 0; i < length; i++) {
			Dict tempDict = sexDictList.get(i);
			//找到符合的字典,将相对应的字典值存入对象
			if(tempDict.getName().equals(cellString)){
				lawEnforceMan.setLemSex(tempDict.getNum());
				i--;
				break;
			}
		}
		
		//如果整个字典都跑完了,没有匹配到,则返回错误
		if(i == length) {
			errData.append("性别不正确,");
			pass = false;
		}

		//出生年月
		cell = row.getCell(3);
		cellString = cell.getStringCellValue(); 
		if(Pattern.matches("^\\d{4}-[0-9]{0,2}-[0-9]{0,2}$", cellString)) {
			lawEnforceMan.setLemBirthday(cellString);
		} else {
			errData.append("出生日期格式不正确,");
			pass = false;
		}
		
		
		//学历
		cell = row.getCell(4);
		cellString = cell.getStringCellValue();
		length = edbgDictList.size();
		//对字典列表进行遍历
		for(i = 0; i < length; i++) {
			Dict tempDict = edbgDictList.get(i);
			//找到符合的字典,将相对应的字典值存入对象
			if(tempDict.getName().equals(cellString)){
				lawEnforceMan.setLemEduBg(tempDict.getNum());
				i--;
				break;
			}
		}
		
		//如果整个字典都跑完了,没有匹配到,则返回错误
		if(i == length) {
			errData.append("学历不正确,");
			pass = false;
		}
		
		
		//政治面貌
		cell = row.getCell(5);
		cellString = cell.getStringCellValue();
		length = polDictList.size();
		//对字典列表进行遍历
		for(i = 0; i < length; i++) {
			Dict tempDict = polDictList.get(i);
			//找到符合的字典,将相对应的字典值存入对象
			if(tempDict.getName().equals(cellString)){
				lawEnforceMan.setLemPoliticsStatus(tempDict.getNum());
				i--;
				break;
			}
		}
		
		//如果整个字典都跑完了,没有匹配到,则返回错误
		if(i == length) {
			errData.append("政治面貌不正确,");
			pass = false;
		}
		
		
		//身份证
		cell = row.getCell(6);
		cellString = cell.getStringCellValue();
		//先匹配身份证格式
		if(Pattern.matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$", cellString)) {
			//到数据库查,是否有重复的
			temp = lawEnforceManDao.check(cellString, "", "0");
			if(temp > 0) {
				errData.append("身份证号重复,");
				pass = false;
			} else {
				lawEnforceMan.setLemIdCardNum(cellString);
			}
		} else {
			errData.append("身份证格式不正确,");
			pass = false;
		}
		
		
		//执法单位名称
		cell = row.getCell(7);
		cellString = cell.getStringCellValue();
		lawEnforceMan.setLawEnforcementAgencies(cellString);
		
		//手机号码
		cell = row.getCell(8);
		cellString = cell.getStringCellValue();
		//^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,2,5-9]))\\d{8}$
		if(Pattern.matches("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,2,5-9]))\\d{8}$", cellString)) {
			lawEnforceMan.setLemContact(cellString);
		} else {
			errData.append("手机格式不正确,");
			pass = false;
		}
		
		//职务
		cell = row.getCell(9);
		cellString = cell.getStringCellValue();
		length = dutyDictList.size();
		//对字典列表进行遍历
		for(i = 0; i < length; i++) {
			Dict tempDict = dutyDictList.get(i);
			//找到符合的字典,将相对应的字典值存入对象
			if(tempDict.getName().equals(cellString)){
				lawEnforceMan.setLemDuty(tempDict.getNum());
				i--;
				break;
			}
		}
		
		//如果整个字典都跑完了,没有匹配到,则返回错误
		if(i == length) {
			errData.append("职务不正确,");
			pass = false;
		}
		
		
		
		//执法人员等级
		cell = row.getCell(10);
		cellString = cell.getStringCellValue();
		length = gradeDictList.size();
		//对字典列表进行遍历
		for(i = 0; i < length; i++) {
			Dict tempDict = gradeDictList.get(i);
			//找到符合的字典,将相对应的字典值存入对象
			if(tempDict.getName().equals(cellString)){
				lawEnforceMan.setLemGrade(Integer.parseInt(tempDict.getNum()));
				i--;
				break;
			}
		}
		
		//如果整个字典都跑完了,没有匹配到,则返回错误
		if(i == length) {
			errData.append("执法人员等级不正确,");
			pass = false;
		}
		
		//行政区域编码
		cell = row.getCell(11);
		cellString = cell.getStringCellValue();
		if(Pattern.matches("^\\d{6}$", cellString)) {

			EntityWrapper<Area> ew = new EntityWrapper<Area>();
			ew.eq("areacode",cellString);
            List<Area> areas = areaMapper.selectList(ew);
            if(areas.size() >= 1) {
//                System.out.println(areas.get(0));
                lawEnforceMan.setAreacode(cellString);
            } else {
                errData.append("区域编码不存在,");
                pass = false;
            }


        } else {
			errData.append("区域编码格式不正确,");
			pass = false;
		}
		
		if(pass) {
			return msgMap(0, "通过", lawEnforceMan);
		} else {
			return msgMap(1, "数据不通过", errData);
		}
		
	}


	/**
	 * 根据传经来的文件,将文件内的人员信息添加到数据库
	 * @param file	传来的文件
	 * @param fileName	文件名
	 * @return
	 */
	@Override
	public Map<String, Object> saveInfo(MultipartFile file, String fileName) {

		List<LawEnforceMan> lefmList = new ArrayList<LawEnforceMan>();
		List<String> errList = new ArrayList<String>();
    	
    	File f = null;
    	try {
    	    f=File.createTempFile("tmp", null);
    	    file.transferTo(f);
    	    f.deleteOnExit(); 

			HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(f));
    	    
    		//读取默认第一个sheet
			HSSFSheet sheet = workbook.getSheetAt(0);

			//得到当前工作表的物理行数
			int lastRowNum = sheet.getPhysicalNumberOfRows();
			
			
			for (int i = 0; i < lastRowNum; i++) {

				//得到当前行
				HSSFRow row = sheet.getRow(i);
				//拿到验证完之后的结果
				Map<String, Object> rowResult = checkDataOfRow(row);
				Integer codeStatus = (Integer)rowResult.get("code");
				if(codeStatus == 0) {
					lefmList.add((LawEnforceMan) rowResult.get("data"));
				} else if(codeStatus == 1) {
					errList.add(rowResult.get("data").toString());
				}

			}
    		
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    	
    	if(errList.size() > 0) {
    		return msgMap(400, "上传的数据出错", errList);
    	} 


    	int lenght = lefmList.size();
        for(int i = 0; i < lenght; i++) {
            this.insert(lefmList.get(i));
        }

    	return msgMap(200, "成功", lefmList);
    	
	}
	
	/**
     * 生成返回的对象
     */
    public Map<String, Object> msgMap(Integer code, Object message, Object data) {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("code", code);
		msg.put("msg", message);
		msg.put("data", data);
		return msg;
	}

}
