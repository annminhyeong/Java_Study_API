import org.json.JSONArray;
import org.json.JSONObject;


//JSON-JAVA
public class Project01_B {
	
	public static void main(String[] args) {
		
		//JSON Array ����
		JSONArray students = new JSONArray();
		
		//JSON Object ����
		JSONObject student = new JSONObject();
		
		student = new JSONObject();
		student.put("name", "�̸�1");
		student.put("phone", "010-1111-1111");
		student.put("address", "�ּ�1");
		
		students.put(student);
		
		
		student = new JSONObject();
		student.put("name", "�̸�2");
		student.put("phone", "010-2222-2222");
		student.put("address", "�ּ�2");
		
		students.put(student);
		
		JSONObject object = new JSONObject();
		object.put("students", students);
		
		System.out.println(object.toString(5));
		
		
		
	}
}