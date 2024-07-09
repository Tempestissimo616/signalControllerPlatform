package com.phoenix.signal.controller.platform.business;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phoenix.signal.controller.platform.dto.request.ProductRequest;
import com.phoenix.signal.controller.platform.model.OriginalProduct;

import java.util.List;

public interface OriginalProductService{
    List<OriginalProduct> getAllOriginalProduct();

    String create(ProductRequest productRequest);

    IPage<OriginalProduct> page(Page<OriginalProduct> page);

    String update(Long productId, ProductRequest productRequest);

    String delete(Long productId);
}
