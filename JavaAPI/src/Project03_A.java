import java.io.FileInputStream;
import java.util.*;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import dto.ExcelDTO;


public class Project03_A {

	public static void main(String[] args) {
		
		//엑셀 파일경로
		String fileName = "bookList.xls";
		
		//엑셀 데이터 담기
		List<ExcelDTO> data = new ArrayList<ExcelDTO>();
		
		//파일객체 생성
		try(FileInputStream fis = new FileInputStream(fileName)) {
			
			//엑셀파일을 읽어서 로딩 (가상의 엑셀파일을 생성)
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			
			//엑셀의 첫번째 시트 선택
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			//시트에서 전체열을 iterator 형태로 가져옴
			Iterator<Row> rows = sheet.rowIterator();
			
			//첫번째는 데이터가 2번째 부터 데이터를 가져옴
			rows.next();
			
			//임시 배열
			String[] imsi = new String[5];
			
			//row에 데이터가 있으면 ture 없으면 false
			while(rows.hasNext()) {
				
				//한줄의 열의 데이터를 가져옴
				HSSFRow row = (HSSFRow) rows.next();
				
				//열의 전체 셀(칼럼) 가져오기
				Iterator<Cell> cells = row.cellIterator();
				
				int i = 0;
				//cell의 값이 존재하면 true 없으면 false
				while(cells.hasNext()) {
					
					//셀값을 하나씩 가져옴
					HSSFCell cell = (HSSFCell) cells.next();
					
					//셀값을 넣는 임시 배열
					imsi[i] = cell.toString();
					i++;
				}
				
				ExcelDTO dto = new ExcelDTO(imsi[0], imsi[1], imsi[2], imsi[3], imsi[4]);
				data.add(dto);
			}
			workbook.close();
			
			showExcelData(data);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showExcelData(List<ExcelDTO> data) {
		
		for(ExcelDTO dto : data) {
			System.out.println(dto);
		}
	}
	
}
