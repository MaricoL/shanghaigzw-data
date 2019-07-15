package com.gzw.service.datasta;

import java.util.List;

import com.gzw.entity.datasta.PublicSta;
import com.gzw.entity.system.SysUser;
import com.gzw.util.PageBean;

public interface IPublicStaService {

	PageBean<PublicSta> getPublicStaInfoByConditions(PublicSta entity, Integer currentPage, int pageSize);

	List<PublicSta> getPublicStaExistCheck(PublicSta entity);

	PublicSta getPublicStaInfoById(Integer id);

	void deletePublicStaInfoById(Integer id, SysUser user);

	void addPublicStaInfo(PublicSta entity, SysUser user);

	void updatePublicStaInfoById(PublicSta entity, SysUser user);

	List<PublicSta> publicStaExport(PublicSta entity);

}
