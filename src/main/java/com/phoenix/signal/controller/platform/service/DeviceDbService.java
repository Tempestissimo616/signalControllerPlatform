package com.phoenix.signal.controller.platform.service;

import com.github.yulichang.base.MPJBaseService;
import com.phoenix.signal.controller.platform.model.Device;

import java.util.List;

public interface DeviceDbService extends MPJBaseService<Device> {
    List<Device> getAll();

    Long countByProductId(Long deviceId);
}
