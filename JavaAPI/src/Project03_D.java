import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import dto.ExcelDTO;


public class Project03_D {
	
	
	public static void main(String[] args) {
		
		//Ű���� �Է¹ޱ� ���� BufferedReader ����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("å����: ");
			String title = br.readLine();
			
			System.out.print("����: ");
			String author = br.readLine();
			
			System.out.print("���ǻ�: ");
			String company = br.readLine();
			
			ExcelDTO dto = new ExcelDTO(title, author, company);
			
			getIsbnImage(dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void getIsbnImage(ExcelDTO dto) {
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
			}else {
				System.out.println("�˻� �����Ͱ� �����ϴ�.");
			}
			
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
}