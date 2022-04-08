import java.io.BufferedReader;
import java.io.InputStreamReader;

import dao.ExcelDAO;

public class Project03_E {
	public static void main(String[] args) {
		ExcelDAO dao = new ExcelDAO();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.print("입력처리(i)/종료(e):");
			String sw = br.readLine();
			
			switch (sw) {
			case "i":
				dao.excel_input();
				break;
			case "e":
				
				break;

			default:
				System.out.println("i나 e를 입력해주세요");
				break;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
