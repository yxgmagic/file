package com.zhichao.service.system.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhichao.dal.system.RoleDao;
import com.zhichao.service.system.IRoleService;
import com.zhichao.beans.guns.Relation;
import com.zhichao.core.util.Convert;
import com.zhichao.dal.mapper.RelationMapper;
import com.zhichao.dal.mapper.RoleMapper;

@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    RoleMapper roleMapper;

    @Resource
    RoleDao roleDao;

    @Resource
    RelationMapper relationMapper;

    @Override
    @Transactional(readOnly = false)
    public void setAuthority(Integer roleId, String ids) {

        // 删除该角色所有的权限
        this.roleDao.deleteRolesById(roleId);

        // 添加新的权限
        for (Long id : Convert.toLongArray(true, Convert.toStrArray(",", ids))) {
            Relation relation = new Relation();
            relation.setRoleid(roleId);
            relation.setMenuid(id);
            this.relationMapper.insert(relation);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void delRoleById(Integer roleId) {
        //删除角色
        this.roleMapper.deleteById(roleId);

        // 删除该角色所有的权限
        this.roleDao.deleteRolesById(roleId);
    }

}
