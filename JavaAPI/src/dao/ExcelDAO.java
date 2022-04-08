package dao;

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.poi.hssf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.nodes.Element;

import dto.ExcelDTO;
import util.DownloadBroker;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
public class ExcelDAO {
	private List<ExcelDTO> list;
	private HSSFWorkbook wb;
	
	public ExcelDAO() {
		list = new ArrayList<ExcelDTO>();
		wb = new HSSFWorkbook();
	}
	
	public void excel_input() {
		
		//�Է� ��ü ����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			//���� ��Ʈ ����
			HSSFSheet firstsheet = wb.createSheet("BOOK SHEET");
			//��� 1�� row ����(��)
			HSSFRow rowA = firstsheet.createRow(0);
			
			//0��° �� ����
			HSSFCell cellA = rowA.createCell(0);
			//���� �� �ֱ�
			cellA.setCellValue(new HSSFRichTextString("å����"));
			
			//1��° �� ����
			HSSFCell cellB = rowA.createCell(1);
			//���� �� �ֱ�
			cellB.setCellValue(new HSSFRichTextString("����"));
			
			//2��° �� ����
			HSSFCell cellC = rowA.createCell(2);
			//���� �� �ֱ�
			cellC.setCellValue(new HSSFRichTextString("���ǻ�"));
			
			//3��° �� ����
			HSSFCell cellD = rowA.createCell(3);
			//���� �� �ֱ�
			cellD.setCellValue(new HSSFRichTextString("isbn"));
			
			//4��° �� ����
			HSSFCell cellE = rowA.createCell(4);
			//���� �� �ֱ�
			cellE.setCellValue(new HSSFRichTextString("�̹����̸�"));
			
			//5��° �� ����
			HSSFCell cellF = rowA.createCell(5);
			//���� �� �ֱ�
			cellF.setCellValue(new HSSFRichTextString("�̹���"));
			
			//Book ������
			int i=1;
			while(true) {
				System.out.print("å ����:");
				String title = br.readLine();
				
				System.out.print("����:");
				String author = br.readLine();
				
				System.out.print("���ǻ�:");
				String company = br.readLine();
				
				HSSFRow rowRal = firstsheet.createRow(i);
				//0��° �� ����
				HSSFCell cellTitle= rowRal.createCell(0);
				cellTitle.setCellValue(new HSSFRichTextString(title));
				
				//1��° �� ����
				HSSFCell cellAuthor= rowRal.createCell(1);
				cellAuthor.setCellValue(new HSSFRichTextString(author));
				
				//2��° �� ����
				HSSFCell cellCompany= rowRal.createCell(2);
				cellCompany.setCellValue(new HSSFRichTextString(company));
				
				i++;
				
				ExcelDTO dto = new ExcelDTO(title, author, company);
				ExcelDTO data = naver_search(dto);
				
				list.add(data);
				System.out.print("��� �Է� y / �Է� ����: n :");
				String key = br.readLine();
				if(key.equals("n")) break;
			}
			System.out.println("������ ������.....");
			excel_save();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excel_save() {
		try {
			//��Ʈ ��������
			HSSFSheet sheet = wb.getSheetAt(0);
			
			//workbook�� sheet�� null�� �ƴϸ�
			if(wb != null && sheet != null) {
				//��ü row ��������
				Iterator<Row> rows= sheet.rowIterator();
				//ù���� �����̹Ƿ� �ѱ�
				rows.next();
				
				int i = 0;
				//row�� �������� ������
				while(rows.hasNext()) {
					//row�� ���ֱ�
					HSSFRow row = (HSSFRow) rows.next();
					
					//���ǻ� ������ ������ ���� �ϹǷ� 3��° ���� ����
					HSSFCell cell = row.createCell(3);
					//�� Ÿ�� ����
					cell.setCellType(CellType.STRING);
					//���� ���ֱ�
					cell.setCellValue(list.get(i).getIsbn());
					
					//isbn ������ ������ ���� �ϹǷ� 3��° ���� ����
					cell = row.createCell(4);
					//�� Ÿ�� ����
					cell.setCellType(CellType.STRING);
					//���� ���ֱ�
					cell.setCellValue(list.get(i).getImgurl());
					
					//------������ �̹��� �ֱ�----------
					
					//�̹��� ���� �о����
					InputStream inputstream = new FileInputStream(list.get(i).getImgurl());
					
					//�̹��������� ����Ʈ������ ����(poi api���� �Լ� ����)
					byte[] bytes = IOUtils.toByteArray(inputstream);
					
					//������ �������Ͽ� �޸� ����
					int pictureIdx= wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
					inputstream.close();
					
					//��Ŀ �����
					CreationHelper helper = wb.getCreationHelper();
					Drawing drawing = sheet.createDrawingPatriarch();
					ClientAnchor anchor = helper.createClientAnchor();
					
					//��Ŀ ��ġ ���� (�̹����� ���� �� ����)
					//ù��° ��ǥ
					anchor.setCol1(5);
					anchor.setRow1(i+1);

					//�ι�° ��ǥ
					anchor.setCol2(6);
					anchor.setRow2(i+2);
					
					//��Ŀ��ġ�� �̹��� �׸���
					drawing.createPicture(anchor, pictureIdx);
					
					//�������
					Cell cellImg = row.createCell(5);
					//�� ������ ����
					//����
					//��������� 256/1ũ��� ��������� ������ 256�� ������� 20ũ���� ���� �������
					int w = 20 * 256; 
					sheet.setColumnWidth(5, w);
					//����
					//��������� 256/1ũ��� ��������� ������ 20�� ������� 120ũ���� ���� �������
					short h  = 120 * 20;
					cell.getRow().setHeight(h);
					i++;
				}
				//�������Ͽ� ����
				FileOutputStream fileOut = new FileOutputStream("isbn.xls");
				wb.write(fileOut);
				wb.close();
				fileOut.close();
				System.out.println("ibsn ���� �Ϸ�");
			  	
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	public ExcelDTO naver_search(ExcelDTO dto) {
		try {
			String bookAPI = "https://openapi.naver.com/v1/search/book_adv.xml?";
			bookAPI += "d_titl="+URLEncoder.encode(dto.getTitle(), "UTF-8");
			bookAPI += "&d_auth="+URLEncoder.encode(dto.getAuthor(), "UTF-8");
			bookAPI += "&d_publ="+URLEncoder.encode(dto.getCompany(), "UTF-8");
			
			//URL ��ü ����
			URL url = new URL(bookAPI);
			
			//URL ����
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			//URL ȣ�� �ʿ�����
			conn.setRequestMethod("GET");
			conn.setRequestProperty("X-Naver-Client-Id", "0d9qeS9wLCpLJQ0Q0dwH");
			conn.setRequestProperty("X-Naver-Client-Secret", "wEFUV2zJfQ");
			
			//URL ����Ȯ��
			int getResponseCode = conn.getResponseCode();
			
			BufferedReader br1;
			
			//����
			if(getResponseCode==200) {
				br1 = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			}else {
				br1 = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			//xml �����͸� ���پ� �о��
			String inputLine;
			
			//xml ������ ����
			StringBuffer response = new StringBuffer();
			
			while((inputLine = br1.readLine())!= null) {
				response.append(inputLine);
				
			}
			br1.close();
			
			//��ü xml ������ html ���·� ��ȯ
			Document doc = Jsoup.parse(response.toString());
			//System.out.println(doc.toString());
			
			Element total = doc.select("total").first();
			System.out.println(total.text());
			
			if(!(total.text().equals("0"))){
				//isbn ����
				Element isbn = doc.select("isbn").first();
				String isbnStr = isbn.text();
				//���������� �ڸ����� �ι�° �� ��������
				String isbn_find = isbnStr.split(" ")[1];
				dto.setIsbn(isbn_find);
				
				//image ����
				String imgDoc = doc.toString();
				String imgTag = imgDoc.substring(imgDoc.indexOf("<img>")+5);
				String imgURL = imgTag.substring(0, imgTag.indexOf("?"));
				String fileName = imgURL.substring(imgURL.lastIndexOf("/")+1);
				dto.setImgurl(fileName);
				System.out.println(dto);
				
				Runnable dl = new DownloadBroker(imgURL, fileName);
				Thread t = new Thread(dl);
				t.start();
			}else {
				System.out.println("�˻� �����Ͱ� �����ϴ�.");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

}