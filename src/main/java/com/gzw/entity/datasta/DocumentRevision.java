package com.gzw.entity.datasta;
import lombok.Data;
@Data
public class DocumentRevision {
	private Integer id;
	private String revisionDate;
	private String changeContent;
	private String modifier;
	private String modifierId;
	private String reviewer;
	private String reviewerId;
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
