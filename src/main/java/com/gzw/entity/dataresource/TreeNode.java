package com.gzw.entity.dataresource;

import java.io.Serializable;

import lombok.Data;

@Data
public class TreeNode implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String pId;
	private String name;
	private String level;
}
