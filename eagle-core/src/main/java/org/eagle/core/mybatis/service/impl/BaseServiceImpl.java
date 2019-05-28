package org.eagle.core.mybatis.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.eagle.core.model.BaseEntity;
import org.eagle.core.mybatis.mapper.BaseMapper;
import org.eagle.core.mybatis.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;

/**
 * 抽象Service
 *
 * @author lucy
 *
 * @param <T>
 */
@Service
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

	@Autowired
	private BaseMapper<T> baseMapper;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean insert(T entity) {
		entity.setCreatetime(new Date());
		entity.setUpdatetime(new Date());
		return baseMapper.insert(entity) > 0;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean insertSelective(T entity) {
		return baseMapper.insertSelective(entity) > 0;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean insertBatch(List<T> recordList) {
		return baseMapper.insertList(recordList) > 0;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean deleteById(Object key) {
		return baseMapper.deleteByPrimaryKey(key) > 0;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public T selectById(Object key) {
		return baseMapper.selectByPrimaryKey(key);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateById(T entity) {
		return baseMapper.updateByPrimaryKey(entity) > 0;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateSelectiveById(T entity) {
		entity.setUpdatetime(new Date());
		return baseMapper.updateByPrimaryKeySelective(entity) > 0;
	}

	@Override
	public List<T> listAll() {
		return baseMapper.selectAll();
	}

	@Override
	public List<T> select(T record) {
		return baseMapper.select(record);
	}

	@Override
	public T selectOne(T record) {
		return baseMapper.selectOne(record);
	}

	@Override
	public T findByExample(Example example) {
		final List<T> records = this.baseMapper.selectByExample(example);
		if (records == null) {
			return null;
		} else if (records.size() > 1) {
			throw new TooManyResultsException();
		}
		return null;
	}

}
