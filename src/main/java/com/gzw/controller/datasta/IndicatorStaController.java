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

import com.gzw.entity.datasta.IndicatorSta;
import com.gzw.service.datasta.IIndicatorStaService;
import com.gzw.util.ExcelUtil;
import com.gzw.util.ImportExcelUtils;
import com.gzw.util.PageBean;
import com.gzw.entity.system.SysUser;


@Controller
@RequestMapping("/indicatorsta")
public class IndicatorStaController {
	@Autowired
	private IIndicatorStaService indicatorStaService;
	
//	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@RequestMapping("/_list")
	public String toIndicatorStaList(IndicatorSta entity, Integer currentPage, Map<String, Object> map,
			HttpServletRequest request) {
		if (currentPage == null || currentPage == 0) {
			currentPage = 1;
		}		
		entity.setIsDel(false);
		PageBean<IndicatorSta> pageBean = indicatorStaService.getIndicatorStaInfoByConditions(entity, currentPage, 10);
		map.put("pageBean", pageBean);
		map.put("conditionEntity", entity);		
		return "datasta/indicatorStaList";

	}
	@RequestMapping("/_viewlist")
	public String toIndicatorStaViewList(IndicatorSta entity, Integer currentPage, Map<String, Object> map,
			HttpServletRequest request) {
		if (currentPage == null || currentPage == 0) {
			currentPage = 1;
		}		
		entity.setIsDel(false);
		PageBean<IndicatorSta> pageBean = indicatorStaService.getIndicatorStaInfoByConditions(entity, currentPage, 10);
		map.put("pageBean", pageBean);
		map.put("conditionEntity", entity);		
		return "datasta/indicatorStaViewList";

	}
	

