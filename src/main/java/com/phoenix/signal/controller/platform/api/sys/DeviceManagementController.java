package com.phoenix.signal.controller.platform.api.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phoenix.signal.controller.platform.business.DeviceService;
import com.phoenix.signal.controller.platform.business.OriginalProductService;
import com.phoenix.signal.controller.platform.dto.request.DeviceRequest;
import com.phoenix.signal.controller.platform.dto.request.PageRequest;
import com.phoenix.signal.controller.platform.dto.request.ProductRequest;
import com.phoenix.signal.controller.platform.model.Device;
import com.phoenix.signal.controller.platform.model.OriginalProduct;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/device-management")
@Tag(name = "设备管理", description = "设备数据库相关的api")
public class DeviceManagementController {

    OriginalProductService originalProductService;
    DeviceService deviceService;

    @Tag(name = "产品表")
    @Operation(summary = "获取所有产品出厂信息")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/product/get-all")
    public ResponseEntity<List<OriginalProduct>> getAllOriginalProduct() {
        return ResponseEntity.ok(originalProductService.getAllOriginalProduct());
    }

    @Tag(name = "产品表")
    @Operation(summary = "分页获取所有产品出厂信息")
    @Parameters({
        @Parameter(name = "page", description = "页码", in = ParameterIn.QUERY, example = "1"),
        @Parameter(name = "size", description = "每页数量", in = ParameterIn.QUERY, example = "10")
    })
    @GetMapping("/product/page")
    public ResponseEntity<List<OriginalProduct>> pageOriginalProduct(@RequestBody PageRequest pageRequest){
        Page page = new Page(pageRequest.getIndex(),pageRequest.getSize());
        return ResponseEntity.ok(originalProductService.page(page).getRecords());
    }

    @Tag(name = "产品表")
    @Operation(summary = "产品注册")
    @ApiResponses(
            @ApiResponse(responseCode = "201", description = "CREATED")
    )
    @PostMapping("/product/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest productRequest){
        return new ResponseEntity<>(originalProductService.create(productRequest), HttpStatus.CREATED);
    }


    @Tag(name = "设备表")
    @Operation(summary = "获取所有设备信息")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/device/get-all")
    public ResponseEntity<List<Device>> getAllDevice() {
        return ResponseEntity.ok(deviceService.getAllDevice());
    }

    @Tag(name = "设备表")
    @Operation(summary = "设备注册")
    @ApiResponses(
            @ApiResponse(responseCode = "201", description = "CREATED")
    )
    @PostMapping("/device/create")
    public ResponseEntity<String> createDevice(@RequestBody DeviceRequest deviceRequest){
        return new ResponseEntity<>(deviceService.createDevice(deviceRequest),HttpStatus.CREATED);
    }
}
