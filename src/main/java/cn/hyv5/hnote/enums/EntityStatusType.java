package cn.hyv5.hnote.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EntityStatusType {
    NORMAL("1"),
    SUSPEND("2"),
    STOP("3")
    ;
    @EnumValue
    private String value;
}
