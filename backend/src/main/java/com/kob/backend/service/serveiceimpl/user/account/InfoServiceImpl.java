package com.kob.backend.service.serveiceimpl.user.account;


import com.kob.backend.entity.User;
import com.kob.backend.service.serveiceimpl.utils.UserDetailsImpl;
import com.kob.backend.service.user.account.InfoService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {
    @Override
    public Map<String, String> getInfo() {
        UsernamePasswordAuthenticationToken
                authentication=(UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authentication.getPrincipal();
        User user=loginUser.getUser();
        Map<String,String> map=new HashMap<>();
        map.put("error_message","success");
        map.put("id",user.getId().toString());
        map.put("username",user.getUsername());
        map.put("photo",user.getPhoto());
        map.put("personalsignature",user.getPersonalsignature());
        map.put("age",user.getAge().toString());
        map.put("hobby",user.getHobby());
        return map;
    }
}
