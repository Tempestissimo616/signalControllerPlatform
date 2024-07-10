package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.dto.response.DeviceWorkingInfoResponse;

import java.util.List;

public interface SignalOverviewService {
    List<DeviceWorkingInfoResponse> getAllDevicesWorkingInfo();
}
