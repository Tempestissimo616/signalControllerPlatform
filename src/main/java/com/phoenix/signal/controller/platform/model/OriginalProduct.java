package com.phoenix.signal.controller.platform.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.context.annotation.Primary;

@Data
@Schema(description = "设备产品库")
@TableName("t_original_product")
public class OriginalProduct {

    @Schema(description = "产品名称")
    @TableField("product_name")
    private String productName;

    @Schema(description = "产品ID")
    @TableId(value = "product_id",type = IdType.INPUT)
    private Long productId;

    @Schema(description = "品牌/型号")
    @TableField("product_brand")
    private String productBrand;

    @Schema(description = "厂商")
    @TableField("producer")
    private String producer;

    @Schema(description = "功能类别")
    @TableField("product_type")
    private String productType;

    @Schema(description = "产品数量")
    @TableField("product_num")
    private Integer productNum;
}
