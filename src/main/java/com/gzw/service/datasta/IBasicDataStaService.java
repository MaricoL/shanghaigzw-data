package com.gzw.service.datasta;

import java.util.List;

import com.gzw.entity.datasta.BasicDataSta;
import com.gzw.entity.system.SysUser;
import com.gzw.util.PageBean;

public interface IBasicDataStaService {

	PageBean<BasicDataSta> getBasicDataStaInfoByConditions(BasicDataSta entity, Integer currentPage, int pageSize);

	List<BasicDataSta> getBasicDataStaExistCheck(BasicDataSta entity);

	BasicDataSta getBasicDataStaInfoById(Integer id);

	void deleteBasicDataStaInfoById(Integer id, SysUser user);

	void addBasicDataStaInfo(BasicDataSta entity, SysUser user);

	void updateBasicDataStaInfoById(BasicDataSta entity, SysUser user);

	List<BasicDataSta> basicDataStaExport(BasicDataSta entity);

}
