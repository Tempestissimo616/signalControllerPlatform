package com.phoenix.signal.controller.platform.api.sys;

import com.phoenix.signal.controller.platform.business.PlanPhaseService;
import com.phoenix.signal.controller.platform.business.PlanPhaseServiceImpl;
import com.phoenix.signal.controller.platform.dto.request.PhaseControllRequest;
import com.phoenix.signal.controller.platform.dto.response.PhaseControlResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/plan/phase")
@Tag(name = "方案相位控制", description = "方案相位控制相关的api")
public class PlanPhaseController {

    PlanPhaseService planPhaseService;

    @Operation(summary = "根据设备ID和方案号获取方案阶段")
    @GetMapping("{deviceId}/{planNumber}")
    public ResponseEntity<List<PhaseControlResponse>> getPhaseByDeviceIdAndPlanNum(@PathVariable Long deviceId, @PathVariable Integer planNumber){
        return ResponseEntity.ok(planPhaseService.getPhasesByDeviceIdAndPlanNum(deviceId,planNumber));
    }

    @Operation(summary = "创建方案阶段")
    @PostMapping("/create")
    public ResponseEntity<String> createPhase(PhaseControllRequest phaseControllRequestList) {
        return ResponseEntity.ok(planPhaseService.createPhase(phaseControllRequestList));
    }

    @Operation(summary = "更新方案阶段")
    @PutMapping("/update")
    public ResponseEntity<String> updatePhase(PhaseControllRequest phaseControllRequest) {
        return ResponseEntity.ok(planPhaseService.updatePhase(phaseControllRequest));
    }

    @Operation(summary = "删除方案阶段")
    @DeleteMapping("/delete/{deviceId}/{planNumber}/{phaseNumber}")
    public ResponseEntity<String> deletePhase(@PathVariable Long deviceId,@PathVariable Integer planNumber,@PathVariable Integer phaseNumber) {
        return ResponseEntity.ok(planPhaseService.deletePhase(deviceId,planNumber,phaseNumber));
    }


}
