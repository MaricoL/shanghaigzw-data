package com.gzw.entity.system;

import lombok.Data;

@Data
public class SysUserrole {
    private Integer id;

    private String userId;

    private String roleId;

    private String operatorId;

    private String updateTime;
}