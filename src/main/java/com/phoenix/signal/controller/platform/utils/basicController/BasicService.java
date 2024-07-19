package com.phoenix.signal.controller.platform.utils.basicController;

import com.phoenix.signal.controller.platform.utils.baseModel.BasicModel;

import java.util.List;

public interface BasicService<T extends BasicModel>{

    List<T> getAll();


}
