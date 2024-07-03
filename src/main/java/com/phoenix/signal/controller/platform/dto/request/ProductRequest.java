package com.phoenix.signal.controller.platform.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductRequest {

    @NotBlank(message = "产品名称不能为空")
    @Schema(description = "产品名称")
    private String productName;

    @NotBlank(message = "产品ID不能为空")
    @Schema(description = "产品ID")
    private Long productId;

    @NotBlank(message = "品牌/型号不能为空")
    @Schema(description = "品牌/型号")
    private String productBrand;

    @NotBlank(message = "厂商不能为空")
    @Schema(description = "厂商")
    private String producer;

    @NotBlank(message = "功能类别不能为空")
    @Schema(description = "功能类别")
    private String productType;

    @NotBlank(message = "产品数量不能为空")
    @Schema(description = "产品数量")
    private Integer productNum;
}
