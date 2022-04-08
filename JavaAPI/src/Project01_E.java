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
	
	//���� �̹��� �����Լ�
	public static void map_service(String point_address, String point_x, String point_y) throws Exception {

		// Naver Static Map API(������ ��ġ�� ǥ��)
		String URL_STATICMAP = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?";

		String client_id = "nigxfzzjii";
		String client_secret = "S2oOpGhT5SWQcxkouSg9Mf1zJ2FPybGZlnASEzdR";
		// �ɼ�

		try {
			// 1. pos : ��Ŀ�� ǥ�õ� ��ġ (UTF-8�� ���ڵ��Ͽ� ���� ���ֱ�)
			String pos = URLEncoder.encode(point_x + " " + point_y, "UTF-8");

			// 2. label : ��Ŀ�� ǥ���� ����
			String label = URLEncoder.encode(point_address, "UTF-8");

			// ���� url
			String url = URL_STATICMAP;

			// 2. center : ������ �߽� ��ǥ�� ����
			url += "center=" + point_x + "," + point_y;

			// 3. level : �� ����, w : �̹����� ���α���, h : �̹����� ���α���
			url += "&level=16&w=700&h=500";

			// 4. markers : ���� ���� ��Ŀ�� ǥ��
			// type : ��Ŀ ����, size : ��Ŀ ũ��, pos : ��Ŀ�� ǥ�õ� ��ġ, label : ��Ŀ�� ǥ���� ����
			url += "&markers=type:t|size:mid|pos:" + pos + "|label:" + label;
			System.out.println(url);
			
			// url ��ü ����
			URL u = new URL(url);

			// openConnection���� url�� ������ �� HttpURLConnection���� ����� ����
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();

			// ��û��� GET
			conn.setRequestMethod("GET");

			// ù��° ���ڰ�
			conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", client_id);

			// �ι�° ���ڰ�
			conn.setRequestProperty("X-NCP-APIGW-API-KEY", client_secret);

			// ������ ���������� Ȯ�� (200 ��������, 400 ��û ����)
			int responseCode = conn.getResponseCode();

			// json������ �����͸� ���پ� �ޱ� ���ؼ� BufferedReader�� ����
			if (responseCode == 200) {

				// ��û����� InputStream ����
				InputStream is = conn.getInputStream();
				int read = 0;

				byte[] bytes = new byte[1024];

				// �����̸� ����
				String tempname = Long.valueOf(new Date().getTime()).toString();

				// ���ϰ�ü ����
				File f = new File(tempname + ".jpg");

				// ���� ����
				f.createNewFile();

				// ������ �����۾��� �ϱ����� outputStream ����
				try (OutputStream outputStream = new FileOutputStream(f)) {
					// byte���̸�ŭ �����͸� ����
					while ((read = is.read(bytes)) != -1) {
						// �����Ͱ� ������ 0���� read��ŭ ������ �����۾��� �ض�
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

		// Ű����� ���� �Է¹ޱ�
		// System.in:Ű����� �Է¹ޱ�, byte ��Ʈ��
		// BufferReader�� �����Ҽ� �ְ� InputStreamReader���� ��ȯ
		BufferedReader io = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.print("�ּҸ� �Է��ϼ���:");

			// readline ���ٴ����� �о��
			String address = io.readLine();

			// ���� ���ֱ�
			String addr = URLEncoder.encode(address, "UTF-8");

			// ��û URL
			String reqURL = apiURL + addr;
			System.out.println(reqURL);

			// url ��ü�� ��û url �ֱ�
			URL url = new URL(reqURL);

			// openConnection���� url�� ������ �� HttpURLConnection���� ����� ����
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// ��û��� GET
			conn.setRequestMethod("GET");

			// ù��° ���ڰ�
			conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", client_id);

			// �ι�° ���ڰ�
			conn.setRequestProperty("X-NCP-APIGW-API-KEY", client_secret);

			// ������ ���������� Ȯ�� (200 ��������, 400 ��û ����)
			int responseCode = conn.getResponseCode();

			// json������ �����͸� ���پ� �ޱ� ���ؼ� BufferedReader�� ����
			BufferedReader br;
			if (responseCode == 200) {

				// getInputStream���� �����ͷ� ���´��� BufferedReader�� �����Ҽ� �ְ� InputStreamReader���� ��ȯ
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

			} else {
				// ������ getErrorStream���� ���� Ȯ��
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			String line;
			// ���ڿ��� ���پ� �о���� ���� StringBuffer �� ����
			StringBuffer response = new StringBuffer();

			while ((line = br.readLine()) != null) {
				// ���� ���پ� �޾� ���ڹ��ۿ� ����
				response.append(line);
			}
			br.close();

			// json �����͸� �б� ���� JSONTokener ��ü ����
			JSONTokener tokener = new JSONTokener(response.toString());

			// json object ����
			JSONObject object = new JSONObject(tokener);
			System.out.println(object.toString(2));

			// JSONArray ������
			JSONArray arr = object.getJSONArray("addresses");

			String z = ""; // �ּ�
			String x = ""; // �浵
			String y = ""; // ����

			// ������ ����
			for (int i = 0; i < arr.length(); i++) {

				// JSONArray���� object ����
				JSONObject tmp = (JSONObject) arr.get(i);
				// Object key������ ������ ��������
				System.out.println("address:" + tmp.get("roadAddress"));
				System.out.println("jibunAddress:" + tmp.get("jibunAddress"));
				System.out.println("�浵:" + tmp.get("x"));
				System.out.println("����:" + tmp.get("y"));

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
