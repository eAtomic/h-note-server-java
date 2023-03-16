package cn.hyv5.hnote.entity.bo.login;

import cn.hyv5.hnote.entity.enums.ClientPlatformSoftwareType;
import cn.hyv5.hnote.entity.enums.ClientPlatformSystemType;
import cn.hyv5.hnote.entity.enums.PlatformArchType;
import lombok.Data;

import java.time.LocalDateTime;

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
}
