package com.udfex.ams.module.account.service;

import com.udfex.ucs.module.user.entity.SysPermissions;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmomen.framework.mybatis.dao.MybatisDao;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private MybatisDao mybatisDao;
	
    public SysPermissions createPermission(SysPermissions permission) {
    	permission = mybatisDao.saveByModel(permission);
        return permission;
    }

    public void deletePermission(Long permissionId) {
    	mybatisDao.deleteByPrimaryKey(SysPermissions.class, permissionId);
    }
}