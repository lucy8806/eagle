package org.eagle.core.utils;

import com.github.pagehelper.PageInfo;
import org.eagle.core.consts.CommonConst;
import org.eagle.core.enums.ResponseStatusEnum;
import org.eagle.core.model.PageResultVo;
import org.eagle.core.model.ResponseVo;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ResultUtil
 * @Description: TODO
 * @Author lucy
 * @Date 2019/5/815:47
 * @Version 1.0
 */
public class ResultUtil {
    /**
     * ModelAndView
     **/
    public static ModelAndView view(String view) {
        return new ModelAndView(view);
    }

    public static ModelAndView view(String view, Map<String, Object> model) {
        return new ModelAndView(view, model);
    }

    public static ModelAndView redirect(String view) {
        return new ModelAndView("redirect:" + view);
    }

    /**
     * vo
     **/
    public static ResponseVo vo(int code, String message, Object data) {
        return new ResponseVo<>(code, message, data);
    }

    /**
     * error
     **/
    public static ResponseVo error(int code, String message) {
        return vo(code, message, null);
    }

    public static ResponseVo error(ResponseStatusEnum statusEnum) {
        return vo(statusEnum.getCode(), statusEnum.getMessage(), null);
    }

    public static ResponseVo error(String message) {
        return vo(CommonConst.DEFAULT_ERROR_CODE, message, null);
    }

    /**
     * success
     **/
    public static ResponseVo success(String message, Object data) {
        return vo(CommonConst.DEFAULT_SUCCESS_CODE, message, data);
    }

    public static ResponseVo success(String message) {
        return success(message, null);
    }

    public static ResponseVo success(ResponseStatusEnum statusEnum) {
        return vo(statusEnum.getCode(), statusEnum.getMessage(), null);
    }

    /**
     * PageResultVo
     **/
    public static PageResultVo tablePage(Long total, List<?> list) {
        return new PageResultVo(total, list);
    }

    public static PageResultVo tablePage(PageInfo pageInfo) {
        if (pageInfo == null) {
            return new PageResultVo(0L, new ArrayList());
        }
        return tablePage(pageInfo.getTotal(), pageInfo.getList());
    }
}
