package com.phoenix.signal.controller.platform.utils.basicController;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.phoenix.signal.controller.platform.mapper.BasicMapper;
import com.phoenix.signal.controller.platform.utils.baseModel.BasicModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Service
public abstract class BasicDbServiceImpl<M extends BasicMapper<T>, T extends BasicModel> extends MPJBaseServiceImpl<M, T> implements BasicDbService<T>{
    protected M baseMapper;

    private Class<T> clazz;//当前泛型的真实类型Class

    public BasicDbServiceImpl(M baseMapper) {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) pt.getActualTypeArguments()[1];
        this.baseMapper = baseMapper;
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
