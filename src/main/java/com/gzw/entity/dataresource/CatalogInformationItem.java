package com.gzw.entity.dataresource;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author zz
 * @since 2019-07-04
 */
@Data
public class CatalogInformationItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * 信息资源标识符
	 */
	private String informationResourceIdentifier;

	/**
	 * 信息项标识符
	 */
	private String informationItemIdentifier;

	/**
	 * 中文名称
	 */
	private String chineseName;

	/**
	 * 英文名称
	 */
	private String englishName;

	/**
	 * 定义说明
	 */
	private String explanatoryDefinition;

	/**
	 * 数据类型
	 */
	private String dataType;

	/**
	 * 数据长度
	 */
	private String dataLength;

	/**
	 * 共享类型
	 */
	private String sharedType;

	/**
	 * 共享方式
	 */
	private String sharingMode;

	/**
	 * 处室名称
	 */
	private String departmentName;

	/**
	 * 是否删除
	 */

	private Boolean isDel;

}
