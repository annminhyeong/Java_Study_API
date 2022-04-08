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
		
		//키보드 입력받기 위해 BufferedReader 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("책제목: ");
			String title = br.readLine();
			
			System.out.print("저자: ");
			String author = br.readLine();
			
			System.out.print("출판사: ");
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
			
			//URL 객체 생성
			URL url = new URL(bookAPI);
			
			//URL 연결
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			//URL 호출 필요정보
			conn.setRequestMethod("GET");
			conn.setRequestProperty("X-Naver-Client-Id", "0d9qeS9wLCpLJQ0Q0dwH");
			conn.setRequestProperty("X-Naver-Client-Secret", "wEFUV2zJfQ");
			
			//URL 연결확인
			int getResponseCode = conn.getResponseCode();
			
			BufferedReader br1;
			
			//성공
			if(getResponseCode==200) {
				br1 = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			}else {
				br1 = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			//xml 데이터를 한줄씩 읽어옴
			String inputLine;
			
			//xml 데이터 저장
			StringBuffer response = new StringBuffer();
			
			while((inputLine = br1.readLine())!= null) {
				response.append(inputLine);
				
			}
			br1.close();
			
			//전체 xml 파일을 html 형태로 변환
			Document doc = Jsoup.parse(response.toString());
			//System.out.println(doc.toString());
			
			Element total = doc.select("total").first();
			System.out.println(total.text());
			
			if(!(total.text().equals("0"))){
				//isbn 추출
				Element isbn = doc.select("isbn").first();
				String isbnStr = isbn.text();
				//공백을으로 자른다음 두번째 값 가져오기
				String isbn_find = isbnStr.split(" ")[1];
				dto.setIsbn(isbn_find);
				
				//image 추출
				String imgDoc = doc.toString();
				String imgTag = imgDoc.substring(imgDoc.indexOf("<img>")+5);
				String imgURL = imgTag.substring(0, imgTag.indexOf("?"));
				String fileName = imgURL.substring(imgURL.lastIndexOf("/")+1);
				dto.setImgurl(fileName);
				System.out.println(dto);
			}else {
				System.out.println("검색 데이터가 없습니다.");
			}
			
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
}