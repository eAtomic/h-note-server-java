package cn.hyv5.hnote.entity.bo.login;

import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import cn.hyv5.hnote.entity.enums.UserVipType;
import cn.hyv5.hnote.entity.po.Permission;
import cn.hyv5.hnote.entity.po.Role;
import cn.hyv5.hnote.entity.po.User;
import cn.hyv5.hnote.utils.SpringUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TinyUser {
    private String id;
    private String username;
    private UserVipType vipType;
    private LocalDateTime vipExpire;
    private String rand;
    private List<String> roles;
    private List<String> permissions;

    public TinyUser(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.vipType = user.getVipType();
        this.vipExpire = user.getVipExpire();
        // this.expire = LocalDateTime.now().plusSeconds(during);
        this.rand = RandomUtil.randomString(8);
        if(user.getRoles() == null){
            this.roles = new ArrayList<>();
        }else{
            this.roles = user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());
        }
        if(user.getPermissions() == null){
            this.permissions = new ArrayList<>();
        }else{
            this.permissions = user.getPermissions().stream().map(Permission::getPermissionName).collect(Collectors.toList());
        }
    }

    // public String getToken(){
    //     var secret = SpringUtil.getProperty(TOKEN_SECRET_CONFIG);
    //     var json = JSONUtil.toJsonStr(this);
    //     var aes = new SymmetricCrypto(SymmetricAlgorithm.AES, secret.getBytes());
    //     return aes.encryptBase64(json);
    // }
}
