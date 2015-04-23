
import org.jsoup.Jsoup; 
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element; 
import org.jsoup.select.Elements; 


public class innerPage {
	public boolean isCorrect ; 
	
	public innerPage(String url,Date date){
		this.isCorrect=false ;
		
		if (url.contains("www.seu.edu.cn")){
			//System.out.println(url);
			Document tc1=ListPage.getPage(url) ; 
			Element ac=tc1.getElementsByClass("Article_Content").first() ;
			Elements p=ac.getElementsByTag("p") ;
			for(Element tp:p){
				String temp=tp.text().replace(Jsoup.parse("&nbsp;").text(), "") ;
				temp=temp.replace(" ", "") ;
				String sdate_1=Integer.toString(date.month)+"月"+Integer.toString(date.day)+"日" ;
				String sdate_2=Integer.toString(date.month)+"月"+Date.outStandard(date.day)+"日" ;
				String sdate_3=Integer.toString(date.day)+"日" ;
				if (temp.contains(sdate_1)||temp.contains(sdate_2)||temp.contains(sdate_3)){
					this.isCorrect=true ;
					break ;
				}
			}
		}
	}
}
