package com.gzw.entity.system;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUser implements Serializable {
	private Integer id;
	private String account;
	private String password;
	private String userName;
	private Integer userSex;
	private String compUscd;
	private Integer flag;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String isDel;
	
	private String companyName;
}
