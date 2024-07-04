package com.phoenix.signal.controller.platform.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Schema(name = "设备添加请求")
public class DeviceRequest {

    @NotBlank(message = "设备名称不能为空")
    @Schema(description = "设备名称")
    String deviceName;

    @NotNull(message = "设备ID不能为空")
    @Schema(description = "设备ID")
    Long deviceId;

    @NotBlank(message = "产品类型不能为空")
    @Schema(description = "产品类型")
    String productType;

    @NotBlank(message = "产品名称不能为空")
    @Schema(description = "产品名称")
    String productName;

    @NotBlank(message = "设备厂商不能为空")
    @Schema(description = "设备厂商")
    String deviceProducer;

    @NotBlank(message = "产品型号不能为空")
    @Schema(description = "产品型号")
    String productModel;

    @NotNull(message = "在线状态不能为空")
    @Schema(description = "在线状态: 1-是 0-否")
    Integer status;

    @NotNull(message = "路口ID不能为空")
    @Schema(description = "路口ID")
    Long intersectionId;



}
