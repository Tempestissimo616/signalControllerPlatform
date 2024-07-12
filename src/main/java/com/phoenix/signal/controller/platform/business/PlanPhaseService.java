package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.dto.request.PhaseControllRequest;
import com.phoenix.signal.controller.platform.dto.response.PhaseControlResponse;

import java.util.List;

public interface PlanPhaseService {

    List<PhaseControlResponse> getPhasesByDeviceIdAndPlanNum(Long deviceId, Integer planNumber);

    String createPhase(PhaseControllRequest phaseControllRequest);

    String updatePhase(PhaseControllRequest phaseControllRequest);

    String deletePhase(Long deviceId, Integer planNumber, Integer phaseNumber);


}
