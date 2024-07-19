package com.phoenix.signal.controller.platform.utils.basicController;

import com.phoenix.signal.controller.platform.utils.baseModel.BasicModel;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public abstract class BaseController<T extends BasicModel, Service extends BasicService<T>> {

    protected Service service;

    @Operation(summary = "获取所有数据")
    @GetMapping("/list")
    public ResponseEntity<List<T>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

}
