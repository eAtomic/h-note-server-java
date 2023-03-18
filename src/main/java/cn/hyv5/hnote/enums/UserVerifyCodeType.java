package cn.hyv5.hnote.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserVerifyCodeType {
    OK(0x00),
    MAIL_UN_VERIFY(0x01),
    PHONE_UN_VERIFY(0x02)
    ;
    @EnumValue
    private int value;
}