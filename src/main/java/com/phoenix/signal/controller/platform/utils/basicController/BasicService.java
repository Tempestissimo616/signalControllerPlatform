package com.phoenix.signal.controller.platform.utils.basicController;

import com.phoenix.signal.controller.platform.utils.baseModel.BasicModel;

import java.util.List;

public interface BasicService<T extends BasicModel, CreateData>{

    List<T> getAll();

    String create(CreateData createData);
}
