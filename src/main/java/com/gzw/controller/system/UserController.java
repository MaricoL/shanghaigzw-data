package com.gzw.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.gzw.entity.system.SysRole;
import com.gzw.entity.system.SysUser;
import com.gzw.entity.system.SysUserrole;
import com.gzw.service.system.IRoleService;
import com.gzw.service.system.IUserService;
import com.gzw.util.PageBean;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对用户表的增删该查操作。
 * @author sht
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;


	@RequestMapping("/_list")
	public String userlistPage(SysUser entity, Integer currentPage, Map<String,Object> map, HttpServletRequest request) {
		if(currentPage == null || currentPage == 0) {
			currentPage = 1;
		}
		PageBean<SysUser> pageBean = userService.getUsersByCondition(entity, currentPage, 10);
		map.put("pageBean", pageBean);
		map.put("searchurl", request.getContextPath()+"/user/_list");
		map.put("user", entity);
		return "system/userList";
	}
	
	/**
	 * 验证账号唯一
	 * 新增
	 * 编辑
	 * @param entity
	 * @return
	 */
	private boolean vaildateAccountUnique(SysUser entity) {
		boolean result = true;
		if(entity.getId() != null) {
			SysUser oldUser = userService.getUserById(entity.getId());
			if(!oldUser.getAccount().equals(entity.getAccount())) {
				SysUser temp = userService.getUserByAccount(entity.getAccount());
				if(temp != null) {
					result = false;
				}
			}
		}else {
			SysUser temp = userService.getUserByAccount(entity.getAccount());
			if(temp != null) {
				result = false;
			}
		}
		return result;
	}
	
	
	/**
	 * 进入查看页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/_view")
	public String toUserViewPage(Integer id,Map<String,Object> map) {
		SysUser user = userService.getUserById(id);
		map.put("user", user);
		return "system/userView";
	}
	
	/**
	 * 进入新增编辑页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/_edit")
	public String toUserEditPage(Integer id,Map<String,Object> map,HttpServletRequest request) {
		SysUser user = userService.getUserById(id);
		map.put("user", user);
		return "system/userEdit";
	}
	/**
	  * 异步，新增&编辑
	 * @param entity
	 * @return
	 */
	@RequestMapping("/_saveOrUpdate")
	@ResponseBody
	public String saveOrUpdateUser(SysUser entity,HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		SysUser opUser = (SysUser) session.getAttribute("userSession");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(new Date());
		//账号唯一性验证
		if(!vaildateAccountUnique(entity)) {
			return "error_account_not_unique";
		}
		
		if(entity.getId() != null) {//编辑
			entity.setLastModifyDate(date);
			entity.setLastModifyPersonId(opUser.getId().toString());
			entity.setLastModifyPersonName(opUser.getUserName());
			
			userService.updateUser(entity);
		}else {//新增
			entity.setCreateDate(date);
			entity.setCreatePersonId(opUser.getId().toString());
			entity.setCreatePersonName(opUser.getUserName());
			userService.addUser(entity);
		}
		return "success";
	}
	
	/**
	 * 进入修改密码页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/_modifyPassword")
	public String toUserModifyPassword(Integer id,Map<String,Object> map,HttpServletRequest request) {
		SysUser user = userService.getUserById(id);
		map.put("user", user);
		return "system/userPasswordEdit";
	}
	
	
	/**删除用户
	 * @author wangyihong
	 * */
	@RequestMapping(value = "/_cancel", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> usercancel(Integer id, HttpServletRequest request) {

		userService.delUser(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", "1");
		map.put("message", "删除成功！");
		return map;
	}
	
	/**
	 * 用户分配角色加载页面
	 * @author wangyihong
	 * */
	@RequestMapping(value ="/_setRoles", method = RequestMethod.GET)
	public String setRoles(Integer id, Map<String,Object> map){
		List<SysRole> roles = roleService.getAllRoles();
		List<SysUserrole> list = userService.getUserroleByUserId(id.toString());
		SysUser user = userService.getUserById(id);
		for(SysRole role : roles) {
			for(SysUserrole userrole : list){
				if(role.getId().toString().equals(userrole.getRoleId())) {
					role.setChecked("1");
				}
			}
		}
		map.put("roles", roles);
		map.put("user", user);
		return "system/userroleEdit";
	}
	
	/**
	 * 角色分配保存
	 * @author wangyihong
	 * */
	@RequestMapping(value ="/_saveUserRoles", method = RequestMethod.POST)
	@ResponseBody
	public String saveUserRoles(HttpServletRequest request){
		String userId = request.getParameter("userId");
		String rolesIds = request.getParameter("roleIds");
		userService.saveUserRoles(userId, rolesIds);
		return "success";
	}
	
}
