package com.gzw.entity.datasta;
import lombok.Data;
@Data
public class PublicSta {
	private Integer id;
	private String publicStaruleNum;
	private String publicStaruleName;
	private String ruleDescription;
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
