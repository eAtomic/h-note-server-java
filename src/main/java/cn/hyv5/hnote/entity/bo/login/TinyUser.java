package cn.hyv5.hnote.entity.bo.login;

import cn.hutool.core.util.RandomUtil;
import cn.hyv5.hnote.entity.po.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TinyUser {
    private String id;
    private String username;
    // private UserVipType vipType;
    // private LocalDateTime vipExpire;
    private String rand;
    // private List<String> roles;
    // private List<String> permissions;

    public TinyUser(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        // this.vipType = user.getVipType();
        // this.vipExpire = user.getVipExpire();
        // this.expire = LocalDateTime.now().plusSeconds(during);
        this.rand = RandomUtil.randomString(8);
    }

    // public String getToken(){
    //     var secret = SpringUtil.getProperty(TOKEN_SECRET_CONFIG);
    //     var json = JSONUtil.toJsonStr(this);
    //     var aes = new SymmetricCrypto(SymmetricAlgorithm.AES, secret.getBytes());
    //     return aes.encryptBase64(json);
    // }
}
