package com.phoenix.signal.controller.platform.api.sys;

import com.phoenix.signal.controller.platform.business.PlanPhaseService;
import com.phoenix.signal.controller.platform.business.PlanPhaseServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/plan/phase")
@Tag(name = "方案相位控制", description = "方案相位控制相关的api")
public class PlanPhaseController {

    PlanPhaseService planPhaseService;


}
