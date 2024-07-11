package com.phoenix.signal.controller.platform.dto.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PlanManagementResponse {

    @Schema(description = "方案号")
    private Integer planNumber;

    @Schema(description = "相位差")
    private Integer phaseDifference;

    @Schema(description = "协调阶段号 默认为0:不协调 ")
    private Integer coordinatedPhaseNumber;

    @Schema(description = "周期")
    private Integer period;

    @Schema(description = "备注")
    private String note;
}
