package com.gzw.service.datasta;

import java.util.List;

import com.gzw.entity.datasta.IndicatorSta;
import com.gzw.entity.system.SysUser;
import com.gzw.util.PageBean;

public interface IIndicatorStaService {

	PageBean<IndicatorSta> getIndicatorStaInfoByConditions(IndicatorSta entity, Integer currentPage, int pageSize);

	void deleteIndicatorStaInfoById(Integer id, SysUser user);

	void addIndicatorStaInfo(IndicatorSta entity, SysUser user);

	List<IndicatorSta> getIndicatorStaExistCheck(IndicatorSta entity);

	void updateIndicatorStaInfoById(IndicatorSta entity, SysUser user);
	
	IndicatorSta getIndicatorStaInfoById(Integer id);

	List<IndicatorSta> indicatorStaExport(IndicatorSta entity);

}
