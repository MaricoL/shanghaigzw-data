package com.gzw.dao.dataresource;

import java.util.List;

import com.gzw.entity.dataresource.DataResourceDirectory;
import com.gzw.entity.dataresource.TreeNode;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zz
 * @since 2019-07-02
 */
public interface DataResourceDirectoryDao  {
	
	public List<DataResourceDirectory> listDataResourceDirectory();

	public List<TreeNode> listSecondDataResourceDirectory();
}
