package com.easylinker.iot.v2.repository;

import com.easylinker.iot.v2.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wwhai on 2017/11/15.
 */
public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findTop1ByUsernameOrEmailOrPhone(String parame, String parame1, String parame2);
}
