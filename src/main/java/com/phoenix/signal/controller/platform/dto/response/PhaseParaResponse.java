package com.phoenix.signal.controller.platform.dto.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhaseParaResponse {

    @Schema(description = "设备ID")
    Long deviceId;

    @Schema(description = "方案号")
    Integer planNumber;

    @Schema(description = "相位号")
    Integer phaseNumber;

    @Schema(description = "相位参数号")
    Integer phaseParaNumber;

    @Schema(description = "相位名称")
    String phaseName;

    @Schema(description = "最小绿灯时长")
    Integer minGreenDuration;

    @Schema(description = "最大绿灯时长")
    Integer maxGreenDuration;

    @Schema(description = "黄灯时长")
    Integer yellowDuration;

    @Schema(description = "全红时长")
    Integer allRedDuration;
}
