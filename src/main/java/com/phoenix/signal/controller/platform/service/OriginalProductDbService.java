package com.phoenix.signal.controller.platform.service;

import com.github.yulichang.base.MPJBaseService;
import com.phoenix.signal.controller.platform.dto.request.ProductRequest;
import com.phoenix.signal.controller.platform.model.OriginalProduct;

import java.util.List;

public interface OriginalProductDbService extends MPJBaseService<OriginalProduct> {

    List<OriginalProduct> getAll();

    Long countByProductId(Long productId);



}
