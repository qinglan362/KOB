package com.kob.backend.controller.user.bot;

import com.kob.backend.service.user.bot.RemoveService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class RemoveController {
    @Resource
    private RemoveService removeService;

    @PostMapping("/user/bot/remove/")
    public Map<String,String> add(@RequestParam Map<String,String> data ) {
        return removeService.remove(data);
    }
}
