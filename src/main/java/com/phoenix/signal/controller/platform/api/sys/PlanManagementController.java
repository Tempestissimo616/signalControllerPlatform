package com.phoenix.signal.controller.platform.api.sys;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/plan")
@Tag(name = "方案管理", description = "方案管理相关的api")
public class PlanManagementController {
}
