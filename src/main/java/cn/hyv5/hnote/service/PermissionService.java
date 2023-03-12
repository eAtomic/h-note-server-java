package cn.hyv5.hnote.service;

import cn.hyv5.hnote.entity.po.Permission;
import cn.hyv5.hnote.mapper.PermissionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PermissionService extends ServiceImpl<PermissionMapper, Permission> {
}
