import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Project01_C {
	
	public static void main(String[] args) throws Exception {
		
		//JSON 파일 읽어오기
		//파일에서 데이터를 읽어올려면 IO의 Stream이 필요함
		String src ="json_test/info.json";
		
		//JSON 파일 위치
		InputStream is = Project01_C.class.getResourceAsStream(src);
		
		//JSON 한글 인코딩
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		if(is==null) {
			throw new NullPointerException("Cannot find resource file");
		}
		
		//JSON 파일의 문자열을 넣는 작업
		JSONTokener tokener = new JSONTokener(br);
		
		//JSON 객체 생성
		JSONObject object = new JSONObject(tokener);
		
		//students의 키값을 뽑아냄
		JSONArray students = object.getJSONArray("students");
		
		//데이터 얻기
		for(int i=0; i<students.length(); i++) {
			JSONObject student = (JSONObject) students.get(i);
			System.out.println(student.get("address")+"\t"+student.get("phone")+"\t"+student.get("name"));
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
