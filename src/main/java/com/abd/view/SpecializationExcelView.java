package com.abd.view;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.abd.entity.Specialization;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SpecializationExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws Exception {
		//1. Define your own excel file name, if we dont mentioned this it will take file name as excel.xlsx
		response.addHeader("Content-Disposition", "attachment;filename=SPEC.xlsx");
		
		//2.Read data given by controller
		@SuppressWarnings("unchecked")
		List<Specialization> list = (List<Specialization>) model.get("list");
		
		//3.Create one sheet
		Sheet sheet = workbook.createSheet("SPECIALIZATION");
		
		//4.Create row#0 as header 
		setHead(sheet);
		
		//5. Create row #1 onwards as List<T>
		setBody(sheet, list);
		
		//6. 
		

	}

	
	private void setHead(Sheet sheet) {
		Row row = sheet.createRow(0);
		
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("CODE");
		row.createCell(2).setCellValue("NAME");
		row.createCell(3).setCellValue("NOTE");
		
	}
	
	private void setBody(Sheet sheet, List<Specialization> list) {
		int rowNum=1;
		for(Specialization spec : list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(spec.getId());
			row.createCell(1).setCellValue(spec.getSpecCode());
			row.createCell(2).setCellValue(spec.getSpecName());
			row.createCell(3).setCellValue(spec.getSpecNote());
		}
		
	}


}
