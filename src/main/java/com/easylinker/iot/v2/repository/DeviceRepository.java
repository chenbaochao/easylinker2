package com.easylinker.iot.v2.repository;

import com.easylinker.iot.v2.model.device.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wwhai on 2017/11/24.
 */
public interface DeviceRepository extends JpaRepository<Device, String> {
    Device findTopByDeviceName(String deviceName);

    Page<Device> findAll(Pageable pageable);

    //Page<Device> findAllByDeviceDataList(Pageable pageable);

    Device findTopByOpenId(String openId);
}
