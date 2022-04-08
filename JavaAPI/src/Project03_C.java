import java.io.*;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class Project03_C {
	
	public static void main(String[] args) {
		String fileName = "cellDataType.xls";
		try(FileInputStream fis = new FileInputStream(fileName)) {
			//엑셀파일을 읽어서 로딩 (가상의 엑셀파일을 생성)
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			
			//엑셀의 첫번째 시트 선택
			HSSFSheet sheet= workbook.getSheetAt(0);
			
			//시트에서 전체열을 iterator 형태로 가져옴
			Iterator<Row> rows = sheet.rowIterator();
			
			//row에 데이터가 있으면 ture 없으면 false
			while(rows.hasNext()) {
				
				//한줄의 열의 데이터를 가져옴
				HSSFRow row = (HSSFRow) rows.next();
				
				//열의 전체 셀(칼럼) 가져오기
				Iterator<Cell> cells= row.cellIterator();
				
				//cell의 값이 존재하면 true 없으면 false
				while(cells.hasNext()) {
					
					//셀값을 하나씩 가져옴
					HSSFCell cell = (HSSFCell) cells.next();
					
					//셀 타입 확인
					CellType type = cell.getCellType();
					
					if(type == CellType.STRING) {
						System.out.print("["+cell.getRowIndex()+","+cell.getColumnIndex()+"]");
						System.out.println("=STRING; VALUE ="+cell.getRichStringCellValue().toString());
						
					}else if(type == CellType.NUMERIC) {
						System.out.print("["+cell.getRowIndex()+","+cell.getColumnIndex()+"]");
						System.out.println("=NUMERIC; VALUE ="+cell.getNumericCellValue());
					}else if(type == CellType.BOOLEAN) {
						System.out.print("["+cell.getRowIndex()+","+cell.getColumnIndex()+"]");
						System.out.println("=BOOLEAN; VALUE ="+cell.getBooleanCellValue());
					}else if(type == CellType.BLANK) {
						System.out.print("["+cell.getRowIndex()+","+cell.getColumnIndex()+"]");
						System.out.println("=BLANK; VALUE = BLANK CELL");
					}
				}
			}
			workbook.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
