package cn.hyv5.hnote.entity.bo.login;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

import cn.hyv5.hnote.entity.po.User;

@Data
@AllArgsConstructor
public class LoginInfo {
    private User user;
    private Set<LoginClient> clients;
}
