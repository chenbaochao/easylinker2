package com.easylinker.iot.v2.repository;

import com.easylinker.iot.v2.model.device.Device;
import com.easylinker.iot.v2.model.device.DeviceData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Created by wwhai on 2017/11/27.
 * 设备数据
 */
public interface DeviceDataRepository extends JpaRepository<DeviceData, String> {
    Page<DeviceData> findAllByDevice(Device device, Pageable pageable);

//    @Query("select all from DeviceData where device=:device and createTime between :startTime  and :endTime")
//    Page<DeviceData> findAllByDeviceBetween(@Param(value = "device") Device device,
//                                            @Param(value = "startTime")Date startTime,
//                                            @Param(value = "endTime")Date endTime,
//                                            Pageable pageable);
}
