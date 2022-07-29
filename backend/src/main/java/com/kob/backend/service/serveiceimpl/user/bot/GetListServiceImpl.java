package com.kob.backend.service.serveiceimpl.user.bot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.entity.Bot;
import com.kob.backend.entity.User;
import com.kob.backend.mapper.BotMapper;
import com.kob.backend.service.serveiceimpl.utils.UserDetailsImpl;
import com.kob.backend.service.user.bot.GetListService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GetListServiceImpl implements GetListService {

    @Resource
    private BotMapper botMapper;
    @Override
    public List<Bot> getList() {
        UsernamePasswordAuthenticationToken authenticationToken=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authenticationToken.getPrincipal();
        User user=loginUser.getUser();
        QueryWrapper<Bot> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getId());
        return botMapper.selectList(queryWrapper);
    }
}
