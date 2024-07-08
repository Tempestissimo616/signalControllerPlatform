package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.dto.request.DeviceRequest;
import com.phoenix.signal.controller.platform.model.Device;

import java.util.List;

public interface DeviceService {
    List<Device> getAllDevice();

    String createDevice(DeviceRequest deviceRequest);
}