package com.phoenix.signal.controller.platform.service;

import com.github.yulichang.base.MPJBaseService;
import com.phoenix.signal.controller.platform.dto.request.PhaseParaRequest;
import com.phoenix.signal.controller.platform.model.PhaseParameter;

import java.util.List;

public interface PhaseParameterDbService extends MPJBaseService<PhaseParameter> {
    List<PhaseParameter> getAllParasByPlan(PhaseParaRequest phaseParaRequest);

    PhaseParameter getByPlan(Long deviceId, Integer planNumber, Integer phaseNumber, Integer phaseParaNumber);
}
