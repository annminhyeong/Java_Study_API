import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Project02_A {
	
	//Jsoup API
	public static void main(String[] args) {
		
		//파싱할 URL
		String url = "https://sports.news.naver.com/wfootball/index";
		
		//html 전체 문서 저장
		Document doc = null;
		
		try {
			//Jsoup연결 Get 방식
			doc = Jsoup.connect(url).get();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Elements = Element가 모인 자료형
		Elements element= doc.select("div.home_news");
		
		//Element = Document의 html 요소
		String title = element.select("h2").text();
		System.out.println("======================================");
		System.out.println(title);
		System.out.println("======================================");
		
		for(Element el: element.select("li")) {
			System.out.println(el.text());
		}
		System.out.println("======================================");
		
	}
}
