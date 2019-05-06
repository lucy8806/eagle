package org.eagle.core.mybatis.mapper;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用Mapper
 * 
 * @author lucy
 *
 * @param <T>
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, ConditionMapper<T> {

}
