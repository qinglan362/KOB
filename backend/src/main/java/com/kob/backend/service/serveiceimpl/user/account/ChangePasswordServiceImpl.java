package com.kob.backend.service.serveiceimpl.user.account;

import com.kob.backend.entity.User;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.service.serveiceimpl.utils.UserDetailsImpl;
import com.kob.backend.service.user.account.ChangePasswordService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChangePasswordServiceImpl implements ChangePasswordService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserMapper userMapper;

    @Override
    public Map<String, String> changePassword(String oldPassword, String newPassword) {

        UsernamePasswordAuthenticationToken
                authentication=(UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authentication.getPrincipal();
        User user=loginUser.getUser();
        Map<String,String> map=new HashMap<>();
         if(!passwordEncoder.matches(oldPassword,user.getPassword())){
            map.put("error_message","旧密码错误");
            return map;
        }
         else  if (oldPassword.equals(newPassword)){
             map.put("error_message","新旧密码相同");
             return map;
         }
         else if(newPassword.length()<4){
             map.put("error_message","新密码长度不得小于4");
             return map;
         }
         else if(newPassword.length()>20){
             map.put("error_message","新密码长度不得大于20");
             return map;
         }
         else {
              User new_user=userMapper.selectById(user.getId());
              new_user.setPassword(passwordEncoder.encode(newPassword));
              userMapper.updateById(new_user);
              map.put("error_message","success");
              return map;
        }
    }
}
