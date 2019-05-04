package org.eagle.core.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:分页信息
 * @author lucy
 * @date:2019年5月3日 上午10:46:17
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageInfoVo {
	public final static int DEFAULT_PAGE_SIZE = 10;
	private int pageNum = 1;
	private int pageSize = 0;
	private int pageStart = 0;
	private String orderField;
	private String orderDirection;
	private String keywords;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endDate;

	public int getPageSize() {
		return pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
	}

	public int getPageStart() {
		return pageNum > 0 ? (pageNum - 1) * pageSize : 0;
	}
}
