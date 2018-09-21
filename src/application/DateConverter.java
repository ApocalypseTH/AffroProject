package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

	private SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat s2 = new SimpleDateFormat("dd/MM/yyyy");
	
	public String mysqlToLocal(String mysqldate) throws ParseException {
		if(mysqldate != null && !"".equals(mysqldate)) {
			Date d;
			d = s.parse(mysqldate);
			return s2.format(d);
		} return "";
	}
	
	public String localToMysql(String localdate) throws ParseException {
		if(!localdate.equals(null) && !"".equals(localdate)) {
			Date d;
			d = s2.parse(localdate);
			return s.format(d);
		} return ""; 
	}
	
}
