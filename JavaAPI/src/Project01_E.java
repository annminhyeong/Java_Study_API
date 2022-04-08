import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Project01_E {
	
	//지도 이미지 생성함수
	public static void map_service(String point_address, String point_x, String point_y) throws Exception {

		// Naver Static Map API(지도에 위치를 표시)
		String URL_STATICMAP = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?";

		String client_id = "nigxfzzjii";
		String client_secret = "S2oOpGhT5SWQcxkouSg9Mf1zJ2FPybGZlnASEzdR";
		// 옵션

		try {
			// 1. pos : 마커가 표시될 위치 (UTF-8로 인코딩하여 공백 없애기)
			String pos = URLEncoder.encode(point_x + " " + point_y, "UTF-8");

			// 2. label : 매커에 표시할 내용
			String label = URLEncoder.encode(point_address, "UTF-8");

			// 최종 url
			String url = URL_STATICMAP;

			// 2. center : 지도의 중심 좌표를 설정
			url += "center=" + point_x + "," + point_y;

			// 3. level : 줌 레벨, w : 이미지의 가로길이, h : 이미지의 세로길이
			url += "&level=16&w=700&h=500";

			// 4. markers : 지도 위에 마커를 표시
			// type : 마커 유형, size : 마커 크기, pos : 마커가 표시될 위치, label : 매커에 표시할 내용
			url += "&markers=type:t|size:mid|pos:" + pos + "|label:" + label;
			System.out.println(url);
			
			// url 객체 생성
			URL u = new URL(url);

			// openConnection으로 url과 연결한 뒤 HttpURLConnection으로 결과값 받음
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();

			// 요청방식 GET
			conn.setRequestMethod("GET");

			// 첫번째 인자값
			conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", client_id);

			// 두번째 인자값
			conn.setRequestProperty("X-NCP-APIGW-API-KEY", client_secret);

			// 응답이 정상적인지 확인 (200 정상응답, 400 요청 오류)
			int responseCode = conn.getResponseCode();

			// json형태의 데이터를 한줄씩 받기 위해서 BufferedReader로 받음
			if (responseCode == 200) {

				// 요청결과를 InputStream 저장
				InputStream is = conn.getInputStream();
				int read = 0;

				byte[] bytes = new byte[1024];

				// 랜덤이름 생성
				String tempname = Long.valueOf(new Date().getTime()).toString();

				// 파일객체 생성
				File f = new File(tempname + ".jpg");

				// 파일 생성
				f.createNewFile();

				// 파일을 쓰기작업을 하기위해 outputStream 생성
				try (OutputStream outputStream = new FileOutputStream(f)) {
					// byte길이만큼 데이터를 읽음
					while ((read = is.read(bytes)) != -1) {
						// 데이터가 있으면 0부터 read만큼 파일을 쓰기작업을 해라
						outputStream.write(bytes, 0, read);
					}
				}
			}
		}

		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		// Naver Geocoding API key
		String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";

		String client_id = "nigxfzzjii";
		String client_secret = "S2oOpGhT5SWQcxkouSg9Mf1zJ2FPybGZlnASEzdR";

		// 키보드로 부터 입력받기
		// System.in:키보드로 입력받기, byte 스트림
		// BufferReader가 이해할수 있게 InputStreamReader형태 변환
		BufferedReader io = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.print("주소를 입력하세요:");

			// readline 한줄단위로 읽어옴
			String address = io.readLine();

			// 공백 없애기
			String addr = URLEncoder.encode(address, "UTF-8");

			// 요청 URL
			String reqURL = apiURL + addr;
			System.out.println(reqURL);

			// url 객체에 요청 url 넣기
			URL url = new URL(reqURL);

			// openConnection으로 url과 연결한 뒤 HttpURLConnection으로 결과값 받음
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// 요청방식 GET
			conn.setRequestMethod("GET");

			// 첫번째 인자값
			conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", client_id);

			// 두번째 인자값
			conn.setRequestProperty("X-NCP-APIGW-API-KEY", client_secret);

			// 응답이 정상적인지 확인 (200 정상응답, 400 요청 오류)
			int responseCode = conn.getResponseCode();

			// json형태의 데이터를 한줄씩 받기 위해서 BufferedReader로 받음
			BufferedReader br;
			if (responseCode == 200) {

				// getInputStream으로 데이터로 얻어온다음 BufferedReader가 이해할수 있게 InputStreamReader형태 변환
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

			} else {
				// 에러시 getErrorStream으로 오류 확인
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			String line;
			// 문자열을 한줄씩 읽어오기 위해 StringBuffer 로 받음
			StringBuffer response = new StringBuffer();

			while ((line = br.readLine()) != null) {
				// 값을 한줄씩 받아 문자버퍼에 담음
				response.append(line);
			}
			br.close();

			// json 데이터를 읽기 위해 JSONTokener 객체 생성
			JSONTokener tokener = new JSONTokener(response.toString());

			// json object 생성
			JSONObject object = new JSONObject(tokener);
			System.out.println(object.toString(2));

			// JSONArray 빼오기
			JSONArray arr = object.getJSONArray("addresses");

			String z = ""; // 주소
			String x = ""; // 경도
			String y = ""; // 위도

			// 데이터 추출
			for (int i = 0; i < arr.length(); i++) {

				// JSONArray안의 object 추출
				JSONObject tmp = (JSONObject) arr.get(i);
				// Object key값으로 데이터 가져오기
				System.out.println("address:" + tmp.get("roadAddress"));
				System.out.println("jibunAddress:" + tmp.get("jibunAddress"));
				System.out.println("경도:" + tmp.get("x"));
				System.out.println("위도:" + tmp.get("y"));

				z = (String) tmp.get("roadAddress");
				x = (String) tmp.get("x");
				y = (String) tmp.get("y");
			}

			map_service(z, x, y);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
