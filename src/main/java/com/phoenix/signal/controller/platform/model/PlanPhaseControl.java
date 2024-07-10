package com.phoenix.signal.controller.platform.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.awt.*;

@Data
@Schema(description = "方案阶段控制")
@TableName("t_phase_control")
public class PlanPhaseControl {

    @Schema(description = "序号")
    @TableId(value = "id",type = IdType.AUTO)
    Long id;

    @Schema(description = "方案Id")
    Long parentPlanId;

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

    @Schema(description = "最小绿灯时长")
    Integer minGreenDuration;

    @Schema(description = "最大绿灯时长")
    Integer maxGreenDuration;

    @Schema(description = "黄灯时长")
    Integer yellowDuration;

    @Schema(description = "全红时长")
    Integer allRedDuration;

    @Schema(description = "路口图片")
    private byte[] phaseImageData;
}
