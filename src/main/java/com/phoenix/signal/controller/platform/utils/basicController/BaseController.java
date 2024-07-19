package com.phoenix.signal.controller.platform.utils.basicController;

import com.phoenix.signal.controller.platform.utils.baseModel.BasicModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class BaseController<T extends BasicModel, Service extends BasicService<T>> {

    protected Service service;

    public ResponseEntity<List<T>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

}
