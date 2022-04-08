import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Project01_D {
	
	public static void main(String[] args) {
		
		//Naver Geocoding API key
		String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";
		
		String client_id = "nigxfzzjii";
		String client_secret = "S2oOpGhT5SWQcxkouSg9Mf1zJ2FPybGZlnASEzdR";
		
		//키보드로 부터 입력받기 
		//System.in:키보드로 입력받기, byte 스트림
		//BufferReader가 이해할수 있게 InputStreamReader형태 변환
		BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.print("주소를 입력하세요:");
			
			//readline 한줄단위로 읽어옴
			String address = io.readLine();
			
			//공백 없애기
			String addr = URLEncoder.encode(address, "UTF-8");
			
			//요청 URL
			String reqURL = apiURL+addr;
			
			//url 객체에 요청 url 넣기
			URL url = new URL(reqURL);
			
			//openConnection으로 url과 연결한 뒤 HttpURLConnection으로 결과값 받음
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			//요청방식 GET
			conn.setRequestMethod("GET");
			
			//첫번째 인자값
			conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", client_id);
			
			//두번째 인자값
			conn.setRequestProperty("X-NCP-APIGW-API-KEY", client_secret);
			
			//응답이 정상적인지 확인 (200 정상응답, 400 요청 오류)
			int responseCode = conn.getResponseCode();
			
			//json형태의 데이터를 한줄씩 받기 위해서 BufferedReader로 받음
			BufferedReader br;
			if(responseCode == 200) {
				
				//getInputStream으로 데이터로 얻어온다음 BufferedReader가 이해할수 있게 InputStreamReader형태 변환
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
				
			}else {
				//에러시 getErrorStream으로 오류 확인
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			
			String line;
			//문자열을 한줄씩 읽어오기 위해 StringBuffer 로 받음
			StringBuffer response = new StringBuffer();
			
			while((line = br.readLine()) != null) {			
				//값을 한줄씩 받아 문자버퍼에 담음
				response.append(line);
			}
			br.close();
			
			//json 데이터를 읽기 위해 JSONTokener 객체 생성
			JSONTokener tokener = new JSONTokener(response.toString());
			
			//json object 생성
			JSONObject object = new JSONObject(tokener);
			System.out.println(object.toString(2));
			
			//JSONArray 빼오기
			JSONArray arr=object.getJSONArray("addresses");
			
			//데이터 추출
			for(int i=0; i<arr.length(); i++) {
				
				//JSONArray안의 object 추출
				JSONObject tmp = (JSONObject) arr.get(i);
				//Object key값으로 데이터 가져오기
				System.out.println("address:"+ tmp.get("roadAddress"));
				System.out.println("jibunAddress:"+ tmp.get("jibunAddress"));
				System.out.println("경도:"+ tmp.get("x"));
				System.out.println("위도:"+ tmp.get("y"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
