package com.zhichao.core.base.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ValidationException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhichao.common.exception.BusinessException;
import com.zhichao.common.exception.DatabaseException;
import com.zhichao.common.vo.RestAPIResult;
import com.zhichao.core.base.tips.SuccessTip;
import com.zhichao.core.base.warpper.BaseControllerWarpper;
import com.zhichao.core.page.PageInfoBT;
import com.zhichao.core.support.HttpKit;
import com.zhichao.core.util.FileUtil;

public class BaseController {

    protected static String SUCCESS = "SUCCESS";
    protected static String ERROR = "ERROR";

    protected static String REDIRECT = "redirect:";
    protected static String FORWARD = "forward:";

    protected static SuccessTip SUCCESS_TIP = new SuccessTip();

    protected HttpServletRequest getHttpServletRequest() {
        return HttpKit.getRequest();
    }

    protected HttpServletResponse getHttpServletResponse() {
        return HttpKit.getResponse();
    }

    protected HttpSession getSession() {
        return HttpKit.getRequest().getSession();
    }

    protected HttpSession getSession(Boolean flag) {
        return HttpKit.getRequest().getSession(flag);
    }

    protected String getPara(String name) {
        return HttpKit.getRequest().getParameter(name);
    }

    protected void setAttr(String name, Object value) {
        HttpKit.getRequest().setAttribute(name, value);
    }

    protected Integer getSystemInvokCount() {
        return (Integer) this.getHttpServletRequest().getServletContext().getAttribute("systemCount");
    }

    /**
     * 把service层的分页信息，封装为bootstrap table通用的分页封装
     */
    protected <T> PageInfoBT<T> packForBT(Page<T> page) {
        return new PageInfoBT<T>(page);
    }

    /**
     * 包装一个list，让list增加额外属性
     */
    protected Object warpObject(BaseControllerWarpper warpper) {
        return warpper.warp();
    }

    /**
     * 删除cookie
     */
    protected void deleteCookieByName(String cookieName) {
        Cookie[] cookies = this.getHttpServletRequest().getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                Cookie temp = new Cookie(cookie.getName(), "");
                temp.setMaxAge(0);
                this.getHttpServletResponse().addCookie(temp);
            }
        }
    }

    /**
     * 返回前台文件流
     *
     * @author fengshuonan
     * @date 2017年2月28日 下午2:53:19
     */
    protected ResponseEntity<byte[]> renderFile(String fileName, String filePath) {
        byte[] bytes = FileUtil.toByteArray(filePath);
        return renderFile(fileName, bytes);
    }

    /**
     * 返回前台文件流
     *
     * @author fengshuonan
     * @date 2017年2月28日 下午2:53:19
     */
    protected ResponseEntity<byte[]> renderFile(String fileName, byte[] fileBytes) {
        String dfileName = null;
        try {
            dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.CREATED);
    }
    
    /*异常处理-统一返回对象  yanjiangjie*/
    
    private static final Logger LOGGER = Logger.getLogger(BaseController.class);

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    RestAPIResult<T> handleUncaughtException(Exception ex) {//系统异常
        printStackTrace(ex);
        return new RestAPIResult<T>("操作异常，如果刷新后操作仍存在异常，请联系管理人员");
    }

    @ExceptionHandler(ValidationException.class)
    public @ResponseBody
    RestAPIResult<T> handleValidationException(ValidationException validationEx) {//数据校验异常
        LOGGER.error(validationEx.getMessage(), validationEx.getCause());
        return new RestAPIResult<T>(validationEx.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public @ResponseBody
    RestAPIResult<T> handleBusinessException(BusinessException businessEx) {//业务逻辑异常
        printStackTrace(businessEx);
        return new RestAPIResult<T>(businessEx.getMessage());
    }

    @ExceptionHandler(DatabaseException.class)
    public @ResponseBody
    RestAPIResult<T> handleValidationException(DatabaseException dbEx) {//数据库操作异常
        printStackTrace(dbEx);
        return new RestAPIResult<T>(dbEx.getMessage());
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    public @ResponseBody
    RestAPIResult<T> handleJSONConvertException(HttpMessageNotWritableException jsonEx) {//JSON格式转换异常
        printStackTrace(jsonEx);
        return new RestAPIResult<T>("JSON格式转换异常");
    }    
    
    public String getRootContext(HttpServletRequest httpServletRequest){
        return httpServletRequest.getContextPath();
    }    
    
    /**
     * printStackTrace:日志中打印错误信息<br/>
     *
     * @author yanjiangjie
     * @param ex
     * @since JDK 1.6
     */
    private void printStackTrace(Exception ex){
        StackTraceElement[] ste = ex.getStackTrace();
        //只打出最先导致出错的地方
        if(ste.length>3){
        	for (int i = 0; i < 4; i++) {
				StackTraceElement stackTraceElement = ste[i];
				String errorMsg="[error Class]："+stackTraceElement.getClassName()+"------[error Method]："+stackTraceElement.getMethodName()+"------[error Line]："+stackTraceElement.getLineNumber()+"---------[error message]："+ex.getMessage();
				LOGGER.error(errorMsg);
			}
        }else{
        	for (int i = 0; i < ste.length; i++) {
        		StackTraceElement stackTraceElement = ste[i];
        		String errorMsg="[error Class]："+stackTraceElement.getClassName()+"------[error Method]："+stackTraceElement.getMethodName()+"------[error Line]："+stackTraceElement.getLineNumber()+"---------[error message]："+ex.getMessage();
        		LOGGER.error(errorMsg);
        	}
        	
        }
        	
    }

    protected <T> RestAPIResult<T> result(T t){
        RestAPIResult<T> restAPIResult = new RestAPIResult<>();
        restAPIResult.setRespData(t);
        return restAPIResult;
    }
    
}
