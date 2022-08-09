package com.kob.backend.consumer;

import com.alibaba.fastjson.JSONObject;
import com.kob.backend.consumer.utils.Game;
import com.kob.backend.consumer.utils.JwtAuthentication;
import com.kob.backend.entity.User;
import com.kob.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket/{token}")  // 注意不要以'/'结尾
public class WebSocketServer {

    private Session session=null;
    private User user;
   final private static ConcurrentHashMap<Integer,WebSocketServer>  users=new ConcurrentHashMap<>();
    final private static CopyOnWriteArraySet<User> matchpool=new CopyOnWriteArraySet<>();

    private static UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        WebSocketServer.userMapper= userMapper;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
        // 建立连接
        this.session=session;
        Integer userId= JwtAuthentication.getUserId(token);
        this.user=userMapper.selectById(userId);

        if(this.user!=null){
            users.put(userId,this);
        }else {
            this.session.close();
        }
        System.out.println(users);
    }

    @OnClose
    public void onClose() {
        // 关闭链接
        System.out.println("disconnected");
        if(this.user!=null){
            users.remove(this.user.getId());
            matchpool.remove(this.user);
        }
    }

    private void startMatching() throws IOException {
        // 开始匹配
        System.out.println("start matching");
        matchpool.add(this.user);

        while (matchpool.size()>=2){
            Iterator<User> it=matchpool.iterator();
            User user1=it.next(),user2=it.next();
            matchpool.remove(user1);
            matchpool.remove(user2);

            Game game=new Game(13,14,14);
            game.createMap();

            JSONObject  resp1=new JSONObject();
            resp1.put("event","start-matching");
            resp1.put("opponent_username",user2.getUsername());
            resp1.put("opponent_photo",user2.getPhoto());
            resp1.put("gamemap",game.getG());
            users.get(user1.getId()).sendMessage(resp1.toJSONString());

            JSONObject  resp2=new JSONObject();
            resp2.put("event","start-matching");
            resp2.put("opponent_username",user1.getUsername());
            resp2.put("opponent_photo",user1.getPhoto());
            resp2.put("gamemap",game.getG());
            users.get(user2.getId()).sendMessage(resp2.toJSONString());
        }
    }
    private void stopMatching(){
        // 停止匹配
        matchpool.remove(this.user);
        System.out.println("stop matching");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        // 从Client接收消息
        JSONObject data=JSONObject.parseObject(message);
        String event=data.getString("event");
        if("start-matching".equals(event)) {
            startMatching();
        } else if ("stop-matching".equals(event)) {
             stopMatching();
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) {
        synchronized (this.session){
          try {
              this.session.getBasicRemote().sendText(message);
          } catch (Exception e) {
              e.printStackTrace();
          }
        }
     }
}
