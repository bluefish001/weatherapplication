package com.weather.bean;

public class WeatherBean {
	private String city;
	private String updatedTime;
	private String weather;
	private float temperature;
	private float wind;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
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
