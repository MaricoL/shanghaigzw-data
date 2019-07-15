package com.gzw.entity.dataresource;


import java.io.Serializable;

import lombok.Data;

/**
* <p>
    * 
    * </p>
*
* @author zz
* @since 2019-07-02
*/
    @Data
    public class DataResourceDirectory implements Serializable{

   
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

            /**
            * 信息资源标识符
            */
    private String informationResourceIdentifier;

            /**
            * 信息资源名称
            */
    private String informationResourceName;

            /**
            * 上级信息资源标识符
            */
    private String parentInformationResourceIdentifier;

            /**
            * 信息资源标识类型
            */
    private String informationResourceType;

            /**
            * 信息资源摘要
            */
    private String informationResourceAbstract;

            /**
            * 所属处室
            */
    private String department;

            /**
            * 所属处室代码
            */
    private String departmentCode;

            /**
            * 共享类型
            */
    private String sharedType;

            /**
            * 共享方式
            */
    private String sharedMethod;

            /**
            * 共享更新频度
            */
    private String sharedUpdateFrequenc;

            /**
            * 共享条件
            */
    private String sharedConditions;

            /**
            * 所属系统名称
            */
    private String belongSystemName;

            /**
            * 所属系统代码
            */
    private String belongSystemCode;

            /**
            * 所属表样/文件名称
            */
    private String belongFileName;

            /**
            * 所属系统运行状态
            */
    private String belongSystemState;

      
}
