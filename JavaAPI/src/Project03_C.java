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
			//���������� �о �ε� (������ ���������� ����)
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			
			//������ ù��° ��Ʈ ����
			HSSFSheet sheet= workbook.getSheetAt(0);
			
			//��Ʈ���� ��ü���� iterator ���·� ������
			Iterator<Row> rows = sheet.rowIterator();
			
			//row�� �����Ͱ� ������ ture ������ false
			while(rows.hasNext()) {
				
				//������ ���� �����͸� ������
				HSSFRow row = (HSSFRow) rows.next();
				
				//���� ��ü ��(Į��) ��������
				Iterator<Cell> cells= row.cellIterator();
				
				//cell�� ���� �����ϸ� true ������ false
				while(cells.hasNext()) {
					
					//������ �ϳ��� ������
					HSSFCell cell = (HSSFCell) cells.next();
					
					//�� Ÿ�� Ȯ��
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
