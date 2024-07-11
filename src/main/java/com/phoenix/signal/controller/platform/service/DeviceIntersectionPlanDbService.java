package com.phoenix.signal.controller.platform.service;

import com.github.yulichang.base.MPJBaseService;
import com.phoenix.signal.controller.platform.model.DeviceIntersectionPlan;

import java.util.List;

public interface DeviceIntersectionPlanDbService extends MPJBaseService<DeviceIntersectionPlan> {
    List<DeviceIntersectionPlan> getAllByDeviceId(Long deviceId);
}
