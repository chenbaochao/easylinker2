package com.easylinker.iot.v2.service;

import com.easylinker.iot.v2.constants.FailureMessageEnum;
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
            throw new UsernameNotFoundException(FailureMessageEnum.USER_NOT_EXIST.toString());
        } else if (!appUser.isEnabled()) {
            throw new DisabledException(FailureMessageEnum.NO_ACTIVE.toString());
        } else if (!appUser.isAccountNonLocked()) {
            throw new LockedException(FailureMessageEnum.USER_LOCKED.toString());
        } else {
            return appUser;
        }
    }
}