import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dto.BookDTO;


//GSON JSON
public class Project01_A {
	
	
	public static void main(String[] args) {
		
		//Object(BookDTO) -> JSON(String)
		BookDTO dto = new BookDTO("자바", 21000, "에이콘", 670);
		
		//gson api 객체 생성
		Gson g = new Gson();

		//json으로 변환
		String json = g.toJson(dto);
		
		System.out.println(json);
		
		//JSON(String) -> DTO
		BookDTO dto1 =g.fromJson(json, BookDTO.class);
		System.out.println(dto1);
		
		
		//Object(List<BookDTO>) -> JSON(String) :[{   }, {   }, {   }]
		List<BookDTO> list = new ArrayList<BookDTO>();
		
		//list 저장
		list.add(new BookDTO("자바1", 21000, "에이콘1", 670));
		list.add(new BookDTO("자바2", 31000, "에이콘2", 770));
		list.add(new BookDTO("자바3", 41000, "에이콘3", 870));
		
		//List<BookDTO>를 JSON으로 변환
		String listJson =g.toJson(list);
		System.out.println(listJson);
		
		//JSON를 List<BookDTO>으로 변환
		List<BookDTO> list1 = g.fromJson(listJson, new TypeToken<List<BookDTO>>(){}.getType());
		for(BookDTO dto3 : list1) {
			System.out.println(dto3);
		}
		
		
		
		
				
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
