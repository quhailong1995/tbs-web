package cn.jeeweb.modules.tbs.helper;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 导出Excel公共方法
 * 
 * @version 1.0
 * 
 * @author wangcp
 *
 */
public class ExportExcel {
	
	/**
	 * 
	 * @param info,格式如下：
	 * {
		"isAll":是否导出全部，boolean类型,
		"fileName":文件名(可以为空),
		"tableName":表格标题(可以为空),
		"sheetName":sheet页标题(可以为空),
		"columnKeys":需要导出的列的实体类变量名，如"cardNo,fullName,channelName,loanBegin,term",
		"columnValues":对应的中文名，如"证件号码,姓名,贷款银行,开始日期,贷款期限",
		};
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static HSSFWorkbook getWorkBook(JSONObject info,JSONArray data) throws Exception {
		// 1.创建一个workbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 2.在workbook中添加一个sheet，对应Excel中的一个sheet
		HSSFSheet sheet = wb.createSheet();
		//如果提供了sheet名字，使用它
		if (info.containsKey("sheetName")) {
			wb.setSheetName(0, info.getString("sheetName"));
		}		
		
		//获得列名的key，打散成数组
		String[] columnKeys = info.getString("columnKeys").split(",");
		String[] columnValues = info.getString("columnValues").split(",");
		
		//如果提供了表格标题，需要设置表格标题
		if (info.containsKey("tableName")) {
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue(info.getString("tableName"));
			cell.setCellStyle(getTableTitleStyle(wb));
			//合并表格标题单元格
			CellRangeAddress region = new CellRangeAddress(0, 0, 0, columnKeys.length - 1);
			sheet.addMergedRegion(region);
		}
		
		//写入列标题
		HSSFRow columnTitleRow = sheet.createRow(sheet.getPhysicalNumberOfRows());
		for(int j = 0;j < columnValues.length;j++){
			HSSFCell dataCell = columnTitleRow.createCell(j);//创建单元格
			dataCell.setCellValue(columnValues[j]);
			dataCell.setCellStyle(getColumnTitleStyle(wb));
		}
		
		
		// 循环将数据写入Excel
		for(int i = 0;i < data.size();i++) {
			JSONObject item = data.getJSONObject(i);
			//创建新的行
			HSSFRow dataRow = sheet.createRow(sheet.getPhysicalNumberOfRows());
			// 创建单元格，设置值
			for(int j = 0;j < columnKeys.length;j++){
				HSSFCell dataCell = dataRow.createCell(j);//创建单元格
				String value = item.getString(columnKeys[j]);//获取这个单元格需要填充的内容
				if(value == null){
					value = "";
				}
				dataCell.setCellValue(value);
				
				//手动设置列宽自适应
				int oldWidth = sheet.getColumnWidth(j);
				int newWidth = value.getBytes().length * 300;
				if(newWidth > oldWidth){
					sheet.setColumnWidth(j,newWidth);
				}
			}
		}
		
//		FileOutputStream out = new FileOutputStream("E:/XXX.xls");
//		wb.write(out);
//		out.close();
		
		return wb;
		

	}
	
	
	
	/**
	 * 表格标题样式
	 * @param wb
	 * @return
	 */
	private static HSSFCellStyle getTableTitleStyle(HSSFWorkbook wb){
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		
		//设置字体
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
		font.setFontHeightInPoints((short)12);
		style.setFont(font);
		
		return style;
	}
	
	
	/**
	 * 表格列标题样式
	 * @param wb
	 * @return
	 */
	private static HSSFCellStyle getColumnTitleStyle(HSSFWorkbook wb){
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
		style.setFont(font);
		
		return style;
	}
	
	
	
	
}
