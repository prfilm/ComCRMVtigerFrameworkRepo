package generic.webDriverUtility;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int getRandomNumber() {
		Random ranDom = new Random();
		int ranDomNumber = ranDom.nextInt(5000);
		return ranDomNumber;
	}
	
	public String getSystemDateyyyyMMdd() {
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(d);
		return date;
	}
	
	public String getRequiredDateyyyyMMdd(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, days);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String Value = sdf.format(d);
		return Value;	
	}
	
	public String getSystemDateddMMyyyy() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter df =  DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String currentDate = date.format(df);
		return currentDate;
	}
	public String getRequiredDateMMddyyyy(int days) {
		LocalDate date = LocalDate.now().plusDays(days);
		DateTimeFormatter df =  DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String finalDate = date.format(df);
		return finalDate;		
	}
	
	

}
