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
import com.gzw.dao.datasta.PublicStaDao;
import com.gzw.entity.datasta.DocumentRevision;
import com.gzw.entity.datasta.PublicSta;
import com.gzw.entity.system.SysUser;
import com.gzw.service.datasta.IPublicStaService;
import com.gzw.service.file.IHhFileService;
import com.gzw.util.PageBean;

@Service("publicStaService")
public class PublicStaService implements IPublicStaService{
	@Autowired
	private PublicStaDao publicStaDao;
	@Autowired
	private DocumentRevisionDao documentRevisionDao;
	@Autowired
	private IHhFileService hhFileService;
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//返回全部信息
		@Override
		public PageBean<PublicSta> getPublicStaInfoByConditions(PublicSta entity, Integer currentPage,
				int pageSize) {
			// 固定的加载项
			Page<PublicSta> page = PageHelper.startPage(currentPage, pageSize);					
			List<PublicSta> list = publicStaDao.getPublicStaInfoByConditions(entity);
			PageBean<PublicSta> pageBean = new PageBean<>(page.getPageNum(), page.getPageSize(), page.getTotal());
			pageBean.setItems(list);

			return pageBean;
		}
		
		@Override
		public List<PublicSta> publicStaExport(PublicSta entity){
			return publicStaDao.getPublicStaInfoByConditions(entity);
			
		}
		
		@Override
		public List<PublicSta> getPublicStaExistCheck(PublicSta entity){
			return publicStaDao.getPublicStaExistCheck(entity);
			
		}
		@Override
		public PublicSta getPublicStaInfoById(Integer id){
			return publicStaDao.getPublicStaInfoById(id);
			
		}
		
		@Override
		@Transactional
		public void deletePublicStaInfoById(Integer id,SysUser user) {
			
			PublicSta publicSta = publicStaDao.getPublicStaInfoById(id);
//			System.err.println("+++++++++++++++++++++++++++++++++++++++++++"+PublicSta);
			publicStaDao.deletePublicStaInfoById(id);
			DocumentRevision documentRevision = new DocumentRevision();
			documentRevision.setVersion(publicSta.getVersion());
			documentRevision.setChangeContent("删除公共统计规则："+publicSta.getPublicStaruleNum());
			documentRevision.setRemark("已删除");
			addDocumentRevision(documentRevision,user);
		}
		@Override
		@Transactional
		public void addPublicStaInfo(PublicSta entity,SysUser user) {			
			entity.setVersion("1.0");
			
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(user.getId().toString());
			entity.setCreatePersonName(user.getUserName());
			entity.setLastModifyDate(entity.getCreateDate());
			entity.setLastModifyPersonId(user.getId().toString());
			entity.setLastModifyPersonName(user.getUserName());
			
			publicStaDao.addPublicStaInfo(entity);	
			DocumentRevision documentRevision = new DocumentRevision();
			documentRevision.setVersion(entity.getVersion());
			documentRevision.setChangeContent("新增公共统计规则："+entity.getPublicStaruleNum());
			documentRevision.setRemark("已新增");
			addDocumentRevision(documentRevision,user);
		}
		@Override
		@Transactional
		public void updatePublicStaInfoById(PublicSta entity,SysUser user) {				
			PublicSta PublicSta = publicStaDao.getPublicStaInfoById(entity.getId());
			float version = Float.parseFloat(PublicSta.getVersion());
			float lastVersion = (float) (version + 1.0);
			entity.setVersion(String.valueOf(lastVersion));
			
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(user.getId().toString());
			entity.setLastModifyPersonName(user.getUserName());
			
			publicStaDao.updatePublicStaInfoById(entity);	
			DocumentRevision documentRevision = new DocumentRevision();
			documentRevision.setVersion(String.valueOf(lastVersion));
			documentRevision.setChangeContent("修改公共统计规则："+entity.getPublicStaruleNum());
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
