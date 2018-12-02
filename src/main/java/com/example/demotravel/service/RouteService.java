package com.example.demotravel.service;

public interface RouteService {
    public String getWalkingRoute(String origin,String destination,String key);
    public String getBicyclingRoute(String origin,String destination,String key);
    public String getTransitRoute(String origin,String destination,String city,String cityd,String strategy,String key);
    public String getDrivingRoute(String origin,String destination,String strategy,String extensions,String key);
    public String getTruckRoute(String origin,String destination,String strategy,String size,String province,String number,String key);
}
