package com.gzw.service.system.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzw.dao.system.SysUserroleDao;
import com.gzw.dao.system.UserDao;
import com.gzw.service.system.IUserService;
import com.gzw.entity.system.SysUser;
import com.gzw.entity.system.SysUserrole;
import com.gzw.util.PageBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("userService")
@Transactional
public class UserService implements IUserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private SysUserroleDao userroleDao;
	


	@Override
	public PageBean<SysUser> getUsersByCondition(SysUser entity, Integer currentPage, Integer pageSize) {
		//固定的加载项
		Page<SysUser> page = PageHelper.startPage(currentPage, pageSize);
		//数据库查询数据
		List<SysUser> allUsers = userDao.getUsersByConditions(entity);
		
		PageBean<SysUser> pageBean = new PageBean<>(page.getPageNum(),page.getPageSize(),page.getTotal());
		
		pageBean.setItems(allUsers);
		
		return pageBean;
	}

	@Override
	public void addUser(SysUser entity) {
		userDao.addUser(entity);
	}

	@Override
	public void updateUser(SysUser entity) {
		userDao.updateUser(entity);
	}

	@Override
	public SysUser getUserById(Integer id) {
		return userDao.getUserById(id);
	}

	@Override
	public SysUser getUserByAccount(String account) {
		return userDao.getUserByAccount(account);
	}

	


	@Override
	public List<SysUserrole> getUserroleByUserId(String userId){
		return userroleDao.getByUserId(userId);
	}
	
	@Override
	@Transactional
	public void saveUserRoles(String userId, String roleIds) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		//删除原roleId相关的数据
		userroleDao.deleteByUserId(userId);
		String[] s = roleIds.split(",");
		for(int i=0;i<s.length;i++) {
			String roleId = s[i];
			if(roleId != null && !roleId.equals("")) {
				SysUserrole record = new SysUserrole();
				record.setUserId(userId);
				record.setRoleId(roleId);
				record.setOperatorId(userId);
				record.setUpdateTime(df.format(cal.getTime()));
				userroleDao.insertUserrole(record);
			}
		}
	}

	@Override
	public List<String> getRolesByUserId(Integer userId){
		return userDao.getRolesByUserId(userId);
	}

	
	@Override
	public void delUser(Integer userId) {
		userDao.delUser(userId);
	}

	@Override
	public List<SysUser> getAllUsers() {
		return userDao.getUsersByConditions(null);
	}
}
