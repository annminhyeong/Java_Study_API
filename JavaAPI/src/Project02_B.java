import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import util.DownloadBroker;

public class Project02_B {
	
	public static void main(String[] args) {
		
		//�Ľ��� URL
		String url = "https://sum.su.or.kr:8888/bible/today/Ajax/Bible/BodyMatter?qt_ty=QT1";
		
		//Ű���� �Է�
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("[�Է�-> ��(yyyy),��(mm),��(dd)]:");
			String bible = br.readLine();
			url += "&Base_de="+bible+"&bibleType=1";
			System.out.println("===========================================================");
			
			//post ������� ����
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
			 
			//���ҽ� �ٿ�ε�(mp3)
			Element tag = doc.select("source").first();
			
			String dPath = tag.attr("src").trim();
			System.out.println(dPath);
			
			String fileName = dPath.substring(dPath.lastIndexOf("/")+1);
			System.out.println(fileName);
			System.out.println("===========================================================");
			 
			//���ҽ� �ٿ�ε�(image)
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
