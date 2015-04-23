import java.lang.Comparable;

public class Date implements Comparable<Date>{
	public int year ;
	public int month ;
	public int day ;
	public int id ;
	public Date(int year,int month,int day){
		this.year=year ;
		this.month=month ;
		this.day=day ;
		this.id=date2int(year,month,day) ;
	}
	public static String outStandard(int input){ //不论进去的是几位数，出来的统一是2位数
		if (input>9){
			String temp="0"+Integer.toString(input);
			return temp ;
		}
		else{
			String temp=Integer.toString(input) ;
			return temp ;
		}
	}
	
	public static int date2int(int year,int month,int day){
		int temp2=0 ;
		for(int i=2001;i<year;i++){
			if (i%4==0){ 
				temp2+=366 ;
			}else
				temp2+=365 ;
		}
		int[] monday ;
		if (year%4==0){
			int[] temp={31,29,31,30,31,30,31,31,30,31,30,31} ;
			monday=temp ;
		}else{
			int[] temp={31,28,31,30,31,30,31,31,30,31,30,31} ;
			monday=temp ;
		}
		int temp1=temp2 ;
		for(int i=0;i<month-1;i++){
			temp1+=monday[i] ;
		}
		temp1+=day ;
		return temp1 ;
	}
	
	@Override
	public int compareTo(Date o){
		if (this==o){
			return 0 ;
		}
		else if (o!=null && o instanceof Date){
			if (this.id<=o.id) return -1 ;
			else return 1 ;
		}else return -1 ;
	}
	
	
}
