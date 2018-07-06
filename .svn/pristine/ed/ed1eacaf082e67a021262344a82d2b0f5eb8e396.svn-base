package com.zhichao.service.core.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 图片与二进制互转
 * @author imyzt
 *
 */
public class ImgHelper {

	static BASE64Encoder encoder = new sun.misc.BASE64Encoder();  
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
	
    /** 
     * 将图片转换成二进制 
     *  
     * @return 
     */  
    public static String getImageBinary(File file) {  
        BufferedImage bi;  
        try {  
            bi = ImageIO.read(file);  
            ByteArrayOutputStream baos = new ByteArrayOutputStream();  
            
            String fileName = file.getName();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            
            ImageIO.write(bi, suffix, baos);
            byte[] bytes = baos.toByteArray();  
  
            return encoder.encodeBuffer(bytes).trim();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
//    /** 
//     * 将二进制转换为图片 
//     *  
//     * @param base64String 
//     */  
//    static File base64StringToImage(String base64String) {  
//        try {  
//            byte[] bytes1 = decoder.decodeBuffer(base64String);  
//  
//            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);  
//            BufferedImage bi = ImageIO.read(bais);  
//            
////            File file = new File("e://QQ.jpg");// 可以是jpg,png,gif格式  
//            ImageIO.write(bi1, "jpg", file);// 不管输出什么格式图片，此处不需改动  
//            
//            return bi;
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        }  
//    }  
}
