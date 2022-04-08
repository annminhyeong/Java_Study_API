import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import util.DownloadBroker;

public class Project02_B {
	
	public static void main(String[] args) {
		
		//파싱할 URL
		String url = "https://sum.su.or.kr:8888/bible/today/Ajax/Bible/BodyMatter?qt_ty=QT1";
		
		//키보드 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("[입력-> 년(yyyy),월(mm),일(dd)]:");
			String bible = br.readLine();
			url += "&Base_de="+bible+"&bibleType=1";
			System.out.println("===========================================================");
			
			//post 방식으로 연결
			 Document doc= Jsoup.connect(url).post();
			 
			 Element bible_text= doc.select(".bible_text").first();
			 System.out.println(bible_text.text());
			 
			 Element bibleinfo_box= doc.select(".bibleinfo_box").first();
			 System.out.println(bibleinfo_box.text());
			 
			 
			 Elements lilist= doc.select(".body_list > li");
			 for(Element li : lilist ) {
				 System.out.print(li.select(".num").first().text()+":");
				 System.out.println(li.select(".info").first().text());
			 }
			 System.out.println("===========================================================");
			 
			//리소스 다운로드(mp3)
			Element tag = doc.select("source").first();
			
			String dPath = tag.attr("src").trim();
			System.out.println(dPath);
			
			String fileName = dPath.substring(dPath.lastIndexOf("/")+1);
			System.out.println(fileName);
			System.out.println("===========================================================");
			 
			//리소스 다운로드(image)
			tag = doc.select(".img >img").first();
			
			String iPath = "https://sum.su.or.kr:8888/" + tag.attr("src").trim();
			System.out.println(iPath);
			
			String imgName = dPath.substring(dPath.lastIndexOf("/")+1);
			System.out.println(imgName);
			System.out.println("===========================================================");
			
			Runnable r = new DownloadBroker(dPath, fileName);
			Thread dload = new Thread(r);
			dload.start();
			for(int i=0; i<10; i++) {
				try {
					Thread.sleep(1000);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(""+(i+1));
			}
			System.out.println();
			System.out.println("===========================================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
