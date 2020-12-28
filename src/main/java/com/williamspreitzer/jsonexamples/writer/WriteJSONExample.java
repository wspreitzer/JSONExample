package com.williamspreitzer.jsonexamples.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WriteJSONExample {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		WriteJSONExample writer = new WriteJSONExample();
		
		JSONArray empList = new JSONArray();
		Optional<JSONObject> empDetails1 = writer.createJSONObject("firstName", "Lokesh", 
				                                           "lastName", "Gupta",
                                                           "website", "howtodoinjava.com");
		
		Optional<JSONObject> empDetails2 = writer.createJSONObject("firstName", "Brian", "lastName", "Schultz",
                "website", "example.com");
		JSONObject empObj = new JSONObject();
		empObj.put("employee", empDetails1);
		JSONObject empObj2 = new JSONObject();
		empObj2.put("employee", empDetails2);
		
		empList.add(empObj);
		empList.add(empObj2);
		
		writer.writeToFile(empList);
	}
	
	@SuppressWarnings("unchecked")
	private Optional<JSONObject> createJSONObject(String... details) {
		JSONObject jObj = new JSONObject();
		for(int i = 0; i < details.length; i++) {
			jObj.put(details[i], details[i + 1]);
			i++;
		}
		return Optional.of(jObj);
	}
	
	private void writeToFile(JSONArray empList) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("employees.json");
			fw.write(empList.toJSONString());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				fw.flush();
				fw.close();
			} catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}
