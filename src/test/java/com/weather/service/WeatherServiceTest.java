package com.weather.service;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.weather.service.impl.WeatherServiceImp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class WeatherServiceTest {
	String[] city = {"sydney"};
	
	@Configuration
	@PropertySource(value = { "classpath:application.properties" })
	static class WeatherServiceTestContextConfiguration {
		@Bean
		public WeatherService weatherService(){
			return new WeatherServiceImp();
		}
		
		@Autowired
	    private Environment env;
	}
		
	@Autowired
	private WeatherService weatherService;
	
	
	@Test()
	public void testgetWeather() {
		assertNotNull(weatherService.getWeather(city));
	}
}
