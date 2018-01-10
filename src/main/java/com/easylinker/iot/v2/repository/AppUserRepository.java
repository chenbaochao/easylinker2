package com.easylinker.iot.v2.repository;

import com.easylinker.iot.v2.model.device.Device;
import com.easylinker.iot.v2.model.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wwhai on 2017/11/15.
 */
public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findTop1ByUsernameOrEmailOrPhone(String param0, String param1, String param2);

    AppUser findTop1ByUsername(String username);

    AppUser findTopByDeviceList(List<Device> deviceList);
}
