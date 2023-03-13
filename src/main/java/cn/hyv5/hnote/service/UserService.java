package cn.hyv5.hnote.service;

import cn.hyv5.hnote.entity.po.User;
import cn.hyv5.hnote.entity.qo.SimpleUserResult;
import cn.hyv5.hnote.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements UserDetailsService {
    @Resource
    private UserMapper mapper;
    public User getUserByUsername(String username) {
        return mapper.getUserByUsername(username);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }
}
