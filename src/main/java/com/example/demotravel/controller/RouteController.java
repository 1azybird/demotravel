package com.example.demotravel.controller;

import com.example.demotravel.service.GeoCodeService;
import com.example.demotravel.service.RouteService;
import com.example.demotravel.tools.DATA;
import com.example.demotravel.utils.ResultVOUtil;
import com.example.demotravel.vo.ResultVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/travelling/route")
public class RouteController {

    @Autowired
    private  RouteService routeService;
    @Autowired
    private GeoCodeService geoCodeService;
    private static String key= DATA.KEY;

    @RequestMapping("getGeoCode")
    private ResultVO<String> getGeoCode(){
        String address="孙琳";
        String result=geoCodeService.getGeoCode(address,key);
        if(result.equals(""))
            return ResultVOUtil.error("0","当前地址有误，请重新输入！");
        else
        return ResultVOUtil.result("1","ok",result);
    }

    private String getAddressCode(String address){
        String code;
        if(address.contains(","))
            code=address;
        else code=geoCodeService.getGeoCode(address,key);
        return code;
    }
    @RequestMapping("walking")
    public ResultVO<String> walking(@RequestParam("origin") String origin,
                                    @RequestParam("destination") String destination
    ){

//        String origin="121.436908,31.025668";
//        String destination="121.215566,31.054710";

        String ocode=getAddressCode(origin);
        String dcode=getAddressCode(destination);

        if(ocode.equals(dcode)) return ResultVOUtil.error("0","起点和终点相同，请重新输入！");
        if(ocode.equals("")) return ResultVOUtil.error("0","起点有误，请重新输入！");
        if(dcode.equals("")) return ResultVOUtil.error("0","终点有误，请重新输入！");

        String result=routeService.getWalkingRoute(ocode,dcode,key);
        JSONObject jsStr = JSONObject.parseObject(result);
        return ResultVOUtil.result((String)jsStr.get("status"),(String)jsStr.get("info"),jsStr);
    }

    @RequestMapping("bicycling")
    public  ResultVO<String> bicycling(@RequestParam("origin") String origin,
                                       @RequestParam("destination") String destination
    ){
        String ocode=getAddressCode(origin);
        String dcode=getAddressCode(destination);
        if(ocode.equals(dcode)) return ResultVOUtil.error("00","起点和终点相同，请重新输入！");
        if(ocode.equals("")) return ResultVOUtil.error("00","起点有误，请重新输入！");
        if(dcode.equals("")) return ResultVOUtil.error("00","终点有误，请重新输入！");

        String result=routeService.getBicyclingRoute(ocode,dcode,key);
        JSONObject jsStr = JSONObject.parseObject(result);
        return ResultVOUtil.result(jsStr.get("errcode").toString(),(String)jsStr.get("errmsg"),jsStr);
    }
/*

* */
    @RequestMapping("transit")
    public ResultVO<String> transit(@RequestParam("origin") String origin,
                                    @RequestParam("destination") String destination,
                                    @RequestParam("city") String city,
                                    @RequestParam("cityd") String cityd,
                                    @RequestParam(value = "strategy",defaultValue = "0")String strategy
                          ){
//        String origin="上海交通大学闵行校区";
//        String destination="东华大学松江校区";
//        String city="上海";
//        String cityd="上海";
//        String strategy="0";
        String ocode=getAddressCode(origin);
        String dcode=getAddressCode(destination);
        if(ocode.equals(dcode)) return ResultVOUtil.error("0","起点和终点相同，请重新输入！");
        if(ocode.equals("")) return ResultVOUtil.error("0","起点有误，请重新输入！");
        if(dcode.equals("")) return ResultVOUtil.error("0","终点有误，请重新输入！");

        /*strategy
            0：最快捷模式
            1：最经济模式
            2：最少换乘模式
            3：最少步行模式
            5：不乘地铁模式
        */
        String result=routeService.getTransitRoute(ocode,dcode,city,cityd,strategy,key);
        JSONObject jsStr = JSONObject.parseObject(result);
        return ResultVOUtil.result((String)jsStr.get("status"),(String)jsStr.get("info"),jsStr);
    }

    @RequestMapping("driving")
    public  ResultVO<String> driving(@RequestParam("origin") String origin,
                                     @RequestParam("destination") String destination,
                                     @RequestParam(value = "strategy",defaultValue = "0") String strategy
    ){
          /*strategy
            0：最快捷模式
            1：最经济模式
            2：最少换乘模式
            3：最少步行模式
            5：不乘地铁模式
        */


        /*extensions
        base:返回基本信息；all：返回全部信息
        * */
//        String origin="上海交通大学闵行校区";
//        String destination="东华大学松江校区";
//        String strategy="0";
        String extensions="all";
        String ocode=getAddressCode(origin);
        String dcode=getAddressCode(destination);
        if(ocode.equals(dcode)) return ResultVOUtil.error("0","起点和终点相同，请重新输入！");
        if(ocode.equals("")) return ResultVOUtil.error("0","起点有误，请重新输入！");
        if(dcode.equals("")) return ResultVOUtil.error("0","终点有误，请重新输入！");
        String result=routeService.getDrivingRoute(ocode,dcode,strategy,extensions,key);
        JSONObject jsStr = JSONObject.parseObject(result);
        return ResultVOUtil.result((String)jsStr.get("status"),(String)jsStr.get("info"),jsStr);
    }


    @RequestMapping("truck")
    public  ResultVO<String> truck(
            @RequestParam("origin") String origin,
            @RequestParam("destination") String destination,
            @RequestParam(value="strategy",defaultValue = "1") String strategy,
            @RequestParam("province") String province,
            @RequestParam("number") String number,
            @RequestParam(value="size",defaultValue = "2") String size
    ) {
         /*
         size
    1：微型车，2：轻型车（默认值），3：中型车，4：重型车
    * */
        /*strategy
        * 1：躲避拥堵
        * 2：不走高速
        * 3：避免收费
        * 4：躲避拥堵&不走高速
        * 5：避免收费&不走高速
        * 6：躲避拥堵&避免收费
        * 7：避免拥堵&避免收费&不走高速
        * 8：高速优先
        * 9：躲避拥堵&高速优先
        * 10：不考虑路况，返回速度最优、耗时最短的路线。但是此路线不一定距离最短
        * */

//        String origin="上海交通大学闵行校区";
//        String  destination="东华大学松江校区";
//        String strategy="1";
//        String province="鲁";
//        String number="779769";
//        String size="2";
        String ocode=getAddressCode(origin);
        String dcode=getAddressCode(destination);
        if(ocode.equals(dcode)) return ResultVOUtil.error("0","起点和终点相同，请重新输入！");
        if(ocode.equals("")) return ResultVOUtil.error("0","起点有误，请重新输入！");
        if(dcode.equals("")) return ResultVOUtil.error("0","终点有误，请重新输入！");
        String result=routeService.getTruckRoute(ocode,dcode,strategy,province,number,size,key);
        JSONObject jsStr = JSONObject.parseObject(result);
        return ResultVOUtil.result((String)jsStr.get("status"),(String)jsStr.get("info"),jsStr);
    }
}
