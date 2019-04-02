package com.example.wxproject.controller;


import com.example.wxproject.Config.AppConfig;
import com.example.wxproject.Entity.OpenIdOV;
import com.example.wxproject.dataobject.User;
import com.example.wxproject.service.impl.UserServiceImpl;
import com.google.gson.Gson;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConfig;
import com.soecode.wxtools.api.WxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

//http://localhost:8080/WX/test?signature=a&timestamp=b&nonce&echostr=helloworld'
//打包方式：在根目录下用mvn clean package -Dmaven.test.skip=true命令打包
@Controller
//使用RestController只能返回json（除非使用response.sendRedirect），重定向就用Controller
@RequestMapping("/WX")
@Slf4j
public class WXController {

    private static int FromJiaowu = 1;
    private static int FromComment = 2;

    private static String testOpenId = "abc12";
    private static String JiaowuRedirectURL = "";//教务系统重定向地址
    private static String CommentRedirectURL = "";//评论系统重定向地址；
    private static String BindingRedirectURL = "redirect:" + "http://192.168.31.85:8080/WXCK/html/register.html" + "?";

    @Autowired
    private UserServiceImpl userService;

    private IService iService = new WxService();

    //微信公众号绑定验证
    @RequestMapping("/authorize")
    public String AuthorizeWX(@RequestParam("signature")String signature,@RequestParam("timestamp")String timestamp,
                       @RequestParam("nonce")String nonce,@RequestParam("echostr")String echostr) {


        System.out.println("/authorize:  "+signature+"/"+timestamp+"/"+nonce+"/"+echostr);
        System.out.println("/authorize:  "+iService.checkSignature(signature, timestamp, nonce, echostr));
        System.out.println("/authorize:  "+WxConfig.getInstance().getToken()+"token");
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (iService.checkSignature(signature, timestamp, nonce, echostr)) {
            return echostr;
        }
        else return null;

    }

    //教务系统页面访问
    @RequestMapping("/jiaowu")
    public String JiaowuEntrance(@RequestParam("code")String code,@RequestParam("state")String state){
        log.info("访问教务系统，code = ");
        OpenIdOV openIdOV = getOpenidOV(code);
        String openid;
        //根据微信返回的openid到数据库换取user信息
        if (openIdOV.getOpenid() != null){
            openid = openIdOV.getOpenid();
        }else {
            //在目前拿不到code的时候，用一个假的openId做测试用，正式开发去除
            log.info("根据code获取openId失败");
            openid = testOpenId;
        }
        return RedirectJump(openid, JiaowuRedirectURL, FromJiaowu);
    }

    //评论系统访问
    @RequestMapping("/comment")
    public String CommentEntrance(@RequestParam("code")String code,@RequestParam("state")String state){
        System.out.println("code = "+code);
        OpenIdOV openIdOV = getOpenidOV(code);
        String openid;
        //根据微信返回的openid到数据库换取user信息
        if (openIdOV.getOpenid() != null){
            openid = openIdOV.getOpenid();
        }else {
            //在目前拿不到code的时候，用一个假的openId做测试用，正式开发去除
            log.info("根据code获取openId失败");
            openid = testOpenId;
        }
        return RedirectJump(openid, CommentRedirectURL, FromComment);
    }

    //绑定数据页面请求的数据接口
    @RequestMapping("/binding")
    @ResponseBody
    public String BindingOpenId(@RequestParam("openId")String openId, @RequestParam("usrName")String userName,
                                @RequestParam("usrId")Integer usrId, @RequestParam("sourceType")Integer sourceType){

        log.info(userName+"/"+usrId);
        User user = userService.findByUserIdAndUserName(usrId, userName);
        if (user == null){
            log.info("数据库中没有该用户数据");
            return "{\"code\":1}";
        }else if (user.getOpenId()==null || user.getOpenId().equals("")){
            user.setOpenId(openId);
            User result = userService.save(user);
            if (result.getOpenId().equals(openId)){
                //TODO:插入数据库成功，这里做个重定向跳转
                log.info("插入数据库成功，绑定成功");
                return "绑定成功,sourceType = " + sourceType+"; usrName = "+ userName;
            }else {
                log.info("插入数据库失败，绑定成失败");
                return "绑定失败";
            }
        }
        log.info("该用户已被绑定");
        return "该用户已被绑定";
    }
    //根据openId做重定向跳转
    private String RedirectJump(String openId, String redirectURL, int sourceType){
        User user = userService.findOne(openId);
        if (user == null){
            //user信息为空，跳转去绑定界面
            log.info("openId未绑定，跳转绑定界面, openId : "+openId);
            String returnUrl = BindingRedirectURL+"openId="+openId+"&sourceType="+sourceType;
            log.info("redirectURL:"+redirectURL);
            return returnUrl;
        }else if (user.getRoleId()==1){
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
        return "wrong: can not get open id";
    }

    private OpenIdOV getOpenidOV(String code){
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
