import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;

public class Project03_B {
	
	public static void main(String[] args) {
		try {
			//엑셀파일을 읽어서 로딩 (가상의 엑셀파일을 생성)
			Workbook wb = new HSSFWorkbook();
			
			//시트 생성
			Sheet sheet = wb.createSheet("My Sample Excel");
			
			//이미지 파일 읽어오기
			InputStream is = new FileInputStream("pic.jpg");
			
			//이미지파일을 바이트단위로 저장(poi api에서 함수 제공)
			byte[] bytes = IOUtils.toByteArray(is);
			
			//가상의 엑셀파일에 메모리 생성
			int pictureIdx= wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
			is.close();
			
			//앵커 만들기
			CreationHelper helper = wb.getCreationHelper();
			Drawing drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = helper.createClientAnchor();
			
			//앵커 위치 지정 (이미지를 넣을 셀 지정)
			//첫번째 좌표
			anchor.setCol1(1);
			anchor.setRow1(2);

			//두번째 좌표
			anchor.setCol2(2);
			anchor.setRow2(3);
			
			//앵커위치에 이미지 그리기
			drawing.createPicture(anchor, pictureIdx);
			
			//셀만들기
			Cell cell = sheet.createRow(2).createCell(1);
			//셀 사이즈 지정
			//가로
			//셀사이즈는 256/1크기로 만들어지기 때문에 256을 곱해줘야 20크기의 셀이 만들어짐
			int w = 20 * 256; 
			sheet.setColumnWidth(1, w);
			//세로
			//셀사이즈는 256/1크기로 만들어지기 때문에 20을 곱해줘야 120크기의 셀이 만들어짐
			short h  = 120 * 20;
			cell.getRow().setHeight(h);
			
			//엑셀파일에 저장
			FileOutputStream fileOut = new FileOutputStream("myFile.xls");
			wb.write(fileOut);
			wb.close();
			fileOut.close();
			System.out.println("이미지 생성 완료");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
