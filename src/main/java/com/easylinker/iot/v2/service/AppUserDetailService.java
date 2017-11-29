package com.easylinker.iot.v2.service;

import com.easylinker.iot.v2.model.user.AppUser;
import com.easylinker.iot.v2.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        AppUser appUser = null;
        try {
            appUser = appUserRepository.findTop1ByUsernameOrEmailOrPhone(argument, argument, argument);
        } catch (Exception e) {
            //e.printStackTrace();
            throw e;
        }


        return appUser;
    }
}