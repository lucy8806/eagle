package org.eagle.admin.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.eagle.core.utils.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: PageController
 * @Description: 页面跳转Controller
 * @Author lucy
 * @Date 2019/5/17 11:25
 * @Version 1.0
 */
@Api(value = "页面跳转", description = "页面跳转管理api")
@Controller
@Slf4j
public class PageController {

    @ApiOperation(value = "用户管理", notes = "用户管理")
    @RequiresPermissions("sys:user:view")
    @GetMapping("/sys/user")
    public ModelAndView user() {
        return ResultUtil.view("admin/user/user");
    }
}
