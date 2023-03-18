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
    private String rand;

    public TinyUser(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.rand = RandomUtil.randomString(8);
    }
}
