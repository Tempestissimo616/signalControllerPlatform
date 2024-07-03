package com.phoenix.signal.controller.platform.service;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
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
    public List<OriginalProduct> page(Integer pageNum, Integer pageSize) {
        return List.of();
    }
}
