package com.phoenix.signal.controller.platform.utils.basicController;

import com.phoenix.signal.controller.platform.type.SysLogType;
import com.phoenix.signal.controller.platform.utils.aop.Log;
import com.phoenix.signal.controller.platform.utils.baseModel.BasicModel;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public abstract class BaseController<T extends BasicModel, Service extends BasicService<T, CreateData>, CreateData> {

    @Autowired
    protected Service service;

    @Operation(summary = "获取所有数据")
    @Log(value = "获取所有数据",type = SysLogType.LIST)
    @GetMapping("/list")
    public ResponseEntity<List<T>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "创建数据")
    @Log(value = "创建数据",type = SysLogType.ADD)
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CreateData createData){
        return ResponseEntity.ok(service.create(createData));
    }

}
