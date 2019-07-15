package com.gzw.service.file;

import com.gzw.entity.file.SysFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IHhFileService {
	public SysFile saveFile(MultipartFile picture, Integer entityId, Integer typeId, String package_path);
	
	SysFile getFileByUuid(String uuid);
	
	List<SysFile> getListFile(SysFile sysfile);
//	获取列表附件名
}
