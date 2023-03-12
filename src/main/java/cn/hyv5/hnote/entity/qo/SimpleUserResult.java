package cn.hyv5.hnote.entity.qo;

import cn.hyv5.hnote.entity.enums.UserVipType;
import cn.hyv5.hnote.entity.po.Permission;
import cn.hyv5.hnote.entity.po.Role;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SimpleUserResult {
    private String id;
    private String username;
    private String nickname;
    private String phone;
    private String countryCd;
    private String mail;
    private String avatar;
    private String avatarWrapper;
    private UserVipType vipType;
    private LocalDateTime vipExpire;
    private List<Role> roles;
    private List<Permission> permissions;
}
