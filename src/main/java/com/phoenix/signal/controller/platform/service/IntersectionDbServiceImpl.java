package com.phoenix.signal.controller.platform.service;

import com.phoenix.signal.controller.platform.mapper.IntersectionMapper;
import com.phoenix.signal.controller.platform.model.Intersection;
import com.phoenix.signal.controller.platform.utils.basicController.BasicDbServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntersectionDbServiceImpl extends BasicDbServiceImpl<IntersectionMapper, Intersection> implements IntersectionDbService {

    @Autowired
    public IntersectionDbServiceImpl(IntersectionMapper intersectionMapper) {
        super(intersectionMapper);
    }
}
