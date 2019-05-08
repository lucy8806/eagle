package org.eagle.core.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:分页查询结果
 * 
 * @author lucy
 * @date:2019年5月3日 上午11:04:17
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageResultVo {

	private Long total;
	private List rows;

	public PageResultVo(Long total, List rows) {
		this.total = total;
		this.rows = rows;
	}

	public PageResultVo() {
	}
}
