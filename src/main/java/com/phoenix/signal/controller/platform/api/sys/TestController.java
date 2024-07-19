package com.phoenix.signal.controller.platform.api.sys;

import com.phoenix.signal.controller.platform.model.Device;
import com.phoenix.signal.controller.platform.utils.basicController.BaseController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.ldap.BasicControl;

@RestController
@AllArgsConstructor
@RequestMapping("/api/test")
public class TestController extends BaseController<> {

}