	/**
	 * 进入新增页面
	 * 
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/_edit")
	public String toIndicatorStaInfoEditPage(Integer id, Map<String, Object> map) {
		IndicatorSta indicatorSta = indicatorStaService.getIndicatorStaInfoById(id);
		map.put("indicatorSta", indicatorSta);	
		return "datasta/indicatorStaEdit";
	}
	@RequestMapping("/_view")
	public String toIndicatorStaInfoViewPage(Integer id, Map<String, Object> map) {
		IndicatorSta indicatorSta = indicatorStaService.getIndicatorStaInfoById(id);
		map.put("indicatorSta", indicatorSta);	
		return "datasta/indicatorStaView";
	}
	
	@RequestMapping(value = "/_delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteIndicatorStaInfoById(Integer id, HttpServletRequest request) {		
		SysUser user = (SysUser) request.getSession(false).getAttribute("userSession");		
		indicatorStaService.deleteIndicatorStaInfoById(id,user);	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", "1");
		map.put("message", "删除成功！");
		return map;
	}
	@RequestMapping(value = "/_addupdate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addIndicatorStaInfo(IndicatorSta entity, HttpServletRequest request) {		
		SysUser user = (SysUser) request.getSession(false).getAttribute("userSession");		
		List<IndicatorSta> check = indicatorStaService.getIndicatorStaExistCheck(entity);
		Map<String, Object> map = new HashMap<String, Object>();

		
		if (entity.getId() != null) {			
				if (entity != null) {
				entity.setIsDel(false);
				indicatorStaService.updateIndicatorStaInfoById(entity,user);			
				map.put("flag", "1");
				map.put("message", "保存成功！");	}			
		}else {	
			if(check.size() != 0) {
				map.put("flag", "1");
				map.put("message", "保存失败！已有相同信息存在！");}else {	
				if (entity != null) {
				entity.setIsDel(false);	
				indicatorStaService.addIndicatorStaInfo(entity,user);			
				map.put("flag", "1");
				map.put("message", "保存成功！");	}
				}		
		}
			
		return map;		
		}
	@SuppressWarnings("resource")
	@RequestMapping(value = "/_export")
	@ResponseBody
	public void getPropertyCheck(IndicatorSta entity, Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<IndicatorSta> checkList = indicatorStaService.indicatorStaExport(entity);
		String[] title = { "序号", "标准类别", "指标标准编号", "标准主题", "标准大类", "标准子类", "指标中文名称", "指标英文名称", "指标中文别名", "相关指标标准", "相关基础数据标准", "业务定义", "统计口径", "业务规则", "适用的公共统计规则",  
				 "指标格式", "常用度量单位", "数据长度", "取值范围", "取值精度", "权威系统来源", "制定依据", "标准责任部门", "最低生成频次", "标准制定人员", "标准制定日期", "标准修改人员", "标准修改日期", "标准版本"};
		String fileName = "指标标准表" + System.currentTimeMillis() + ".xls";
		String sheetName = "指标标准表";
		String content[][] = new String[checkList.size()][title.length];
		for (int i = 0; i < checkList.size(); i++) {
			IndicatorSta obj = checkList.get(i);
			String num = String.valueOf(i + 1);
			content[i][0] = num;
			if (obj.getStaCategory() != null) {
				content[i][1] = obj.getStaCategory().toString();
			}
			if (obj.getIndicatorStaNum() != null) {
				content[i][2] = obj.getIndicatorStaNum().toString();
			}
			if (obj.getStaTheme() != null) {
				content[i][3] = obj.getStaTheme().toString();
			}
			if (obj.getStaClass() != null) {
				content[i][4] = obj.getStaClass().toString();
			}
			if (obj.getStaSubclass() != null) {
				content[i][5] = obj.getStaSubclass().toString();
			}
			if (obj.getIndicatorCname() != null) {
				content[i][6] = obj.getIndicatorCname().toString();
			}
			if (obj.getIndicatorEname() != null) {
				content[i][7] = obj.getIndicatorEname().toString();
			}
			if (obj.getIndicatorCalias() != null) {
				content[i][8] = obj.getIndicatorCalias().toString();
			}
			if (obj.getRelevantIndicatorSta() != null) {
				content[i][9] = obj.getRelevantIndicatorSta().toString();
			}
			if (obj.getRelevantBasicDataSta() != null) {
				content[i][10] = obj.getRelevantBasicDataSta().toString();
			}
			if (obj.getBusinessDefinition() != null) {
				content[i][11] = obj.getBusinessDefinition().toString();
			}			
			if (obj.getStatisticalCaliber() != null) {
				content[i][12] = obj.getStatisticalCaliber().toString();
			}
			if (obj.getBusinessRules() != null) {
				content[i][13] = obj.getBusinessRules().toString();
			}
			if (obj.getPublicStatisticalRules() != null) {
				content[i][14] = obj.getPublicStatisticalRules().toString();
			}
			if (obj.getIndicatorFormat() != null) {
				content[i][15] = obj.getIndicatorFormat().toString();
			}
			if (obj.getCommonUnit() != null) {
				content[i][16] = obj.getCommonUnit().toString();
			}
			if (obj.getDataLength() != null) {
				content[i][17] = obj.getDataLength().toString();
			}			
			if (obj.getRanges() != null) {
				content[i][18] = obj.getRanges().toString();
			}
			if (obj.getValueAccuracy() != null) {
				content[i][19] = obj.getValueAccuracy().toString();
			}
			if (obj.getAuthoritativeSystemSource() != null) {
				content[i][20] = obj.getAuthoritativeSystemSource().toString();
			}
			if (obj.getBasisMaking() != null) {
				content[i][21] = obj.getBasisMaking().toString();
			}
			if (obj.getStaResponsibilityDepartment() != null) {
				content[i][22] = obj.getStaResponsibilityDepartment().toString();
			}
			if (obj.getMinGenerationFrequency() != null) {
				content[i][23] = obj.getMinGenerationFrequency().toString();
			}
			if (obj.getCreatePersonName() != null) {
				content[i][24] = obj.getCreatePersonName().toString();
			}
			if (obj.getCreateDate() != null) {
				content[i][25] = obj.getCreateDate().toString();
			}
			if (obj.getLastModifyPersonName() != null) {
				content[i][26] = obj.getLastModifyPersonName().toString();
			}
			if (obj.getLastModifyDate() != null) {
				content[i][27] = obj.getLastModifyDate().toString();
			}
			if (obj.getVersion() != null) {
				content[i][28] = obj.getVersion().toString();
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
	public String _upload() {	
		return "datasta/indicatorStaUpload";
	}
	
	@RequestMapping(value = "/inport", method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object>  indicatorStaUpload(HttpServletRequest requests,HttpServletResponse  reponse,
			@RequestParam(value = "reasonf", required = false) MultipartFile file) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
         SysUser user = (SysUser) requests.getSession().getAttribute("userSession");      
         System.err.println(file);
         System.err.println(user);

         InputStream in =null;
         List<List<Object>> listob = null;
         if(file.isEmpty()){
             throw new Exception("文件不存在！");
         }
         in = file.getInputStream();
         listob = new ImportExcelUtils().getBankListByExcel(in,file.getOriginalFilename());
//         System.err.println("================================="+listob);
         in.close();
        
         				 String staCategory=null;
         				 String indicatorStaNum=null;
         				 String staTheme=null;
         				 String staClass=null;
         				 String staSubclass=null;
                         String indicatorCname=null;
                         String indicatorCalias=null;
                         String indicatorEname=null;
                         String relevantIndicatorSta=null;
                         String relevantBasicDataSta=null;
                         String businessDefinition=null;
                         String statisticalCaliber=null;
                         String businessRules=null;
                         String publicStatisticalRules=null;
                         String indicatorFormat=null;
                         String commonUnit=null;
                         String dataLength=null;
                         String ranges=null;
                         String valueAccuracy=null;
                         String authoritativeSystemSource=null;
                         String basisMaking=null;
                         String staResponsibilityDepartment=null;
                         String minGenerationFrequency=null;
                         String createPersonName=null;
                         String createDate=null;
                         String lastModifyPersonName=null;
                         String lastModifyDate=null;
                         String version=null;
             
         List<String> CnameList=new ArrayList<>();
               
         List<IndicatorSta> PList = new ArrayList<>();
         for (int i = 0; i < listob.size(); i++) {
             List<Object> lo = listob.get(i);
             IndicatorSta sta=new IndicatorSta();   //创建对象         
             System.err.println(sta.getAuthoritativeSystemSource().getClass());
             staCategory=String.valueOf(lo.get(1)).trim();
             indicatorStaNum=String.valueOf(lo.get(2)).trim();
             staTheme=String.valueOf(lo.get(3)).trim();
             staClass=String.valueOf(lo.get(4)).trim();
             staSubclass=String.valueOf(lo.get(5)).trim();
             indicatorCname=String.valueOf(lo.get(6)).trim();
             indicatorCalias=String.valueOf(lo.get(7)).trim();
             indicatorEname=String.valueOf(lo.get(8)).trim();
             relevantIndicatorSta=String.valueOf(lo.get(9)).trim();
             relevantBasicDataSta=String.valueOf(lo.get(10)).trim();
             businessDefinition=String.valueOf(lo.get(11)).trim();
             statisticalCaliber=String.valueOf(lo.get(12)).trim();
             businessRules=String.valueOf(lo.get(13)).trim();
             publicStatisticalRules=String.valueOf(lo.get(14)).trim();
             indicatorFormat=String.valueOf(lo.get(15)).trim();
             commonUnit=String.valueOf(lo.get(16)).trim();
             dataLength=String.valueOf(lo.get(17)).trim();
             ranges=String.valueOf(lo.get(18)).trim();
             valueAccuracy=String.valueOf(lo.get(19)).trim();
             authoritativeSystemSource=String.valueOf(lo.get(20)).trim();
             basisMaking=String.valueOf(lo.get(21)).trim();
             staResponsibilityDepartment=String.valueOf(lo.get(22)).trim();
             minGenerationFrequency=String.valueOf(lo.get(23)).trim();
             createPersonName=String.valueOf(lo.get(24)).trim();
             createDate=String.valueOf(lo.get(25)).trim();
             lastModifyPersonName=String.valueOf(lo.get(26)).trim();
             lastModifyDate=String.valueOf(lo.get(27)).trim();
             version=String.valueOf(lo.get(28)).trim();
             
             if(indicatorStaNum==null){
                 String msg = "第" + (i + 2) + "行的指标标准编号不能为空";
                
          		map.put("message", "数据导入出错！"+msg);
          		return map;
             }
             if(indicatorCname==null){
                 String msg = "第" + (i + 2) + "行的指标中文名称名称不能为空";
                
          		map.put("message", "数据导入出错！"+msg);
          		return map;
             }
             sta.setStaCategory(staCategory);
             sta.setIndicatorStaNum(indicatorStaNum);
             sta.setStaTheme(staTheme);
             sta.setStaClass(staClass);
             sta.setStaSubclass(staSubclass);
             sta.setIndicatorCname(indicatorCname);
             sta.setIndicatorCalias(indicatorCalias);
             sta.setIndicatorEname(indicatorEname);
             sta.setRelevantIndicatorSta(relevantIndicatorSta);
             sta.setBusinessRules(businessRules);
             sta.setPublicStatisticalRules(publicStatisticalRules);
             sta.setIndicatorFormat(indicatorFormat);
             sta.setCommonUnit(commonUnit);
             sta.setDataLength(dataLength);
             sta.setRanges(ranges);
             sta.setValueAccuracy(valueAccuracy);
             sta.setAuthoritativeSystemSource(authoritativeSystemSource);
             sta.setBasisMaking(basisMaking);
             sta.setStaResponsibilityDepartment(staResponsibilityDepartment);
             sta.setMinGenerationFrequency(minGenerationFrequency);
             sta.setCreatePersonName(createPersonName);
             sta.setCreateDate(createDate);
             sta.setLastModifyPersonName(lastModifyPersonName);
             sta.setLastModifyDate(lastModifyDate);
             sta.setVersion(version);
                                                              
             PList.add(sta);
             
      }       
         for (int i = 0; i < PList.size(); i++) {
        	 indicatorStaService.addIndicatorStaInfo(PList.get(i),user);
		}
         
        map.put("flag", "1");
 		map.put("message", "数据导入成功！");
        return map;

    }

}
