package tools.apis;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {

	public static void main(String[] args) {
		
		
		
    	
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
			Date aujourdhui = new Date();
			Date date1 = sdf.parse("29/10/2014 05:03");
			Date date2 = sdf.parse("29/10/2014 03:33");
			System.out.println(sdf.format(date1));
	    	System.out.println(sdf.format(date2));
	    	
	    	
	    	if(date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth()  && date1.getDay() 
	    			== date2.getDay() && date1.getHours() - date2.getHours() < 2 && date1.getHours() - date2.getHours() > - 2){
	    		//OKK
	    	}
	    	
	    	if(date1.compareTo(date2)>0){
	    		System.out.println("Date1 is after Date2");
	    	}else if(date1.compareTo(date2)<0){
	    		System.out.println("Date1 is before Date2");
	    	}else if(date1.compareTo(date2)==0){
	    		System.out.println("Date1 is equal to Date2");
	    	}else{
	    		System.out.println("How to get here?");
	    	}
	    	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    	

    	
	}

}
