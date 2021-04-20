package com.analytique.storage;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class LogsStorage {
	
	private final String fileName = "logs.json";
	private LinkedHashSet<TrafficHTTP> Traffic;
	
	public LogsStorage() {
		
		try (FileReader reader = new FileReader(fileName)) {
			 
			System.out.println("Logs >> Loading File (" + fileName + ")");
			Type type = new TypeToken<ArrayList<TrafficHTTP>>(){}.getType();
			Traffic = new Gson().fromJson(reader, type);
            
        } catch (IOException e) {
        	
        	Traffic = new LinkedHashSet<>();
        	
        	save();
        	
        }
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(true) {
					
					try {
						
						Thread.sleep(120*1000);
						
					} catch (Exception e) {}
					
					save();
					
				}
				
			}
			
		}).start();
		
	}
	
	public void save() {
		
		System.out.println("Logs >> Save File (" + fileName + ")");
		
		try (FileWriter writer = new FileWriter(fileName)) {
    		
    		Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
			 
			writer.write(gson.toJson(Traffic));
			writer.flush();
 
        } catch (IOException e1) {
        	
            e1.printStackTrace();
            
        }
		
	}
	
	public LinkedHashSet<TrafficHTTP> getTrafficList() {
		
		return Traffic;
		
	}

}
