package com.phoenix.signal.controller.platform.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "设备产品库")
public class OriginalProduct {

    @Schema(description = "产品名称")
    private String productName;

    @Schema(description = "产品ID")
    private Long productId;

    @Schema(description = "品牌/型号")
    private String productBrand;

    @Schema(description = "厂商")
    private String producer;

    @Schema(description = "功能类别")
    private String productType;

    @Schema(description = "产品数量")
    private Integer productNum;
}
