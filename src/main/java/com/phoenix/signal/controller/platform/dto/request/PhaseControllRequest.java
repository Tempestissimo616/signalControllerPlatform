package com.phoenix.signal.controller.platform.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhaseControllRequest {
    @Schema(description = "设备ID")
    Long deviceId;

    @Schema(description = "方案号")
    Integer planNumber;

    @Schema(description = "阶段号")
    Integer phaseNumber;

    @Schema(description = "阶段时长")
    Integer phaseDuration;

    @Schema(description = "绿信比")
    String greenRatio;

    @Schema(description = "出现类型")
    String phaseType;

    @Schema(description = "通行相位")
    String trafficPhase;
}
