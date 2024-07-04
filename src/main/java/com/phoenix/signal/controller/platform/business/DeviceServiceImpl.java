package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.model.Device;
import com.phoenix.signal.controller.platform.service.DeviceDbService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeviceServiceImpl implements DeviceService{

    private DeviceDbService deviceDbService;
    @Override
    public List<Device> getAllDevice() {
        return deviceDbService.getAll();
    }
}
