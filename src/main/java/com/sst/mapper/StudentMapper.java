package com.sst.mapper;

import java.util.List;
import java.util.Map;

import com.sst.entity.Student;

public interface StudentMapper {

	public int create(Student student);

	public int delete(Integer id);

	public int update(Student student);

	public int updateSelective(Student student);

	public List<Student> query(Student student);

	public List<Student> queryByTeacher(Student student);




	public Student login(Student student);

	public Student detail(Integer id);



	public int count(Student student);

}