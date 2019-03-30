package com.example.wxproject.controller;


import com.example.wxproject.Config.AppConfig;
import com.example.wxproject.Entity.OpenIdOV;
import com.google.gson.Gson;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConfig;
import com.soecode.wxtools.api.WxService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//http://localhost:8080/WX/test?signature=a&timestamp=b&nonce&echostr=helloworld'
@RestController
@RequestMapping("/WX")
public class WXController {

    private IService iService = new WxService();

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

    @RequestMapping("/code")
    public String GetOpenIdWX(@RequestParam("code")String code,@RequestParam("state")String state){
        System.out.println("code = "+code);
        String uri = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ AppConfig.AppID +
                "&secret="+AppConfig.AppSecret+"&code="+code+"&grant_type=authorization_code";
        RestTemplate template = new RestTemplate();
        String responseString=template.getForObject(uri,String.class);
        Gson gson = new Gson();
        OpenIdOV openIdOV = gson.fromJson(responseString, OpenIdOV.class);
        if (openIdOV != null){
            String result = "openId = "+openIdOV.getOpenid();
            return result;
        }
        return "wrong: can not get open id";
    }
}
