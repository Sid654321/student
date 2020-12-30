package com.sst.service;

import com.sst.entity.Score;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.sst.entity.Student;
import com.sst.entity.Teacher;
import com.sst.mapper.*;
import com.sst.utils.PageHelperUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScoreService {

    @Resource
    private ScoreMapper scoreMapper;
    @Resource
    private SectionMapper sectionMapper;
    @Resource
    private ClazzMapper clazzMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private StudentMapper studentMapper;

    public int create(String sectionIds, String courseIds, HttpSession session) {
        //当前的学生信息
        Student student = (Student) session.getAttribute("user");
        //删除该学生原有的选课信息
        Score scoreBefore = new Score();
        scoreBefore.setStuId(student.getId());
        scoreMapper.delete(scoreBefore);
        //创建该学生现在的选课信息
        String[] sectionIdArrs = sectionIds.split(",");
        String[] couresIdArrs = courseIds.split(",");
        int flag = 0;

        for (int i = 0; i < couresIdArrs.length; i++) {
            Score score = new Score();
            score.setStuId(student.getId());
            score.setCourseId(Integer.parseInt(couresIdArrs[i]));
            score.setSectionId(Integer.parseInt(sectionIdArrs[i]));
            flag = scoreMapper.create(score);
        }
        return flag;
    }

    public int delete(Score score) {

        return scoreMapper.delete(score);
    }

    public int update(Score score) {
        System.out.println(score);
        return scoreMapper.updateSelective(score);
    }

    public int updateSelective(Score score) {
        return scoreMapper.updateSelective(score);
    }



    public List<Score> query(Score score) {
        PageHelperUtils.pageHelper(score);
        if (score!=null){
            System.out.println(score.getStuId());
            List<Score> scores = scoreMapper.query(score);
            scores.forEach(score1 -> {
                score1.setCourse(courseMapper.detail(score1.getCourseId()));
                score1.setSection(sectionMapper.detail(score1.getSectionId()));
                score1.setClazz(clazzMapper.detail(score1.getSection().getClazzId()));
                score1.setTeacher(teacherMapper.detail(score1.getSection().getTeacherId()));

            });

            return scores;
        }else {
            return scoreMapper.query(null);
        }

    }

    public List<Score> querys(Score score) {


            return scoreMapper.query(score);


    }

    public List<Integer> distinctCourse(){
        return scoreMapper.distinctCourse();
    }

    public List<Score> queryForTeacher(Score score) {

        PageHelperUtils.pageHelper(score);
        List<Score> scores = scoreMapper.queryForTeacher(score);
        System.out.println("查出的scores："+scores);
        scores.forEach(score1 -> {
            score1.setSection(sectionMapper.detail(score1.getSectionId()));
            score1.setCourse(courseMapper.detail(score1.getCourseId()));
            score1.setClazz(clazzMapper.detail(score1.getClazzId()));
            score1.setStudent(studentMapper.detail(score1.getStuId()));
            score1.setTeacher(teacherMapper.detail(score1.getTeacherId()));

        });
        return scores;
    }



    public Score detail(Integer id) {
        return scoreMapper.detail(id);
    }

    public int count(Score score) {
        return scoreMapper.count(score);
    }
}
