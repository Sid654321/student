package com.sst.controller;

import com.sst.entity.Clazz;
import com.sst.entity.Score;
import com.sst.entity.Student;
import com.sst.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
class IndexController {
    @Resource
    private SubjectService subjectService;
    @Resource
    private ClazzService clazzService;
    @Resource
    private CourseService courseService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private SectionService sectionService;
    @Resource
    private StudentService studentService;
    @Resource
    private ScoreService scoreService;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/userIndex")
    public String userIndex(){
        return "userIndex";
    }

    @GetMapping("/teacherIndex")
    public String teacherIndex(){
        return "teacherIndex";
    }

    @GetMapping("/studentIndex")
    public String studentIndex(){
        return "studentIndex";
    }
    @GetMapping("/main")
    public String main(ModelMap modelMap){
        //为系统概览提供数据
        int subjectCount = subjectService.count(null);
        int clazzCount = clazzService.count(null);
        int courseCount = courseService.count(null);
        int teacherCount = teacherService.count(null);
        int sectionCount = sectionService.count(null);
        int studentCount = studentService.count(null);
        modelMap.addAttribute("subjectCount",subjectCount);
        modelMap.addAttribute("clazzCount", clazzCount);
        modelMap.addAttribute("courseCount",courseCount);
        modelMap.addAttribute("teacherCount",teacherCount);
        modelMap.addAttribute("sectionCount",sectionCount);
        modelMap.addAttribute("studentCount",studentCount);
        //为班级学生数量提供数据
        List<Map<String, Object>> clazzList = new ArrayList<>();
        List<Clazz> clazzes = clazzService.query(null);
        List<Student> students = studentService.query(null);
        for (Clazz clazz : clazzes) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", clazz.getClazzName());
            int cnt = 0;
            for (Student student : students) {
                if (clazz.getId() == student.getClazzId()) {
                    cnt++;
                }
            }
            map.put("cnt", cnt);
            clazzList.add(map);
        }
        modelMap.addAttribute("clazzList",clazzList);
        //为课程平均成绩提供数据
        List<Map<String, Object>> courseList = new ArrayList<>();
        List<Score> scores = scoreService.query(null);

        scores.forEach(score -> {
           score.setCourse(courseService.detail(score.getCourseId()));
        });

        List<Integer> distinctCourses = scoreService.distinctCourse();

        for (Integer distinctCourse:distinctCourses) {
            Map<String, Object> map = new HashMap<>();
            map.put("courseName",courseService.detail(distinctCourse).getCourseName());
            double num = 0;
            double sum = 0;
            for (Score score:scores) {

                if(distinctCourse.intValue()== score.getCourseId()){
                    if(score.getScore()!=null){
                        num++;
                        sum += score.getScore().intValue();

                    }

                }
            }
            map.put("cnt",(sum/num));
            courseList.add(map);
            System.out.println(map);
        }

        Map<String, Object> map = new HashMap<>();
        modelMap.addAttribute("courseList",courseList);
        return "main";
    }

    @PostMapping("sum")
    @ResponseBody
    public Map<String,Object> sum (ModelMap modelMap) {

        return null;
    }

}
