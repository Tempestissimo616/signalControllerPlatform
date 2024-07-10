package com.phoenix.signal.controller.platform.api.sys;

import com.phoenix.signal.controller.platform.business.SignalOverviewService;
import com.phoenix.signal.controller.platform.dto.response.DeviceWorkingInfoResponse;
import com.phoenix.signal.controller.platform.model.Device;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/dashboard")
@Tag(name = "信控概览", description = "信控概览相关api")
public class SignalOverviewController {

    SignalOverviewService signalOverviewService;

    @Operation(summary = "获取所有设备工作信息")
    @GetMapping
    public ResponseEntity<List<DeviceWorkingInfoResponse>> getAllDevicesWorkingInfo() {
        return ResponseEntity.ok().body(signalOverviewService.getAllDevicesWorkingInfo());
    }

}
