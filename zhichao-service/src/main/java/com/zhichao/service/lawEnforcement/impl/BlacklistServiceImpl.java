package com.zhichao.service.lawEnforcement.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.beans.guns.Blacklist;
import com.zhichao.common.util.ToolUtil;
import com.zhichao.common.util.YUtil;
import com.zhichao.dal.mapper.BlacklistMapper;
import com.zhichao.service.compreAnalysis.IBOefullinfoService;
import com.zhichao.service.lawEnforcement.IBlacklistService;

import sun.misc.BASE64Encoder;

/**
 * <p>
 * 黑白名单 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-23
 */
@Service
public class BlacklistServiceImpl extends ServiceImpl<BlacklistMapper, Blacklist> implements IBlacklistService {

	private static final int imgSize = 5 * 1024 * 1024;

	@Autowired
	BlacklistMapper lacklistMapper;
	
	@Autowired
    private IBOefullinfoService bOefullinfoService;
	
	@Override
	public List<String> getVehicleImages(String vehicleid) {

		Blacklist blacklist = lacklistMapper.getVehicleImages(vehicleid);
		List<String> resultList = new ArrayList<String>(); 
		if(!ToolUtil.isEmpty(blacklist)) {
			if(!YUtil.isNullOrEmpty(blacklist.getVehicleimage(), true) ) {
				resultList.add(blacklist.getVehicleimage());
			}
			if(!YUtil.isNullOrEmpty(blacklist.getVehicleimage1(), true) ) {
				resultList.add(blacklist.getVehicleimage1());
			}
			if(!YUtil.isNullOrEmpty(blacklist.getVehicleimage2(), true) ) {
				resultList.add(blacklist.getVehicleimage2());
			}

		}

		return resultList;
	}

	@Override
	public List<Map<String, Object>> getRecordList(String vehicleid) {
		
		return null;
	}

	@Override
	public List<Map<String, Object>> selList(String vehicleid, String drivername, String corpname) {
		return lacklistMapper.selList(vehicleid, drivername, corpname);
		
	}

	@Override
	public void updateBlackStatus(String id, String statusValue) {
		lacklistMapper.updateBlackStatus(id, statusValue);
	}

	@Override
	public int generateBlacklist(String time) {
		
		String begin = "";
		String end = "";
		if(!ToolUtil.isEmpty(time)) {
			begin = time + "-00-00 00:00:00";
			end = time + "-12-31 23:59:59";
		}
		
		
		//从立案表中获取超限次数大于3次的记录
		List<Blacklist> generateBlacklist = lacklistMapper.generateBlacklist(time, begin, end);
		//用于存入黑名单表的列表
		List<Blacklist> saveList = new ArrayList();
		
		//基本属性
		String vehicleid = "";
		List<String> corpNames;
		String corpName = "";
		int count = 0;
		
		//遍历从立案表中获取到的列表
		for(Blacklist temp : generateBlacklist) {
			
			//取出车牌号,为后面使用
			vehicleid = temp.getVehicleid();
			
			//利用车牌号在黑名单表里面查,是否有已存在的车牌号
			count = lacklistMapper.getRowCount(vehicleid);
			
			//如果没有已存在的,则将记录存入saveList
			if(count == 0) {
				
				//根据车牌号获取企业名称的结果集 corpNames
				corpNames = lacklistMapper.getCorpNameByvehicleid(vehicleid);
				//如果获取到企业名称,则存到corpName
				if(corpNames.size() > 0) {
					corpName = corpNames.get(0);
				}
				
				//设置一些基本的属性
				temp.setCorpname(corpName);
				temp.setIspunitive("1");
				temp.setIsblack("0");
				temp.setRemarks("一年里内违法三次");

				//将数据存到saveList 为后面插入黑名单数据使用
				saveList.add(temp);
			}
		}
		
		for(Blacklist temp : saveList) {
			lacklistMapper.insert(temp);
		}
		
		
		return saveList.size();
	}

