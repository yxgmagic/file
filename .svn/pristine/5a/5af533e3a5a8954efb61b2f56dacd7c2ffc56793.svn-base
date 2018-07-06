package com.zhichao.service.core.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

public class PubAES{
	static String file =Class.class.getClass().getResource("/").getPath();
	
	private static void PublicKey()throws Exception {  
	 
	    //实例化Key  
	    KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");  
	    //获取一对钥匙  
	    KeyPair keyPair=keyPairGenerator.generateKeyPair();  
	    //获得公钥  
	    Key publicKey=keyPair.getPublic();  
	    //获得私钥   
	    Key privateKey=keyPair.getPrivate();  
	   
	    //将Key写入到文件  
	    saveKey(privateKey,file+"zxx_private.key");  
	    saveKey(publicKey,file+"zxx_public.key");  
	    System.out.println(file+"zxx_private.key");
	}  
	
	private static byte [] PublicEnrypt(String s1,String s2,String s3,String s4,String s5)throws Exception {  
	    Cipher cipher =Cipher.getInstance("RSA");  
	    //实例化Key  
	    KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");  
 
	    Key publicKey=readKey(file+"zxx_public.key");  
	    //获得私钥   
	    Key privateKey=readKey(file+"zxx_private.key");  
	    //用公钥加密  
	    cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
	    byte [] result=cipher.doFinal((s1+"|_|"+s2+"|_|"+s3+"|_|"+s4).getBytes("UTF-8"));  
	    return result;
  
	    
	}  
	  
	/* 
	 * 私钥解密 
	 */  
	private static String privateDecrypt(byte [] src) throws Exception {  
	    Cipher cipher=Cipher.getInstance("RSA");  
	    //得到Key  
	    Key privateKey=readKey(file+"zxx_private.key");  
	    //用私钥去解密  
	    cipher.init(Cipher.DECRYPT_MODE, privateKey);  
 
	    //得到解密后的结果  
	    byte[] result=cipher.doFinal(src);  
	    //二进制数据要变成字符串需解码  
	    return new String(result,"UTF-8");  
	}  
	  
	private static void saveData(byte[] result, String fileName) throws Exception {  
	    // TODO Auto-generated method stub  
	    FileOutputStream fosData=new FileOutputStream(fileName);  
	    fosData.write(result);  
	    fosData.close();  
	}  
	public static void saveKey(Key key,String fileName)throws Exception{  
	    FileOutputStream fosKey=new FileOutputStream(fileName);  
	    ObjectOutputStream oosSecretKey =new ObjectOutputStream(fosKey);  
	    oosSecretKey.writeObject(key);  
	    oosSecretKey.close();  
	    fosKey.close();  
	}  
	private static Key readKey(String fileName) throws Exception {  
	    FileInputStream fisKey=new FileInputStream(fileName);  
	    ObjectInputStream oisKey =new ObjectInputStream(fisKey);  
	    Key key=(Key)oisKey.readObject();  
	    oisKey.close();  
	    fisKey.close();  
	    return key;  
	}  
	private static byte[] readData(String filename) throws Exception {  
	    FileInputStream fisDat=new FileInputStream(filename);  
	    byte [] src=new byte [fisDat.available()];  
	    int len =fisDat.read(src);  
	    int total =0;  
	    while(total<src.length){  
	        total +=len;  
	        len=fisDat.read(src,total,src.length-total);  
	    }  
	    fisDat.close();  
	    return src;  
	} 
	 /**
	  * 16进制的字符串表示转成字节数组
	  * 
	  * @param hexString
	  *            16进制格式的字符串
	  * @return 转换后的字节数组
	  **/
	 public static byte[] toByteArray(String hexString) {
	  hexString = hexString.toLowerCase();
	  final byte[] byteArray = new byte[hexString.length() / 2];
	  int k = 0;
	  for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
	   byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
	   byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
	   byteArray[i] = (byte) (high << 4 | low);
	   k += 2;
	  }
	  return byteArray;
	 }
	 
	 /**
	  * 字节数组转成16进制表示格式的字符串
	  * 
	  * @param byteArray
	  *            需要转换的字节数组
	  * @return 16进制表示格式的字符串
	  **/
	 public static String toHexString(byte[] byteArray) {
	  if (byteArray == null || byteArray.length < 1)
	   throw new IllegalArgumentException("this byteArray must not be null or empty");
	 
	  final StringBuilder hexString = new StringBuilder();
	  for (int i = 0; i < byteArray.length; i++) {
	   if ((byteArray[i] & 0xff) < 0x10)//0~F前面不零
	    hexString.append("0");
	   hexString.append(Integer.toHexString(0xFF & byteArray[i]));
	  }
	  return hexString.toString().toLowerCase();
	 }
	public static void main(String[] args){
		PubAES aes=new PubAES();
		try{
			//aes.PublicKey();
			byte[] result = aes.PublicEnrypt("2018-02-20","2018-02-28","dataTranServer2","WD-WCC6Z2YN0PJP","");
			System.out.println("result="+result);
			String lll="5a1810e803eeba870be2eced80bfa413e09920cc731e82e015c7c9a77cc5be686a605679cdfe1cb78b5a7fe04cab75773b9cdaa3f8eaa2ae0877ed721edfc946bba178edab2be8f1b1fae386cacd92fd0eeca308bad15a21d83abeb3de196e26a61b72efe546036235c5f8b62f720c0e43edb16b22be3a7c9ad032c0d59456c6";
			String test =toHexString(result);
			System.out.println("test="+test);
			byte[] resulttest=toByteArray(lll);
			System.out.println("resulttest="+resulttest);
			System.out.println("resulttest2="+new String(resulttest));
		 
			 String str= privateDecrypt(resulttest);
			 System.out.println("str"+str);
		}catch(Exception e ){
			e.printStackTrace();
		}
	}
	
}