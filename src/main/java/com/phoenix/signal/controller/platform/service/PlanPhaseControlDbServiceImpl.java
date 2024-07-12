package com.phoenix.signal.controller.platform.service;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.phoenix.signal.controller.platform.mapper.PlanPhaseControlMapper;
import com.phoenix.signal.controller.platform.model.PlanPhaseControl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlanPhaseControlDbServiceImpl extends MPJBaseServiceImpl<PlanPhaseControlMapper, PlanPhaseControl> implements PlanPhaseControlDbService{

    PlanPhaseControlMapper planPhaseControlMapper;

    @Override
    public PlanPhaseControl getByDeviceIdPlanNumPhaseNum(Long deviceId, Integer planNumber, Integer phaseNumber) {
        return planPhaseControlMapper.selectOne(new MPJLambdaWrapper<PlanPhaseControl>()
                .eq(PlanPhaseControl::getDeviceId,deviceId)
                .eq(PlanPhaseControl::getPlanNumber,planNumber)
                .eq(PlanPhaseControl::getPhaseNumber,phaseNumber));
    }

    @Override
    public List<PlanPhaseControl> getAllByDeviceIdAndPlanNum(Long deviceId, Integer planNumber) {
        return planPhaseControlMapper.selectList(new MPJLambdaWrapper<PlanPhaseControl>()
                .eq(PlanPhaseControl::getDeviceId, deviceId)
                .eq(PlanPhaseControl::getPlanNumber, planNumber)
                .orderByAsc(PlanPhaseControl::getPhaseNumber));
    }
}
