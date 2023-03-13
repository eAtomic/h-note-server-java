package cn.hyv5.hnote.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthy")
public class HealthyApi {
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String healthProbe(){
        return "OK";
    }
}
