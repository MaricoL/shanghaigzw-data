package com.gzw.dao.datasta;

import java.util.List;

import com.gzw.entity.datasta.PublicSta;

public interface PublicStaDao {
	List<PublicSta> getPublicStaInfoByConditions(PublicSta entity);
	List<PublicSta> getPublicStaExistCheck(PublicSta entity);
	PublicSta getPublicStaInfoById(Integer id);
	void deletePublicStaInfoById(Integer id);
	void addPublicStaInfo(PublicSta entity);
	void updatePublicStaInfoById(PublicSta entity);
}
