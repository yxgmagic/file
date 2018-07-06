package com.zhichao.service.platformConfig;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.LawEnforceMan;

/**
 * <p>
 * 执法人员信息表 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-08
 */
public interface ILawEnforceManService extends IService<LawEnforceMan> {

	/**
	 * 根据传经来的文件,将文件内的人员信息添加到数据库
	 * @param file	传来的文件
	 * @param fileName	文件名
	 * @return
	 */
	Map<String, Object> saveInfo(MultipartFile file, String fileName);
}
