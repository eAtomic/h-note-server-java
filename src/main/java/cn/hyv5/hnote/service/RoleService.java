package cn.hyv5.hnote.service;

import cn.hyv5.hnote.entity.po.Role;
import cn.hyv5.hnote.mapper.RoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {
}
