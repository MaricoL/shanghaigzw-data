package com.gzw.controller.datasta;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gzw.entity.datasta.PublicSta;
import com.gzw.entity.system.SysUser;
import com.gzw.service.datasta.IPublicStaService;
import com.gzw.util.ExcelUtil;
import com.gzw.util.ImportExcelUtils;
import com.gzw.util.PageBean;
@Controller
@RequestMapping("/publicsta")
public class PublicStaController {
	@Autowired
	private IPublicStaService publicStaService;
	
//	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@RequestMapping("/_list")
	public String toPublicStaList(PublicSta entity, Integer currentPage, Map<String, Object> map,
			HttpServletRequest request) {
		if (currentPage == null || currentPage == 0) {
			currentPage = 1;
		}		
		entity.setIsDel(false);
		PageBean<PublicSta> pageBean = publicStaService.getPublicStaInfoByConditions(entity, currentPage, 10);
		map.put("pageBean", pageBean);
		map.put("conditionEntity", entity);		
		return "datasta/publicStaList";

	}
	@RequestMapping("/_viewlist")
	public String toPublicStaViewList(PublicSta entity, Integer currentPage, Map<String, Object> map,
			HttpServletRequest request) {
		if (currentPage == null || currentPage == 0) {
			currentPage = 1;
		}		
		entity.setIsDel(false);
		PageBean<PublicSta> pageBean = publicStaService.getPublicStaInfoByConditions(entity, currentPage, 10);
		map.put("pageBean", pageBean);
		map.put("conditionEntity", entity);		
		return "datasta/publicStaViewList";

	}
	

	/**
	 * 进入新增页面
	 * 
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/_edit")
	public String toPublicStaInfoEditPage(Integer id, Map<String, Object> map) {
		PublicSta publicSta = publicStaService.getPublicStaInfoById(id);
		map.put("publicSta", publicSta);	
		return "datasta/publicStaEdit";
	}
	@RequestMapping("/_view")
	public String toPublicStaInfoViewPage(Integer id, Map<String, Object> map) {
		PublicSta publicSta = publicStaService.getPublicStaInfoById(id);
		map.put("publicSta", publicSta);	
		return "datasta/publicStaView";
	}
	
	@RequestMapping(value = "/_delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deletePublicStaInfoById(Integer id, HttpServletRequest request) {		
		SysUser user = (SysUser) request.getSession(false).getAttribute("userSession");		
		publicStaService.deletePublicStaInfoById(id,user);	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", "1");
		map.put("message", "删除成功！");
		return map;
	}
	@RequestMapping(value = "/_addupdate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addPublicStaInfo(PublicSta entity, HttpServletRequest request) {		
		SysUser user = (SysUser) request.getSession(false).getAttribute("userSession");		
		List<PublicSta> check = publicStaService.getPublicStaExistCheck(entity);
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (entity.getId() != null) {			
				if (entity != null) {
				entity.setIsDel(false);
				publicStaService.updatePublicStaInfoById(entity,user);			
				map.put("flag", "1");
				map.put("message", "保存成功！");	}			
		}else {	
			if(check.size() != 0) {
				map.put("flag", "1");
				map.put("message", "保存失败！已有相同信息存在！");}else {	
				if (entity != null) {
				entity.setIsDel(false);	
				publicStaService.addPublicStaInfo(entity,user);			
				map.put("flag", "1");
				map.put("message", "保存成功！");	}}		
		}
			
		return map;		
		}
	
	
	@SuppressWarnings("resource")
	@RequestMapping(value = "/_export")
	@ResponseBody
	public void getPropertyCheck(PublicSta entity, Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<PublicSta> checkList = publicStaService.publicStaExport(entity);
		String[] title = { "序号", "公共统计规则编号", "公共统计规则名称", "规则描述", "备注" };
		String fileName = "公共统计规则表" + System.currentTimeMillis() + ".xls";
		String sheetName = "公共统计规则表";

		String content[][] = new String[checkList.size()][title.length];
		for (int i = 0; i < checkList.size(); i++) {
			PublicSta obj = checkList.get(i);
			String num = String.valueOf(i + 1);
			content[i][0] = num;
			if (obj.getPublicStaruleNum() != null) {
				content[i][1] = obj.getPublicStaruleNum().toString();
			}
			if (obj.getPublicStaruleName() != null) {
				content[i][2] = obj.getPublicStaruleName().toString();
			}
			if (obj.getRuleDescription() != null) {
				content[i][3] = obj.getRuleDescription().toString();
			}
			if (obj.getRemark() != null) {
				content[i][4] = obj.getRemark().toString();
			}
		}
		HSSFWorkbook wb = new HSSFWorkbook();
		wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, wb);
		OutputStream os = null;
		// 响应到客户端
		try {
			this.setResponseHeader(response, fileName);
			os = response.getOutputStream();
			wb.write(os);
			os.flush();
			/* os.close(); */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				os.close();
			}
		}

	}

	// 发送响应流方法
	public void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	@RequestMapping("/_upload")
	public String upload() {	
		return "datasta/publicStaUpload";
	}
	
	@RequestMapping(value = "/inport", method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object>  publicStaUpload(HttpServletRequest requests,HttpServletResponse  reponse,
			@RequestParam(value = "reasonf", required = false) MultipartFile file) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
         SysUser user = (SysUser) requests.getSession().getAttribute("userSession");      
         System.err.println(file);
        
         
         
         InputStream in =null;
         List<List<Object>> listob = null;
         if(file.isEmpty()){
             throw new Exception("文件不存在！");
         }
         in = file.getInputStream();
         listob = new ImportExcelUtils().getBankListByExcel(in,file.getOriginalFilename());
         System.err.println("================================="+listob);
         in.close();

         //一个个的取值放入到对应的属性setXX()中
        //声明变量，接受数据
         String publicStaruleNum=null;
         String publicStaruleName=null;
         String ruleDescription=null;
         String remark=null;         
         List<PublicSta> PList = new ArrayList<>();
         for (int i = 0; i < listob.size(); i++) {
             List<Object> lo = listob.get(i);

             PublicSta sta=new PublicSta();   //创建对象         

             publicStaruleNum=String.valueOf(lo.get(1)).trim();
             publicStaruleName=String.valueOf(lo.get(2)).trim();
             ruleDescription=String.valueOf(lo.get(3)).trim();
             remark=String.valueOf(lo.get(4)).trim();
             if(publicStaruleNum==null){
                 String msg = "第" + (i + 2) + "行的公共统计规则编号不能为空";
                
          		map.put("message", "数据导入出错！"+msg);
          		return map;
             }
             if(publicStaruleName==null){
                 String msg = "第" + (i + 2) + "行的公共统计规则名称不能为空";
                
          		map.put("message", "数据导入出错！"+msg);
          		return map;
             }
             sta.setPublicStaruleNum(publicStaruleNum);
             sta.setPublicStaruleName(publicStaruleName);
             sta.setRuleDescription(ruleDescription);
             sta.setRemark(remark);
             sta.setIsDel(false);
             PList.add(sta);
             
      }       
         for (int i = 0; i < PList.size(); i++) {
        	 publicStaService.addPublicStaInfo(PList.get(i),user);
		}
         
        map.put("flag", "1");
 		map.put("message", "数据导入成功！");
        return map;

    }
	
	
}
