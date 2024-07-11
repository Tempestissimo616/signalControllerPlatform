package com.phoenix.signal.controller.platform.api.sys;

import com.phoenix.signal.controller.platform.business.PlanManagementService;
import com.phoenix.signal.controller.platform.dto.request.PlanManagementRequest;
import com.phoenix.signal.controller.platform.dto.response.PlanManagementResponse;
import com.phoenix.signal.controller.platform.service.PlanPhaseControlDbService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/plan")
@Tag(name = "方案管理", description = "方案管理相关的api")
public class PlanManagementController {

    PlanManagementService planManagementService;

    @Operation(summary = "根据设备ID获取方案")
    @GetMapping("{deviceId}")
    public ResponseEntity<List<PlanManagementResponse>> getPlanByDeviceId(@PathVariable Long deviceId){
        return ResponseEntity.ok(planManagementService.getPlanListByDeviceId(deviceId));
    }

    @Operation(summary = "创建方案")
    @PostMapping("create")
    public ResponseEntity<String> createPlan(@RequestBody @Valid PlanManagementRequest planManagementRequest){
        return ResponseEntity.ok(planManagementService.createPlan(planManagementRequest));
    }

    @Operation(summary = "更新方案")
    @PutMapping("update")
    public ResponseEntity<String> updatePlan(@RequestBody @Valid PlanManagementRequest planManagementRequest){
        return ResponseEntity.ok(planManagementService.updatePlan(planManagementRequest));
    }

    @Operation(summary = "删除方案")
    @DeleteMapping("delete/{deviceId}/{planNumber}")
    public ResponseEntity<String> deletePlan(@PathVariable Long deviceId, @PathVariable Integer planNumber){
        return ResponseEntity.ok(planManagementService.deletePlan(deviceId, planNumber));
    }

}
