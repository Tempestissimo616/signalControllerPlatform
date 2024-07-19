package com.phoenix.signal.controller.platform.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.phoenix.signal.controller.platform.utils.baseModel.BasicModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
@TableName("t_intersection")
public class Intersection extends BasicModel {

}
