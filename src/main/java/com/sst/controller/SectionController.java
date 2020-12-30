package com.sst.controller;

import com.sst.entity.*;
import com.sst.service.*;
import com.sst.utils.MapControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("section")
public class SectionController {

    private static final String LIST = "section/list";
    private static final String ADD = "section/add";
    private static final String UPDATE = "section/update";

    @Resource
    private SectionService sectionService;
    @Resource
    private SubjectService subjectService;
    @Resource
    private CourseService courseService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private ClazzService clazzService;
    @Resource
    private ScoreService scoreService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> create(@RequestBody Section section){
        int result = sectionService.create(section);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(String ids){
        int result = sectionService.delete(ids);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }


    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Section section){
        int result = sectionService.update(section);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id,ModelMap modelMap){
        addResource(modelMap);
        Section section = sectionService.detail(id);
        modelMap.addAttribute("section",section);
        return UPDATE;

    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String, Object> query(@RequestBody Section section){
        List<Clazz> clazzes = clazzService.query(null);
        List<Course> courses = courseService.query(null);
        List<Teacher> teachers = teacherService.query(null);
        List<Section> list = sectionService.query(section);
        list.forEach(section1 -> {
            clazzes.forEach(clazz -> {
                if(section1.getClazzId()==clazz.getId()){
                    section1.setClazz(clazz);
                }
            });
            courses.forEach(course -> {
                if (section1.getCourseId()==course.getId()){
                    section1.setCourse(course);
                }
            });
            teachers.forEach(teacher -> {
                if (section1.getTeacherId()==teacher.getId()){
                    section1.setTeacher(teacher);
                }
            });
        });
        int count = sectionService.count(section);
        return  MapControl.getInstance().success().add("count",count).add("data",list).getMap();
    }

    @GetMapping("/list")
    public String list (ModelMap modelMap){
        addResource(modelMap);
        return  LIST;
    }


    @PostMapping("/tree")
    @ResponseBody
    public  List<Map<String, Object>> tree(){
        //查询专业和班级列表
        List<Subject> subjects= subjectService.query(null);
        List<Clazz> clazzes = clazzService.query(null);

        //将专业和班级列表匹配为对应的json
        List<Map<String, Object>> list = new ArrayList<>();
        //循环前新建list 循环中新建map
        subjects.forEach(subject -> {
            Map<String, Object> subjectMap = new HashMap<>();
            subjectMap.put("id",subject.getId());
            subjectMap.put("pid",0);
            subjectMap.put("name",subject.getSubjectName());

            List<Map<String, Object>> clazzlist = new ArrayList<>();
            clazzes.forEach(clazz -> {
                if (subject.getId() == clazz.getSubjectId().intValue()){
                    Map<String, Object> clazztMap = new HashMap<>();
                    clazztMap.put("id",clazz.getId());
                    clazztMap.put("pid",subject.getId());
                    clazztMap.put("name",clazz.getClazzName());
                    clazzlist.add(clazztMap);
                }
            });
            subjectMap.put("children",clazzlist);
            list.add(subjectMap);
        });
        return list;
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap, Integer clazzId){
        addResource(modelMap);
        modelMap.addAttribute("clazzId",clazzId);
        return ADD;
    }

    @GetMapping("listStudent")
    public String listStudent(HttpSession session, ModelMap modelMap){
        return "section/listStudent";
    }

    @PostMapping("queryStudentSection")
    @ResponseBody
    public Map<String, Object> queryStudentSection (HttpSession session){
        Student student = (Student) session.getAttribute("user");
        List<Section> sections = sectionService.queryStudentSection(student.getId());
        System.out.println("sections:"+sections);
        int count = sections.size();
        sections.forEach(section -> {
            Score score = new Score();
            score.setStuId(student.getId());
            score.setCourseId(section.getCourseId());
            score.setSectionId(section.getId());
            boolean unselected = (scoreService.querys(score).isEmpty());

            System.out.println("没有查到已选课程"+unselected+"学生id："+score.getStuId()+"sectionid:"+score.getSectionId()+"courseId:"+score.getCourseId());

//            System.out.println(unselected);
//            System.out.println(scoreService.query(score));

            section.setChecked(unselected?0:1);
        });

        return MapControl.getInstance().success().add("count",count).add("data",sections).getMap();
    }



    private void addResource (ModelMap modelMap){
        List<Course> courses = courseService.query(null);
        List<Teacher> teachers = teacherService.query(null);
        modelMap.addAttribute("courses",courses);
        modelMap.addAttribute("teachers",teachers);
    }

}
