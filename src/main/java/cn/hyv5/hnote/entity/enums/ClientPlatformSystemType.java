package cn.hyv5.hnote.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ClientPlatformSystemType {
    WINDOWS("windows"),
    MACOS("macos"),
    LINUX("linux"),
    ANDROID("android"),
    IOS("ipad"),
    UNKNOWN("unknown")
    ;
    private String value;
}
