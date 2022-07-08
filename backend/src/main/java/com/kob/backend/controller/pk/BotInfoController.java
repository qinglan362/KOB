package com.kob.backend.controller.pk;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pk/")
public class BotInfoController {
      @RequestMapping("get/")
    public Map<String,String> geeBotInfo(){
           Map<String,String> map=new HashMap<>();
            map.put("name","xiaoyu");
            map.put("rate","1500");
            return map;
      }
}
