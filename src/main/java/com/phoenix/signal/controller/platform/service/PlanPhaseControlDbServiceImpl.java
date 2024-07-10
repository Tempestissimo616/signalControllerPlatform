package com.phoenix.signal.controller.platform.service;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.phoenix.signal.controller.platform.mapper.PlanPhaseControlMapper;
import com.phoenix.signal.controller.platform.model.PlanPhaseControl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlanPhaseControlDbServiceImpl extends MPJBaseServiceImpl<PlanPhaseControlMapper, PlanPhaseControl> implements PlanPhaseControlDbService{

    PlanPhaseControlMapper planPhaseControlMapper;
    ModelMapper modelMapper;
}
