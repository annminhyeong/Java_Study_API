import java.io.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

public class Project04_C {
	public static void main(String[] args) {
		
		
		//�޸𸮿� ���� pdf �����
		Document doc = new Document();
		
		try {
			//���� pdf���� �����
			PdfWriter.getInstance(doc, new FileOutputStream("ImgageDemo.pdf"));
			
			//Document ����
			doc.open();
			
			String fileName = "������.jpg";
			
			//img ��ü ����
			Image image = Image.getInstance(fileName);
		
			//doc�� �߰�
			doc.add(image);
			
			String url = "https://www.jungle.co.kr/image/93c382474895891c3246d4de";
			image = Image.getInstance(url);
			doc.add(image);
			
			System.out.println("���� �Ϸ�");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			doc.close();
		}
	}
}
