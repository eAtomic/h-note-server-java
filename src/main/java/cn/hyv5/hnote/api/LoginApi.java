package cn.hyv5.hnote.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginApi {
    @RequestMapping(value = "standard", method = RequestMethod.POST)
    public Object usernameLogin(){
        return null;
    }
}
