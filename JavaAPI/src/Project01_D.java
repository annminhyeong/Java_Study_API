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
		
		//Ű����� ���� �Է¹ޱ� 
		//System.in:Ű����� �Է¹ޱ�, byte ��Ʈ��
		//BufferReader�� �����Ҽ� �ְ� InputStreamReader���� ��ȯ
		BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.print("�ּҸ� �Է��ϼ���:");
			
			//readline ���ٴ����� �о��
			String address = io.readLine();
			
			//���� ���ֱ�
			String addr = URLEncoder.encode(address, "UTF-8");
			
			//��û URL
			String reqURL = apiURL+addr;
			
			//url ��ü�� ��û url �ֱ�
			URL url = new URL(reqURL);
			
			//openConnection���� url�� ������ �� HttpURLConnection���� ����� ����
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			//��û��� GET
			conn.setRequestMethod("GET");
			
			//ù��° ���ڰ�
			conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", client_id);
			
			//�ι�° ���ڰ�
			conn.setRequestProperty("X-NCP-APIGW-API-KEY", client_secret);
			
			//������ ���������� Ȯ�� (200 ��������, 400 ��û ����)
			int responseCode = conn.getResponseCode();
			
			//json������ �����͸� ���پ� �ޱ� ���ؼ� BufferedReader�� ����
			BufferedReader br;
			if(responseCode == 200) {
				
				//getInputStream���� �����ͷ� ���´��� BufferedReader�� �����Ҽ� �ְ� InputStreamReader���� ��ȯ
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
				
			}else {
				//������ getErrorStream���� ���� Ȯ��
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			
			String line;
			//���ڿ��� ���پ� �о���� ���� StringBuffer �� ����
			StringBuffer response = new StringBuffer();
			
			while((line = br.readLine()) != null) {			
				//���� ���پ� �޾� ���ڹ��ۿ� ����
				response.append(line);
			}
			br.close();
			
			//json �����͸� �б� ���� JSONTokener ��ü ����
			JSONTokener tokener = new JSONTokener(response.toString());
			
			//json object ����
			JSONObject object = new JSONObject(tokener);
			System.out.println(object.toString(2));
			
			//JSONArray ������
			JSONArray arr=object.getJSONArray("addresses");
			
			//������ ����
			for(int i=0; i<arr.length(); i++) {
				
				//JSONArray���� object ����
				JSONObject tmp = (JSONObject) arr.get(i);
				//Object key������ ������ ��������
				System.out.println("address:"+ tmp.get("roadAddress"));
				System.out.println("jibunAddress:"+ tmp.get("jibunAddress"));
				System.out.println("�浵:"+ tmp.get("x"));
				System.out.println("����:"+ tmp.get("y"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
