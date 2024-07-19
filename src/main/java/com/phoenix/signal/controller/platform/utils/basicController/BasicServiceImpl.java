package com.phoenix.signal.controller.platform.utils.basicController;

import com.phoenix.signal.controller.platform.utils.baseModel.BasicModel;

import java.util.List;

public class BasicServiceImpl <T extends BasicModel, S extends BasicDbService<T>> implements BasicService<T>{
    protected S basicDbService;

    @Override
    public List<T> getAll() {
        return basicDbService.getList();
    }
}
