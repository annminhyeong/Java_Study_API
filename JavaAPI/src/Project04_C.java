import java.io.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

public class Project04_C {
	public static void main(String[] args) {
		
		
		//메모리에 가상 pdf 만들기
		Document doc = new Document();
		
		try {
			//실제 pdf파일 만들기
			PdfWriter.getInstance(doc, new FileOutputStream("ImgageDemo.pdf"));
			
			//Document 열기
			doc.open();
			
			String fileName = "에비츄.jpg";
			
			//img 객체 생성
			Image image = Image.getInstance(fileName);
		
			//doc에 추가
			doc.add(image);
			
			String url = "https://www.jungle.co.kr/image/93c382474895891c3246d4de";
			image = Image.getInstance(url);
			doc.add(image);
			
			System.out.println("생성 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			doc.close();
		}
	}
}
