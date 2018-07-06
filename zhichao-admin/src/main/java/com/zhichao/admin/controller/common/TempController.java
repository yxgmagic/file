package com.zhichao.admin.controller.common;

import com.zhichao.service.core.util.FileCopy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhichao.core.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/tempController")
public class TempController extends BaseController {

    private String PREFIX = "/common/temp/";

    //抄告对象管理
    @RequestMapping("/chaogu_object")
    public String toChaoObject() {
        return PREFIX + "chaogu_object.html";
    }

    //抄告内容管理
    @RequestMapping("/chaogu_content")
    public String toChaoContent() {
        return PREFIX + "chaogu_content.html";
    }

    //抄告回馈
    @RequestMapping("/chaogu_info_result")
    public String toChaoInfoResult() {
        return PREFIX + "chaogu_info_result.html";
    }

    //短信模板管理
    @RequestMapping("/mms_template")
    public String toMmsTemplate() {
        return PREFIX + "mms_template.html";
    }

    //短信发送情况统计
    @RequestMapping("/mms_send_statistics")
    public String toMmsSendStatistics() {
        return PREFIX + "mms_send_statistics.html";
    }

    // 边境治超
    @RequestMapping("/border_govern")
    public String toBorderGovern() {
        return PREFIX + "border_govern.html";
    }

    // 超限许可证预警
    @RequestMapping("/certificate_early_warning")
    public String toCertificateEarlyWarning() {
        return PREFIX + "certificate_early_warning.html";
    }

    //违法车辆统计表
    @RequestMapping("/wfcltjb")
    public String toWfcltjb() {
        return PREFIX + "wfcltjb.html";
    }

    //违法驾驶员统计表
    @RequestMapping("/wfjsytjb")
    public String toWfjsytjb() {
        return PREFIX + "wfjsytjb.html";
    }

    //货运企业统计表
    @RequestMapping("/hyqytjb")
    public String toHyqytjb() {
        return PREFIX + "hyqytjb.html";
    }

    //源头企业统计表
    @RequestMapping("/ytqytjb")
    public String toYtqytjb() {
        return PREFIX + "ytqytjb.html";
    }

    //PCI数据管理
    @RequestMapping("/PCIsjgl")
    public String toPCIsjgl() {
        return PREFIX + "PCIsjgl.html";
    }

    //路面衰减趋势
    @RequestMapping("/lmsjqs")
    public String toLmsjqs() {
        return PREFIX + "lmsjqs.html";
    }

    //文件下载接口,但不是公用的,记得到时候转到公共类当中

    /**
     * 文件下载(预览)
     * @param res   //spring boot自己传过来的,不用管
     * @param fileUrl   //需要下载的文件的相对路径
     * @param fileName  //下载之后的文件名
     * @param isDownload    //是否直接下载
     * @throws IOException
     */
    @RequestMapping(value = "/fileDownload")
    public void fileDownload(HttpServletResponse res,
                             @RequestParam(value = "fileUrl")String fileUrl,
                             @RequestParam(value = "fileName", required = false)String fileName,
                             @RequestParam(value = "isDownload")String isDownload) throws IOException {
        fileUrl = URLDecoder.decode(fileUrl,"UTF-8");
        String driverName = "";
        //获取磁盘路径
        try {
            driverName = FileCopy.getDriveName();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            res.getWriter().write("未找到磁盘路径");
            return;
        }



        String newFileName = "文件";
        //需要被下载的文件路径
        String myFileUrl = driverName + fileUrl;

        //准备要被下载的文件对象
        File outFile = new File(myFileUrl);
        //如果文件不存在
        if(!outFile.exists()) {
            res.setContentType("text/html;charset=UTF-8");
            res.getWriter().write("还没有上传文件,请上传相对应的文件再操作");
            return;
        }


        if(fileName != null) {
            //设置文件下载之后的文件名
            newFileName = fileName + fileUrl.substring(fileUrl.lastIndexOf("."));
        } else {
            newFileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        }




        //不下载走这里
        if("0".equals(isDownload)) {
            String suffixName = fileUrl.substring(fileUrl.lastIndexOf(".") + 1);
            //设置响应头
            res.setHeader("content-type", (String) imageContentType.get(suffixName));
        } else {
            //下载走这里
            //设置文件名的编码
            newFileName = URLEncoder.encode(newFileName, "UTF-8");
            //告诉浏览器这是文件
            res.setHeader("content-type", "application/octet-stream");
            res.setContentType("application/octet-stream");
            res.setHeader("Content-Disposition", "attachment;filename=" + newFileName);
        }
        //设置临时文件缓冲区
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        ServletOutputStream os = null;
        //开始传送啦,准备好
        try {
            //已拿到响应输出流,请指示
            os = res.getOutputStream();
            //已开始构建文件路径
            bis = new BufferedInputStream(new FileInputStream(outFile));
            //开始从文件读取充能
            int i = bis.read(buff);
            //循环发射
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
            //发射完毕
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭发射管道
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //执行完毕

    }


    private static Map imageContentType = new HashMap<>();

    static {

        imageContentType.put("jpg","image/jpeg");

        imageContentType.put("jpeg","image/jpeg");

        imageContentType.put("png","image/png");

        imageContentType.put("tif","image/tiff");

        imageContentType.put("tiff","image/tiff");

        imageContentType.put("ico","image/x-icon");

        imageContentType.put("bmp","image/bmp");

        imageContentType.put("gif","image/gif");

    }



}
