package com.phoenix.signal.controller.platform.utils.basicController;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.phoenix.signal.controller.platform.utils.baseModel.BasicModel;

import java.io.Serializable;
import java.util.List;

public abstract class BasicDbServiceImpl<M extends BasicMapper<T>, T extends BasicModel> extends MPJBaseServiceImpl<M, T> implements BasicDbService<T>{
    M basicMapper;

    protected M baseMapper;
    @Override
    public T getById(Serializable id) {
        return baseMapper.selectOne(new MPJLambdaWrapper<T>().eq(T::getId, id));
    }

    @Override
    public List<T> getList(){
        return baseMapper.selectList(new MPJLambdaWrapper<T>().selectAll(null));
    }
}
