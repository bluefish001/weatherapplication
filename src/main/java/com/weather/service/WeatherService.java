package com.weather.service;

import java.util.List;

import com.weather.bean.WeatherBean;

public interface WeatherService {
	
	public List<WeatherBean> getWeather(String[] cityArray);
	
}
