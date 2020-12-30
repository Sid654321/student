package com.sst.mapper;

import java.util.List;
import java.util.Map;

import com.sst.entity.Job;

public interface JobMapper {

	public int create(Job job);

	public int delete(Integer id);

	public int update(Job job);

	public int updateSelective(Job job);

	public List<Job> query(Job job);


	public Job detail(Integer id);

	public int count(Job job);

}