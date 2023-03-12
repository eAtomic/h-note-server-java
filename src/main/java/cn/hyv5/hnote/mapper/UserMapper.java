package cn.hyv5.hnote.mapper;

import cn.hyv5.hnote.entity.po.User;
import cn.hyv5.hnote.entity.qo.SimpleUserResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    SimpleUserResult getSimpleUser(String userid, String username);
}
