package com.zhichao.service.core.util;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
public class PDFutil {
	 
	 @SuppressWarnings("deprecation")
	public static void getPdf(String[] filesInFolder,String destinationFileName) throws Exception{
		 PDFMergerUtility mergePdf = new PDFMergerUtility();
	        for(int i = 0; i < filesInFolder.length; i++){
	            //循环添加要合并的pdf存放的路径
	            mergePdf.addSource(  filesInFolder[i]); 
	        } 
	        //设置合并生成pdf文件名称
	        mergePdf.setDestinationFileName( destinationFileName); 
	        //合并pdf
	        mergePdf.mergeDocuments(); 
	    }
		 
	   public static void main(String[] args) {
		   String[] filesInFolder={"d:/1.pdf","d:/2.pdf"};
		   String destinationFileName="d:/test.pdf";
		   try {
			PDFutil.getPdf(filesInFolder, destinationFileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
 }
	
	
}
