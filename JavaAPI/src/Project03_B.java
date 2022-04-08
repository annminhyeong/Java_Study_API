import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;

public class Project03_B {
	
	public static void main(String[] args) {
		try {
			//���������� �о �ε� (������ ���������� ����)
			Workbook wb = new HSSFWorkbook();
			
			//��Ʈ ����
			Sheet sheet = wb.createSheet("My Sample Excel");
			
			//�̹��� ���� �о����
			InputStream is = new FileInputStream("pic.jpg");
			
			//�̹��������� ����Ʈ������ ����(poi api���� �Լ� ����)
			byte[] bytes = IOUtils.toByteArray(is);
			
			//������ �������Ͽ� �޸� ����
			int pictureIdx= wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
			is.close();
			
			//��Ŀ �����
			CreationHelper helper = wb.getCreationHelper();
			Drawing drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = helper.createClientAnchor();
			
			//��Ŀ ��ġ ���� (�̹����� ���� �� ����)
			//ù��° ��ǥ
			anchor.setCol1(1);
			anchor.setRow1(2);

			//�ι�° ��ǥ
			anchor.setCol2(2);
			anchor.setRow2(3);
			
			//��Ŀ��ġ�� �̹��� �׸���
			drawing.createPicture(anchor, pictureIdx);
			
			//�������
			Cell cell = sheet.createRow(2).createCell(1);
			//�� ������ ����
			//����
			//��������� 256/1ũ��� ��������� ������ 256�� ������� 20ũ���� ���� �������
			int w = 20 * 256; 
			sheet.setColumnWidth(1, w);
			//����
			//��������� 256/1ũ��� ��������� ������ 20�� ������� 120ũ���� ���� �������
			short h  = 120 * 20;
			cell.getRow().setHeight(h);
			
			//�������Ͽ� ����
			FileOutputStream fileOut = new FileOutputStream("myFile.xls");
			wb.write(fileOut);
			wb.close();
			fileOut.close();
			System.out.println("�̹��� ���� �Ϸ�");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
