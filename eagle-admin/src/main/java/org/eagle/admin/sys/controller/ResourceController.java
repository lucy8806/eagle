package org.eagle.admin.sys.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.eagle.admin.sys.entity.SysResource;
import org.eagle.admin.sys.service.ResourceService;
import org.eagle.core.model.router.VueRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

/**
 * @ClassName: MenuController
 * @Description: TODO
 * @Author lucy
 * @Date 2019/5/15 17:05
 * @Version 1.0
 */
@Api(value = "菜单管理", description = "菜单管理api", produces = "http")
@RestController
@Slf4j
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/{userId}")
    public ArrayList<VueRouter<SysResource>> getUserRouters(@NotBlank(message = "{required}") @PathVariable Integer userId) {
        return resourceService.getUserRouters(userId);
    }
}
