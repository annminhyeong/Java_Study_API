import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

public class Project04_D {
	public static void main(String[] args) {
		
		
		//�޸��� ������ pdf����
		Document doc = new Document();
		
		try {
			//���� pdf���� ����
			PdfWriter.getInstance(doc, new FileOutputStream("google.pdf"));
			
			//doc ����
			doc.open();
			
			String fileName = "googlelego.png";
			//�̹��� ����
			Image image = Image.getInstance(fileName);
			//doc�� ����ְ�
			doc.add(image);
			
			fileName = "googlelego.png";
			//�̹��� ����
			image = Image.getInstance(fileName);
			//�̹��� ũ��
			image.scaleAbsolute(200f, 200f);
			//doc�� ����ְ�
			doc.add(image);
			
			String url = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";
			//�̹��� ����
			image = Image.getInstance(url);
			//�̹��� ũ��
			image.scalePercent(200f);
			//doc�� ����ְ�
			doc.add(image);
			
			url = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";
			//�̹��� ����
			image = Image.getInstance(url);
			//�̹��� ũ��
			image.scaleToFit(100f, 200f);
			//doc�� ����ְ�
			doc.add(image);
			
			System.out.println("ũ�� ���� ����");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			doc.close();
		}
	}
}