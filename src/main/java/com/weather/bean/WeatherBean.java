package com.weather.bean;

import java.util.Calendar;

public class WeatherBean {
	private String city;
	private Calendar updatedTime;
	private String weather;
	private float temperature;
	private float wind;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Calendar getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Calendar updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public float getWind() {
		return wind;
	}
	public void setWind(float wind) {
		this.wind = wind;
	}
}
