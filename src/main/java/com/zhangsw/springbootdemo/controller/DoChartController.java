package com.zhangsw.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value="/do-chart",method = {RequestMethod.POST,RequestMethod.GET})
public class DoChartController {

    @RequestMapping(value = "/class-number")
    public String classNumber(Integer stationId, Date dateTime){
        System.out.println("do-find-by-id");
        return "my-find-by-id";

        //List<BasUser> buList = jsonTagDao.find("from BasUser where id = ? ", id);
        //return buList.get(0);
    }

}
