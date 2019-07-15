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
import com.gzw.dao.datasta.BasicDataStaDao;
import com.gzw.entity.datasta.DocumentRevision;
import com.gzw.entity.datasta.BasicDataSta;
import com.gzw.entity.system.SysUser;
import com.gzw.service.datasta.IBasicDataStaService;
import com.gzw.service.file.IHhFileService;
import com.gzw.util.PageBean;

@Service("basicDataStaService")
public class BasicDataStaService implements IBasicDataStaService{
	@Autowired
	private BasicDataStaDao basicDataStaDao;
	@Autowired
	private DocumentRevisionDao documentRevisionDao;
	@Autowired
	private IHhFileService hhFileService;
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//返回全部信息
		@Override
		public PageBean<BasicDataSta> getBasicDataStaInfoByConditions(BasicDataSta entity, Integer currentPage,
				int pageSize) {
			// 固定的加载项
			Page<BasicDataSta> page = PageHelper.startPage(currentPage, pageSize);					
			List<BasicDataSta> list = basicDataStaDao.getBasicDataStaInfoByConditions(entity);
			PageBean<BasicDataSta> pageBean = new PageBean<>(page.getPageNum(), page.getPageSize(), page.getTotal());
			pageBean.setItems(list);

			return pageBean;
		}
		@Override
		public List<BasicDataSta> basicDataStaExport(BasicDataSta entity){
			return basicDataStaDao.getBasicDataStaInfoByConditions(entity);
			
		}
		@Override
		public List<BasicDataSta> getBasicDataStaExistCheck(BasicDataSta entity){
			return basicDataStaDao.getBasicDataStaExistCheck(entity);
			
		}
		@Override
		public BasicDataSta getBasicDataStaInfoById(Integer id){
			return basicDataStaDao.getBasicDataStaInfoById(id);
			
		}
		
		@Override
		@Transactional
		public void deleteBasicDataStaInfoById(Integer id,SysUser user) {
			
			BasicDataSta basicDataSta = basicDataStaDao.getBasicDataStaInfoById(id);
//			System.err.println("+++++++++++++++++++++++++++++++++++++++++++"+BasicDataSta);
			basicDataStaDao.deleteBasicDataStaInfoById(id);
			DocumentRevision documentRevision = new DocumentRevision();
			documentRevision.setVersion(basicDataSta.getVersion());
			documentRevision.setChangeContent("删除基础数据标准："+basicDataSta.getStandardNum());
			documentRevision.setRemark("已删除");
			addDocumentRevision(documentRevision,user);
		}
		@Override
		@Transactional
		public void addBasicDataStaInfo(BasicDataSta entity,SysUser user) {			
			entity.setVersion("1.0");
			
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(user.getId().toString());
			entity.setCreatePersonName(user.getUserName());
			entity.setLastModifyDate(entity.getCreateDate());
			entity.setLastModifyPersonId(user.getId().toString());
			entity.setLastModifyPersonName(user.getUserName());
			
			basicDataStaDao.addBasicDataStaInfo(entity);	
			DocumentRevision documentRevision = new DocumentRevision();
			documentRevision.setVersion(entity.getVersion());
			documentRevision.setChangeContent("新增基础数据标准："+entity.getStandardNum());
			documentRevision.setRemark("已新增");
			addDocumentRevision(documentRevision,user);
		}
		@Override
		@Transactional
		public void updateBasicDataStaInfoById(BasicDataSta entity,SysUser user) {				
			BasicDataSta basicDataSta = basicDataStaDao.getBasicDataStaInfoById(entity.getId());
			float version = Float.parseFloat(basicDataSta.getVersion());
			float lastVersion = (float) (version + 1.0);
			entity.setVersion(String.valueOf(lastVersion));
			
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(user.getId().toString());
			entity.setLastModifyPersonName(user.getUserName());
			
			basicDataStaDao.updateBasicDataStaInfoById(entity);	
			DocumentRevision documentRevision = new DocumentRevision();
			documentRevision.setVersion(String.valueOf(lastVersion));
			documentRevision.setChangeContent("修改基础数据标准："+entity.getStandardNum());
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
