package com.narvee.usit.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

//import javax.validation.constraints.NotBlank;
//
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xddf.usermodel.chart.DisplayBlanks;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.narvee.usit.entity.Vms;


public class HelperVmsUpload {
	// check that file is of excel type or not
	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();
		System.out.println("Content Type from Excel :" + contentType);
		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}
	}
	// convert excel to list of products
	public static List<Vms> convertExcelToListOfProduct(InputStream is) throws IOException {
		List<Vms> list = new ArrayList<>();
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		System.out.println(workbook);
		XSSFSheet sheet = workbook.getSheet("data");
		System.out.println(sheet);
		int rowNumber = 0;
		Iterator<Row> iterator = sheet.iterator();
		iterator.next();
		while(iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			Vms vms = new Vms();
			while(cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();
				switch (columnIndex) {
				case 0:
					vms.setCompany_name(nextCell.getStringCellValue());
					break;
				case 1:
					 vms.setRecruiter_name(nextCell.getStringCellValue());
					 break;
				case 2:
					 nextCell.setCellType(CellType.STRING);
					 vms.setCp_mobile(nextCell.getStringCellValue());
					break;
				case 3:
				
					vms.setCpemail(nextCell.getStringCellValue());
					break;
				case 4:
					vms.setHeadQuarters(nextCell.getStringCellValue());
					break;
				case 5:
					vms.setVendortype(nextCell.getStringCellValue());
					break;
				
				}
			}
			if(!list.contains(vms))
		        {
		         list.add(vms);
		        }
		}
		
		workbook.close();
	
		return list;

}
	
}
