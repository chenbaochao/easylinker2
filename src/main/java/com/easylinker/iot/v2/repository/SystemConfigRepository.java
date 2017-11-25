package com.easylinker.iot.v2.repository;

import com.easylinker.iot.v2.model.system.SystemConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wwhai on 2017/11/25.
 */
public interface SystemConfigRepository extends JpaRepository<SystemConfig, String> {
}
