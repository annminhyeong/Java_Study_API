import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class Project04_A {
	public static void main(String[] args) {
		//iText API
		String[] title = new String[] {"����","����","���ǻ�","�̹���URL"};
		
		String[][] rows = new String[][] {
			{"������Ģ�� ����","��ó�����θ�","�س���","https://bookthumb-phinf.pstatic.net/cover/100/365/10036542.jpg"},
			{"Java�� ����","���ü�","��������","https://bookthumb-phinf.pstatic.net/cover/100/365/10036542.jpg"},
			{"������ ���α׷���","â����","��������","https://bookthumb-phinf.pstatic.net/cover/100/365/10036542.jpg"}
		};
		
		//�޸𸮿� �ӽ� pdf���� �����
		Document doc = new Document(PageSize.A4);
		try {
			//���� pdf����� �Լ�
			PdfWriter.getInstance(doc, new FileOutputStream(new File("book.pdf")));
			
			//pdf����
			doc.open();
			
			//pdf �ѱ� ��Ʈ
			//IDENTITY_H : ���ڸ� ���η� ����, NOT_EMBEDDED : ������ ��Ʈ�� ������� �ʰ� �ܺ��� ��Ʈ�� ����Ѵ�
			BaseFont bf = BaseFont.createFont("MALGUN.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			
			//��Ʈ ����: 12����Ʈ
			Font fontTitle = new Font(bf, 12);
			
			//��Ʈ ����: 10����Ʈ
			Font fontRows = new Font(bf, 10);
			
			//pdf ���̺� �����: ��� column(��)���� ������ ����
			PdfPTable table = new PdfPTable(title.length);
			
			//���̺� ���α��� ����
			table.setWidthPercentage(100);
			
			//������ �� ���α��� ����
			float[] colwidth = new float[] {20f, 15f, 15f, 30f};
			table.setWidths(colwidth);
			
			//title �����
			for(String header : title) {
				//�������
				PdfPCell cell = new PdfPCell();
				
				//�� ����(���)
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				//�� �е� �ֱ�(�����¿� 10��)
				cell.setPadding(10);
				
				//�� ��� �ֱ�(�׷��� ����)
				cell.setGrayFill(0.9f);
				
				//��� ���� �ֱ�
				cell.setPhrase(new Phrase(header, fontTitle));
				
				//���� �ش� ���� �����ϱ�
				table.addCell(cell);
			}
			//row �Ѱ��� �����ɽ�
			table.completeRow();
			
			for(String[] row : rows) {
				for(String data : row) {
					//���� �����
					Phrase phrase = new Phrase(data, fontRows);
					
					//�� �����
					PdfPCell cell = new PdfPCell(phrase);
					
					//�� ����(���)
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					
					//�� �е� �ֱ�
					cell.setPaddingTop(20);
					cell.setPaddingRight(30);
					cell.setPaddingLeft(30);
					cell.setPaddingBottom(20);
					
					//���̺��� �� �߰�
					table.addCell(cell);
				}
				//row������ table�� �߰�
				table.completeRow();
			}
			
			//�� ����
			PdfPCell cell4 = new PdfPCell(new Phrase("Cell 5"));
			//�� ����
			cell4.setColspan(2);
			
			//�� ����
			PdfPCell cell5 = new PdfPCell(new Phrase("Cell 6"));
			//�� ����
			cell5.setColspan(2);
			
			//���̺��� �߰�
			table.addCell(cell4);
			table.addCell(cell5);
			
			
			
			//pdf �̸�
			doc.addTitle("PDF Table Demo");
			//doc�� table �ֱ�
			doc.add(table);
			
			System.out.println("���̺� ���� �Ϸ�");
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			// document �ݱ�
			doc.close();
		}
		
		
		
		
		
	}

}