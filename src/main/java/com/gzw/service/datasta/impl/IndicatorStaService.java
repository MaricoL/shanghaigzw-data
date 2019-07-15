package com.gzw.service.datasta.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzw.dao.datasta.DocumentRevisionDao;
import com.gzw.dao.datasta.IndicatorStaDao;
import com.gzw.entity.datasta.DocumentRevision;
import com.gzw.entity.datasta.IndicatorSta;
import com.gzw.entity.system.SysUser;
import com.gzw.service.datasta.IIndicatorStaService;
import com.gzw.service.file.IHhFileService;
import com.gzw.util.PageBean;



@Service("indicatorStaService")
public class IndicatorStaService implements IIndicatorStaService{
	@Autowired
	private IndicatorStaDao indicatorStaDao;
	@Autowired
	private DocumentRevisionDao documentRevisionDao;
	@Autowired
	private IHhFileService hhFileService;
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//返回全部信息
		@Override
		public PageBean<IndicatorSta> getIndicatorStaInfoByConditions(IndicatorSta entity, Integer currentPage,
				int pageSize) {
			// 固定的加载项
			Page<IndicatorSta> page = PageHelper.startPage(currentPage, pageSize);					
			List<IndicatorSta> list = indicatorStaDao.getIndicatorStaInfoByConditions(entity);
			PageBean<IndicatorSta> pageBean = new PageBean<>(page.getPageNum(), page.getPageSize(), page.getTotal());
			pageBean.setItems(list);

			return pageBean;
		}
		@Override
		public List<IndicatorSta> indicatorStaExport(IndicatorSta entity){
			return indicatorStaDao.getIndicatorStaInfoByConditions(entity);
			
		}
		@Override
		public List<IndicatorSta> getIndicatorStaExistCheck(IndicatorSta entity){
			return indicatorStaDao.getIndicatorStaExistCheck(entity);
			
		}
		@Override
		public IndicatorSta getIndicatorStaInfoById(Integer id){
			return indicatorStaDao.getIndicatorStaInfoById(id);
			
		}
		
		@Override
		@Transactional
		public void deleteIndicatorStaInfoById(Integer id,SysUser user) {
			
			IndicatorSta indicatorSta = indicatorStaDao.getIndicatorStaInfoById(id);
//			System.err.println("+++++++++++++++++++++++++++++++++++++++++++"+indicatorSta);
			indicatorStaDao.deleteIndicatorStaInfoById(id);
			DocumentRevision documentRevision = new DocumentRevision();
			documentRevision.setVersion(indicatorSta.getVersion());
			documentRevision.setChangeContent("删除指标标准："+indicatorSta.getIndicatorStaNum());
			documentRevision.setRemark("已删除");
			addDocumentRevision(documentRevision,user);
		}
		@Override
		@Transactional
		public void addIndicatorStaInfo(IndicatorSta entity,SysUser user) {	
			entity.setVersion("1.0");
			
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(user.getId().toString());
			entity.setCreatePersonName(user.getUserName());
			entity.setLastModifyDate(entity.getCreateDate());
			entity.setLastModifyPersonId(user.getId().toString());
			entity.setLastModifyPersonName(user.getUserName());
			
			indicatorStaDao.addIndicatorStaInfo(entity);	
			DocumentRevision documentRevision = new DocumentRevision();
			documentRevision.setVersion(entity.getVersion());
			documentRevision.setChangeContent("新增指标标准："+entity.getIndicatorStaNum());
			documentRevision.setRemark("已新增");
			addDocumentRevision(documentRevision,user);
		}
		@Override
		@Transactional
		public void updateIndicatorStaInfoById(IndicatorSta entity,SysUser user) {				
			IndicatorSta indicatorSta = indicatorStaDao.getIndicatorStaInfoById(entity.getId());		
			float version = Float.parseFloat(indicatorSta.getVersion());
			float lastVersion = (float) (version + 1.0);
			entity.setVersion(String.valueOf(lastVersion));
			
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(user.getId().toString());
			entity.setLastModifyPersonName(user.getUserName());
			
			indicatorStaDao.updateIndicatorStaInfoById(entity);	
			DocumentRevision documentRevision = new DocumentRevision();
			documentRevision.setVersion(String.valueOf(lastVersion));
			documentRevision.setChangeContent("修改指标标准："+entity.getIndicatorStaNum());
			documentRevision.setRemark("已修改");
			addDocumentRevision(documentRevision,user);
		}
		
		
		public void addDocumentRevision(DocumentRevision documentRevision,SysUser user) {
				
			String date = df.format(new Date());
			documentRevision.setRevisionDate(date);
			documentRevision.setModifier(user.getUserName());
			documentRevision.setModifierId(user.getAccount());
			documentRevisionDao.addDocumentRevisionInfo(documentRevision);
		}
		
}
