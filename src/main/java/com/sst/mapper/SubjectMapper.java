package com.sst.mapper;

import java.util.List;
import java.util.Map;

import com.sst.entity.Subject;

public interface SubjectMapper {

	public int create(Subject subject);

	public int delete(Integer id);

	public int update(Subject subject);

	public int updateSelective(Subject subject);

	public List<Subject> query(Subject subject);


	public Subject detail(Subject subject);

	public int count(Subject subject);

}