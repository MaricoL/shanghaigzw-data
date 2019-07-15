package com.gzw.dao.datasta;

import java.util.List;

import com.gzw.entity.datasta.IndicatorSta;

public interface IndicatorStaDao {
	List<IndicatorSta> getIndicatorStaInfoByConditions(IndicatorSta entity);
	List<IndicatorSta> getIndicatorStaExistCheck(IndicatorSta entity);
	IndicatorSta getIndicatorStaInfoById(Integer id);
	void deleteIndicatorStaInfoById(Integer id);
	void addIndicatorStaInfo(IndicatorSta entity);
	void updateIndicatorStaInfoById(IndicatorSta entity);
}
