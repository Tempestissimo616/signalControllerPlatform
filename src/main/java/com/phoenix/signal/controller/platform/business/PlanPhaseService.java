package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.dto.request.PhaseControlRequest;
import com.phoenix.signal.controller.platform.dto.request.image.PhaseControlImageRequest;
import com.phoenix.signal.controller.platform.dto.response.PhaseControlResponse;

import java.io.IOException;
import java.util.List;

public interface PlanPhaseService {

    List<PhaseControlResponse> getPhasesByDeviceIdAndPlanNum(Long deviceId, Integer planNumber);

    String createPhase(PhaseControlRequest phaseControlRequest);

    String updatePhase(PhaseControlRequest phaseControlRequest);

    String deletePhase(Long deviceId, Integer planNumber, Integer phaseNumber);

    byte[] getPhaseImageData(Long deviceId, Integer planNumber, Integer phaseNumber);

    String updatePhaseImageData(PhaseControlImageRequest phaseControlImageRequest) throws IOException;

    String removePhaseImageData(Long deviceId, Integer planNumber, Integer phaseNumber) throws IOException;



}
