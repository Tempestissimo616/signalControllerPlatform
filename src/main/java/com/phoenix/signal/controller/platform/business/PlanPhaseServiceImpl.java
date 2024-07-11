package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.service.PhaseParameterDbService;
import com.phoenix.signal.controller.platform.service.PlanPhaseControlDbService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlanPhaseServiceImpl implements PlanPhaseService{

    PlanPhaseControlDbService planPhaseControlDbService;
    PhaseParameterDbService phaseParameterDbService;
    ModelMapper modelMapper;

}
