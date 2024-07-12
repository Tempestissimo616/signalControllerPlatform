package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.dto.request.PhaseControlRequest;
import com.phoenix.signal.controller.platform.dto.response.PhaseControlResponse;

import java.util.List;

public interface PlanPhaseService {

    List<PhaseControlResponse> getPhasesByDeviceIdAndPlanNum(Long deviceId, Integer planNumber);

    String createPhase(PhaseControlRequest phaseControlRequest);

    String updatePhase(PhaseControlRequest phaseControlRequest);

    String deletePhase(Long deviceId, Integer planNumber, Integer phaseNumber);


}
