package com.phoenix.signal.controller.platform.service;

import com.github.yulichang.base.MPJBaseService;
import com.phoenix.signal.controller.platform.model.PlanPhaseControl;

import java.util.List;

public interface PlanPhaseControlDbService extends MPJBaseService<PlanPhaseControl> {
    PlanPhaseControl getByDeviceIdPlanNumPhaseNum(Long deviceId, Integer planNumber, Integer phaseNumber);

    List<PlanPhaseControl> getAllByDeviceIdAndPlanNum(Long deviceId, Integer planNumber);


}
