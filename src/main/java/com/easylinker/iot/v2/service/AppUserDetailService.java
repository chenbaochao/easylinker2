package com.easylinker.iot.v2.service;

import com.easylinker.iot.v2.model.user.AppUser;
import com.easylinker.iot.v2.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by wwhai on 2017/11/15.
 */
public class AppUserDetailService implements UserDetailsService {
    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String argument) throws UsernameNotFoundException {

        /**
         * 可以用Username Or Email Or Phone 登录
         */


        AppUser appUser = appUserRepository.findTop1ByUsernameOrEmailOrPhone(argument, argument, argument);

        if (appUser == null) {
            throw new UsernameNotFoundException("");
        } else if (!appUser.isEnabled()) {
            System.out.println("用户找不到");
            throw new UsernameNotFoundException("用户找不到");
        } else if (!appUser.isEnabled()) {
            System.out.println("用户没有激活");
            throw new DisabledException("用户没有激活");
        } else if (appUser.isAccountNonLocked()) {
            System.out.println("用户锁定");
            throw new LockedException("用户锁定");

        } else {
            return appUser;
        }
    }
}