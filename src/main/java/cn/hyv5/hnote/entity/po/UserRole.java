package cn.hyv5.hnote.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@TableName("user_role")
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseEntity{
    private String userId;
    private String roleId;
    private LocalDateTime expire;
}
