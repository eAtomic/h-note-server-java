package cn.hyv5.hnote.entity.po;

import cn.hyv5.hnote.entity.enums.EntityStatusType;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;

@Data
@EqualsAndHashCode(callSuper=false)
public class BaseEntity<T> extends Model<BaseEntity<T>> {
    @TableId(type = ASSIGN_ID)
    private String id;
    @TableLogic
    private String deleted;
    private EntityStatusType status = EntityStatusType.NORMAL;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
}
