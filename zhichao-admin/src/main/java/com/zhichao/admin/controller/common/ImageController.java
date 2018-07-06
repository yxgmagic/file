package com.zhichao.admin.controller.common;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhichao.beans.guns.Image;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.service.common.IBsImageEntityService;
import com.zhichao.service.common.IImageService;

import sun.misc.BASE64Encoder;


/**
 * 公用图片上传方法控制器
 *
 * @author fengshuonan
 * @Date 2018-01-02 19:31:12
 */
@Controller
@RequestMapping("/image")
public class ImageController extends BaseController {

	private static final int imgSize = 5 * 1024 * 1024;
	

	@Autowired
	private IImageService imageService;
	@Autowired
	private IBsImageEntityService imageEntityService;


	/**
	 * 上传图片
	 * 返回文件base64编码
	 */
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public @ResponseBody Object upload(
			@RequestParam("file") MultipartFile file) throws IOException{

		if (file != null) {

			long fileSize = file.getSize();
			
			if(fileSize <= imgSize) {
				
				String uuid = UUID.randomUUID().toString().replace("-", "");
				String fileName = file.getOriginalFilename();

				String suffix = fileName.substring(fileName.indexOf('.') + 1);

				String imgData = null;
				if (!"BMP".equals(suffix.toUpperCase())) {
					//图片编码
					imgData = resize(file.getBytes(), 1024, 0.7f);
				}else {
					String baseStr = "";  
					 //转换成64字节码  
		            BASE64Encoder encoder = new BASE64Encoder();
		            baseStr = encoder.encode(file.getBytes());  
					imgData = baseStr;
				}
				
				Image img = new Image();
				img.setImgname(uuid + "." + suffix);
				img.setImgext(suffix);
				img.setImg(imgData);

				imageService.insertImage(img);

				return msgMap(200, img.getId());
			}else {
				return msgMap(400, "上传图片不能超过5M");
			}
		}
		return msgMap(404, "请选择上传的文件");
	}

	/**
	 * 修改时获取图片
	 * @param Id  实体对象id
	 * @param imagetype 照片来源类型
	 * @return
	 */
	@PostMapping("/findImg")
	public @ResponseBody Object findImg(
			@RequestParam(value = "entityId", required = true) Integer entityId,
			@RequestParam(value = "imagetype", required = true) String imagetype) {
		
		Image img = imageEntityService.findImg(entityId, imagetype);
		
		if (img != null) {
			return msgMap(200, img);
		}
		return msgMap(404, "未上传图片或图片未找到");
	}
	
	/*
	 * 上传base64图片编码字符串数据
	 */
	@RequestMapping(value="/uploadBase64",method=RequestMethod.POST)
    @ResponseBody
	public Object base64UpLoad(HttpServletRequest request) throws IOException{
		Image img = new Image();
	
		String base64Data="";
		
		base64Data=getRequestPostStr(request).replaceAll("base64Data=", "");
		
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String suffix="bmp";
		img.setImgname(uuid + "." + suffix);
		img.setImgext(suffix);
		img.setImg(base64Data.toString());

		imageService.insertImage(img);

		return msgMap(200, img.getId());
	}
	
	/*
	 * 获取HttpServletRequest数据流
	 */
	public static byte[] getRequestPostBytes(HttpServletRequest request)  
            throws IOException {  
        int contentLength = request.getContentLength();  
        /*当无请求参数时，request.getContentLength()返回-1 */  
        if(contentLength<0){  
            return null;  
        }  
        byte buffer[] = new byte[contentLength];  
        for (int i = 0; i < contentLength;) {  
  
            int readlen = request.getInputStream().read(buffer, i,  
                    contentLength - i);  
            if (readlen == -1) {  
                break;  
            }  
            i += readlen;  
        }  
        return buffer;  
    }  
  
    /*** 
     * 获取HttpServletRequest字符串
     *  
     * @param request 
     * @return 
     * @throws IOException 
     */  
    public static String getRequestPostStr(HttpServletRequest request)  
            throws IOException {  
        byte buffer[] = getRequestPostBytes(request);  
        String charEncoding = request.getCharacterEncoding();  
        if (charEncoding == null) {  
            charEncoding = "UTF-8";  
        }  
        return new String(buffer, charEncoding);  
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
	

	/**
	 * 返回消息类型
	 * @param code 消息码
	 * @param id 
	 * @param str 消息值
	 * @return
	 */
	public Map<String, Object> msgMap(Integer code, Object obj) {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("code", code);
		msg.put("msg", obj);
		return msg;
	}

}
