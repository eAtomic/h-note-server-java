package cn.hyv5.hnote.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ClientPlatformSoftwareType {
    BROWSER("browser"),
    APP("app"),
    H5("h5")
    ;
    private String value;
}
