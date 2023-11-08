package com.qnkj.clouds.modules.VmInstances.controller;


import com.qnkj.clouds.modules.VmInstances.services.IVmInstancesService;

import com.qnkj.clouds.modules.utils.WebResponse;
//import com.qnkj.core.base.BaseEntityUtils;
import com.qnkj.clouds.modules.utils.WebViews;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bingshuai@nj.iscas.ac.cn
 * @since 11.01
 */


@Validated
@Controller("VmInstances")
@RequiredArgsConstructor
@Api(tags = "低代码演示：云主机")
@Scope("prototype")
@RequestMapping("VmInstance")
public class VmInstancesController {
    private final IVmInstancesService vminstancesService;
//    private BaseEntityUtils viewEntitys = null;

//    @PostConstruct
//    public void init() { viewEntitys = BaseEntityUtils.init(this); }

    @ApiOperation("显示控制台")
    @GetMapping("/viewNoVnc")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "record", value = "记录ID", required = true, dataType = "String", paramType = "query")
//    })
    public Object viewAddProduct( HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            Integer port = vminstancesService.startWebsockifyServer();
            model.addAttribute("port", port);
            return WebViews.view("/VmInstances/viewNoVnc");
        }catch (Exception e) {
            return WebViews.body(response,new WebResponse().alert().message("无法获得控制台端口"));
        }
    }

}
