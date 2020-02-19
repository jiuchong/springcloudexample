package cn.blackme.securityoauth2.service.impl;

import cn.blackme.securityoauth2.domain.SysUser;
import cn.blackme.securityoauth2.mapper.SysUserMapper;
import cn.blackme.securityoauth2.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public void insertUser(SysUser sysUser) {
        sysUserMapper.insertSelective(sysUser);
    }

    @Override
    public SysUser selectSysUserByUsername(String username) {
        return sysUserMapper.selectByUsername(username);
    }

    @Override
    public SysUser selectSysUserById(Integer id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteSysUserById(Integer id) {
        sysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateUserSelective(SysUser sysUser) {
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }
}
