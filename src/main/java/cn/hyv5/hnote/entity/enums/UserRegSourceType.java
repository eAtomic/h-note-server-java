package cn.hyv5.hnote.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserRegSourceType {
    SELF("0"),      //用户自己注册
    PROMOTION("1"), //促销活动注册
    INVITE("2")     //好友邀请注册
    ;
    @EnumValue
    private String value;
}
