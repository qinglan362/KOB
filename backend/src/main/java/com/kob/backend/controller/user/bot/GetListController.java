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


//// $.ajax({
////   url:"http://localhost:9090/user/bot/remove/",
////   type:'POST',
////   data:{
////   bot_id:1,
////   },
////   headers:{
////     Authorization: "Bearer "+store.state.user.token,
////   },
////   success(resp){
////     console.log(resp);
////   },
////   error(resp){
////     console.log(resp);
////   }
//// })
//// $.ajax({
////   url:"http://localhost:9090/user/bot/update/",
////   type:'POST',
////   data:{
////     bot_id:7,
////     title:"xxx",
////     description:"yyy",
////     content:"代码",
////   },
////   headers:{
////     Authorization: "Bearer "+store.state.user.token,
////   },
////   success(resp){
////     console.log(resp);
////   },
////   error(resp){
////     console.log(resp);
////   }
//// })
//    $.ajax({
//            url:"http://localhost:9090/user/bot/getlist/",
//            type:'GET',
//            headers:{
//            Authorization: "Bearer "+store.state.user.token,
//            },
//            success(resp){
//            console.log(resp);
//            },
//            error(resp){
//            console.log(resp);
//            }
//            })