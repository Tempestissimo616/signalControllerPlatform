package com.phoenix.signal.controller.platform.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.phoenix.signal.controller.platform.utils.baseModel.BasicModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BasicMapper <T extends BasicModel> extends MPJBaseMapper<T>{
}
