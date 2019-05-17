package org.eagle.admin.sys.entity;

import lombok.Data;
import org.eagle.core.model.BaseEntity;

/**
 * @ClassName: UserConfig
 * @Description: TODO
 * @Author lucy
 * @Date 2019/5/15 15:56
 * @Version 1.0
 */
@Data
public class UserConfig extends BaseEntity {
    private static final long serialVersionUID = -4979040862465445153L;

    public static final String DEFAULT_THEME = "dark";
    public static final String DEFAULT_LAYOUT = "side";
    public static final String DEFAULT_MULTIPAGE = "0";
    public static final String DEFAULT_FIX_SIDERBAR = "1";
    public static final String DEFAULT_FIX_HEADER = "1";
    public static final String DEFAULT_COLOR = "rgb(66, 185, 131)";

    /**
     * 用户 ID
     */
    //@TableId(value = "USER_ID")
    private Long userId;

    /**
     * 系统主题 dark暗色风格，light明亮风格
     */
    private String theme = DEFAULT_THEME;

    /**
     * 系统布局 side侧边栏，head顶部栏
     */
    private String layout = DEFAULT_LAYOUT;

    /**
     * 页面风格 1多标签页 0单页
     */
    private String multiPage = DEFAULT_MULTIPAGE;

    /**
     * 页面滚动是否固定侧边栏 1固定 0不固定
     */
    private String fixSiderbar = DEFAULT_FIX_SIDERBAR;

    /**
     * 页面滚动是否固定顶栏 1固定 0不固定
     */
    private String fixHeader = DEFAULT_FIX_HEADER;

    /**
     * 主题颜色 RGB值
     */
    private String color = DEFAULT_COLOR;
}
