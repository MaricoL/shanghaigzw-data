package com.gzw.service.system;

import java.util.List;

import com.gzw.entity.system.SysRole;
import com.gzw.util.PageBean;



public interface IRoleService {
	
	/**
	 * 获角色列表，带分页
	 * @param entity
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageBean<SysRole> getRolesByCondition(SysRole entity,Integer currentPage,Integer pageSize);
	
	/**
	 * 添加角色
	 * @param entity
	 */
	void addRole(SysRole entity);
	
	/**
	 * 修改角色
	 * @param entity
	 */
	void updateRole(SysRole entity);
	/**
	 * 注销角色
	 * @param entity
	 */
	void deleterole(Integer ID);
	/**
	 * 根据id获取角色
	 * @param id
	 * @return
	 */
	SysRole getRoleById(Integer ID);
	/**
	 * 根据名称获取角色
	 * @param id
	 * @return
	 */
	SysRole getRoleByName(String rolename);
	
	
	/**
	 * 获取角色列表
	 * */
	List<SysRole> getAllRoles();
	
}
