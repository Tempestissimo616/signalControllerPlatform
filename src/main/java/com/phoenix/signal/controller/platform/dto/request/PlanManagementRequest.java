package com.phoenix.signal.controller.platform.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PlanManagementRequest {

    @NotNull
    @Schema(description = "方案管理 设备ID")
    private Long parentPlanDeviceId;

    @NotNull
    @Schema(description = "方案号")
    private Integer planNumber;

    @NotNull
    @Schema(description = "相位差")
    private Integer phaseDifference;

    @NotNull
    @Schema(description = "协调阶段号 默认为0:不协调 ")
    private Integer coordinatedPhaseNumber;

    @NotNull
    @Schema(description = "周期")
    private Integer period;

    @Schema(description = "备注")
    private String note;

}
