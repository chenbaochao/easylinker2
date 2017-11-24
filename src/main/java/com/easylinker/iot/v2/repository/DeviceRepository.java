package com.easylinker.iot.v2.repository;

import com.easylinker.iot.v2.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wwhai on 2017/11/24.
 */
public interface DeviceRepository extends JpaRepository<Device, String> {

}
