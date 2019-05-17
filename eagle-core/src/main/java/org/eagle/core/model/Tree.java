package org.eagle.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: Tree
 * @Description: 树形数据结构
 * @Author lucy
 * @Date 2019/5/15 17:50
 * @Version 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tree<T> {
    private String id;

    private String key;

    private String icon;

    private String title;

    private String value;

    private String text;

    private String permission;

    private String type;

    private Integer order;

    private String path;

    private String component;

    private List<Tree<T>> children;

    private String parentId;

    private boolean hasParent = false;

    private boolean hasChildren = false;

    private Date createTime;

    private Date modifyTime;

    public void initChildren(){
        this.children = new ArrayList<>();
    }
}
