import java.io.IOException;
import java.net.URL;

import org.jsoup.Connection; 
import org.jsoup.Jsoup; 
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element; 
import org.jsoup.select.Elements; 
import org.jsoup.helper.Validate;

public class ListPage {
	public Document content ;
	public int first_time ; //最新时间
	public int last_time ;  //最早时间
	public int[] time ;
	public String[] url ;
	public int current_page ;
	public int total_page ;
	
	public ListPage(int page){
		String url="http://www.seu.edu.cn/138/list"+Integer.toString(page)+".htm" ;
		Document tc1=getPage(url) ; //temp_content_1 
		Element e_news=tc1.getElementById("wp_news_w3") ;
			Elements list=e_news.child(0).child(0).children();
			int len=list.size()/2 ;
			this.time=new int[len] ;
			this.url=new String[len] ;
			for (int i=0;i<len;i++){
				Elements e=list.get(i*2).getElementsByAttributeValue("align", "left") ;
				String stime=e.get(2).text() ;
				this.time[i]=Date.date2int(Integer.parseInt(stime.substring(0, 4)),
						Integer.parseInt(stime.substring(5,7)),Integer.parseInt(stime.substring(8,10))) ;
				this.url[i]=e.get(1).getElementsByTag("a").first().absUrl("href") ;
			}
			this.first_time=this.time[0] ;
			this.last_time=this.time[this.time.length-1] ;
		Element e_paging=tc1.getElementById("wp_paging_w3") ;
			Element li=e_paging.getElementsByTag("li").get(2) ;
			this.current_page=Integer.parseInt(li.getElementsByClass("curr_page").first().text()) ;
			this.total_page=Integer.parseInt(li.getElementsByClass("all_pages").first().text()) ;
	}
	public static void main(String[] args){
		String url="http://www.seu.edu.cn/138/list"+Integer.toString(1)+".htm" ;
		Document tc1=getPage(url) ; //temp_content_1
		Element e_paging=tc1.getElementById("wp_paging_w3") ;
		System.out.println(e_paging.getElementsByTag("li").get(2).getElementsByClass("all_pages").first().text()) ;
	}
	
	public static Document getPage_inner(String url) throws IOException{
		Document doc ;
		try{
			doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
			//doc=Jsoup.connect(url).get() ;
			return doc ;
		}catch(IOException e){
			return null;
		}
	}
	public static Document getPage(String url){
		try{
			Document doc ;
			int times=0 ;
			//System.out.println("正在请求获取网页"+url);
			doc=getPage_inner(url) ;
			while(doc.equals(null)&&times<8){
				doc=getPage_inner(url) ;
				System.out.println("第"+times+"次请求失败，正在进行第"+(++times)+"次请求");
			}
			
			if (doc.equals(null)){
				System.out.println("ERROR:获取网页失败");
				return null ;
			}else{
				return doc ;
			}
		}catch(IOException e){
			return null ;
		}
		
	}
}
