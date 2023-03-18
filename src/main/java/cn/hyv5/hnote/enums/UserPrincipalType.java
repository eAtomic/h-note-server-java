package cn.hyv5.hnote.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserPrincipalType {
    UID("uid"),
    USERNAME("username"),
    PHONE("phone"),
    MAIL("mail")
    ;
    private String value;

}
