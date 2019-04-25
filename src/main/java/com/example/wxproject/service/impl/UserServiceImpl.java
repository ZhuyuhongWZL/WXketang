package com.example.wxproject.service.impl;

import com.example.wxproject.Config.AppConfig;
import com.example.wxproject.Config.WXControllerConfig;
import com.example.wxproject.Entity.OpenIdOV;
import com.example.wxproject.Utils.BindingVOUtil;
import com.example.wxproject.Utils.FindUserUtil;
import com.example.wxproject.dataobject.User;
import com.example.wxproject.dto.BindingDTO;
import com.example.wxproject.service.UserService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.wxproject.repository.UserRepository;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByOpenId(String openId) {
        //暂时不做缓存处理
        /*if (userLruCache.get(openId) != null){
            return (User) userLruCache.get(openId);
        }*/
        return userRepository.findByOpenId(openId);
    }

    @Override
    public User findByUserIdAndUserName(Integer userId, String userName) {
        return userRepository.findByUsrIdAndUsrName(userId, userName);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    public BindingDTO BindingOpenId(String openId, String userName, Integer usrId, Integer sourceType){
        log.info(userName+"/"+usrId);
        User user = userRepository.findByUsrIdAndUsrName(usrId, userName);
        if (user == null){
            log.info("数据库中没有该用户数据");
            return BindingVOUtil.nouser();
        }else if (user.getOpenId()==null || user.getOpenId().equals("")){
            user.setOpenId(openId);
            User result = save(user);
            if (result.getOpenId().equals(openId)){
                //TODO:插入数据库成功，这里做个重定向跳转
                log.info("插入数据库成功，绑定成功");
                FindUserUtil.userLruCache.put(user.getUserId(),user);
                return BindingVOUtil.success();
            }else {
                log.info("插入数据库失败，绑定失败");
                return BindingVOUtil.failed();
            }
        }
        log.info("该用户已被绑定");
        return BindingVOUtil.doublebinding();
    }

    //根据openId做重定向跳转
    public String RedirectJump(String openId, String redirectURL, int sourceType){
        User user = findByOpenId(openId);
        if (user != null){
            FindUserUtil.userLruCache.put(user.getUserId(),user);
            if (user.getRoleId()==1){
                //user为学生
                return "欢迎您："+ user.getUserName() + "!";
            /*String returnUrl = "";
            return "redirect:"+returnUrl+"?openid="+openid;*/
            }else if (user.getRoleId().equals("teacher")){
                //user为老师
                String returnUrl = "";
                return "redirect:"+returnUrl+"?openid="+openId;
            }else if (user.getRoleId().equals("jiaowuyuan")){
                //user为教务员
                String returnUrl = "";
                return "redirect:"+returnUrl+"?openid="+openId;
            }else if (user.getRoleId().equals("school")){
                //user为学校
                String returnUrl = "";
                return "redirect:"+returnUrl+"?openid="+openId;
            }
        } else {
            //user信息为空，跳转去绑定界面
            log.info("openId未绑定，跳转绑定界面, openId : "+openId);
            String returnUrl = WXControllerConfig.BindingRedirectURL+"openId="+openId+"&sourceType="+sourceType;
            log.info("redirectURL:"+redirectURL);
            return returnUrl;
        }

        return "wrong: can not get open id";
    }

    public OpenIdOV getOpenidOV(String code){
        if (code == null || code.equals("")){

        }
        String uri = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ AppConfig.AppID +
                "&secret="+AppConfig.AppSecret+"&code="+code+"&grant_type=authorization_code";
        RestTemplate template = new RestTemplate();
        String responseString=template.getForObject(uri,String.class);
        System.out.println("responseString = " + responseString);
        Gson gson = new Gson();
        OpenIdOV openIdOV = gson.fromJson(responseString, OpenIdOV.class);
        //这里用gson.fromJson转换实体对象时，如果转换出错，对象不为null，但对应的属性为null
        return openIdOV;
    }
}
