package com.phoenix.signal.controller.platform.service;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.phoenix.signal.controller.platform.mapper.DeviceMapper;
import com.phoenix.signal.controller.platform.model.Device;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeviceDbServiceImpl extends MPJBaseServiceImpl<DeviceMapper, Device> implements DeviceDbService{

    DeviceMapper deviceMapper;
    @Override
    public List<Device> getAll() {
        return deviceMapper.selectList(new MPJLambdaWrapper<Device>().selectAll(Device.class));
    }

    @Override
    public Long countByProductId(Long deviceId) {
        return deviceMapper.selectCount(new MPJLambdaWrapper<Device>().eq(Device::getDeviceId, deviceId));
    }
}
