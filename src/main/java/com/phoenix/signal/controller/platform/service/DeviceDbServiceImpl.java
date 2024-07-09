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

    @Override
    public Device getByDeviceId(Long deviceId) {
        return deviceMapper.selectOne(new MPJLambdaWrapper<Device>().eq(Device::getDeviceId, deviceId));
    }

    @Override
    public Boolean updateByDeviceId(Long deviceId, Device device) {
        return deviceMapper.update(device, new MPJLambdaWrapper<Device>().eq(Device::getDeviceId, deviceId)) > 0;
    }

    @Override
    public Boolean deleteByDeviceId(Long deviceId) {
        return deviceMapper .delete(new MPJLambdaWrapper<Device>().eq(Device::getDeviceId, deviceId)) > 0;
    }
}
