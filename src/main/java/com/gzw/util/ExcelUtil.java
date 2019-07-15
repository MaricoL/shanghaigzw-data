package com.gzw.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class ExcelUtil {

	/**
	 * 导出Excel
	 * 
	 * @param sheetName sheet名称
	 * @param title     标题
	 * @param values    内容
	 * @param wb        HSSFWorkbook对象
	 * @return
	 */
	public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, HSSFWorkbook wb) {

		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		if (wb == null) {
			return null;
		}

		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
		HSSFRow row = sheet.createRow(0);

		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		// 声明列对象
		HSSFCell cell = null;

		// 创建标题
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}

		// 创建内容
		for (int i = 0; i < values.length; i++) {
			row = sheet.createRow(i + 1);
			for (int j = 0; j < values[i].length; j++) {
				// 将内容按顺序赋给对应的列对象
				row.createCell(j).setCellValue(values[i][j]);
			}
		}
		return wb;
	}

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
//	public static String getHSSFCellFormatValue(HSSFCell cell) {
//		String cellvalue = "";
//		if (cell != null) {
//			// 判断当前Cell的Type
//			switch (cell.getCellType()) {
//			// 如果当前Cell的Type为NUMERIC
//			case NUMERIC: {
//				cellvalue = String.valueOf(cell.getNumericCellValue());
//				break;
//			}
//			case FORMULA: {
//				// 判断当前的cell是否为Date
//				if (HSSFDateUtil.isCellDateFormatted(cell)) {
//					Date date = cell.getDateCellValue();
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//					cellvalue = sdf.format(date);
//				}
//				break;
//			}
//			// 如果当前Cell的Type为STRIN
//			case STRING:
//				// 取得当前的Cell字符串
//				cellvalue = cell.getRichStringCellValue().getString();
//				break;
//			// 默认的Cell值
//			default:
//				cellvalue = " ";
//				break;
//			}
//		} else {
//			cellvalue = "";
//		}
//		return cellvalue;
//
//	}

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
//	public static String getXSSFCellFormatValue(XSSFCell cell) {
//		if (cell != null) {
//			String cellvalue = "";
//			// 判断当前Cell的Type
//			switch (cell.getCellType()) {
//			// 如果当前Cell的Type为NUMERIC
//			case NUMERIC: {
//				cellvalue = String.valueOf(cell.getNumericCellValue());
//				break;
//			}
//			case FORMULA: {
//				// 判断当前的cell是否为Date
//				if (DateUtil.isCellDateFormatted(cell)) {
//					Date date = cell.getDateCellValue();
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//					cellvalue = sdf.format(date);
//				}
//				break;
//			}
//			// 如果当前Cell的Type为STRIN
//			case STRING:
//				// 取得当前的Cell字符串
//				cellvalue = cell.getRichStringCellValue().getString();
//				break;
//			// 默认的Cell值
//			default:
//				cellvalue = " ";
//				break;
//			}
//			return cellvalue;
//		}
//		return null;
//	}

	public static String getStringCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}
		String strCell = "";
		switch (cell.getCellType()) {
		case STRING:
			strCell = cell.getStringCellValue();
			break;
		case NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
				SimpleDateFormat sdf = null;
				if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
					sdf = new SimpleDateFormat("HH:mm");
				} else {// 日期
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				Date date = cell.getDateCellValue();
				strCell = sdf.format(date);
			} else if (cell.getCellStyle().getDataFormat() == 58 || cell.getCellStyle().getDataFormat() == 14
					|| cell.getCellStyle().getDataFormat() == 57 || cell.getCellStyle().getDataFormat() == 31) {
				// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				double value = cell.getNumericCellValue();
				Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
				strCell = sdf.format(date);
			} else {
				double value = cell.getNumericCellValue();
				CellStyle style = cell.getCellStyle();
				DecimalFormat format = new DecimalFormat();
				String temp = style.getDataFormatString();
				// 单元格设置成常规
				if (temp.equals("General")) {
					format.applyPattern("#");
				}
				strCell = format.format(value);
			}
			break;
		case BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case FORMULA:// 新加的公式类型
			strCell = cell.getCellFormula().toString();
			break;
		case BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		if (cell == null) {
			return "";
		}
		return strCell;
	}
}