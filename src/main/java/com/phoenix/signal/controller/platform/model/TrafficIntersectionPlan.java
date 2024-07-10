package com.phoenix.signal.controller.platform.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Schema(description = "信号灯路口方案")
@TableName("t_intersection_plan")
public class TrafficIntersectionPlan {

    @Schema(description = "序号")
    @TableId(value = "id",type = IdType.AUTO)
    private Long Id;

    @Schema(description = "方案管理 设备ID")
    private Long parentPlanDeviceId;

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
