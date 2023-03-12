package cn.hyv5.hnote.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserVipType {
    GUEST("0"),
    NORMAL_USER("10"),
    EXPIRED_VIP("20"),
    PROMOTION_VIP("30"), //活动送的会员，范围30-40
    MONTHLY_VIP("40"),
    QUARTERLY_VIP("50"),
    YEARLY_VIP("60"),
    ENDLESS_VIP("70"),
    ADMIN("1000"),
    SYS_ADMIN("1001")
    ;
    @EnumValue
    private String value;
}