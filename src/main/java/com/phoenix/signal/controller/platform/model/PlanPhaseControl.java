package com.phoenix.signal.controller.platform.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "方案阶段控制")
@TableName("t_phase_control")
public class PlanPhaseControl {

    @Schema(description = "序号")
    @TableId(value = "id",type = IdType.INPUT)
    Long parentPlanId;

    @Schema(description = "阶段号")
    Integer phaseNumber;

    @Schema(description = "阶段时长")
    Integer phaseDuration;

    @Schema(description = "绿信比")
    String phaseType;

    @Schema(description = "通行相位")
    String phaseName;
}
