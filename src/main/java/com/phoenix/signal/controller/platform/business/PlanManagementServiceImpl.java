package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.dto.request.PlanManagementRequest;
import com.phoenix.signal.controller.platform.dto.response.PlanManagementResponse;
import com.phoenix.signal.controller.platform.exception.ConflictException;
import com.phoenix.signal.controller.platform.exception.NotFoundException;
import com.phoenix.signal.controller.platform.model.Device;
import com.phoenix.signal.controller.platform.model.DeviceIntersectionPlan;
import com.phoenix.signal.controller.platform.service.DeviceDbService;
import com.phoenix.signal.controller.platform.service.DeviceIntersectionPlanDbService;
import com.phoenix.signal.controller.platform.service.PlanPhaseControlDbService;
import com.phoenix.signal.controller.platform.type.ExceptionEnum;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        checkDevicePlanNumNotExists(planManagementRequest.getParentPlanDeviceId(), planManagementRequest.getPlanNumber());

        DeviceIntersectionPlan deviceIntersectionPlan = modelMapper.map(planManagementRequest,DeviceIntersectionPlan.class);
        //modelMapper会错误识别Id,并把deviceId复制给主键Id
        deviceIntersectionPlan.setId(null);

        return deviceIntersectionPlanDbService.save(deviceIntersectionPlan) ?
                String.format("设备%s方案%s:创建成功",deviceIntersectionPlan.getParentPlanDeviceId(),deviceIntersectionPlan.getPlanNumber())
                : String.format("设备%s方案%s:创建失败",deviceIntersectionPlan.getParentPlanDeviceId(),deviceIntersectionPlan.getPlanNumber());
    }

    @Override
    public String deletePlan(Long deviceId, Integer planNumber) {

        DeviceIntersectionPlan deviceIntersectionPlan = deviceIntersectionPlanDbService.getByDeviceIdAndPlanNum(deviceId, planNumber);
        if(deviceIntersectionPlan == null) throw new NotFoundException(ExceptionEnum.NOT_FOUND);

        if(deviceIntersectionPlanDbService.removeById(deviceIntersectionPlan.getId())){
            return String.format("设备%s方案%s:删除成功",deviceIntersectionPlan.getParentPlanDeviceId(),deviceIntersectionPlan.getPlanNumber());
        }
        return String.format("设备%s方案%s:删除失败",deviceIntersectionPlan.getParentPlanDeviceId(),deviceIntersectionPlan.getPlanNumber());
    }

    @Override
    public String updatePlan(PlanManagementRequest planManagementRequest) {

        DeviceIntersectionPlan deviceIntersectionPlan = deviceIntersectionPlanDbService.getByDeviceIdAndPlanNum(planManagementRequest.getParentPlanDeviceId(),planManagementRequest.getPlanNumber());
        if (deviceIntersectionPlan == null) throw new NotFoundException(ExceptionEnum.NOT_FOUND);

        Long primaryId = deviceIntersectionPlan.getId();
        modelMapper.map(planManagementRequest, deviceIntersectionPlan);
        //防止modelMapper Id误传
        deviceIntersectionPlan.setId(primaryId);
        deviceIntersectionPlan.setParentPlanDeviceId(planManagementRequest.getParentPlanDeviceId());
        deviceIntersectionPlan.setPlanNumber(planManagementRequest.getPlanNumber());

        return deviceIntersectionPlanDbService.updateById(deviceIntersectionPlan)
                ? String.format("设备%s方案%s:更新成功",deviceIntersectionPlan.getParentPlanDeviceId(),deviceIntersectionPlan.getPlanNumber())
                : String.format("设备%s方案%s:更新失败",deviceIntersectionPlan.getParentPlanDeviceId(),deviceIntersectionPlan.getPlanNumber());
    }


    private void checkDeviceIdExists(Long deviceId){
        if(deviceDbService.countByProductId(deviceId) == 0){
            throw new NotFoundException(ExceptionEnum.NOT_FOUND);
        }
    }

    private void checkDevicePlanNumNotExists(Long deviceId, Integer planNumber){
        DeviceIntersectionPlan deviceIntersectionPlan = deviceIntersectionPlanDbService.getByDeviceIdAndPlanNum(deviceId,planNumber);
        if(deviceIntersectionPlan != null){
            throw new ConflictException(ExceptionEnum.CONFLICT_EXCEPTION);
        }
    }
}
