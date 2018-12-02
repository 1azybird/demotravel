package com.example.demotravel.service.impl;

import com.example.demotravel.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getWalkingRoute(String origin, String destination,String key) {
        String apiURL="https://restapi.amap.com/v3/direction/walking?origin="+origin+"&destination="+destination+"&key="+key;
        String route=restTemplate.getForObject(apiURL,String.class);
        return route;
    }

    @Override
    public String getBicyclingRoute(String origin, String destination,String key) {
        String apiURL="https://restapi.amap.com/v4/direction/bicycling?origin="+origin+"&destination="+destination+"&key="+key;
        String route=restTemplate.getForObject(apiURL,String.class);
        return route;
    }

    @Override
    public String getTransitRoute(String origin, String destination, String city, String cityd, String strategy, String key) {
        String apiURL="https://restapi.amap.com/v3/direction/transit/integrated?origin="+origin+"&destination="+destination+"&city="+city+"&cityd"+cityd+"&strategy="+strategy+"&key="+key;
        String route=restTemplate.getForObject(apiURL,String.class);
        return route;
    }

    @Override
    public String getDrivingRoute(String origin, String destination, String strategy, String extensions, String key) {
        String apiURL="https://restapi.amap.com/v3/direction/driving?origin="+origin+"&destination="+destination+"&strategy="+strategy+"&extensions="+extensions+"&key="+key;
        String route=restTemplate.getForObject(apiURL,String.class);
        return route;
    }

    @Override
    public String getTruckRoute(String origin, String destination, String strategy, String province,String number, String size, String key) {
        String apiURL="https://restapi.amap.com/v4/direction/truck?origin="+origin+"&destination="+destination+"&strategy="+strategy+"&province="+province+"&number="+number+"&size="+size+"&key="+key;
        String route=restTemplate.getForObject(apiURL,String.class);
        return route;
    }
}
