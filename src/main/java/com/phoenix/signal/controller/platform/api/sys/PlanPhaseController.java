package com.phoenix.signal.controller.platform.api.sys;

import com.phoenix.signal.controller.platform.business.PlanPhaseService;
import com.phoenix.signal.controller.platform.business.PlanPhaseServiceImpl;
import com.phoenix.signal.controller.platform.dto.request.PhaseControlRequest;
import com.phoenix.signal.controller.platform.dto.request.PhaseParaRequest;
import com.phoenix.signal.controller.platform.dto.request.image.PhaseControlImageRequest;
import com.phoenix.signal.controller.platform.dto.response.PhaseControlResponse;
import com.phoenix.signal.controller.platform.dto.response.PhaseParaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public ResponseEntity<String> createPhase(@RequestBody @Valid PhaseControlRequest phaseControlRequest) {
        return ResponseEntity.ok(planPhaseService.createPhase(phaseControlRequest));
    }

    @Operation(summary = "更新方案阶段")
    @PutMapping("/update")
    public ResponseEntity<String> updatePhase(@RequestBody @Valid PhaseControlRequest phaseControlRequest) {
        return ResponseEntity.ok(planPhaseService.updatePhase(phaseControlRequest));
    }

    @Operation(summary = "删除方案阶段")
    @DeleteMapping("/delete/{deviceId}/{planNumber}/{phaseNumber}")
    public ResponseEntity<String> deletePhase(@PathVariable Long deviceId,@PathVariable Integer planNumber,@PathVariable Integer phaseNumber) {
        return ResponseEntity.ok(planPhaseService.deletePhase(deviceId,planNumber,phaseNumber));
    }

    @Operation(summary = "获取方案阶段图片")
    @GetMapping("/image/{deviceId}/{planNumber}/{phaseNumber}")
    public ResponseEntity<byte[]> getPhaseImageData(@PathVariable Long deviceId,@PathVariable Integer planNumber,@PathVariable Integer phaseNumber){
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(planPhaseService.getPhaseImageData(deviceId,planNumber,phaseNumber));
    }

    @Operation(summary = "更新方案阶段图片")
    @Parameters({
            @Parameter(name = "deviceId", description = "设备ID", required = true, in = ParameterIn.PATH),
            @Parameter(name = "planNumber", description = "方案号", required = true, in = ParameterIn.PATH),
            @Parameter(name = "phaseNumber", description = "阶段号", required = true, in = ParameterIn.PATH),
            @Parameter(name = "image", description = "相位图片MultiPartFile", required = true, in = ParameterIn.PATH)
    })
    @PutMapping("/image/update")
    public ResponseEntity<String> updatePhaseImageData(@ModelAttribute PhaseControlImageRequest phaseControlImageRequest) throws IOException {
        return ResponseEntity.ok(planPhaseService.updatePhaseImageData(phaseControlImageRequest));
    }

    @Operation(summary = "删除方案阶段图片")
    @DeleteMapping("/image/delete/{deviceId}/{planNumber}/{phaseNumber}")
    public ResponseEntity<String> removePhaseImageData(@PathVariable Long deviceId,@PathVariable Integer planNumber,@PathVariable Integer phaseNumber) throws IOException {
        return ResponseEntity.ok(planPhaseService.removePhaseImageData(deviceId,planNumber,phaseNumber));
    }



    @Operation(summary = "获取方案相位参数")
    @GetMapping("/para")
    public ResponseEntity<List<PhaseParaResponse>> getPhaseParas(@RequestBody @Valid PhaseParaRequest phaseParaRequest){
        return ResponseEntity.ok(planPhaseService.getPhaseParas(phaseParaRequest));
    }

    @Operation(summary = "创建方案相位参数")
    @PostMapping("/para/create")
    public ResponseEntity<String> createPhasePara(@RequestBody @Valid PhaseParaRequest phaseParaRequest){
        return ResponseEntity.ok(planPhaseService.createPhasePara(phaseParaRequest));
    }

    @Operation(summary = "更新方案相位参数")
    @PutMapping("/para/update")
    public ResponseEntity<String> updatePhasePara(@RequestBody @Valid PhaseParaRequest phaseParaRequest){
        return ResponseEntity.ok(planPhaseService.updatePhasePara(phaseParaRequest));
    }

    @Operation(summary = "删除方案相位参数")
    @DeleteMapping("/para/delete")
    public ResponseEntity<String> deletePhasePara(@RequestBody @Valid PhaseParaRequest phaseParaRequest){
        return ResponseEntity.ok(planPhaseService.deletePhasePara(phaseParaRequest));
    }


}
