package cn.hyv5.hnote.config;

import cn.hyv5.hnote.entity.qo.SimpleUserResult;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MybatisMetaDataHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("MybatisMetaDataHandler::insertFill");
        SimpleUserResult user = new SimpleUserResult();
        this.strictInsertFill(metaObject, "createTime", ()-> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(metaObject, "createBy",()->"junit", String.class);
        this.strictInsertFill(metaObject, "updateTime", ()-> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(metaObject, "updateBy",()->"junit", String.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        SimpleUserResult user = new SimpleUserResult();
        this.strictInsertFill(metaObject, "updateTime", ()-> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(metaObject, "updateBy",()->"junit", String.class);
    }
}
