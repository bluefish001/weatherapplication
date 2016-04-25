package com.weather.service.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.bean.WeatherBean;
import com.weather.service.WeatherService;

@Service("weatherService")
public class WeatherServiceImp implements WeatherService {
	private Logger log =  LoggerFactory.getLogger(WeatherServiceImp.class);
	
	@Override
	public List<WeatherBean> getWeather(String[] cityArray) {
		// TODO Auto-generated method stub
		String url;
		List<WeatherBean> weatherBeanList = new ArrayList<>();
		for(String city: cityArray){
			url = makeupURL(city);
			log.debug(" URL is "+ url);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = 
					  restTemplate.getForEntity(url , String.class);
			WeatherBean weatherBean = convertoWeatherBean(response);
			weatherBeanList.add(weatherBean);
			
		}
		return weatherBeanList;
	}
	
	//create new url based on city
	private String makeupURL(String city){
		String url = "http://api.openweathermap.org/data/2.5/weather?q=";
		String appId = "&appid=b4b2821d80b375a7300dff17f1e7daba";
		String finalUrl = url+city+appId;
		return finalUrl;
	}
	
	//time out
	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		int timeout = 5000;
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
		new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(timeout);
		return clientHttpRequestFactory;
		}
	
	//retrieve the JSON data and convert to weather bean
	private WeatherBean convertoWeatherBean(ResponseEntity<String>response){
		WeatherBean weatherBean = new WeatherBean();
		 ObjectMapper mapper = new ObjectMapper();
		 
		 JsonNode root;
			try {
				root = mapper.readTree(response.getBody());
				JsonNode city = root.path("name");
				log.debug("name is "+ city.asText());
				weatherBean.setCity(city.asText());
				
				JsonNode weather = root.path("weather");
				 for( JsonNode weatherMain : weather){
					 log.debug("weather is "+weatherMain.path("main").asText());
					 weatherBean.setWeather(weatherMain.path("main").asText());
				 }
				 
				 JsonNode mainNode = root.path("main");
				 log.debug("weather is "+mainNode.path("temp").floatValue());
				 weatherBean.setTemperature(mainNode.path("temp").floatValue());
				 
				 JsonNode windNode = root.path("wind");
				 log.debug("weather is "+windNode.path("speed").floatValue());
				 weatherBean.setWind(windNode.path("speed").floatValue());
				 
								 
				 Date now = new Date();
				 SimpleDateFormat sdf = new SimpleDateFormat("EEEE 'at' hh:mm a ");
				 weatherBean.setUpdatedTime(sdf.format(now));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return weatherBean;
	}

}
