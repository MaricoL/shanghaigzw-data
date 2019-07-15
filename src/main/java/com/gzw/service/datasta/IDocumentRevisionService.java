package com.gzw.service.datasta;

import com.gzw.entity.datasta.DocumentRevision;
import com.gzw.util.PageBean;

public interface IDocumentRevisionService {

	PageBean<DocumentRevision> getDocumentRevisionInfoByConditions(DocumentRevision entity, Integer currentPage,
			int pageSize);

}
