package com.phoenix.signal.controller.platform.service;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.phoenix.signal.controller.platform.mapper.PhaseParameterMapper;
import com.phoenix.signal.controller.platform.model.PhaseParameter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PhaseParameterDbServiceImpl extends MPJBaseServiceImpl<PhaseParameterMapper, PhaseParameter> implements PhaseParameterDbService{

    PhaseParameterDbService phaseParameterDbService;
}
