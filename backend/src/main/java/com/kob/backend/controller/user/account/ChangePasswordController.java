package com.kob.backend.controller.user.account;

import com.kob.backend.service.user.account.ChangePasswordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class ChangePasswordController {
    @Resource
    private ChangePasswordService changePasswordService;

    @PostMapping("/user/account/changePassword/")
    public Map<String,String> getInfo(@RequestParam Map<String,String> map){
        return changePasswordService.changePassword(map.get("oldPassword"),map.get("newPassword"));
    }
}
