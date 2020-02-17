package cn.blackme.securityoauth2.service;

import cn.blackme.securityoauth2.domain.SysUser;

public interface SysUserService {

    void insertUser(SysUser sysUser);

    SysUser selectSysUserByUsername(String username);

    SysUser selectSysUserById(Integer id);

    void deleteSysUserById(Integer id);

    void updateUserSelective(SysUser sysUser);
}
