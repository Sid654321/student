package com.sst.service;

import com.sst.entity.Course;

import javax.annotation.Resource;

import com.sst.mapper.CourseMapper;
import com.sst.utils.PageHelperUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {

    @Resource
    private CourseMapper courseMapper;

    public int create(Course course) {
        return courseMapper.create(course);
    }

    public int delete(String ids) {
        String[] strings = ids.split(",");
        int flag = 0;
        for (String id:strings) {
            flag = courseMapper.delete(Integer.parseInt(id));
        }
        return flag;
    }

    public int update(Course course) {
        return courseMapper.update(course);
    }

    public int updateSelective(Course course) {
        return courseMapper.updateSelective(course);
    }

    public List<Course> query(Course course) {
        PageHelperUtils.pageHelper(course);
        return courseMapper.query(course);
    }

    public Course detail(Integer id) {
        Course course = new Course();
        course.setId(id);
        return courseMapper.detail(course);
    }

    public int count(Course course) {
        return courseMapper.count(course);
    }
}
