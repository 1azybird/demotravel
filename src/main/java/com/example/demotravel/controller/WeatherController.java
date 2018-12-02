package com.example.demotravel.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demotravel.service.WeatherService;
import com.example.demotravel.tools.DATA;
import com.example.demotravel.utils.ResultVOUtil;
import com.example.demotravel.vo.ResultVO;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/travelling/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;
    private static String key= DATA.KEY;

    @RequestMapping("weatherInfo")
    public ResultVO<String> getWeatherInfo(@RequestParam("city")String city){
        
        String code=getCityCode(city);
        if(code.equals("")) return ResultVOUtil.error("0","当前地址有误，请重新输入！");
        String result=weatherService.getWeartherInfo(code,key);
        JSONObject jsStr = JSONObject.parseObject(result);
        return ResultVOUtil.result((String)jsStr.get("status"),(String)jsStr.get("info"),jsStr);
    }


    public  String getCityCode(String city){
        Workbook book= null;
        try {
            book = Workbook.getWorkbook(new File("citycode.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        Sheet sheet=book.getSheet(0);
        int rsRows = sheet.getRows();
        int i=1;
        for(;i<rsRows;i++)
        {
            String str=sheet.getCell(0,i).getContents();
            if(str.equals(city)){
                break;
            }
        }
        if(i==rsRows) return "";
        String code=sheet.getCell(1,i).getContents();
        return code;
    }
}
