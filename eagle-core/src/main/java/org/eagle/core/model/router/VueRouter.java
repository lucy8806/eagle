package org.eagle.core.model.router;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: VueRouter
 * @Description: 构建Vue路由
 * @Author lucy
 * @Date 2019/5/15 17:27
 * @Version 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VueRouter<T> implements Serializable {

    private static final long serialVersionUID = -2824588575291059266L;

    @JsonIgnore
    private String id;

    @JsonIgnore
    private String parentId;

    private String path;

    private String name;

    private String component;

    private String icon;

    private String redirect;

    private RouterMeta meta;

    private List<VueRouter<T>> children;

    @JsonIgnore
    private boolean hasParent = false;

    @JsonIgnore
    private boolean hasChildren = false;

    public void initChildren(){
        this.children = new ArrayList<>();
    }
}
