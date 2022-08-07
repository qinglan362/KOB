package com.kob.backend.controller.user.account;

import com.kob.backend.service.user.account.UserInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;
    @PostMapping("/user/account/updateinfo/")
    public Map<String,String> update(@RequestParam Map<String,String> data){
        System.out.println("Fsafas"+data);
        return userInfoService.update(data);
    }
}
