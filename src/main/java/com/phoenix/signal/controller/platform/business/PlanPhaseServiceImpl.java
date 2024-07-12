package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.dto.request.PhaseControlRequest;
import com.phoenix.signal.controller.platform.dto.request.PhaseParaRequest;
import com.phoenix.signal.controller.platform.dto.request.image.PhaseControlImageRequest;
import com.phoenix.signal.controller.platform.dto.response.PhaseControlResponse;
import com.phoenix.signal.controller.platform.dto.response.PhaseParaResponse;
import com.phoenix.signal.controller.platform.exception.ConflictException;
import com.phoenix.signal.controller.platform.exception.NotFoundException;
import com.phoenix.signal.controller.platform.model.PhaseParameter;
import com.phoenix.signal.controller.platform.model.PlanPhaseControl;
import com.phoenix.signal.controller.platform.service.DeviceIntersectionPlanDbService;
import com.phoenix.signal.controller.platform.service.PhaseParameterDbService;
import com.phoenix.signal.controller.platform.service.PlanPhaseControlDbService;
import com.phoenix.signal.controller.platform.type.ExceptionEnum;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    @Override
    public byte[] getPhaseImageData(Long deviceId, Integer planNumber, Integer phaseNumber) {
        return planPhaseControlDbService.getByDeviceIdPlanNumPhaseNum(deviceId,planNumber,phaseNumber).getPhaseImageData();
    }

    @Override
    public String updatePhaseImageData(PhaseControlImageRequest phaseControlImageRequest) throws IOException {
        PlanPhaseControl planPhaseControl = planPhaseControlDbService.getByDeviceIdPlanNumPhaseNum(phaseControlImageRequest.getDeviceId(),phaseControlImageRequest.getPlanNumber(),phaseControlImageRequest.getPhaseNumber());
        if(phaseControlImageRequest.getImage() != null) {
            planPhaseControl.setPhaseImageData(phaseControlImageRequest.getImage().getBytes());
        }else{
            planPhaseControl.setPhaseImageData(null);
        }

        return planPhaseControlDbService.updateById(planPhaseControl) ? String.format("设备Id%s方案号%s阶段号%s:图片修改成功",planPhaseControl.getDeviceId(),planPhaseControl.getPlanNumber(),planPhaseControl.getPhaseNumber())
                : String.format("设备Id%s方案号%s阶段号%s:图片修改失败",planPhaseControl.getDeviceId(),planPhaseControl.getPlanNumber(),planPhaseControl.getPhaseNumber());
    }

    @Override
    public String removePhaseImageData(Long deviceId, Integer planNumber, Integer phaseNumber) throws IOException {
        PhaseControlImageRequest phaseControlImageRequest = new PhaseControlImageRequest();
        phaseControlImageRequest.setImage(null);
        phaseControlImageRequest.setDeviceId(deviceId);
        phaseControlImageRequest.setPlanNumber(planNumber);
        phaseControlImageRequest.setPhaseNumber(phaseNumber);
        return updatePhaseImageData(phaseControlImageRequest);
    }



    @Override
    public List<PhaseParaResponse> getPhaseParas(PhaseParaRequest phaseParaRequest) {
        List<PhaseParameter> phaseParameterList = phaseParameterDbService.getAllParasByPlan(phaseParaRequest);
        return phaseParameterList.stream().map(phaseParameter -> modelMapper.map(phaseParameter, PhaseParaResponse.class)).toList();
    }

    @Override
    public String createPhasePara(PhaseParaRequest phaseParaRequest) {
        if(planPhaseControlDbService.getByDeviceIdPlanNumPhaseNum(phaseParaRequest.getDeviceId(),phaseParaRequest.getPlanNumber(),phaseParaRequest.getPhaseNumber()) == null)
            throw new NotFoundException(ExceptionEnum.NOT_FOUND);
        if(phaseParameterDbService.getByPlan(phaseParaRequest.getDeviceId(),phaseParaRequest.getPlanNumber(),phaseParaRequest.getPhaseNumber(),phaseParaRequest.getPhaseParaNumber()) != null)
            throw new ConflictException(ExceptionEnum.CONFLICT_EXCEPTION);

        PhaseParameter phaseParameter = new PhaseParameter();
        Long deviceId = phaseParaRequest.getDeviceId();
        modelMapper.map(phaseParaRequest,phaseParameter);
        phaseParameter.setDeviceId(deviceId);
        phaseParameter.setId(null);
        return phaseParameterDbService.save(phaseParameter) ? "相位创建成功" : "相位创建失败";
    }

    @Override
    public String updatePhasePara(PhaseParaRequest phaseParaRequest) {
        PhaseParameter phaseParameter = phaseParameterDbService.getByPlan(phaseParaRequest.getDeviceId(),phaseParaRequest.getPlanNumber(),phaseParaRequest.getPhaseNumber(),phaseParaRequest.getPhaseParaNumber());
        if(phaseParameter == null) throw new NotFoundException(ExceptionEnum.NOT_FOUND);

        Long id = phaseParameter.getId();
        Long deviceId = phaseParaRequest.getDeviceId();
        modelMapper.map(phaseParaRequest,phaseParameter);
        phaseParameter.setId(id);
        phaseParameter.setDeviceId(deviceId);
        return phaseParameterDbService.updateById(phaseParameter) ? "相位修改成功" : "相位创建失败";
    }

    @Override
    public String deletePhasePara(PhaseParaRequest phaseParaRequest) {
        PhaseParameter phaseParameter = phaseParameterDbService.getByPlan(phaseParaRequest.getDeviceId(),phaseParaRequest.getPlanNumber(),phaseParaRequest.getPhaseNumber(),phaseParaRequest.getPhaseParaNumber());
        if(phaseParameter == null) throw new NotFoundException(ExceptionEnum.NOT_FOUND);
        return phaseParameterDbService.removeById(phaseParameter) ? "相位删除成功" : "相位删除失败";
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

