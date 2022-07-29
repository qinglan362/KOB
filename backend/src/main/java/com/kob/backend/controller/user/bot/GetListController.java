package com.kob.backend.controller.user.bot;

import com.kob.backend.entity.Bot;
import com.kob.backend.service.user.bot.GetListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class GetListController {
    @Resource
    private GetListService getListService;

    @GetMapping("/user/bot/getlist/")
    public List<Bot> getlist( ) {
        return getListService.getList();
    }
}
