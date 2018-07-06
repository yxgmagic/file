package com.zhichao.admin.controller.employee;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.zhichao.service.core.util.ParaUtil;

@Component
@PropertySource("classpath:/application.yml")
public class FileCopy {
	
	@Value("${guns.path}")
	private static String  uploadPath;
	/**
	 * 文件夹并入拷贝方法
	 */
	public static void corpFiles(String from, String to) {
		File fromfile=new File(from);
		File tofile=new File(to);
		File[] fromfiles = fromfile.listFiles();
		File[] tofiles = tofile.listFiles();
		List<String> fromlist =new ArrayList();
		List<String> tolist =new ArrayList();

		for (File f : tofiles) { tolist.add(f.getName());}
		for (File f : fromfiles) {
			boolean isexists=false;
			for(int i=0;i<tolist.size();i++){
				if(tolist.get(i).equals(f.getName())){
					isexists=true;
				}

			}
			if(!isexists){
				fromlist.add(f.getName());
			}

		}
		for(int i=0;i<fromlist.size();i++){

			copy(from+fromlist.get(i),to+fromlist.get(i));

		}
	}
	/**
	 * 封装好的文件拷贝方法
	 */
	public static void copy(String from, String to) {
		try {
			InputStream in = new FileInputStream(from);
			OutputStream out = new FileOutputStream(to);

			byte[] buff = new byte[1024];
			int len = 0;
			while ((len = in.read(buff)) != -1) {
				out.write(buff, 0, len);
			}
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 保存文件到指定目录
	 * @param stream
	 * @param path
	 * @param fileName
	 * @return 返回文件相对路径 "/static/modular/certificate/" + fileName1;
	 * @throws IOException
	 */
	public static String SaveFileFromInputStream(InputStream stream, String path, String fileName) throws IOException {
		String fileName1 = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));

		return saveFile(stream, path, fileName1);

/*		//String fileName1 = fileName;
		//	项目的绝对路径
		String projectPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
		//	拼接存储检定证书的实际目录	因为测试环境硬盘分区总是变动
		String[] pathArr = projectPath.split(":");
		path = pathArr[0] + ":/" + path;
		String fileUrl = path + fileName1;

		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		FileOutputStream fs = new FileOutputStream(fileUrl);
		byte[] buffer = new byte[1024*1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread=stream.read(buffer))!=-1)
		{
			bytesum+=byteread;
			fs.write(buffer,0,byteread);
			fs.flush();
		}
		//验证文件大小吗?,没有写验证了


		//关闭连接
		fs.close();
		stream.close();
		//因为要直接作为下载的链接,所以就不存绝对路径,直接返回相对路径
		fileUrl = "/static/modular/certificate/" + fileName1;
		return fileUrl;*/

	}

	public static String getDriveName() throws FileNotFoundException {
		//	项目的绝对路径
		File f = new File(ResourceUtils.getURL("classpath:").getPath());

		if (!f.exists()){
			f = new File("");
		}
		String projectPath = f.getAbsolutePath();
		//	拼接存储检定证书的实际目录	因为测试环境硬盘分区总是变动
		String[] pathArr = projectPath.split(":");

		String[] driveArr = {"F:/", "E:/", "D:/", "C:/"};
		//先拿项目的根目录
		String driveName = pathArr[0] + ":/";
		//用来检验目录是否存在的file
		File tempFile = new File(driveName);
		//判断有这个盘符吗
		if(!tempFile.exists()) {
			//没有则遍历这个盘符数组,从f到c盘,一个一个找
			for(int i = driveArr.length - 1; i >= 0; i--) {
				//临时盘符
				String tempDiveName = driveArr[i];
				//判断存在吗?
				tempFile = new File(tempDiveName);
				//存在则跳出循环
				if(tempFile.exists()) {
					driveName = tempDiveName;
					break;
				}
				//不存在继续找,如果都没有...去搭建文件服务器吧
			}
		}
		return driveName;
	}

	public static String saveFile(InputStream stream, String path, String fileName) throws IOException {
		//String fileName1 = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
		String fileName1 = fileName;

		String driveName = getDriveName();
		if(path == null || "".equals(path)) {
			path = driveName + uploadPath;
		}else {
			path += driveName;
		}


		String fileUrl = path + fileName1;

		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		FileOutputStream fs = new FileOutputStream(fileUrl);
		byte[] buffer = new byte[1024*1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread=stream.read(buffer))!=-1)
		{
			bytesum+=byteread;
			fs.write(buffer,0,byteread);
			fs.flush();
		}

		//关闭连接
		fs.close();
		stream.close();
		//因为要直接作为下载的链接,所以就不存绝对路径,直接返回相对路径
		fileUrl = path + fileName1;
		return fileUrl;

	}

	/**
	 * 同步文件
	 * @param fromPara
	 * @param toPara
	 */
	public static void synchronizationFile(String fromPara, String toPara) {
		String certificateUrl = ParaUtil.getParaValue(fromPara);
		String certificateUrlTemp = ParaUtil.getParaValue(toPara);

		String driveName = "";
		try {
			driveName = getDriveName();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		certificateUrl = driveName + certificateUrl;
		//判断是否有这个路径,没有的话,创建
		File file = new File(certificateUrl);
		if (!file.exists()) {
			if (file.mkdirs()) {
				return;
			}
		}
		file = new File(certificateUrlTemp);
		if (!file.exists()) {
			if (file.mkdirs()) {
				return;
			}
		}

		if(!file.exists() || !file.exists()) {
			return;
		}

		corpFiles(certificateUrl, certificateUrlTemp);
	}

	/**
	 * 同步检定证书
	 */
	public static void synchronizationCertificate() {
		synchronizationFile("certificate_url", "certificate_url_temp");
	}

	public static void main(String[] args) {
		String fileSavePath ="f:/java/apache-tomcat-9.0.2/lawDocModelUpload/";
		fileSavePath=fileSavePath.replaceAll("/", "\\\\");
		System.out.println("fileSavePath1===>"+fileSavePath);
		File filemdk=new File(fileSavePath);
		if (!filemdk.exists()) {
			if(!filemdk.mkdirs()){
				fileSavePath= Class.class.getClass().getResource("/").getPath();
				fileSavePath=fileSavePath.substring(0, fileSavePath.lastIndexOf("/"));
				fileSavePath=fileSavePath.substring(0, fileSavePath.lastIndexOf("/"));
				fileSavePath=fileSavePath.substring(1, fileSavePath.length())+"/lawDocModelUpload/";
				File absulefilemdk=new File(fileSavePath);
				if (!absulefilemdk.exists()) {
					absulefilemdk.mkdirs();
				}
			}
		}



		System.out.println("fileSavePath2===>"+fileSavePath);
		String from =fileSavePath;
		fileSavePath="F:/java/apache-tomcat-9.0.2/webapps/admin/WEB-INF/classes/static/modular/lawdoc/";
		File filesavemdk=new File(fileSavePath);
		if (!filesavemdk.exists()) {
			if(!filesavemdk.mkdirs()){
				fileSavePath = Class.class.getClass().getResource("/static/modular/lawdoc/").getPath();
				fileSavePath=fileSavePath.substring(1, fileSavePath.length());

				File absulefileSavePath=new File(fileSavePath);
				if (!absulefileSavePath.exists()) {
					absulefileSavePath.mkdirs();
				}
			}
		}
		System.out.println("fileSavePath3===>"+fileSavePath);
		String to =fileSavePath;
		new FileCopy().corpFiles(from, to);
	}
}