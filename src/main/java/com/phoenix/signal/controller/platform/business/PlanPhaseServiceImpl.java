package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.dto.request.PhaseControlRequest;
import com.phoenix.signal.controller.platform.dto.response.PhaseControlResponse;
import com.phoenix.signal.controller.platform.exception.ConflictException;
import com.phoenix.signal.controller.platform.exception.NotFoundException;
import com.phoenix.signal.controller.platform.model.PlanPhaseControl;
import com.phoenix.signal.controller.platform.service.DeviceIntersectionPlanDbService;
import com.phoenix.signal.controller.platform.service.PhaseParameterDbService;
import com.phoenix.signal.controller.platform.service.PlanPhaseControlDbService;
import com.phoenix.signal.controller.platform.type.ExceptionEnum;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlanPhaseServiceImpl implements PlanPhaseService{

    PlanPhaseControlDbService planPhaseControlDbService;
    PhaseParameterDbService phaseParameterDbService;
    DeviceIntersectionPlanDbService deviceIntersectionPlanDbService;
    ModelMapper modelMapper;

    @Override
    public List<PhaseControlResponse> getPhasesByDeviceIdAndPlanNum(Long deviceId, Integer planNumber) {

        List<PlanPhaseControl> phaseControlList = planPhaseControlDbService.getAllByDeviceIdAndPlanNum(deviceId, planNumber);
        return phaseControlList.stream().map(planPhaseControl -> modelMapper.map(planPhaseControl, PhaseControlResponse.class)).toList();
    }

    @Override
    public String createPhase(PhaseControlRequest phaseControlRequest) {
        checkPlanPhaseControlExists(phaseControlRequest);
        checkPlanPhaseControlPhaseNumberNotExists(phaseControlRequest);

        PlanPhaseControl planPhaseControl = new PlanPhaseControl();
        convertDtoToPlanPhaseControl(phaseControlRequest,planPhaseControl);
        return planPhaseControlDbService.save(planPhaseControl) ? String.format("设备Id%s方案号%s阶段号%s:创建成功",planPhaseControl.getDeviceId(),planPhaseControl.getPlanNumber(),planPhaseControl.getPhaseNumber())
                : String.format("设备Id%s方案号%s阶段号%s:创建失败",planPhaseControl.getDeviceId(),planPhaseControl.getPlanNumber(),planPhaseControl.getPhaseNumber());
    }

    @Override
    public String updatePhase(PhaseControlRequest phaseControlRequest) {
        PlanPhaseControl planPhaseControl = planPhaseControlDbService.getByDeviceIdPlanNumPhaseNum(phaseControlRequest.getDeviceId(),phaseControlRequest.getPlanNumber(),phaseControlRequest.getPhaseNumber());
        if(planPhaseControl == null){
            throw new NotFoundException(ExceptionEnum.NOT_FOUND);
        }

        convertDtoToPlanPhaseControl(phaseControlRequest,planPhaseControl);

        return planPhaseControlDbService.updateById(planPhaseControl) ? String.format("设备Id%s方案号%s阶段号%s:创建成功",planPhaseControl.getDeviceId(),planPhaseControl.getPlanNumber(),planPhaseControl.getPhaseNumber())
                : String.format("设备Id%s方案号%s阶段号%s:创建失败",planPhaseControl.getDeviceId(),planPhaseControl.getPlanNumber(),planPhaseControl.getPhaseNumber());
    }
    @Override
    public String deletePhase(Long deviceId, Integer planNumber, Integer phaseNumber) {
        PlanPhaseControl planPhaseControl = planPhaseControlDbService.getByDeviceIdPlanNumPhaseNum(deviceId,planNumber,phaseNumber);
        if (planPhaseControl == null) throw new NotFoundException(ExceptionEnum.NOT_FOUND);

        return planPhaseControlDbService.removeById(planPhaseControl)
                ? String.format("设备Id%s方案号%s阶段号%s:删除成功",planPhaseControl.getDeviceId(),planPhaseControl.getPlanNumber(),planPhaseControl.getPhaseNumber())
                : String.format("设备Id%s方案号%s阶段号%s:删除失败",planPhaseControl.getDeviceId(),planPhaseControl.getPlanNumber(),planPhaseControl.getPhaseNumber());
    }

    private void checkPlanPhaseControlExists(PhaseControlRequest phaseControlRequest) {
        if(deviceIntersectionPlanDbService.getByDeviceIdAndPlanNum(phaseControlRequest.getDeviceId(),phaseControlRequest.getPlanNumber())
            == null){
            throw new NotFoundException(ExceptionEnum.NOT_FOUND);
        }
    }

    private void checkPlanPhaseControlPhaseNumberNotExists(PhaseControlRequest phaseControlRequest) {
        if(planPhaseControlDbService.getByDeviceIdPlanNumPhaseNum(phaseControlRequest.getDeviceId(),phaseControlRequest.getPlanNumber(),phaseControlRequest.getPhaseNumber())
                != null){
            throw new ConflictException(ExceptionEnum.CONFLICT_EXCEPTION);
        }
    }

    private void convertDtoToPlanPhaseControl(PhaseControlRequest phaseControlRequest, PlanPhaseControl planPhaseControl){
        planPhaseControl.setDeviceId(phaseControlRequest.getDeviceId());
        planPhaseControl.setPlanNumber(phaseControlRequest.getPlanNumber());
        planPhaseControl.setPhaseNumber(phaseControlRequest.getPhaseNumber());
        planPhaseControl.setGreenRatio(phaseControlRequest.getGreenRatio());
        planPhaseControl.setPhaseType(phaseControlRequest.getPhaseType());
        planPhaseControl.setPhaseDuration(phaseControlRequest.getPhaseDuration());
        planPhaseControl.setTrafficPhase(phaseControlRequest.getTrafficPhase());
    }
}

