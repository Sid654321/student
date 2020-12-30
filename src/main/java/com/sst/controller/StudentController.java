package com.sst.controller;

import com.sst.entity.Clazz;
import com.sst.entity.Student;
import com.sst.entity.Subject;
import com.sst.service.ClazzService;
import com.sst.service.StudentService;
import com.sst.service.SubjectService;
import com.sst.utils.MD5Utils;
import com.sst.utils.MapControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    private static final String LIST = "student/list";
    private static final String ADD = "student/add";
    private static final String UPDATE = "student/update";

    @Resource
    private StudentService studentService;
    @Resource
    private ClazzService clazzService;
    @Resource
    private SubjectService subjectService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> create(@RequestBody Student student){
        student.setStuPwd(MD5Utils.getMD5(student.getStuPwd()));
        int result = studentService.create(student);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(String ids){
        int result = studentService.delete(ids);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }


    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Student student){
        int result = studentService.update(student);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id,ModelMap modelMap){
        List<Clazz> clazzes = clazzService.query(null);
        Student student = studentService.detail(id);
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("student", student);
        modelMap.addAttribute("clazzes",clazzes);
        modelMap.addAttribute("subjects",subjects);

       return UPDATE;
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String, Object> query(@RequestBody Student student, ModelMap modelMap){

        List<Student> list = studentService.query(student);
        List<Clazz> clazzes = clazzService.query(null);
        List<Subject> subjects = subjectService.query(null);
        //将班级和专业列表放入session
        modelMap.addAttribute("clazzes",clazzes);
        modelMap.addAttribute("subjects",subjects);

        //匹配班级 ,匹配专业
        list.forEach(student1 -> {
            clazzes.forEach(clazz -> {
                if (student1.getClazzId() == clazz.getId()){
                    student1.setClazz(clazz);
                }
            });
            subjects.forEach(subject -> {
                if (student1.getSubjectId() == subject.getId()){
                    student1.setSubject(subject);
                }
            });
        });


        int count = studentService.count(student);
        return  MapControl.getInstance().success().add("data",list).add("count",count).getMap();
    }

    @GetMapping("/list")
    public String list(ModelMap modelMap){
        List<Clazz> clazzes = clazzService.query(null);
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("clazzes",clazzes);
        modelMap.addAttribute("subjects",subjects);
        return LIST;
    }
    @GetMapping("/add")
    public String add(ModelMap modelMap){
        List<Clazz> clazzes = clazzService.query(null);
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("clazzes",clazzes);
        modelMap.addAttribute("subjects",subjects);
        return ADD;
    }

    @GetMapping("listByTeacher")
    public String listByTeacher(){
        return "student/listByTeacher";
    }

    @PostMapping("queryByTeacher")
    @ResponseBody
    public Map<String, Object> queryByTeacher(@RequestBody Student student){
        String clazzName = student.getClazzName();
        if(clazzName!=null&&clazzName.length()!=0){
            List<Clazz> clazzes = clazzService.query(null);
            clazzes.forEach(clazz -> {
                if (clazzName.equals(clazz.getClazzName())){
                    student.setClazzId(clazz.getId());
                }
            });
        }

        List<Student> students = studentService.queryByTeacher(student);
        int count = students.size();

        return MapControl.getInstance().success().add("count",count).add("data",students).getMap();
    }
}
