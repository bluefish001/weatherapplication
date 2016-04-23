package com.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weather.bean.WeatherBean;
import com.weather.service.WeatherService;


@Controller
@RequestMapping("/")
public class weatherController {

	@Autowired
	WeatherService weatherService;
	
	@RequestMapping("getweather")
	public ResponseEntity<List<WeatherBean>> getWeather(@RequestParam(required = false, value = "cities") String cities){
		String[] citiesArray={};
		//get city list from front end
		if(cities!=null&&!"".equalsIgnoreCase(cities)){
			citiesArray =  cities.split(",");
		}
		
		if(null==citiesArray|| citiesArray.length<=0){
			return new ResponseEntity<List<WeatherBean>>(HttpStatus.NOT_FOUND);
		}

		List<WeatherBean> cityWeather = weatherService.getWeather(citiesArray);
				
	    return new ResponseEntity<List<WeatherBean>>(cityWeather, HttpStatus.OK);
	}
	

}
