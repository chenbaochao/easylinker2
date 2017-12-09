package com.easylinker.iot.v2.repository;

import com.easylinker.iot.v2.model.config.UpYunAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wwhai on 2017/12/9.
 */
public interface UpYunAccountRepository extends JpaRepository<UpYunAccount, String> {
    UpYunAccount findTopById(String id);
}
