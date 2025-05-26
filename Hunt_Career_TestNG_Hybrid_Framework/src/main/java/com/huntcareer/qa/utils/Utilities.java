package com.huntcareer.qa.utils;

import java.util.Date;

public class Utilities {
	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_WAIT_TIME = 5;
	
	public static String generateTimeStamp() {
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}
	public static String genarateEmailTimeStamp() {
		Date date = new Date();
		String timeStamp =  date.toString().replace(" ", "_").replace(":", "_");
		return "testUser"+timeStamp+"@mailinator.com";
	}
}
