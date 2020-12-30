package com.sst.mapper;

import java.util.List;
import java.util.Map;

import com.sst.entity.Teacher;

public interface TeacherMapper {

	public int create(Teacher teacher);

	public int delete(Integer id);

	public int update(Teacher teacher);

	public int updateSelective(Teacher teacher);

	public List<Teacher> query(Teacher teacher);


	public Teacher detail(Teacher teacher);

	public Teacher login(Teacher teacher);

	public Teacher detail(Integer id);

	public int count(Teacher teacher);

}