package com.example.demotravel.service.impl;

import com.example.demotravel.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public String getWeartherInfo(String city, String key) {
        String apiURL="https://restapi.amap.com/v3/weather/weatherInfo?city="+city+"&key="+key;
        System.out.println("@@@@@@@@@@@@@@@@@@@@"+apiURL);
        String weaInfo=restTemplate.getForObject(apiURL,String.class);
        return weaInfo;
    }
}
