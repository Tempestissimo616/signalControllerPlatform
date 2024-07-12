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
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/device-management")
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
    public ResponseEntity<List<OriginalProduct>> pageOriginalProduct(@RequestBody @Valid PageRequest pageRequest){
        Page page = new Page(pageRequest.getIndex(),pageRequest.getSize());
        return ResponseEntity.ok(originalProductService.page(page).getRecords());
    }

    @Tag(name = "产品表")
    @Operation(summary = "产品注册")
    @ApiResponses(
            @ApiResponse(responseCode = "201", description = "CREATED")
    )
    @PostMapping("/product/create")
    public ResponseEntity<String> createProduct(@RequestBody @Valid ProductRequest productRequest){
        return new ResponseEntity<>(originalProductService.create(productRequest), HttpStatus.CREATED);
    }

    @Tag(name = "产品表")
    @Operation(summary = "修改产品")
    @PutMapping("/product/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id,@RequestBody @Valid ProductRequest productRequest){
        return new ResponseEntity<>(originalProductService.update(id, productRequest), HttpStatus.OK);
    }

    @Tag(name = "产品表")
    @Operation(summary = "删除产品")
    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        return new ResponseEntity<>(originalProductService.delete(id), HttpStatus.OK);
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
    public ResponseEntity<String> createDevice(@RequestBody @Valid DeviceRequest deviceRequest){
        return new ResponseEntity<>(deviceService.createDevice(deviceRequest),HttpStatus.CREATED);
    }

    @Tag(name = "设备表")
    @Operation(summary = "分页获取设备信息")
    @Parameters({
            @Parameter(name = "page", description = "页码", in = ParameterIn.QUERY, example = "1"),
            @Parameter(name = "size", description = "每页数量", in = ParameterIn.QUERY, example = "10")
    })
    @GetMapping("/device/page")
    public ResponseEntity<List<OriginalProduct>> pageDevice(@RequestBody @Valid PageRequest pageRequest){
        Page page = new Page(pageRequest.getIndex(),pageRequest.getSize());
        return ResponseEntity.ok(deviceService.page(page).getRecords());
    }

    @Tag(name = "设备表")
    @Operation(summary = "修改设备")
    @PutMapping("/device/update/{id}")
    public ResponseEntity<String> updateDevice(@PathVariable Long id,@RequestBody @Valid DeviceRequest deviceRequest){
        return new ResponseEntity<>(deviceService.update(id, deviceRequest), HttpStatus.OK);
    }

    @Tag(name = "设备表")
    @Operation(summary = "删除设备")
    @DeleteMapping("/device/delete/{id}")
    public ResponseEntity<String> deleteDevice(@PathVariable Long id){
        return new ResponseEntity<>(deviceService.delete(id), HttpStatus.OK);
    }
}
