package com.phoenix.signal.controller.platform.api.sys;

import com.phoenix.signal.controller.platform.business.IntersectionService;
import com.phoenix.signal.controller.platform.dto.request.IntersectionRequest;
import com.phoenix.signal.controller.platform.model.Intersection;
import com.phoenix.signal.controller.platform.utils.basicController.BaseController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/intersection")
@Tag(name = "路口", description = "路口相关api")
public class IntersectionController extends BaseController<Intersection, IntersectionService, IntersectionRequest> {

}
