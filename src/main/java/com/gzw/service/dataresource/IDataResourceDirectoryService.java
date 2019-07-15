package com.gzw.service.dataresource;

import java.util.List;

import com.gzw.entity.dataresource.CatalogInformationItem;
import com.gzw.entity.dataresource.DataResourceDirectory;
import com.gzw.entity.dataresource.TreeNode;
import com.gzw.util.PageBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zz
 * @since 2019-07-02
 */
public interface IDataResourceDirectoryService  {
	
	public List<DataResourceDirectory> listDataResourceDirectory();
	
	public List<TreeNode> listSecondDataResourceDirectory();
	
	PageBean<CatalogInformationItem> listByInformationResourceIdentifier(String informationResourceIdentifier, Integer currentPage, Integer pageSize);


}
