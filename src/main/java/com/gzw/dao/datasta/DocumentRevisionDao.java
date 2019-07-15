package com.gzw.dao.datasta;

import java.util.List;

import com.gzw.entity.datasta.DocumentRevision;

public interface DocumentRevisionDao {
	List<DocumentRevision> getDocumentRevisionInfoByConditions(DocumentRevision entity);
	DocumentRevision getDocumentRevisionInfoById(Integer id);
	void deleteDocumentRevisionInfoById(DocumentRevision entity);
	void addDocumentRevisionInfo(DocumentRevision entity);
	void updateDocumentRevisionInfoById(DocumentRevision entity);
}
