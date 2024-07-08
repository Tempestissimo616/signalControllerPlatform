package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.dto.request.DeviceRequest;
import com.phoenix.signal.controller.platform.exception.ConflictException;
import com.phoenix.signal.controller.platform.model.Device;
import com.phoenix.signal.controller.platform.model.OriginalProduct;
import com.phoenix.signal.controller.platform.service.DeviceDbService;
import com.phoenix.signal.controller.platform.type.ExceptionEnum;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeviceServiceImpl implements DeviceService{

    private DeviceDbService deviceDbService;
    private ModelMapper modelMapper;
    @Override
    public List<Device> getAllDevice() {
        return deviceDbService.getAll();
    }

    @Override
    public String createDevice(DeviceRequest deviceRequest) {
        checkDuplicatedProduct(deviceRequest.getDeviceId());

        Device device = new Device();
        device.setDeviceId(deviceRequest.getDeviceId());
        device.setDeviceName(deviceRequest.getDeviceName());
        device.setDeviceProducer(deviceRequest.getDeviceProducer());
        device.setIntersectionId(deviceRequest.getIntersectionId());
        device.setProductModel(deviceRequest.getProductModel());
        device.setProductName(deviceRequest.getProductName());
        device.setProductType(deviceRequest.getProductType());
        device.setStatus(deviceRequest.getStatus());

        if(deviceDbService.save(device)){
            return String.format("%s:%s 添加成功",deviceRequest.getDeviceName(),device.getDeviceId());
        }

        return String.format("%s:%s 添加失败",deviceRequest.getDeviceName(),device.getDeviceId());
    }

    private void checkDuplicatedProduct(Long productId){
        if(deviceDbService.countByProductId(productId) > 0){
            throw new ConflictException(ExceptionEnum.CONFLICT_EXCEPTION);
        }
    }
}
