package Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utils {
	
	public static int getUniqueTransactionId(){
		
		    // It will generate 6 digit random Number.
		    // from 0 to 999999
		    Random rnd = new Random();
		    int number = rnd.nextInt(999999);

		    // this will convert any number sequence into 6 character.
		    return Integer.parseInt(String.format("%06d", number));
		
	}
	
	public static String getDateTime(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

}
