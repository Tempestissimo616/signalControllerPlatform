package com.phoenix.signal.controller.platform.utils.baseModel;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public abstract class BasicStandardModel extends BasicModel{

    @Schema(description = "创建人")
    private Long createdBy;

    @Schema(description = "更新人")
    private Long updatedBy;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;
}
