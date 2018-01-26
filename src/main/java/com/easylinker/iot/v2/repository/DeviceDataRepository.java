package com.easylinker.iot.v2.repository;

import com.easylinker.iot.v2.model.device.Device;
import com.easylinker.iot.v2.model.device.DeviceData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 *
 */
public interface DeviceDataRepository extends JpaRepository<DeviceData, String> {
    Page<DeviceData> findAllByDevice(Device device, Pageable pageable);

    /**
     * 时间段查询
     *
     * @param device
     * @param startTime
     * @param endTime
     * @param pageable
     * @return
     */
    // @Query("select all from DeviceData where createTime between :startTime and :endTime")
    Page<DeviceData> findAllByDeviceAndCreateTimeBetween(
            Device device,
            Date startTime,
            Date endTime,
            Pageable pageable
    );
}
