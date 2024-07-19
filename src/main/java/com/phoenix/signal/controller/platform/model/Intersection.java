package com.phoenix.signal.controller.platform.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.phoenix.signal.controller.platform.utils.baseModel.BasicModel;
import com.phoenix.signal.controller.platform.utils.baseModel.BasicStandardModel;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "路口")
@TableName("t_intersection")
public class Intersection extends BasicStandardModel {

    private final static long serialVersionUID = 1L;

    @Schema(description = "路口名称")
    private String intersectionName;

    @Schema(description = "路口类型")
    private String intersectionType;

    @Schema(description = "经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    private BigDecimal latitude;

    @Schema(description = "行政区域")
    private String administrativeRegion;

    @Schema(description = "路口等级")
    private Integer intersectionLevel;
}
