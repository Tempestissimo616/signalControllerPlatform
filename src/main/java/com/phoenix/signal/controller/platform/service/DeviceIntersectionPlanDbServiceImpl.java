package com.phoenix.signal.controller.platform.service;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.phoenix.signal.controller.platform.mapper.DeviceIntersectionPlanMapper;
import com.phoenix.signal.controller.platform.model.DeviceIntersectionPlan;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeviceIntersectionPlanDbServiceImpl extends MPJBaseServiceImpl<DeviceIntersectionPlanMapper, DeviceIntersectionPlan> implements DeviceIntersectionPlanDbService{

    DeviceIntersectionPlanMapper deviceIntersectionPlanMapper;
    ModelMapper modelMapper;


}
