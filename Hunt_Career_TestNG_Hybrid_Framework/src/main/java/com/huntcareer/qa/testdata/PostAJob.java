package com.huntcareer.qa.testdata;

import java.util.HashMap;
import java.util.Map;

import com.huntcareer.qa.utils.Utilities;

public class PostAJob {
	public static Map<String, String> validJob(){
		Map<String, String> data = new HashMap<>();
		data.put("title", Utilities.generateRandomJobNames());
		data.put("companyName", Utilities.getCompanyName());
		data.put("description", Utilities.generateRandomJobDescription());
		data.put("location", Utilities.generateRandomLocation());
		data.put("jobType", Utilities.getRandomJobType());
		return data;
	}
	
	public static Map<String, String> blankJob(){
		Map<String, String> data = new HashMap<>();
		data.put("title", "");
		data.put("companyName", "");
		data.put("description", "");
		data.put("location", "");
		data.put("jobType", "");
		return data;
	}
	
	public static Map<String, String> withoutTitle(){
		Map<String, String> data = new HashMap<>();
		data.put("title", "");
		data.put("companyName", Utilities.getCompanyName());
		data.put("description", Utilities.generateRandomJobDescription());
		data.put("location", Utilities.generateRandomLocation());
		data.put("jobType", Utilities.getRandomJobType());
		return data;
	}
	
	public static Map<String, String> withoutCompanyName(){
		Map<String, String> data = new HashMap<>();
		data.put("title", Utilities.generateRandomJobNames());
		data.put("companyName", "");
		data.put("description", Utilities.generateRandomJobDescription());
		data.put("location", Utilities.generateRandomLocation());
		data.put("jobType", Utilities.getRandomJobType());
		return data;
	}
	
	public static Map<String, String> withoutDescription(){
		Map<String, String> data = new HashMap<>();
		data.put("title", Utilities.generateRandomJobNames());
		data.put("companyName", Utilities.getCompanyName());
		data.put("description", "");
		data.put("location", Utilities.generateRandomLocation());
		data.put("jobType", Utilities.getRandomJobType());
		return data;
	}
	
	public static Map<String, String> withoutLocation(){
		Map<String, String> data = new HashMap<>();
		data.put("title", Utilities.generateRandomJobNames());
		data.put("companyName", Utilities.getCompanyName());
		data.put("description", Utilities.generateRandomJobDescription());
		data.put("location", "");
		data.put("jobType", Utilities.getRandomJobType());
		return data;
	}
}
