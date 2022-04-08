import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Project02_A {
	
	//Jsoup API
	public static void main(String[] args) {
		
		//�Ľ��� URL
		String url = "https://sports.news.naver.com/wfootball/index";
		
		//html ��ü ���� ����
		Document doc = null;
		
		try {
			//Jsoup���� Get ���
			doc = Jsoup.connect(url).get();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Elements = Element�� ���� �ڷ���
		Elements element= doc.select("div.home_news");
		
		//Element = Document�� html ���
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
