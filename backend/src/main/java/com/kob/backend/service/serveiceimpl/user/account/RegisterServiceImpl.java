package com.kob.backend.service.serveiceimpl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.entity.User;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(String username, String password, String confirmedPassword) {

        Map<String, String> map = new HashMap<>();
        if (username == null) {
        map.put("error_message", "用户名不能为空大笨蛋");
        return map;
    }
        if (password == null || confirmedPassword == null) {
        map.put("error_message", "密码不能为空大笨蛋");
        return map;
    }

       username = username.trim();
        if (username.length() == 0) {
        map.put("error_message", "用户名不能为空大笨蛋");
        return map;
    }

        if (password.length() == 0 || confirmedPassword.length() == 0) {
        map.put("error_message", "密码不能为空大笨蛋");
        return map;
    }
        if(password.length()<6)
        {
            map.put("error_message","密码不能少于六位哦");
            return map;
        }
        if (username.length() > 100) {
        map.put("error_message", "用户名长度不能大于100大笨蛋");
        return map;
    }

        if (password.length() > 100 || confirmedPassword.length() > 100) {
        map.put("error_message", "密码长度不能大于100大笨蛋");
        return map;
    }

        if (!password.equals(confirmedPassword)) {
        map.put("error_message", "两次输入的密码不一致大笨蛋");
        return map;
    }

    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
    List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
        map.put("error_message", "用户名已存在小笨蛋");
        return map;
    }

    String encodedPassword = passwordEncoder.encode(password);
    String photo = "https://cdn.acwing.com/media/user/profile/photo/1_lg_844c66b332.jpg";
    User user = new User(null, username, encodedPassword, photo,
            "该用户很懒，什么也没留下"
            ,
            0,
            "主人觉得是个秘密哟"
            );

        userMapper.insert(user);
        map.put("error_message", "success");
        return map;
}
}
