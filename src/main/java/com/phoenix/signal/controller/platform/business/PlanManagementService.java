package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.dto.request.PlanManagementRequest;
import com.phoenix.signal.controller.platform.dto.response.PlanManagementResponse;

import java.util.List;

public interface PlanManagementService {

    List<PlanManagementResponse> getPlanListByDeviceId(Long deviceId);

    String createPlan(PlanManagementRequest planManagementRequest);

    String deletePlan(Long deviceId, Integer planNumber);

    String updatePlan(PlanManagementRequest planManagementRequest);
}
