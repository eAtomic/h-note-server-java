package cn.hyv5.hnote.entity.bo.login;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class LoginInfo {
    private TinyUser user;
    private Set<LoginClient> clients;
}
