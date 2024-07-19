package com.phoenix.signal.controller.platform.utils.baseModel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BasicModel implements Serializable {

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    private Long Id;

}
