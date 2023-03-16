package cn.hyv5.hnote.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.hutool.json.JSONUtil;
import cn.hutool.json.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SpringUtils {

    private static Logger logger = LoggerFactory.getLogger(SpringUtils.class);

    private static ServletRequestAttributes getServletRequestAttributes(){
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public static HttpServletRequest getRequest(){
        return getServletRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse(){
        return getServletRequestAttributes().getResponse();
    }

    public static void renderResult(Object object) {
        var response = getResponse();
        response.setContentType("text/json;charset=utf-8");
        var message = JSONUtil.toJsonStr(object);
        try {
            response.getWriter().write(message);
        } catch (Exception e) {
            logger.error("renderResult failed. {}", message);
        }
    }
}
