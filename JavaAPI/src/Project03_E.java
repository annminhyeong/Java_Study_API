import java.io.BufferedReader;
import java.io.InputStreamReader;

import dao.ExcelDAO;

public class Project03_E {
	public static void main(String[] args) {
		ExcelDAO dao = new ExcelDAO();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.print("�Է�ó��(i)/����(e):");
			String sw = br.readLine();
			
			switch (sw) {
			case "i":
				dao.excel_input();
				break;
			case "e":
				
				break;

			default:
				System.out.println("i�� e�� �Է����ּ���");
				break;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}