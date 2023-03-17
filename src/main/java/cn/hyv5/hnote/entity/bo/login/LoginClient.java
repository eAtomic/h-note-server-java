package cn.hyv5.hnote.entity.bo.login;

import cn.hyv5.hnote.entity.enums.ClientPlatformSoftwareType;
import cn.hyv5.hnote.entity.enums.ClientPlatformSystemType;
import cn.hyv5.hnote.entity.enums.PlatformArchType;
import cn.hyv5.hnote.utils.SpringUtils;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
public class LoginClient {
    private String ip;
    private LocalDateTime loginTime;
    private LocalDateTime expire;
    private ClientPlatformSystemType system;
    private String systemVer;
    private ClientPlatformSoftwareType software;
    private String softwareVer;
    private PlatformArchType arch;

    public static LoginClient getLoginClient(){
        var request = SpringUtils.getRequest();
        LoginClient client = new LoginClient();
        client.ip = request.getRemoteAddr();
        client.loginTime = LocalDateTime.now();
        var system = Optional.ofNullable(request.getParameter("system")).orElse("").trim();
        var sysVersion = Optional.ofNullable(request.getParameter("sys_ver")).orElse("").trim();
        client.system = ClientPlatformSystemType.valueOf(system);
        client.systemVer = sysVersion;
        
        return client;
    }
}
