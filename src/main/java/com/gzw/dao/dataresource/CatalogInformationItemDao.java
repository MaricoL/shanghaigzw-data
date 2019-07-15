package com.gzw.dao.dataresource;

import java.util.List;

import com.gzw.entity.dataresource.CatalogInformationItem;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zz
 * @since 2019-07-04
 */
public interface CatalogInformationItemDao  {

	List<CatalogInformationItem> listByInformationResourceIdentifier(String informationResourceIdentifier);
}
