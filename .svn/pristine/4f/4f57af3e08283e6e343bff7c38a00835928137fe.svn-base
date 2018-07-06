package com.zhichao.service.core.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class PubEncode{
	
	public  static String[]  PublicEnrypt(String s)throws Exception {  
	    Cipher cipher =Cipher.getInstance("RSA");  
	    //实例化Key  
	    KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");  
	    //获取一对钥匙  
	    KeyPair keyPair=keyPairGenerator.generateKeyPair();  
	    //获得公钥  
	    Key publicKey=keyPair.getPublic();  
	    //获得私钥   
	    Key privateKey=keyPair.getPrivate();  
 	    System.out.println("publicKey.getEncode=="+toHexString(getEncode(publicKey)));  
//	    System.out.println("publicKey.toHexString.getFormat()=="+toHexString(publicKey.getEncoded())); 
 	    System.out.println("privateKey.getEncode=="+toHexString(getEncode(privateKey)));  
//	    System.out.println("publicKey.toHexString.getFormat()=="+toHexString(privateKey.getEncoded())); 
	    
	    
	    String[]str = new String[]{toHexString(getEncode(publicKey)),toHexString(getEncode(privateKey))};
	     return str; 
	}  
	
	public static byte [] getEncode(Key key){
		ObjectOutputStream oos = null;//对象输出流
	    ByteArrayOutputStream baos = null;//byte数组输出流
	    try {
	     //序列化
	     baos = new ByteArrayOutputStream();
	     oos = new ObjectOutputStream(baos);//将数组流传入对象流
	     oos.writeObject(key);//用对象流读取对象。
	     byte[] bytes = baos.toByteArray();//用数组流将传入的对象转化为byte数组
       
          oos.close();
          baos.close();
          return bytes;
	    } catch (Exception e) {
	     
	    }
	    return null;
	}
	 
	private static byte [] PublicEnrypt(String s1,String license)throws Exception {  
	    Cipher cipher =Cipher.getInstance("RSA");  
	    Key publicKey=getKey(license);  
	    //用公钥加密  
	    cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
	    byte [] result=cipher.doFinal(s1.getBytes("UTF-8"));  
	    return result;
	}  
	  
	public static String getPublicEnrypt(String s1,String license)throws Exception {  
	    Cipher cipher =Cipher.getInstance("RSA");  
	    Key publicKey=getKey(license);  
	    //用公钥加密  
	    cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
	    byte [] result=cipher.doFinal(s1.getBytes("UTF-8"));  
	    return toHexString(result);
	}  
	/* 
	 * 私钥解密 
	 */  
	private static String privateDecrypt(byte [] src,String license) throws Exception {  
	    Cipher cipher=Cipher.getInstance("RSA");  
	    //得到Key  
	    Key privateKey=getKey(license);  
	    //用私钥去解密  
	    cipher.init(Cipher.DECRYPT_MODE, privateKey);  
 
	    //得到解密后的结果  
	    byte[] result=cipher.doFinal(src);  
	    //二进制数据要变成字符串需解码  
	    return new String(result,"UTF-8");  
	} 
	/* 
	 * 私钥解密核对 
	 */  
	public static boolean privateDecryptCheck(byte [] src,String s1,String s2,String s3,String s4,String s5,String license) throws Exception {  
	    Cipher cipher=Cipher.getInstance("RSA");  
	    //得到Key  
	    Key privateKey=getKey(license);  
	    //用私钥去解密  
	    cipher.init(Cipher.DECRYPT_MODE, privateKey);  
	    //读数据源  
	   
	    //得到解密后的结果  
	    byte[] result=cipher.doFinal(src);  
	    //二进制数据要变成字符串需解码  
	    String licenses=new String(result,"UTF-8");
	    if(licenses.equals(s1+"|_|"+s2+"|_|"+s3+"|_|"+s4)) return true;
	    return false;
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
	private static Key getKey(String license) throws Exception {  
		byte[] src=	toByteArray(license);
		ByteArrayInputStream byteInt=new ByteArrayInputStream(src);  
		ObjectInputStream oisKey=new ObjectInputStream(new BufferedInputStream(byteInt)); 
		Key key=(Key)oisKey.readObject();  
		oisKey.close();  
		byteInt.close();  
		return key;  
	}  
	private static Key readKey(String fileName) throws Exception {  
	    FileInputStream fisKey=new FileInputStream(fileName);  
	    ObjectInputStream oisKey =new ObjectInputStream(fisKey);  
	    Key key=(Key)oisKey.readObject();  
	    oisKey.close();  
	    fisKey.close();  
	    return key;  
	}  
 
	
	  public  static String decode(String str,String cdkey) throws Exception {

			if(cdkey.length()<6)cdkey="A1H8D4";
			String sessionId = cdkey.substring(6) + "1234567890";

			byte[] key = sessionId.substring(0, 8).getBytes();
			return new String(decode(hexToBytes(str), key));
		}
	    public  static String decodeLicense(String str,String cdkey) throws Exception {

			if(cdkey.length()<6)cdkey="A1H8D4";
			String sessionId = cdkey.substring(6) + "1234567890";

			byte[] key = sessionId.substring(0, 8).getBytes();
			return new String(decode(hexToBytes(str), key));
		}

		/**
		 * 解密
		 * @param input byte[] 需解密字节码
		 * @param key byte[] 密钥
		 * @throws Exception
		 * @return byte[] 解密后字节码
		 */
		public static byte[] decode(byte[] input, byte[] key)
				throws Exception
		{
			SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, "DES") ;
			Cipher c1 = Cipher.getInstance("DES") ;
			c1.init(Cipher.DECRYPT_MODE, deskey) ;

			byte[] clearByte = c1.doFinal(input) ;
			return clearByte ;
		}
		public static final byte[] hexToBytes(String hex) {
			if (null == hex) {
				return new byte[0];
			}
			int len = hex.length();
			byte[] bytes = new byte[len / 2];
			String stmp = null;
			try {
				for (int i = 0; i < bytes.length; i++) {
					stmp = hex.substring(i * 2, i * 2 + 2);
					bytes[i] = (byte) Integer.parseInt(stmp, 16);
				}
			} catch (Exception e) {
				return new byte[0];
			}

			return bytes;
		}
		
	public static void main(String[] args){
		PubEncode aes=new PubEncode();
		try{
			//String keylist[]=PublicEnrypt("1");
			 Cipher cipher =Cipher.getInstance("RSA"); 
			 Key publicKey=getKey("aced0005737200146a6176612e73656375726974792e4b6579526570bdf94fb3889aa5430200044c0009616c676f726974686d7400124c6a6176612f6c616e672f537472696e673b5b0007656e636f6465647400025b424c0006666f726d617471007e00014c00047479706574001b4c6a6176612f73656375726974792f4b657952657024547970653b7870740003525341757200025b42acf317f8060854e00200007870000000a230819f300d06092a864886f70d010101050003818d003081890281810080e4949e9aa6b0404742a07392e878fea6255b9e14727dcb1e2809ff2c298b1a6256509c46a55c6ee2eb1a74f5db646083080c11c947ed668d5bcea46bf0fb4fd0f54aed7ab66ddda3b0dfefe5979f327f2f2e654636e26406506f2ce74658b79f66255f8c8524e92497c8a638f3a88548a7b011c90046211f5cddc6dbf52d5d0203010001740005582e3530397e7200196a6176612e73656375726974792e4b6579526570245479706500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400065055424c4943");
			  
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
			byte[] result =  cipher.doFinal(("2018-02-20"+"|_|"+"2018-03-18"+"|_|"+"dataTranServer1"+"|_|"+"WD-WCC6Z2YN0PJP").getBytes("UTF-8"));
			
			String test =toHexString(result);
			System.out.println("license1="+test);
			
			byte[] result2 =  cipher.doFinal(("2018-02-20"+"|_|"+"2018-03-18"+"|_|"+"dataTranServer2"+"|_|"+"WD-WCC6Z2YN0PJP").getBytes("UTF-8"));
			String test2 =toHexString(result2);
			System.out.println("license2="+test2);
		}catch(Exception e ){
			e.printStackTrace();
		}
	}
	
}