package com.sst.service;

import com.sst.entity.Score;
import com.sst.entity.Section;

import javax.annotation.Resource;

import com.sst.mapper.SectionMapper;
import com.sst.utils.PageHelperUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SectionService {

    @Resource
    private SectionMapper sectionMapper;
    @Resource
    private TeacherService teacherService;
    @Resource
    private ClazzService clazzService;
    @Resource
    private CourseService courseService;
    @Resource
    private ScoreService scoreService;





    public int create(Section section) {
        return sectionMapper.create(section);
    }

    public int delete(String ids) {
        String[] strings = ids.split(",");
        int flag = 0;
        for (String id: strings) {
            flag = sectionMapper.delete(Integer.parseInt(id));
        }
        return flag;
    }

    public int update(Section section) {
        return sectionMapper.update(section);
    }

    public int updateSelective(Section section) {
        return sectionMapper.updateSelective(section);
    }

    public List<Section> query(Section section) {
        PageHelperUtils.pageHelper(section);
        return sectionMapper.query(section);
    }

    public Section detail(Integer id) {
        return sectionMapper.detail(id);
    }

    public int count(Section section) {
        return sectionMapper.count(section);
    }

    public List<Section> queryStudentSection(Integer id){
        List<Section> sections = sectionMapper.queryStudentSection(id);
        sections.forEach(section -> {
            section.setTeacher(teacherService.detail(section.getTeacherId()));
            section.setCourse(courseService.detail(section.getCourseId()));
            section.setClazz(clazzService.detail(section.getClazzId()));
        });

        return sections;

    }
}
