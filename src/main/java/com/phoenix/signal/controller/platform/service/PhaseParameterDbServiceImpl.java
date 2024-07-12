package com.phoenix.signal.controller.platform.service;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.phoenix.signal.controller.platform.dto.request.PhaseParaRequest;
import com.phoenix.signal.controller.platform.mapper.PhaseParameterMapper;
import com.phoenix.signal.controller.platform.model.PhaseParameter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PhaseParameterDbServiceImpl extends MPJBaseServiceImpl<PhaseParameterMapper, PhaseParameter> implements PhaseParameterDbService{

    PhaseParameterMapper phaseParameterMapper;

    @Override
    public List<PhaseParameter> getAllParasByPlan(PhaseParaRequest phaseParaRequest) {
        return phaseParameterMapper.selectList(new MPJLambdaWrapper<PhaseParameter>()
                .eq(PhaseParameter::getDeviceId,phaseParaRequest.getDeviceId())
                .eq(PhaseParameter::getPlanNumber,phaseParaRequest.getPlanNumber())
                .eq(PhaseParameter::getPhaseNumber,phaseParaRequest.getPhaseNumber())
                .orderByAsc(PhaseParameter::getPhaseParaNumber));
    }

    @Override
    public PhaseParameter getByPlan(Long deviceId, Integer planNumber, Integer phaseNumber, Integer phaseParaNumber) {
        return phaseParameterMapper.selectOne(new MPJLambdaWrapper<PhaseParameter>()
                .eq(PhaseParameter::getDeviceId,deviceId)
                .eq(PhaseParameter::getPlanNumber,planNumber)
                .eq(PhaseParameter::getPhaseNumber,phaseNumber)
                .eq(PhaseParameter::getPhaseParaNumber,phaseParaNumber));
    }
}
