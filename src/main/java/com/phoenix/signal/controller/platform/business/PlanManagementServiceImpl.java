package com.phoenix.signal.controller.platform.business;

import ch.qos.logback.core.util.StringUtil;
import com.phoenix.signal.controller.platform.dto.request.PlanManagementRequest;
import com.phoenix.signal.controller.platform.dto.response.PlanManagementResponse;
import com.phoenix.signal.controller.platform.exception.ConflictException;
import com.phoenix.signal.controller.platform.exception.NotFoundException;
import com.phoenix.signal.controller.platform.model.DeviceIntersectionPlan;
import com.phoenix.signal.controller.platform.service.DeviceDbService;
import com.phoenix.signal.controller.platform.service.DeviceIntersectionPlanDbService;
import com.phoenix.signal.controller.platform.service.PlanPhaseControlDbService;
import com.phoenix.signal.controller.platform.type.ExceptionEnum;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PlanManagementServiceImpl implements PlanManagementService{

    DeviceIntersectionPlanDbService deviceIntersectionPlanDbService;
    PlanPhaseControlDbService planPhaseControlDbService;
    DeviceDbService deviceDbService;
    ModelMapper modelMapper;

    @Override
    public List<PlanManagementResponse> getPlanListByDeviceId(Long deviceId) {

        List<DeviceIntersectionPlan> planList = deviceIntersectionPlanDbService.getAllByDeviceId(deviceId);

        List<PlanManagementResponse> responsesList = planList.stream()
                .map(devicePlan -> modelMapper.map(devicePlan,PlanManagementResponse.class))
                .toList();

        return responsesList;
    }

    @Override
    public String createPlan(PlanManagementRequest planManagementRequest) {

        checkDeviceIdExists(planManagementRequest.getParentPlanDeviceId());

        DeviceIntersectionPlan deviceIntersectionPlan = modelMapper.map(planManagementRequest,DeviceIntersectionPlan.class);
        return deviceIntersectionPlanDbService.save(deviceIntersectionPlan) ?
                String.format("设备%s%s:创建成功",deviceIntersectionPlan.getParentPlanDeviceId(),deviceIntersectionPlan.getPlanNumber())
                : String.format("设备%s%s:创建失败",deviceIntersectionPlan.getParentPlanDeviceId(),deviceIntersectionPlan.getPlanNumber());
    }

    @Override
    public String deletePlan(Long deviceId, Long planId) {
        return "";
    }

    @Override
    public String updatePlan(PlanManagementRequest planManagementRequest) {
        return "";
    }

    private void checkDeviceIdExists(Long deviceId){
        if(deviceDbService.countByProductId(deviceId) == 0){
            throw new NotFoundException(ExceptionEnum.NOT_FOUND);
        }
    }
}
