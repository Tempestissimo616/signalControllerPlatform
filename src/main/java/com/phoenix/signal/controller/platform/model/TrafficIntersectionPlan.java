package com.phoenix.signal.controller.platform.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "信号灯路口方案")
@TableName("t_intersection_plan")
public class TrafficIntersectionPlan {

    @Schema(description = "序号")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @Schema(description = "方案号")
    private Long planNumber;

    @Schema(description = "相位差")
    private Long phaseDifference;

    @Schema(description = "协调阶段号 默认为0:不协调 ")
    private Long coordinatedPhaseNumber;

    @Schema(description = "周期")
    private Integer period;

    @Schema(description = "备注")
    private String note;

    @Schema(description = "阶段管理Id")
    private Long planPhaseControlId;

    @Schema(description = "阶段号List")
    List<PlanPhaseControl> phaseList;
}
