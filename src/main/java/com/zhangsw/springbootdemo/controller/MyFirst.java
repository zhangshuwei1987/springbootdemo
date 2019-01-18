package com.zhangsw.springbootdemo.controller;

import com.framework.entity.BasUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/my-first",method = {RequestMethod.POST,RequestMethod.GET})
public class MyFirst {

    @RequestMapping(value = "/find-by-id")
    public String dofindById(@RequestParam(value = "id")Integer id){
        System.out.println("do-find-by-id");
        return "my-find-by-id";

        //List<BasUser> buList = jsonTagDao.find("from BasUser where id = ? ", id);
        //return buList.get(0);
    }

}
