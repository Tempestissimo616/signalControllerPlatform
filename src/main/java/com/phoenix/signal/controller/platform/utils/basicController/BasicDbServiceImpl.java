package com.phoenix.signal.controller.platform.utils.basicController;

import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.phoenix.signal.controller.platform.utils.baseModel.BasicModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BasicDbServiceImpl<M extends MPJBaseMapper<T>, T extends BasicModel> extends MPJBaseServiceImpl<M, T> implements BasicDbService<T>{
    @Autowired
    protected M baseMapper;

    private Class<T> clazz;//当前泛型的真实类型Class

    public BasicDbServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public T getById(Serializable id) {
        return baseMapper.selectOne(new MPJLambdaWrapper<T>().selectAll(clazz).eq(T::getId, id));
    }

    @Override
    public List<T> getList(){
        return baseMapper.selectList(new MPJLambdaWrapper<T>().selectAll(clazz));
    }
}
