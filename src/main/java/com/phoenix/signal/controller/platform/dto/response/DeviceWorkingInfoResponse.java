package com.phoenix.signal.controller.platform.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Schema(name = "设备工作信息响应")
public class DeviceWorkingInfoResponse {

    @Schema(description = "路口ID 没创建路口表 将改成路口名字")
    private Long intersectionId;

    @Schema(description = "设备ID")
    private Long deviceId;

    @Schema(description = "设备状态: 1-在线 0-离线")
    private Integer deviceStatus;

    @Schema(description = "未创建信号机状态表 方案parent将从device改成状态表的id")
    private String controlType;

    @Schema(description = "当前周期")
    private String curPeriod;
}
