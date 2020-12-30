package com.sst.service;

import com.sst.entity.Student;

import javax.annotation.Resource;

import com.sst.mapper.ClazzMapper;
import com.sst.mapper.StudentMapper;
import com.sst.utils.PageHelperUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Resource
    private StudentMapper studentMapper;
    @Resource
    private ClazzMapper clazzMapper;

    public int create(Student student) {
        return studentMapper.create(student);
    }

    public int delete(String ibs) {
        String[] strings = ibs.split(",");
        int flag = 0;
        for (String id:strings) {
            flag = studentMapper.delete(Integer.parseInt(id));
        }
        return flag;
    }

    public int update(Student student) {
        return studentMapper.update(student);
    }

    public int updateSelective(Student student) {
        return studentMapper.updateSelective(student);
    }

    public List<Student> query(Student student) {
        return studentMapper.query(student);
    }

    public List<Student> queryByTeacher(Student student) {
        PageHelperUtils.pageHelper(student);
        List<Student> students = studentMapper.queryByTeacher(student);
        students.forEach(student1 -> {
            student1.setClazz(clazzMapper.detail(student1.getClazzId()));
        });
        return students;
    }

    public Student detail(Integer id) {
        Student student = new Student();
        student.setId(id);
        return studentMapper.login(student);
    }

    public Student login(Student student) {
        return studentMapper.login(student);
    }

    public int count(Student student) {
        return studentMapper.count(student);
    }
}
