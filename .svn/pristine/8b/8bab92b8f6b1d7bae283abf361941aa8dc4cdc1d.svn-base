package com.zhichao.common.asposeword;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import com.aspose.words.License;

/**
 * 
 * 由于ASPOSE比较吃内存，操作大一点的文件就会堆溢出，所以请先设置好java虚拟机参数：-Xms1024m -Xmx1024m(参考值)<br>
 * 
 */
public class TestWord {

    private static InputStream license;
    private static InputStream fileInput;
    private static File outputFile;

    /**
     * 获取license
     * 这个必须要，不然会有水印
     * @return
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            
            String packagePath = loader.getResource("license.xml").getPath().replaceAll("%20","");//解决路径中含有空格的情况
            packagePath = java.net.URLDecoder.decode(packagePath,"utf-8"); //解决路径包含中文的情况
            
            license = new FileInputStream(packagePath);// 凭证文件
            
            License aposeLic = new License();
            aposeLic.setLicense(license);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        // 验证License
        if (!getLicense()) {
            return;
        }
        try {
            long old = System.currentTimeMillis();
            /*Document doc = new Document(fileInput);
             * 
             * 
            fileInput = new FileInputStream(loader.getResource("test.docx").getPath());// 待处理的文件
            outputFile = new File("D:\\test.pdf");// 输出路径

            FileOutputStream fileOS = new FileOutputStream(outputFile);

            doc.save(fileOS, SaveFormat.PDF);*/

            String path =test();
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒\n\n" + "文件保存在:" + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String test(){
    	ArrayList paths=new ArrayList();
//    	String currPath=System.getProperty("user.dir");
    	String currPath="D:\\ZC\\admin-admin";
    	paths.add(currPath+"\\source\\test1.docx");//文档1
    	paths.add(currPath+"\\source\\test2.doc");//文档2
    	paths.add(currPath+"\\source\\test.docx");//文档3
        
        //合并后输出文档，文件名称自己定义
        String outPath=currPath+"\\source\\testAll.docx";
        
        if(paths.size()>0){
	        WordAddImple wordAddImple = new WordAddImple();  
	        
	        //从第1个文档开始合并后输出，不连续，分页
	        if(wordAddImple.AddDocs2DocByContinuous(paths, outPath)!=null)
	        	return outPath;
	        else
	        	return "合并文档出错!";
        }
        
        return "文档数据不能为空!";
    }
}