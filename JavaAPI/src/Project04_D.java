import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

public class Project04_D {
	public static void main(String[] args) {
		
		
		//메모리의 가상의 pdf생성
		Document doc = new Document();
		
		try {
			//실제 pdf파일 생성
			PdfWriter.getInstance(doc, new FileOutputStream("google.pdf"));
			
			//doc 열기
			doc.open();
			
			String fileName = "googlelego.png";
			//이미지 생성
			Image image = Image.getInstance(fileName);
			//doc에 집어넣게
			doc.add(image);
			
			fileName = "googlelego.png";
			//이미지 생성
			image = Image.getInstance(fileName);
			//이미지 크기
			image.scaleAbsolute(200f, 200f);
			//doc에 집어넣게
			doc.add(image);
			
			String url = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";
			//이미지 생성
			image = Image.getInstance(url);
			//이미지 크기
			image.scalePercent(200f);
			//doc에 집어넣게
			doc.add(image);
			
			url = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";
			//이미지 생성
			image = Image.getInstance(url);
			//이미지 크기
			image.scaleToFit(100f, 200f);
			//doc에 집어넣게
			doc.add(image);
			
			System.out.println("크기 조절 성공");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			doc.close();
		}
	}
}
