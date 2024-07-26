package com.phoenix.signal.controller.platform.utils.basicController;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import com.phoenix.signal.controller.platform.utils.baseModel.BasicModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BasicServiceImpl <T extends BasicModel, S extends BasicDbService<T>, CreateData> implements BasicService<T, CreateData>{
    @Autowired
    protected S basicDbService;

    @Autowired
    private ModelMapper modelMapper;

    private Class<T> modelClazz;//当前泛型的真实类型Class

    public BasicServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClazz = (Class<T>) pt.getActualTypeArguments()[0];  //第三个
    }

    @Override
    public List<T> getAll() {
        return basicDbService.getList();
    }

    @Override
    public String create(CreateData createData) {
        T model = modelMapper.map(createData, modelClazz);
        return basicDbService.save(model) ? StrUtil.format("创建成功") : StrUtil.format("创建失败");
    }
}