	/**
	 * 修改新增事件
	 */
	@Override
	public Object insert(Blacklist entity,
						 MultipartFile image0,
						 MultipartFile image1,
						 MultipartFile image2)  throws IOException {
		if(ToolUtil.isNotEmpty(image0)) {
			Map<String, Object> tempMap = transferToBase64(image0);
			Integer codeStatus = (Integer) tempMap.get("code");
			String imgData = "";
			if(codeStatus == 0) {
				imgData = (String)tempMap.get("data");
				entity.setVehicleimage(imgData);
			} else {
				tempMap.put("code", 400);
				return tempMap;
			}
		}

		if(ToolUtil.isNotEmpty(image1)) {
			Map<String, Object> tempMap = transferToBase64(image1);
			Integer codeStatus = (Integer) tempMap.get("code");
			String imgData = "";
			if(codeStatus == 0) {
				imgData = (String)tempMap.get("data");
				entity.setVehicleimage1(imgData);
			} else {
				tempMap.put("code", 400);
				return tempMap;
			}
		}

		if(ToolUtil.isNotEmpty(image2)) {
			Map<String, Object> tempMap = transferToBase64(image2);
			Integer codeStatus = (Integer) tempMap.get("code");
			String imgData = "";
			if(codeStatus == 0) {
				imgData = (String)tempMap.get("data");
				entity.setVehicleimage2(imgData);
			} else {
				tempMap.put("code", 400);
				return tempMap;
			}
		}

		super.insert(entity);

		return msgMap(200, "OK", entity);
	}


	/**
	 * 将MultipartFile转换为base64返回
	 */
	public Map<String, Object> transferToBase64(MultipartFile file) throws IOException{
		if(ToolUtil.isNotEmpty(file)) {
			long fileSize = file.getSize();
			if(fileSize <= imgSize) {
				String fileName = file.getOriginalFilename();
				String suffix = fileName.substring(fileName.indexOf('.') + 1);
				String imgData = null;
				if (!"BMP".equals(suffix.toUpperCase())) {
					imgData = resize(file.getBytes(), 1024, 0.7f);
				}else {
					String baseStr = "";
					//转换成64字节码
					BASE64Encoder encoder = new BASE64Encoder();
					baseStr = encoder.encode(file.getBytes());
					imgData = baseStr;
				}
				return msgMap(0, "OK", imgData);
			}else {
				return msgMap(1, "上传图片不能超过5M", "上传图片不能超过5M");
			}
		} else {
			return msgMap(2, "图片文件不存在", "图片文件不存在");
		}
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
	
	 /**  
     * 压缩图片  
     * @param b 图片的字节流  
     * @param newWidth 压缩的宽度  
     * @param quality 压缩质量 0-1之间float类型  
     * @return  
     * @throws IOException  
     */  
    public static String resize(byte[] b,int newWidth, float quality){  
        if (quality > 1) {  
            throw new IllegalArgumentException("Quality has to be between 0 and 1");  
        }  
        
        String baseStr = "";  
        ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream(4096);
        try{  
            ImageIcon ii = new ImageIcon(b);  
            java.awt.Image i = ii.getImage();  
            java.awt.Image resizedImage = null;  
  
            int iWidth = i.getWidth(null);  
            int iHeight = i.getHeight(null);  
  
            if (iWidth > iHeight) {  
                resizedImage = i.getScaledInstance(newWidth,(newWidth * iHeight)  
                        / iWidth,  java.awt.Image.SCALE_SMOOTH);  
            } else {  
                resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight,  
                        newWidth, java.awt.Image.SCALE_SMOOTH);  
            }  
  
            java.awt.Image temp = new ImageIcon(resizedImage).getImage();  
  
            BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),  
                    temp.getHeight(null), BufferedImage.TYPE_INT_RGB);  
  
            Graphics g = bufferedImage.createGraphics();  
  
            g.setColor(Color.white);  
            g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));  
            g.drawImage(temp, 0, 0, null);  
            g.dispose();  
  
            float softenFactor = 0.05f;  
            float[] softenArray = {0, softenFactor, 0, softenFactor,  
                    1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0};  
            Kernel kernel = new Kernel(3, 3, softenArray);  
            ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);  
            bufferedImage = cOp.filter(bufferedImage, null);  
  

            byteArrayOutputStream = new ByteArrayOutputStream(4096);  
                    ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
  
            //转换成64字节码  
            BASE64Encoder encoder = new BASE64Encoder();  
            baseStr = encoder.encode(byteArrayOutputStream.toByteArray());  
        }catch (IOException e){  
            e.printStackTrace();  
        }  
        return baseStr;  
    }
}
