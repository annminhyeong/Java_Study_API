import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dto.BookDTO;


//GSON JSON
public class Project01_A {
	
	
	public static void main(String[] args) {
		
		//Object(BookDTO) -> JSON(String)
		BookDTO dto = new BookDTO("�ڹ�", 21000, "������", 670);
		
		//gson api ��ü ����
		Gson g = new Gson();

		//json���� ��ȯ
		String json = g.toJson(dto);
		
		System.out.println(json);
		
		//JSON(String) -> DTO
		BookDTO dto1 =g.fromJson(json, BookDTO.class);
		System.out.println(dto1);
		
		
		//Object(List<BookDTO>) -> JSON(String) :[{   }, {   }, {   }]
		List<BookDTO> list = new ArrayList<BookDTO>();
		
		//list ����
		list.add(new BookDTO("�ڹ�1", 21000, "������1", 670));
		list.add(new BookDTO("�ڹ�2", 31000, "������2", 770));
		list.add(new BookDTO("�ڹ�3", 41000, "������3", 870));
		
		//List<BookDTO>�� JSON���� ��ȯ
		String listJson =g.toJson(list);
		System.out.println(listJson);
		
		//JSON�� List<BookDTO>���� ��ȯ
		List<BookDTO> list1 = g.fromJson(listJson, new TypeToken<List<BookDTO>>(){}.getType());
		for(BookDTO dto3 : list1) {
			System.out.println(dto3);
		}
		
		
		
		
				
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
