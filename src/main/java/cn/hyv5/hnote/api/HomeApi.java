package cn.hyv5.hnote.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeApi {

    @RequestMapping("")
    public String home(){
        return "this is home";
    }
}
