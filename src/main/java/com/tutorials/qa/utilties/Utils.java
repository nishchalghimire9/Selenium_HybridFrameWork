package com.tutorials.qa.utilties;
// IT WILL TAKE CARE HARDCODED PART
import java.util.Date;
// this will help to automate the code here i am automating the email so i do not need to write email to register 
// the account it will create new email in every run.
public class Utils {

	public static String emailWithDateTimeStamp() {
		Date date = new Date();
		String emailDateTimeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "hariprasad" + emailDateTimeStamp +"@gmail.com";
		
		
	}
	
	public final static int IMPLICIT_WAIT = 100;
	public final static int PAGELOAD_TIME= 100;
	public final static int SCRIPT_TIME = 100;
	
	}
	
