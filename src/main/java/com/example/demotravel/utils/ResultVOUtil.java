package com.example.demotravel.utils;

import com.example.demotravel.vo.ResultVO;

public class ResultVOUtil {
    public static ResultVO result(String status,String info,Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setStatus(status);
        resultVO.setInfo(info);
        return resultVO;
    }

    public static ResultVO result() {
        return result(null,null,null);
    }

    public static ResultVO error(String status,String info) {
        ResultVO resultVO = new ResultVO();
        resultVO.setStatus(status);
        resultVO.setInfo(info);
        return resultVO;
    }
}
