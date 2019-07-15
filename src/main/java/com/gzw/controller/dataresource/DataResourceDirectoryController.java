package com.gzw.controller.dataresource;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gzw.entity.dataresource.CatalogInformationItem;
import com.gzw.entity.dataresource.DataResourceDirectory;
import com.gzw.entity.dataresource.TreeNode;
import com.gzw.entity.system.SysUser;
import com.gzw.service.dataresource.IDataResourceDirectoryService;
import com.gzw.util.PageBean;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zz
 * @since 2019-07-02
 */
@Controller
@RequestMapping("dataResourceDirectory")
public class DataResourceDirectoryController {
	
	@Autowired
	public IDataResourceDirectoryService dataResourceDirectoryService;

	@RequestMapping("index")
	public String index(Map<String,Object> map, HttpServletRequest request) {
		
		
		return "dataResourceDirectory/dataResourceDirectoryIndex";
	}
	
	@GetMapping(value="tList")
	@ResponseBody
	public List<TreeNode> tList(){
		List<TreeNode> dList=dataResourceDirectoryService.listSecondDataResourceDirectory();
		return dList;
	}
	
	@RequestMapping("informationDetail")
	public String informationDetail(String id,String name,Integer currentPage,Map<String,Object> map, HttpServletRequest request) {
		map.put("name", name);
		map.put("id", id);
		if(currentPage == null || currentPage == 0) {
			currentPage = 1;
		}
		PageBean<CatalogInformationItem> pageBean=dataResourceDirectoryService.listByInformationResourceIdentifier(id, currentPage, 10);
		map.put("pageBean", pageBean);
		map.put("searchurl", request.getContextPath()+"/dataResourceDirectory/informationDetail");
		return "dataResourceDirectory/informationDetailView";
	}
}
