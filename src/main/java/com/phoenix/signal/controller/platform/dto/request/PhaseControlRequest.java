package com.phoenix.signal.controller.platform.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PhaseControlRequest {

    @NotNull
    @Schema(description = "设备ID")
    Long deviceId;

    @NotNull
    @Schema(description = "方案号")
    Integer planNumber;

    @NotNull
    @Schema(description = "阶段号")
    Integer phaseNumber;

    @NotNull
    @Schema(description = "阶段时长")
    Integer phaseDuration;

    @NotBlank
    @Schema(description = "绿信比")
    String greenRatio;

    @NotBlank
    @Schema(description = "出现类型")
    String phaseType;

    @NotBlank
    @Schema(description = "通行相位")
    String trafficPhase;
}
