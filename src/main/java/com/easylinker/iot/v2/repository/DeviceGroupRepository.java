package com.easylinker.iot.v2.repository;

import com.easylinker.iot.v2.model.device.DeviceGroup;
import com.easylinker.iot.v2.model.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wwhai on 2017/11/24.
 */
public interface DeviceGroupRepository extends JpaRepository<DeviceGroup, String> {
    DeviceGroup findTopBySerialNumber(Long serialNumber);

    DeviceGroup findTopByName(String name);

    DeviceGroup findTopByAppUserAndName(AppUser appUser, String name);

    List<DeviceGroup> findAllByAppUser(AppUser appUser);
}
