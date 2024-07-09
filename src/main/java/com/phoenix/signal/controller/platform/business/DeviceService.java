package com.phoenix.signal.controller.platform.business;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phoenix.signal.controller.platform.dto.request.DeviceRequest;
import com.phoenix.signal.controller.platform.dto.request.PageRequest;
import com.phoenix.signal.controller.platform.model.Device;

import java.util.List;

public interface DeviceService {
    List<Device> getAllDevice();

    String createDevice(DeviceRequest deviceRequest);

    IPage<Device> page(Page<Device> page);

    String update(Long deviceId, DeviceRequest deviceRequest);

    String delete(Long deviceId);
}
