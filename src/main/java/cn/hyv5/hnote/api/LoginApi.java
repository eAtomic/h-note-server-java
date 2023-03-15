package cn.hyv5.hnote.api;

import cn.hyv5.hnote.entity.dto.LoginRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginApi {
    @RequestMapping(value = "standard", method = RequestMethod.POST)
    public Object usernameLogin(LoginRequest loginRequest){
        Map<String,String> token = new HashMap<>();
        token.put("token","token123");
        return token;
    }
}
