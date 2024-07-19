package com.phoenix.signal.controller.platform.api.sys;

import com.phoenix.signal.controller.platform.business.IntersectionService;
import com.phoenix.signal.controller.platform.model.Intersection;
import com.phoenix.signal.controller.platform.utils.basicController.BaseController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/intersection")
public class IntersectionController extends BaseController<Intersection, IntersectionService> {

}
