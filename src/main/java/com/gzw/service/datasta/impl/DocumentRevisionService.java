package com.gzw.service.datasta.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzw.dao.datasta.DocumentRevisionDao;
import com.gzw.entity.datasta.DocumentRevision;
import com.gzw.service.datasta.IDocumentRevisionService;
import com.gzw.service.file.IHhFileService;
import com.gzw.util.PageBean;

@Service("documentRevisionService")
public class DocumentRevisionService implements IDocumentRevisionService{

	@Autowired
	private DocumentRevisionDao documentRevisionDao;
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//返回全部信息
		@Override
		public PageBean<DocumentRevision> getDocumentRevisionInfoByConditions(DocumentRevision entity, Integer currentPage,
				int pageSize) {
			// 固定的加载项
			Page<DocumentRevision> page = PageHelper.startPage(currentPage, pageSize);					
			List<DocumentRevision> list = documentRevisionDao.getDocumentRevisionInfoByConditions(entity);
			PageBean<DocumentRevision> pageBean = new PageBean<>(page.getPageNum(), page.getPageSize(), page.getTotal());
			pageBean.setItems(list);

			return pageBean;
		}
}
