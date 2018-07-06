package com.zhichao.admin.controller.permissions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhichao.common.util.ToolUtil;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.service.core.shiro.ShiroKit;
import com.zhichao.service.core.shiro.factory.IShiro;
import com.zhichao.service.core.shiro.factory.ShiroFactroy;

@RestController
@RequestMapping("/permissions")
public class PermissionsController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(PermissionsController.class);

	/**
	 * 自定义标签权限校验
	 * 
	 * @param urlList
	 * @return
	 */
	@RequestMapping(value = "/permissionsCheck", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> permissionsCheck(
			@RequestParam(value = "urlList", required = true) String urlList) {
		Map<String, Boolean> result = new HashMap<>();
		IShiro shiroFactory = ShiroFactroy.me();
		List<Integer> roleList = ShiroKit.getUser().getRoleList();
		Set<String> permissionSet = new HashSet<>();// 角色拥有权限的资源
		for (Integer roleId : roleList) {
			List<String> permissions = shiroFactory.findPermissionsByRoleId(roleId);
			if (permissions != null) {
				for (String permission : permissions) {
					if (ToolUtil.isNotEmpty(permission)) {
						permissionSet.add(permission+"");
					}
				}
			}
		}
		if(StringUtils.isNotEmpty(urlList)){
			urlList = urlList.replace("[", "").replace("]", "");
			String[] urList = urlList.split(",");
			for (String url : urList) {
				url = url.replace("\"", "");//去除页面传进来的引号
				if (permissionSet.contains(url.trim())) {
					result.put(url, true);
					logger.info("用户："+ShiroKit.getUser().getId()+"-----有资源权限------"+url);
				} else {
					result.put(url, false);
					logger.info("用户："+ShiroKit.getUser().getId()+"-----没有资源权限------"+url);
				}
			}
		}	
		return result;
	}
}
