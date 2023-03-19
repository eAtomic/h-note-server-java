package cn.hyv5.hnote.entity.po;

import cn.hyv5.hnote.enums.EntityStatusType;
import cn.hyv5.hnote.enums.UserRegSourceType;
import cn.hyv5.hnote.enums.UserVerifyCodeType;
import cn.hyv5.hnote.enums.UserVipType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("users")
public class User extends BaseEntity<User> implements UserDetails {

    private String username;
    private String nickname;
    private String salt;
    @TableField("pass")
    private String password;
    private String avatar;
    private String avatarWrapper;
    private String phone;
    private String countryCd;
    private String mail;
    private UserVerifyCodeType verifyCd;
    private String secret;
    private UserVipType vipType;
    private LocalDateTime vipExpire;
    private UserRegSourceType regSource;
    private String regInviter;
    private String lastIp;
    private LocalDateTime lastLoginTime;
    private String commonlyIp;
    private String wechatId;
    private String qqId;
    private String weiboId;
    private String microsoftId;
    private String googleId;
    private String appleId;
    @TableField(exist = false)
    private List<Role> roles;
    @TableField(exist = false)
    private List<Permission> permissions;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null || permissions == null) {
            return Lists.newArrayList();
        }
        var roleStream = roles
                .stream()
                .map(role->"ROLE_"+role.getRoleName().toUpperCase());
        var permissionStream = permissions
                .stream()
                .map(permission -> permission.getPermissionName().toUpperCase());
        return Stream.concat(roleStream, permissionStream)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !getStatus().equals(EntityStatusType.STOP);
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return getStatus().equals(EntityStatusType.NORMAL);
    }
}
