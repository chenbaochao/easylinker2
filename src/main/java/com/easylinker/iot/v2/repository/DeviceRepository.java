package com.easylinker.iot.v2.repository;

import com.easylinker.iot.v2.model.device.Device;
import com.easylinker.iot.v2.model.device.DeviceGroup;
import com.easylinker.iot.v2.model.user.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wwhai on 2017/11/24.
 */
public interface DeviceRepository extends JpaRepository<Device, String> {
    Device findTopByDeviceName(String deviceName);

    Page<Device> findAll(Pageable pageable);

    Page<Device> findAllByAppUser(Pageable pageable, AppUser appUser);

    Device findTopByOpenId(String openId);

    List<Device> findAllByDeviceGroup(DeviceGroup deviceGroup);

    List<Device> findAllByDeviceGroup(DeviceGroup deviceGroup, Pageable pageable);


    Device findTopByDeviceCode(String deviceCode);

    Integer countAllByAppUser(AppUser appUser);

    @Query(" select count (id) from Device where is_online =1 and appUser=:appUser")

    Integer countOnlineByAppUser(@Param(value = "appUser") AppUser appUser);

    @Query(" select count (id) from Device where is_online =0 and appUser=:appUser")

    Integer countOfflineByAppUser(@Param(value = "appUser") AppUser appUser);

}
