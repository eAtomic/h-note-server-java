package cn.hyv5.hnote.entity.dto;

import lombok.Data;

@Data
public class FormLoginRequest {
    public enum FormLoginType {
        UID,
        PHONE,
        MAIL,
    }
    private String account; //登录账户：手机、邮箱、用户ID
    private FormLoginType type;
    private String password;
}
