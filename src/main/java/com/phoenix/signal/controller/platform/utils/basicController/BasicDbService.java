package com.phoenix.signal.controller.platform.utils.basicController;

import com.github.yulichang.base.MPJBaseService;
import com.phoenix.signal.controller.platform.model.DeviceIntersectionPlan;
import com.phoenix.signal.controller.platform.utils.baseModel.BasicModel;

import java.io.Serializable;
import java.util.List;

public interface BasicDbService<T extends BasicModel> extends MPJBaseService<T> {
    T getById(Serializable id);

    List<T> getList();

//    DeviceIntersectionPlan getByDeviceIdAndPlanNum(Long deviceId, Integer planNum);
}
