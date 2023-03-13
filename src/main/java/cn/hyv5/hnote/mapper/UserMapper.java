package cn.hyv5.hnote.mapper;

import cn.hyv5.hnote.entity.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User getUserByUsername(String username);
}
