import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class Project04_A {
	public static void main(String[] args) {
		//iText API
		String[] title = new String[] {"제목","저자","출판사","이미지URL"};
		
		String[][] rows = new String[][] {
			{"물리법칙의 이해","리처드파인먼","해나무","https://bookthumb-phinf.pstatic.net/cover/100/365/10036542.jpg"},
			{"Java의 정석","남궁성","도우출판","https://bookthumb-phinf.pstatic.net/cover/100/365/10036542.jpg"},
			{"리눅스 프로그래밍","창병모","생능출판","https://bookthumb-phinf.pstatic.net/cover/100/365/10036542.jpg"}
		};
		
		//메모리에 임시 pdf파일 만들기
		Document doc = new Document(PageSize.A4);
		try {
			//실제 pdf만드는 함수
			PdfWriter.getInstance(doc, new FileOutputStream(new File("book.pdf")));
			
			//pdf열기
			doc.open();
			
			//pdf 한글 폰트
			//IDENTITY_H : 글자를 가로로 쓴다, NOT_EMBEDDED : 기존의 폰트를 사용하지 않고 외부의 폰트를 사용한다
			BaseFont bf = BaseFont.createFont("MALGUN.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			
			//폰트 제목: 12포인트
			Font fontTitle = new Font(bf, 12);
			
			//폰트 내용: 10포인트
			Font fontRows = new Font(bf, 10);
			
			//pdf 테이블 만들기: 몇개의 column(행)으로 만들지 지정
			PdfPTable table = new PdfPTable(title.length);
			
			//테이블 가로길이 지정
			table.setWidthPercentage(100);
			
			//각각의 셀 가로길이 지정
			float[] colwidth = new float[] {20f, 15f, 15f, 30f};
			table.setWidths(colwidth);
			
			//title 만들기
			for(String header : title) {
				//셀만들기
				PdfPCell cell = new PdfPCell();
				
				//셀 정렬(가운데)
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				//셀 패딩 주기(상하좌우 10씩)
				cell.setPadding(10);
				
				//셀 배경 주기(그레이 색깔)
				cell.setGrayFill(0.9f);
				
				//헤더 내용 넣기
				cell.setPhrase(new Phrase(header, fontTitle));
				
				//셀에 해더 내용 부착하기
				table.addCell(cell);
			}
			//row 한개가 생성될시
			table.completeRow();
			
			for(String[] row : rows) {
				for(String data : row) {
					//글자 만들기
					Phrase phrase = new Phrase(data, fontRows);
					
					//셀 만들기
					PdfPCell cell = new PdfPCell(phrase);
					
					//셀 정렬(가운데)
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					
					//셀 패딩 주기
					cell.setPaddingTop(20);
					cell.setPaddingRight(30);
					cell.setPaddingLeft(30);
					cell.setPaddingBottom(20);
					
					//테이블에 셀 추가
					table.addCell(cell);
				}
				//row생성시 table에 추가
				table.completeRow();
			}
			
			//셀 생성
			PdfPCell cell4 = new PdfPCell(new Phrase("Cell 5"));
			//셀 병합
			cell4.setColspan(2);
			
			//셀 생성
			PdfPCell cell5 = new PdfPCell(new Phrase("Cell 6"));
			//셀 병합
			cell5.setColspan(2);
			
			//테이블에 추가
			table.addCell(cell4);
			table.addCell(cell5);
			
			
			
			//pdf 이름
			doc.addTitle("PDF Table Demo");
			//doc에 table 넣기
			doc.add(table);
			
			System.out.println("테이블 생성 완료");
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			// document 닫기
			doc.close();
		}
		
		
		
		
		
	}

}