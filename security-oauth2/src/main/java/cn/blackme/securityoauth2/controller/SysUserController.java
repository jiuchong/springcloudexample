package cn.blackme.securityoauth2.controller;

import cn.blackme.securityoauth2.domain.SysUser;
import cn.blackme.securityoauth2.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String createUser(SysUser sysUser) {
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        sysUserService.insertUser(sysUser);
        return "success";
    }

    @GetMapping
    public SysUser getUserInfo(Integer userId) {
        return sysUserService.selectSysUserById(userId);
    }

    @DeleteMapping
    public String deleteUser(Integer userId) {
        sysUserService.deleteSysUserById(userId);
        return "success delete";
    }

    @PutMapping
    public String updateUser(SysUser sysUser) {
        sysUserService.updateUserSelective(sysUser);
        return "success update";
    }

}
