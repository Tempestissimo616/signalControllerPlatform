package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.dto.response.DeviceWorkingInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SignalOverviewServiceImpl implements SignalOverviewService{
    @Override
    public List<DeviceWorkingInfoResponse> getAllDevicesWorkingInfo() {
        return List.of();
    }
}
