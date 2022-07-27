package com.kob.backend.service.serveiceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.entity.User;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.service.serveiceimpl.utils.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user=userMapper.selectOne(queryWrapper);
        if(user==null)
            throw  new RuntimeException("用户不存在");
        return new UserDetailsImpl(user);
    }
}
