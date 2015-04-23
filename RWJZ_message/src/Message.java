import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Message {
	
	public String url ;
	public Date date ;
	
	public Message(){}
	
	public Message(Date date){
		this.date=date ;
		this.url="Not Found" ;
	}
	public Message(Date date,String url){
		this.date=date ;
		this.url=url ;
	}
	
	public static void println(ArrayList message){
		int len=message.size() ;
		for(int i=0;i<len;i++){
			Message m=(Message)message.get(i) ;
			System.out.println(m.date.year+"年"+m.date.month+"月"+m.date.day+"日\t"+m.url);
		}
	}
	public static void openPage(ArrayList message){
		int len=message.size() ;
		for(int i=0;i<len;i++){
			Message m=(Message)message.get(i) ;
			if (!m.url.contains("Not Found")){
				java.net.URI uri;
				try {
					uri = new java.net.URI(m.url);
					java.awt.Desktop.getDesktop().browse(uri);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
		}
	}
	public static ArrayList FindMessage(List inDate){
		Collections.sort(inDate);  //将输入数列按照增序排序
		Collections.reverse(inDate);
		ArrayList<Message> message=new ArrayList() ;
		
		ListPage current_lp=new ListPage(1) ;
		int current_page_num=current_lp.current_page ;
		int total_page_num=current_lp.total_page ;
		
		for(int i=0;i<inDate.size();i++){
			Date d=(Date)inDate.get(i) ;
			while(true){
				if (d.id<=current_lp.first_time && d.id>=current_lp.last_time){
					break ;
				}
				else{
					if (current_page_num>=total_page_num) break ;
					else{
						current_page_num++ ;
						current_lp=new ListPage(current_page_num) ;
					}
				}
			}
			if (current_page_num>=total_page_num) System.out.println("日期输入错误") ;
			else{
				if (current_lp.last_time>=(d.id-7)){   //要横跨两页的情况
					boolean sig=false ;
					for(int j=0;j<current_lp.time.length;j++){
						if (current_lp.time[j]<=d.id && current_lp.time[j]>=(d.id-7)){ 
							sig=new innerPage(current_lp.url[j],d).isCorrect ;
							if (sig){
								Message m=new Message(d,current_lp.url[j]) ;
								message.add(m) ;
								break ;
							}
						}
					}
					ListPage temp_lp=new ListPage(current_page_num+1) ;
					for(int j=0;j<temp_lp.time.length;j++){
						if (temp_lp.time[j]<=d.id && temp_lp.time[j]>=(d.id-7)){ 
							sig=new innerPage(temp_lp.url[j],d).isCorrect ;
							if (sig){
								Message m=new Message(d,temp_lp.url[j]) ;
								message.add(m) ;
								break ;
							}
						}
					}
					if (!sig) {
						Message m=new Message(d) ;
						message.add(m) ;
					}
				}else{  //在一页内的情况
					boolean sig=false ;
					for (int j=0;j<current_lp.time.length;j++){
						if (current_lp.time[j]<=d.id && current_lp.time[j]>=(d.id-7)){  //初步检查日期是否符合
							innerPage ip=new innerPage(current_lp.url[j],d) ;
							sig=ip.isCorrect ;
							if (sig){
								Message m=new Message(d,current_lp.url[j]) ;
								message.add(m) ;
								break ;
							}
						}
					}
					if (!sig) {
						Message m=new Message(d) ;
						message.add(m) ;
					}
				}

			}

			
		}
		return message ;
	}
	
}
