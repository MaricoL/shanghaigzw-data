package com.gzw.entity.datasta;
import lombok.Data;
@Data
public class IndicatorSta {
	private Integer id;
	private String staCategory;
	private String indicatorStaNum;
	private String staTheme;
	private String staClass;
	private String staSubclass;
	private String indicatorCname;
	private String indicatorCalias;
	private String indicatorEname;
	private String relevantIndicatorSta;
	private String relevantBasicDataSta;
	private String businessDefinition;
	private String statisticalCaliber;
	private String businessRules;
	private String publicStatisticalRules;
	private String indicatorFormat;
	private String commonUnit;
	private String dataLength;
	private String ranges;
	private String valueAccuracy;
	private String authoritativeSystemSource;
	private String basisMaking;
	private String staResponsibilityDepartment;
	private String minGenerationFrequency;
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
