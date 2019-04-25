package com.example.wxproject.Utils;

import com.example.wxproject.dto.BindingDTO;

public class BindingVOUtil {

    public static BindingDTO success(){
        BindingDTO bindingDTO = new BindingDTO();
        bindingDTO.setCode(0);
        bindingDTO.setMsg("绑定成功");
        return bindingDTO;
    }

    public static BindingDTO doublebinding(){
        BindingDTO bindingDTO = new BindingDTO();
        bindingDTO.setCode(1);
        bindingDTO.setMsg("该用户信息已绑定");
        return bindingDTO;
    }

    public static BindingDTO nouser(){
        BindingDTO bindingDTO = new BindingDTO();
        bindingDTO.setCode(-2);
        bindingDTO.setMsg("数据库中无用户");
        return bindingDTO;
    }

    public static BindingDTO failed(){
        BindingDTO bindingDTO = new BindingDTO();
        bindingDTO.setCode(-1);
        bindingDTO.setMsg("数据绑定失败，请重试");
        return bindingDTO;
    }
}
