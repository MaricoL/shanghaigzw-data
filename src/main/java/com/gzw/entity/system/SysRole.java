package com.gzw.entity.system;

import lombok.Data;

@Data
public class SysRole {
	
    private Integer id;

    private String createPersonID;

    private String createPersonName;

    private String createDate;

    private String lastModifyPersonId;

    private String lastModifyPersonName;

    private String lastModifyDate;

    private String roleName;

    private String roleDescription;

    private Boolean isDel;

    private String checked;
    
    private String rolenum;
    
}