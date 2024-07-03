package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.model.Device;
import com.phoenix.signal.controller.platform.model.OriginalProduct;
import com.phoenix.signal.controller.platform.service.DeviceDbService;
import com.phoenix.signal.controller.platform.service.OriginalProductDbService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeviceManagementServiceImpl implements DeviceManagementService{

    DeviceDbService deviceDbService;
    OriginalProductDbService originalProductDbService;

    @Override
    public List<OriginalProduct> getAllOriginalProduct() {
        return originalProductDbService.list();
    }

    @Override
    public List<Device> getAllDevice() {
        return deviceDbService.list();
    }
}
