package com.gzw.controller.datasta;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gzw.entity.datasta.DocumentRevision;
import com.gzw.service.datasta.IDocumentRevisionService;
import com.gzw.util.PageBean;

@Controller
@RequestMapping("/documentrevision")
public class DocumentRevisionController {
	@Autowired
	private IDocumentRevisionService documentRevisionService;
	@RequestMapping("/_list")
	public String toDocumentRevisionList(DocumentRevision entity, Integer currentPage, Map<String, Object> map,
			HttpServletRequest request) {
		if (currentPage == null || currentPage == 0) {
			currentPage = 1;
		}		
		/*entity.setIsDel(false);*/
		PageBean<DocumentRevision> pageBean = documentRevisionService.getDocumentRevisionInfoByConditions(entity, currentPage, 10);
		map.put("pageBean", pageBean);
		map.put("conditionEntity", entity);		
		return "datasta/documentRevisionList";

	}
}
