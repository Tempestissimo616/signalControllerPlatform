package com.phoenix.signal.controller.platform.service;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.phoenix.signal.controller.platform.mapper.DeviceIntersectionPlanMapper;
import com.phoenix.signal.controller.platform.model.DeviceIntersectionPlan;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeviceIntersectionPlanDbServiceImpl extends MPJBaseServiceImpl<DeviceIntersectionPlanMapper, DeviceIntersectionPlan> implements DeviceIntersectionPlanDbService{

    DeviceIntersectionPlanMapper deviceIntersectionPlanMapper;


    @Override
    public List<DeviceIntersectionPlan> getAllByDeviceId(Long deviceId) {
        return deviceIntersectionPlanMapper.selectList(new MPJLambdaWrapper<DeviceIntersectionPlan>()
                .eq(DeviceIntersectionPlan::getParentPlanDeviceId,deviceId));
    }
}
