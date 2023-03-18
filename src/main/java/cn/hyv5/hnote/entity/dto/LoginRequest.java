package cn.hyv5.hnote.entity.dto;

import cn.hyv5.hnote.entity.bo.login.LoginClient;
import cn.hyv5.hnote.enums.UserPrincipalType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collection;

@Getter
@Setter
public class LoginRequest extends PreAuthenticatedAuthenticationToken {
    @NotBlank
    private String account; //登录账户：手机、邮箱、用户ID
    private UserPrincipalType type;
    @NotBlank
    private String password;

    private LoginClient client;

    public LoginRequest(){
        super("","");
    }
    public LoginRequest(String account, UserPrincipalType type, String password, LoginClient client) {
        super(account, password);
        this.account = account;
        this.type = type;
        this.password = password;
        this.client = client;
    }

    public LoginRequest(String account, UserPrincipalType type, String password,Collection<? extends GrantedAuthority> anAuthorities, LoginClient client) {
        super(account, password, anAuthorities);
        this.account = account;
        this.type = type;
        this.password = password;
        this.client = client;
    }
}
