package cn.hyv5.hnote.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginRequest {
    @AllArgsConstructor
    public enum LoginType {
        UID("uid"),
        USERNAME("username"),
        PHONE("phone"),
        MAIL("mail"),
        ;
        private String value;
    }
    @NotBlank
    private String account; //登录账户：手机、邮箱、用户ID
    private LoginType type;
    @NotBlank
    private String password;
}
