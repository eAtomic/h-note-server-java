package cn.hyv5.hnote.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("role_permission")
public class RolePermission {
    private String roleId;
    private String permissionId;
}
