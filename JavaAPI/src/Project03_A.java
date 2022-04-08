import java.io.FileInputStream;
import java.util.*;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import dto.ExcelDTO;


public class Project03_A {

	public static void main(String[] args) {
		
		//���� ���ϰ��
		String fileName = "bookList.xls";
		
		//���� ������ ���
		List<ExcelDTO> data = new ArrayList<ExcelDTO>();
		
		//���ϰ�ü ����
		try(FileInputStream fis = new FileInputStream(fileName)) {
			
			//���������� �о �ε� (������ ���������� ����)
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			
			//������ ù��° ��Ʈ ����
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			//��Ʈ���� ��ü���� iterator ���·� ������
			Iterator<Row> rows = sheet.rowIterator();
			
			//ù��°�� �����Ͱ� 2��° ���� �����͸� ������
			rows.next();
			
			//�ӽ� �迭
			String[] imsi = new String[5];
			
			//row�� �����Ͱ� ������ ture ������ false
			while(rows.hasNext()) {
				
				//������ ���� �����͸� ������
				HSSFRow row = (HSSFRow) rows.next();
				
				//���� ��ü ��(Į��) ��������
				Iterator<Cell> cells = row.cellIterator();
				
				int i = 0;
				//cell�� ���� �����ϸ� true ������ false
				while(cells.hasNext()) {
					
					//������ �ϳ��� ������
					HSSFCell cell = (HSSFCell) cells.next();
					
					//������ �ִ� �ӽ� �迭
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
