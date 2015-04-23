/**
 * @author multiangle
 * @edition 1.0
 * @function 根据输入日期搜集人文讲座信息
 */

 

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections; 

public class Entrance {
	public static void main(String[] args){
		ArrayList<Date> date=new ArrayList() ;
		ArrayList<Message> message=new ArrayList() ;
		date.add(new Date(2015,4,16)) ;
		date.add(new Date(2015,4,8)) ;
		date.add(new Date(2015,3,23)) ;
		date.add(new Date(2015,4,21)) ;
		date.add(new Date(2015,4,20)) ;
		date.add(new Date(2015,3,16)) ;
		date.add(new Date(2014,11,21)) ;
		date.add(new Date(2014,4,17)) ;
		
		message=Message.FindMessage(date) ;
		Message.println(message);
		//Message.openPage(message);
	}
	
	
	

}
