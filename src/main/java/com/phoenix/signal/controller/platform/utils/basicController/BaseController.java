package com.phoenix.signal.controller.platform.utils.basicController;

import com.phoenix.signal.controller.platform.type.SysLogType;
import com.phoenix.signal.controller.platform.utils.aop.Log;
import com.phoenix.signal.controller.platform.utils.baseModel.BasicModel;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public abstract class BaseController<T extends BasicModel, Service extends BasicService<T>> {

    @Autowired
    protected Service service;

    @Operation(summary = "获取所有数据")
    @Log(value = "%s-获取所有数据",type = SysLogType.LIST)
    @GetMapping("/list")
    public ResponseEntity<List<T>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

}
