package com.zhichao.common.constants;

/**
 * 系统常量
 * 
 */
public interface SystemConstants {

    final class Code {
        public static final String SUCCESS = "1";
        public static final String FAIL = "0";
        /**
         * @Fields ok : 成功
         */
        public static final int success = 1;
        /**
         * @Fields error : 失败
         */
        public static final int error = 0;

        /**
         * empty:无数据
         * 
         * @since JDK 1.6
         */
        public static final int empty = -1;
        /**
         * token失效
         */
        public static final int code_failure_token = 101;
        /**
         * fresh_token失效
         */
        public static final int code_failure_fresh_token = 102;

        /**
         * session失效
         * 
         * @since JDK 1.6
         */
        public static final int code_session_timeout = 100;

    }

    /**
     * 案件来源
     */
    final class CaseSource{

        /**
         * 精检站
         */
        public static final int LSC = 1;

        /**
         * 非现场执法
         */
        public static final int OSE = 2;

    }

    /**
     * 立案相关
     */
    final class Case{
        /**
         * 未立案
         */
        public static final Integer UNREGISTERED = 1;

        /**
         * 立案待审批
         */
        public static final Integer WAITAPPROVAL= 2;

        /**
         * 已立案
         */
        public static final Integer HASBEENREGISTERED = 3;

        /**
         * 已结案
         */
        public static final Integer CLOSED= 4;

        /**
         * 驳回
         */
        public static final Integer TURNDOWN = 5;

    }


    /**
     * @ClassName: Message
     * @Description: xaResult的message
     * @author hchen
     * @date 2014年8月15日 下午5:34:20
     *
     */
    public static final class Message {
        /**
         * @Fields ok : 成功
         */
        public static final String success = "成功!";
        /**
         * @Fields error : 失败
         */
        public static final String error = "系统错误!";
    }



    class oprResult {

        public final static String OPR_SUCC = "success";

        public final static String OPR_FAIL = "fail";
    }

    /**
     * 文件后缀类型
     * @author yanjiangjie
     */
    class fileSuffix {
        /**
         * png:png
         * 
         * @since JDK 1.6
         */
        public static final String png = ".png";
        /**
         * gif:gif
         * 
         * @since JDK 1.6
         */
        public static final String gif = ".gif";
        /**
         * jpeg:jpeg
         * 
         * @since JDK 1.6
         */
        public static final String jpeg = ".jpeg";
        /**
         * jpg:jpg
         * 
         * @since JDK 1.6
         */
        public static final String jpg = ".jpg";
        /**
         * doc:doc word2010之前
         * 
         * @since JDK 1.6
         */
        public static final String doc = ".doc";
        /**
         * docs:docs word 2010
         * 
         * @since JDK 1.6
         */
        public static final String docs = ".docs";
        /**
         * docx:docx word 2007
         * 
         * @since JDK 1.6
         */
        public static final String docx = ".docx";
        /**
         * xls:xls excel 2010之前
         * 
         * @since JDK 1.6
         */
        public static final String xls = ".xls";
        /**
         * xlsx:xlsx excel 2010
         * 
         * @since JDK 1.6
         */
        public static final String xlsx = ".xlsx";
        /**
         * pdf:pdf
         * 
         * @since JDK 1.6
         */
        public static final String pdf = ".pdf";
        /**
         * rar:rar
         * 
         * @since JDK 1.6
         */
        public static final String rar = ".rar";
        /**
         * zip:zip
         * 
         * @since JDK 1.6
         */
        public static final String zip = ".zip";
        /**
         * txt:txt格式
         * 
         * @since JDK 1.6
         */
        public static final String txt = ".txt";
        /**
         * txt:mp4格式
         * 
         * @since JDK 1.6
         */
        public static final String mp4 = ".mp4";
        /**
         * xml:xml格式
         * 
         * @since JDK 1.6
         */
        public static final String xml = ".xml";
    }
    /**
     * 案件类型
     * @author yanxingui
     *
     */
    final class CaseType{
    	/**
    	 * 固定治超站(精检站): lsc
    	 */
    	public static final String LSC = "lsc";
    	/**
    	 * 非现场: ose
    	 */
    	public static final String OSC = "ose";
    }
}