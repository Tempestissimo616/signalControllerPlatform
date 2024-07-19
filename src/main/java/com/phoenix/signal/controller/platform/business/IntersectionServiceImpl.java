package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.model.Intersection;
import com.phoenix.signal.controller.platform.service.IntersectionDbService;
import com.phoenix.signal.controller.platform.utils.basicController.BasicServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IntersectionServiceImpl extends BasicServiceImpl<Intersection, IntersectionDbService> implements IntersectionService {

}
