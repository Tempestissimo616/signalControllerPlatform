package com.phoenix.signal.controller.platform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.phoenix.signal.controller.platform.dto.request.ProductRequest;
import com.phoenix.signal.controller.platform.mapper.OriginalProductMapper;
import com.phoenix.signal.controller.platform.model.OriginalProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OriginalProductDbServiceImpl extends MPJBaseServiceImpl<OriginalProductMapper, OriginalProduct> implements OriginalProductDbService {

    OriginalProductMapper originalProductMapper;

    @Override
    public List<OriginalProduct> getAll() {

        MPJLambdaWrapper<OriginalProduct> wrapper = new MPJLambdaWrapper<OriginalProduct>()
                .selectAll(OriginalProduct.class);

        return originalProductMapper.selectList(wrapper);
    }

    @Override
    public Long countByProductId(Long productId) {

        MPJLambdaWrapper<OriginalProduct> wrapper = new MPJLambdaWrapper<OriginalProduct>()
                .eq(OriginalProduct::getProductId, productId);

        return originalProductMapper.selectCount(wrapper);
    }

    @Override
    public IPage<OriginalProduct> page(Page<OriginalProduct> page) {
        MPJLambdaWrapper wrapper = new MPJLambdaWrapper<OriginalProduct>()
                .selectAll(OriginalProduct.class);

        return originalProductMapper.selectPage(page,wrapper);
    }


}
