package cn.blackme.securityoauth2.service.impl;

import cn.blackme.securityoauth2.domain.SysUser;
import cn.blackme.securityoauth2.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user = sysUserService.selectSysUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("user not found.");
        }

        return new User(user.getUsername(), user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
    }
}
