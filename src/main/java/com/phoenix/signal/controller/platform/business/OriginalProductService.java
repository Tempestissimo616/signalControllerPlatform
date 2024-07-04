package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.dto.request.ProductRequest;
import com.phoenix.signal.controller.platform.model.OriginalProduct;

import java.util.List;

public interface OriginalProductService {
    List<OriginalProduct> getAllOriginalProduct();

    String create(ProductRequest productRequest);
}
