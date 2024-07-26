package com.phoenix.signal.controller.platform.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(name = "路口添加请求")
public class IntersectionRequest {

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

    @Schema(description = "创建人")
    private Long createdBy;

    @Schema(description = "更新人")
    private Long updatedBy;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;
}
