package com.gzw.dao.system;

import java.util.List;

import com.gzw.entity.system.SysUserrole;


public interface SysUserroleDao {
	
    int deleteByUserId(String userId);

    int insertUserrole(SysUserrole record);

    List<SysUserrole> getByUserId(String userId);

}