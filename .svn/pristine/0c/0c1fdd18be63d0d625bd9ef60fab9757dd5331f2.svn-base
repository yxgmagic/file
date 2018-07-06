package org.zhichao.jobCenter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author yanjiangjie
 * 定时任务示例
 */
@Component   
@EnableScheduling   
@Service  
@Transactional  
public class ScheduledTaskService { 
	
	private Logger logger = Logger.getLogger(ScheduledTaskService.class);
	
	/**
     * 目标：实现定时任务；实例中是每隔3秒钟执行一次 
     */  
      
    private Integer count_first = 1;  
    private Integer count_second = 1;  
    private Integer count_three = 1;  
      
//    @Scheduled(fixedRate = 10000)  
//    public void printCurrentTime() throws InterruptedException {  
//    	logger.info(String.format("① 第%s次执行，当前时间为：%s", count_first++, dateFormat.format(new Date()))); 
//    }  
//      
//    @Scheduled(fixedDelay = 10000)  
//    public void printCurrentTimeAfterSleep() throws InterruptedException { 
//    	logger.info(String.format("② 第%s次执行，当前时间为：%s", count_second++, dateFormat.format(new Date())));  
//    }  
//      
//    @Scheduled(cron = "*/10 * * * * *")  
//    public void printCurrentTimeCron() throws InterruptedException {  
//    	logger.info(String.format("③ 第%s次执行，当前时间为：%s", count_three++, dateFormat.format(new Date())));  
//    }  
      
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
      
}   
