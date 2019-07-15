package com.gzw.entity.datasta;

import lombok.Data;

@Data
public class BasicDataSta {
	
	private Integer id;
	private String standardNum;
	private String standardName;
	private String codeValue;
	private String codeMeaning;
	private String remark;
	private String version;

	// 通用字段
		private String createPersonName;
		private String createPersonId;
		private String createDate;
		private String lastModifyPersonId;
		private String lastModifyPersonName;
		private String lastModifyDate;
		private Boolean isDel;

}
