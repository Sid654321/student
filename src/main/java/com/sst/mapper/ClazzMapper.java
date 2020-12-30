package com.sst.mapper;

import java.util.List;
import java.util.Map;

import com.sst.entity.Clazz;

public interface ClazzMapper {

	public int create(Clazz clazz);

	public int delete(Integer id);

	public int update(Clazz clazz);

	public int updateSelective(Clazz clazz);

	public List<Clazz> query(Clazz clazz);


	public Clazz detail(Integer id);

	public int count(Clazz clazz);

}