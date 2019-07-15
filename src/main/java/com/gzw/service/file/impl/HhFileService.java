package com.gzw.service.file.impl;


import com.gzw.dao.file.SysFileDao;
import com.gzw.entity.file.SysFile;
import com.gzw.service.file.IHhFileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HhFileService implements IHhFileService {

	@Autowired
	private SysFileDao hhFileDao;
	
	/*@Value("${upload.file.location}") 
	private String uploadLocation;*/

	@Override
	@Transactional
	public SysFile saveFile(MultipartFile picture, Integer entityId, Integer typeId, String package_path) {
		// TODO Auto-generated method stub
		try {
			List<String> fileStrList = saveFile(picture, package_path);
			SysFile file = new SysFile();
			file.setTypeId(typeId);
			file.setReleaseId(entityId);
			file.setFileName(fileStrList.get(0));
			file.setFilePath(fileStrList.get(1));
			file.setFileUuid(fileStrList.get(2));
			// 先执行保存
			hhFileDao.insert(file);
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 附件上传
	 * zhuzhou
	 * 
	 * @param file
	 * @param
	 * @param
	 * @return
	 * @throws FileNotFoundException 
	 */
	public  List<String> saveFile(MultipartFile file, String package_path) throws FileNotFoundException {
		List<String> fileStrList = new ArrayList<String>();
		// 获取文件名
		String name = file.getOriginalFilename();
		// 截取文件后缀
		String nameSub = name.substring(name.lastIndexOf("."));
		// 拼接项目名 + 包名： \ upload \ 项目名 \ xxx \
//		String ITEM = Common.getPath(pkgs, request);
		// 拼接路劲 \ opt \ upload \ 项目名 \ supermarketPicture \
//		String pathDir = decyStr + ITEM;
		// 创建新uuid
		String uuid = java.util.UUID.randomUUID() + "";
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		//String projectPath=request.getSession().getServletContext().getRealPath("file/");
		// 文件最终路径 F:\apache-tomcat\apache-tomcat-6.0.44\webapps\ETMS \ upload \ uuid+fileNameSub
		String filePath = package_path + uuid + nameSub;
		
		fileStrList.add(name);
		fileStrList.add(package_path + uuid + nameSub);
		fileStrList.add(uuid + nameSub);
		// 创建文件夹
		File savePivtureDirFile = new File(package_path);
		if (!savePivtureDirFile.exists()) {
			savePivtureDirFile.mkdirs();
		}
		try {
			// 转存文件
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath));
			//file.transferTo(savePivtureDirFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileStrList;
	}

	@Override
	public SysFile getFileByUuid(String uuid) {
		// TODO Auto-generated method stub
		return hhFileDao.selectByUUID(uuid);
	}

	@Override
	public List<SysFile> getListFile(SysFile sysfile) {
		// TODO Auto-generated method stub
		return hhFileDao.getListFile(sysfile);
	}
	
}
