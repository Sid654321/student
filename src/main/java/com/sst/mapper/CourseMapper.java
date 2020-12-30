package com.sst.mapper;

import java.util.List;
import java.util.Map;

import com.sst.entity.Course;

public interface CourseMapper {

	public int create(Course course);

	public int delete(Integer id);

	public int update(Course course);

	public int updateSelective(Course course);

	public List<Course> query(Course course);


	public Course detail(Integer id);

	public Course detail(Course course);

	public int count(Course course);

}