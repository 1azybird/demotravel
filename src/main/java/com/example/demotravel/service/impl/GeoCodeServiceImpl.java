package com.example.demotravel.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demotravel.service.GeoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GeoCodeServiceImpl implements GeoCodeService {
    @Autowired
    private RestTemplate restTemplate;


    /*
    * 返回值若为"",说明输入地址无效
    * */
    @Override
    public String getGeoCode(String address,String key) {
        String apiURL="https://restapi.amap.com/v3/geocode/geo?address="+address+"&key="+key;
        String result=restTemplate.getForObject(apiURL,String.class);
        System.out.println("*************"+result);
        JSONObject jsonObject=JSONObject.parseObject(result);
        JSONArray geoCodeArr = jsonObject.getJSONArray("geocodes");
        String location;
        if(geoCodeArr.isEmpty()) location="";
        else location=(String) geoCodeArr.getJSONObject(0).get("location");

        return location;
    }
}
