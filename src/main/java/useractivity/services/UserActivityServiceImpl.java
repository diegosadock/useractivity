package useractivity.services;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.OffsetDateTime;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import useractivity.model.UserActivity;
import useractivity.util.OffsetDateTimeAdapter;
import useractivity.util.Util;

public class UserActivityServiceImpl implements IUserActivityService {
	
	static int codigoSucesso = 200;

	@Override
	public List<UserActivity> fetchUserActivities(String username) {
		String webService = "https://api.github.com/users/" + username + "/events";
		
		try {
			URL url = new URL(webService);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			if (connection.getResponseCode() != codigoSucesso) {
				throw new RuntimeException("HTTP error code: " + connection.getResponseCode());
			} else {
				System.out.println("Conexão bem sucedida!");
				
				BufferedReader resposta = new BufferedReader(new InputStreamReader((connection.getInputStream())));
	            String jsonEmString = Util.converteJsonEmString(resposta);
	            
	            Gson gson = new GsonBuilder()
	                    .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
	                    .create();
	            Type listType = new TypeToken<List<UserActivity>>() {}.getType();
	            List<UserActivity> userActivity = gson.fromJson(jsonEmString, listType);
	            
	            return userActivity;
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
