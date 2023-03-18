package cn.hyv5.hnote.enums;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum PlatformArchType {
    X86("x86"),
    X64("x64"),
    ARM64("ARM64")
    ;

    private String value;
}
