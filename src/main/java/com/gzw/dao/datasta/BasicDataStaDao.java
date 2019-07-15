package com.gzw.dao.datasta;

import java.util.List;

import com.gzw.entity.datasta.BasicDataSta;

public interface BasicDataStaDao {
	List<BasicDataSta> getBasicDataStaInfoByConditions(BasicDataSta entity);
	List<BasicDataSta> getBasicDataStaExistCheck(BasicDataSta entity);
	BasicDataSta getBasicDataStaInfoById(Integer id);
	void deleteBasicDataStaInfoById(Integer id);
	void addBasicDataStaInfo(BasicDataSta entity);
	void updateBasicDataStaInfoById(BasicDataSta entity);
}
