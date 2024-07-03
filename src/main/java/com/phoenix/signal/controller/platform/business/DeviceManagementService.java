package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.model.Device;
import com.phoenix.signal.controller.platform.model.OriginalProduct;

import java.util.List;

public interface DeviceManagementService {
    List<OriginalProduct> getAllOriginalProduct();



    List<Device> getAllDevice();
}
