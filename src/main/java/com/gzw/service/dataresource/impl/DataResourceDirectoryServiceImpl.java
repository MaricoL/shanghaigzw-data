package com.gzw.service.dataresource.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzw.dao.dataresource.CatalogInformationItemDao;
import com.gzw.dao.dataresource.DataResourceDirectoryDao;
import com.gzw.entity.dataresource.CatalogInformationItem;
import com.gzw.entity.dataresource.DataResourceDirectory;
import com.gzw.entity.dataresource.TreeNode;
import com.gzw.entity.system.SysUser;
import com.gzw.service.dataresource.IDataResourceDirectoryService;
import com.gzw.util.PageBean;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zz
 * @since 2019-07-02
 */
@Service
@Transactional
public class DataResourceDirectoryServiceImpl  implements IDataResourceDirectoryService {

	@Autowired
	public DataResourceDirectoryDao dataResourceDirectoryDao;
	@Autowired
	public CatalogInformationItemDao catalogInformationItemDao;
	
	@Override
	public List<DataResourceDirectory> listDataResourceDirectory() {
		// TODO Auto-generated method stub
		return dataResourceDirectoryDao.listDataResourceDirectory();
	}

	@Override
	public List<TreeNode> listSecondDataResourceDirectory() {
		// TODO Auto-generated method stub
		return dataResourceDirectoryDao.listSecondDataResourceDirectory();
	}

	@Override
	public PageBean<CatalogInformationItem> listByInformationResourceIdentifier(String informationResourceIdentifier,
			Integer currentPage, Integer pageSize) {
		//固定的加载项
				Page<CatalogInformationItem> page = PageHelper.startPage(currentPage, pageSize);
				//数据库查询数据
				List<CatalogInformationItem> allCatalogInformationItem = catalogInformationItemDao.listByInformationResourceIdentifier(informationResourceIdentifier);
				
				PageBean<CatalogInformationItem> pageBean = new PageBean<>(page.getPageNum(),page.getPageSize(),page.getTotal());
				
				pageBean.setItems(allCatalogInformationItem);
				
				return pageBean;
	}

	

}
