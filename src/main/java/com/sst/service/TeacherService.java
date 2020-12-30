package com.sst.service;

import com.github.pagehelper.PageHelper;
import com.sst.entity.Teacher;

import javax.annotation.Resource;

import com.sst.mapper.TeacherMapper;
import com.sst.utils.PageHelperUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    public int create(Teacher teacher) {
        return teacherMapper.create(teacher);
    }

    public int delete(String ids) {
        String[] strings = ids.split(",");
        int flag = 0;
        for (String id:strings) {
            flag = teacherMapper.delete(Integer.parseInt(id));
        }
        return flag;
    }

    public int update(Teacher teacher) {
        return teacherMapper.update(teacher);
    }

    public int updateSelective(Teacher teacher) {
        return teacherMapper.updateSelective(teacher);
    }

    public List<Teacher> query(Teacher teacher) {
        PageHelperUtils.pageHelper(teacher);
        return teacherMapper.query(teacher);
    }

    public Teacher detail(Integer id) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        return teacherMapper.detail(teacher);
    }

    public Teacher login(Teacher teacher) {
        return teacherMapper.login(teacher);
    }

    public int count(Teacher teacher) {
        return teacherMapper.count(teacher);
    }
}
