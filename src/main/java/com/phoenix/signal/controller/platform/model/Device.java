package com.phoenix.signal.controller.platform.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Data
@Schema(description = "设备库")
@TableName("t_device")
public class Device {

    @Schema(description = "序号")
    @TableId(value = "id",type = IdType.AUTO)
    Long id;

    @Schema(description = "设备名称")
    String deviceName;

    @Schema(description = "设备ID")
    Long deviceId;

    @Schema(description = "产品类型")
    String productType;

    @Schema(description = "产品名称")
    String productName;

    @Schema(description = "设备厂商")
    String deviceProducer;

    @Schema(description = "产品型号")
    String productModel;

    @Schema(description = "在线状态: 1-是 0-否")
    Integer status;

    @Schema(description = "路口ID")
    Long intersectionId;
}
