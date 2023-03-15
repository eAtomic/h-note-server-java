package cn.hyv5.hnote.api;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RootApi {

    @RequestMapping("/")
    public void root(HttpServletResponse response) throws IOException {
        response.sendRedirect("/index.html");
    }

    @RequestMapping("/index.html")
    public String index(){
        return "this is index page";
    }
}
