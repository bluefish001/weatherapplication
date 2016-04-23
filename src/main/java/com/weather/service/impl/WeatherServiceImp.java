package com.weather.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.bean.WeatherBean;
import com.weather.service.WeatherService;

public class WeatherServiceImp implements WeatherService {

	@Override
	public List<WeatherBean> getWeather(List<String> cityList, String url) {
		// TODO Auto-generated method stub
		List<WeatherBean> weatherBeanList = new ArrayList<>();
		for(String city: cityList){
			url = url + city;
			RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
			ResponseEntity<String> response = 
					  restTemplate.getForEntity(url , String.class);
			WeatherBean weatherBean = convertoWeatherBean(response);
			weatherBeanList.add(weatherBean);
			
		}
		return weatherBeanList;
	}
	
	//time out
	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		int timeout = 5000;
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
		new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(timeout);
		return clientHttpRequestFactory;
		}
	
	private WeatherBean convertoWeatherBean(ResponseEntity<String>response){
		WeatherBean weatherBean = new WeatherBean();
		 ObjectMapper mapper = new ObjectMapper();
		 
		 JsonNode root;
			try {
				root = mapper.readTree(response.getBody());
				JsonNode city = root.path("name");
				System.out.println("name is "+ city.asText());
				weatherBean.setCity(city.asText());
				
				JsonNode weather = root.path("weather");
				 for( JsonNode weatherMain : weather){
					 weatherBean.setWeather(weatherMain.path("main").asText());
				 }
				 
				 JsonNode mainNode = root.path("main");
				 weatherBean.setTemperature(mainNode.floatValue());
				 
				 JsonNode windNode = root.path("wind");
				 weatherBean.setWind(windNode.floatValue());
				 
				 Calendar cal = Calendar.getInstance();
				 weatherBean.setUpdatedTime(cal);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return weatherBean;
	}

}
