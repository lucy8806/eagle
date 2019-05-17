package org.eagle.core.model.router;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: RouterMeta
 * @Description: Vue路由Meta
 * @Author lucy
 * @Date 2019/5/15 17:28
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouterMeta implements Serializable {
    private static final long serialVersionUID = -2997232128902374752L;

    private Boolean closeable;

    private Boolean isShow;
}
