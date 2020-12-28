package com.williamspreitzer.jsonexamples.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJSONExample {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ReadJSONExample reader = new ReadJSONExample();
		JSONArray jArray = reader.readJSONFile();
		jArray.forEach(emp -> reader.parseEmployeeObject( (JSONObject) emp));
	}
	
	private JSONArray readJSONFile() {
		JSONParser jParser = null;
		FileReader fr = null;
		Object obj = null;
		try {
			jParser = new JSONParser();
			fr = new FileReader("employees.json");
			obj = jParser.parse(fr);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch(ParseException pe) {
			pe.printStackTrace();
		} finally {
			try { 
				fr.close();
			} catch (FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			} catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return (JSONArray) obj;
	}
	
	private void parseEmployeeObject(JSONObject employee) {
		JSONObject employeeObject = ( JSONObject ) employee.get("employee");
		
		String firstName = (String) employeeObject.get("firstName");
		System.out.println(firstName);
		
		String lastName = (String) employeeObject.get("lastName");
		System.out.println(lastName);
		
		String website = (String) employeeObject.get("website");
		System.out.println(website);
	}
}