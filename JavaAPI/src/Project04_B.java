import java.io.FileOutputStream;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Project04_B {
	//Paragraph : 단락, 문장, 절 만들기
	//chunk : (규모가 큰 부분) 만들기
	public static void main(String[] args) {
		
		//가상의 pdf 파일 만들기
		Document doc = new Document();
		
		try {
			//파일 만들기
			FileOutputStream fos = new FileOutputStream("ParagraphDemo.pdf");
			
			//실제 pdf만드는 함수
			PdfWriter.getInstance(doc, fos);
			
			//pdf 열기
			doc.open();
			
			String content = "your word is a lamp my feet and a light for my path";
			
			//문단 만들기, 줄간격 32
			Paragraph par1 = new Paragraph(32);
			
			//문단 여백 50
			par1.setSpacingBefore(50);
			par1.setSpacingAfter(50);
			
			for(int i=0; i<20; i++) {
				//부분 만들기
				Chunk chunk = new Chunk(content);
				par1.add(chunk);
			}
			
			doc.add(par1);
			doc.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
