package cn.hyv5.hnote.entity.po;

import cn.hyv5.hnote.entity.enums.EntityStatusType;
import cn.hyv5.hnote.entity.enums.UserRegSourceType;
import cn.hyv5.hnote.entity.enums.UserVerifyCodeType;
import cn.hyv5.hnote.entity.enums.UserVipType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !getStatus().equals(EntityStatusType.STOP);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return getStatus().equals(EntityStatusType.NORMAL);
    }
}
