package com.gzw.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzw.dao.system.SysRoleDao;
import com.gzw.entity.system.SysRole;
import com.gzw.service.system.IRoleService;
import com.gzw.util.PageBean;

@Service("roleService")
public class RoleService implements IRoleService {
	
	@Autowired
	private SysRoleDao roleDao;
	
	

	@Override
	public PageBean<SysRole> getRolesByCondition(SysRole entity, Integer currentPage, Integer pageSize) {
		//固定的加载项
		Page<SysRole> page = PageHelper.startPage(currentPage, pageSize);
		//数据库查询数据
		List<SysRole> allRoles = roleDao.getRolesByConditions(entity);
		
		PageBean<SysRole> pageBean = new PageBean<>(page.getPageNum(),page.getPageSize(),page.getTotal());
		
		pageBean.setItems(allRoles);
		
		return pageBean;
	}

	@Override
	@Transactional
	public void addRole(SysRole entity) {
		roleDao.addRole(entity);
	}

	@Override
	@Transactional
	public void updateRole(SysRole entity) {
		roleDao.updateRole(entity);
	}

	@Override
	@Transactional
	public void deleterole(Integer ID) {
		roleDao.deleteByPrimaryKey(ID);
	}
	
	@Override
	public SysRole getRoleById(Integer ID) {
		return roleDao.selectByPrimaryKey(ID);
	}
	
	@Override
	public SysRole getRoleByName(String roleName) {
		return roleDao.getRoleByName(roleName);
	}
	
	
	
	@Override
	public List<SysRole> getAllRoles(){
		SysRole entity = new SysRole();
		return roleDao.getRolesByConditions(entity);
	}
}
