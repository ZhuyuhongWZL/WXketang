package com.example.wxproject.dto;

import lombok.Data;

@Data
public class BindingDTO {

    /*code说明：
    0：绑定成功
    1：用户已绑定
    -1：数据库插入更新失败
    -2：数据库无用户*/

    private Integer code;

    private String msg;
}
