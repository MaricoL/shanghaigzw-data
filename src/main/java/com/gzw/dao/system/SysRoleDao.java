package com.gzw.dao.system;

import java.util.List;

import com.gzw.entity.system.SysRole;


public interface SysRoleDao {
	
    int deleteByPrimaryKey(Integer id);

    int addRole(SysRole entity);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(SysRole record);

    int updateRole(SysRole entity);
    
    List<SysRole> getRolesByConditions(SysRole entity);
    
    SysRole getRoleByName(String roleName);
}