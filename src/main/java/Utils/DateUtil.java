package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	 public static Date convetToDate(String dateStr) throws ParseException{
			if(dateStr==null || dateStr=="") return null;
			dateStr = dateStr.replace('/', '-');
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");		
			return dateFormat.parse(dateStr);
		}
	    public static String getDate(Date date){
			if (date==null) return null;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.format(date);
		}
	    public static String getDate1(Date date){
			if (date==null) return null;
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			return dateFormat.format(date);
		}
	    public static void main(String[] args) throws ParseException {
	        String s = "11/05/1990";
	        Date d = new Date();
	        d = convetToDate(s);
	        System.out.println(d);
	        System.out.println(getDate1(d));	               
	    }
}
