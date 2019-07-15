package com.gzw.entity.file;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysFile implements Serializable {

	private Integer id;
	private Integer typeId;
	private Integer releaseId;
	private String fileName;
	private String filePath;
	private String fileRemark;
	private String fileUuid;
}
