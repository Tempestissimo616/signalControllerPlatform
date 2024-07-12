package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.dto.request.PhaseControllRequest;
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
    public String createPhase(PhaseControllRequest phaseControllRequest) {
        checkPlanPhaseControlNotExists(phaseControllRequest);
        checkPlanPhaseControlPhaseNumberNotExists(phaseControllRequest);

        PlanPhaseControl planPhaseControl = modelMapper.map(phaseControllRequest, PlanPhaseControl.class);
        // 防止误传递
        planPhaseControl.setId(null);
        return planPhaseControlDbService.save(planPhaseControl) ? String.format("设备Id%s方案号%s阶段号%s:创建成功",planPhaseControl.getDeviceId(),planPhaseControl.getPlanNumber(),planPhaseControl.getPhaseNumber())
                : String.format("设备Id%s方案号%s阶段号%s:创建失败",planPhaseControl.getDeviceId(),planPhaseControl.getPlanNumber(),planPhaseControl.getPhaseNumber());
    }

    @Override
    public String updatePhase(PhaseControllRequest phaseControllRequest) {
        PlanPhaseControl planPhaseControl = planPhaseControlDbService.getByDeviceIdPlanNumPhaseNum(phaseControllRequest.getDeviceId(),phaseControllRequest.getPlanNumber(),phaseControllRequest.getPhaseNumber());
        if(planPhaseControl == null){
            throw new NotFoundException(ExceptionEnum.NOT_FOUND);
        }

        Long id = planPhaseControl.getId();
        Long deviceId = planPhaseControl.getDeviceId();
        modelMapper.map(phaseControllRequest,planPhaseControl);
        planPhaseControl.setId(id);
        planPhaseControl.setDeviceId(deviceId);

        return planPhaseControlDbService.save(planPhaseControl) ? String.format("设备Id%s方案号%s阶段号%s:创建成功",planPhaseControl.getDeviceId(),planPhaseControl.getPlanNumber(),planPhaseControl.getPhaseNumber())
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

    private void checkPlanPhaseControlNotExists(PhaseControllRequest phaseControllRequest) {
        if(deviceIntersectionPlanDbService.getByDeviceIdAndPlanNum(phaseControllRequest.getDeviceId(),phaseControllRequest.getPlanNumber())
            == null){
            throw new ConflictException(ExceptionEnum.CONFLICT_EXCEPTION);
        }
    }

    private void checkPlanPhaseControlPhaseNumberNotExists(PhaseControllRequest phaseControllRequest) {
        if(planPhaseControlDbService.getByDeviceIdPlanNumPhaseNum(phaseControllRequest.getDeviceId(),phaseControllRequest.getPlanNumber(),phaseControllRequest.getPhaseNumber())
                == null){
            throw new ConflictException(ExceptionEnum.CONFLICT_EXCEPTION);
        }
    }
}

