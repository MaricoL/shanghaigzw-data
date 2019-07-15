package com.gzw.service.system;



import com.gzw.entity.system.SysUser;
import com.gzw.entity.system.SysUserrole;
import com.gzw.util.PageBean;

import java.util.List;

public interface IUserService {
	
	/**
	 * 获取用户列表，带分页
	 * @param entity
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageBean<SysUser> getUsersByCondition(SysUser entity, Integer currentPage, Integer pageSize);
	
	/**
	 * 添加用户
	 * @param entity
	 */
	void addUser(SysUser entity);
	
	/**
	 * 修改用户
	 * @param entity
	 */
	void updateUser(SysUser entity);
	
	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 */
	SysUser getUserById(Integer id);
	
	/**
	 * 根据账号查询用户
	 * @param account
	 * @return
	 */
	SysUser getUserByAccount(String account);
	

	
	/**
	 * 根据userId获取关联的roleId列表
	 * @param userId
	 * @return list
	 * @author wangyihong
	 * */
	List<SysUserrole> getUserroleByUserId(String userId);
	
	
	void saveUserRoles(String userId, String roleIds);
	

	List<String> getRolesByUserId(Integer userId);

	
	void delUser(Integer userId);
	
	List<SysUser> getAllUsers();
}
