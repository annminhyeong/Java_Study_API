import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Project01_C {
	
	public static void main(String[] args) throws Exception {
		
		//JSON ���� �о����
		//���Ͽ��� �����͸� �о�÷��� IO�� Stream�� �ʿ���
		String src ="json_test/info.json";
		
		//JSON ���� ��ġ
		InputStream is = Project01_C.class.getResourceAsStream(src);
		
		//JSON �ѱ� ���ڵ�
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		if(is==null) {
			throw new NullPointerException("Cannot find resource file");
		}
		
		//JSON ������ ���ڿ��� �ִ� �۾�
		JSONTokener tokener = new JSONTokener(br);
		
		//JSON ��ü ����
		JSONObject object = new JSONObject(tokener);
		
		//students�� Ű���� �̾Ƴ�
		JSONArray students = object.getJSONArray("students");
		
		//������ ���
		for(int i=0; i<students.length(); i++) {
			JSONObject student = (JSONObject) students.get(i);
			System.out.println(student.get("address")+"\t"+student.get("phone")+"\t"+student.get("name"));
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
