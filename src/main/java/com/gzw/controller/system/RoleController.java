package com.gzw.controller.system;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gzw.entity.system.SysRole;
import com.gzw.entity.system.SysUser;
import com.gzw.service.system.IRoleService;
import com.gzw.util.PageBean;


/**
 * 对用户表的增删该查操作。
 * @author sht
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private IRoleService roleService;
	
	
	
	@RequestMapping("/_list")
	public String rolelistPage(SysRole entity,Integer currentPage,Map<String,Object> map,HttpServletRequest request) {
		if(currentPage == null || currentPage == 0) {
			currentPage = 1;
		}
		PageBean<SysRole> pageBean = roleService.getRolesByCondition(entity, currentPage, 10);
		map.put("pageBean", pageBean);
		/*map.put("searchurl", request.getContextPath()+"/role/_list");*/
		map.put("role", entity);
		return "system/roleList";
	}
	/**
	 * 进入新增编辑页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/_new")
	public String Rolenew(Integer id,Map<String,Object> map,HttpServletRequest request) {
		SysRole role = roleService.getRoleById(id);
		map.put("role", role);
		return "system/roleNew";
	}
	/**
	 * 进入新增编辑页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/_edit")
	public String toRoleEditPage(Integer id,Map<String,Object> map,HttpServletRequest request) {
		SysRole role = roleService.getRoleById(id);
		map.put("role", role);
		return "system/roleNew";
	}
	
	/**
	 * 进入角色查看页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/_view")
	public String Roleview(Integer id,Map<String,Object> map,HttpServletRequest request) {
		SysRole role = roleService.getRoleById(id);
		map.put("role", role);
		return "system/roleview";
	}
	
	/**
	 * 删除角色信息
	 * @param id
	 * @param map
	 * @return
	 */
	
	@RequestMapping(value = "/_cancel", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> usercancel(Integer id, HttpServletRequest request) {

		roleService.deleterole(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", "1");
		map.put("message", "删除成功！");
		return map;
	}
	
	/**
	  * 异步，新增&编辑
	 * @param entity
	 * @return
	 */
	@RequestMapping("/_saveOrUpdate")
	@ResponseBody
	public String saveOrUpdateRole(SysRole entity,HttpServletRequest request) {
		HttpSession session = request.getSession();
		SysUser opUser = (SysUser) session.getAttribute("userSession");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(new Date());
		String s=entity.getRoleName();
		//账号唯一性验证
		if(!vaildateAccountUniqueRole(entity)) {
			return "error_account_not_unique";
			
		}
		
		if(entity.getId() != null) {//编辑
			entity.setLastModifyDate(date);
			entity.setLastModifyPersonId(opUser.getId().toString());
			entity.setLastModifyPersonName(opUser.getUserName());
			roleService.updateRole(entity);
		}else {//新增
			
			entity.setIsDel(false);
			entity.setCreateDate(date);
			entity.setCreatePersonID(opUser.getId().toString());
			
			entity.setCreatePersonName(opUser.getUserName());
			
			roleService.addRole(entity);
			
		}
		return "success";
	}
	
	/**验证角色唯一
	 * @param entity
	 * @return
	 */
	private boolean vaildateAccountUniqueRole(SysRole entity) {
		boolean result = true;
		if(entity.getId() != null) {
			SysRole oldUser = roleService.getRoleById(entity.getId());
			if(!oldUser.getRoleName().equals(entity.getRoleName())) {
				SysRole temp = roleService.getRoleByName(entity.getRoleName());
				if(temp != null) {
					result = false;
				}
			}
		}else {
			String s=entity.getRoleName();
			SysRole temp = roleService.getRoleByName(entity.getRoleName());
			if(temp != null) {
				result = false;
			}
		}
		return result;
	}
	
	
}
