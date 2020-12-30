package com.sst.controller;

import com.sst.entity.Teacher;
import com.sst.service.TeacherService;
import com.sst.utils.MD5Utils;
import com.sst.utils.MapControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private static final String LIST = "teacher/list";
    private static final String ADD = "teacher/add";
    private static final String UPDATE = "teacher/update";

    @Resource
    private TeacherService teacherService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> create(@RequestBody Teacher teacher){
       teacher.setTeacherPwd(MD5Utils.getMD5(teacher.getTeacherPwd()));
        System.out.println(teacher.getTeacherName());
        int result = teacherService.create(teacher);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(String ids){
        int result = teacherService.delete(ids);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }


    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Teacher teacher){
        int result = teacherService.update(teacher);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String  detail(@PathVariable("id") Integer id, ModelMap modelMap){
        Teacher teacher = teacherService.detail(id);
        modelMap.addAttribute("teacher",teacher);
       return UPDATE;
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String, Object> query(@RequestBody Teacher teacher){

        List<Teacher> list = teacherService.query(teacher);
        int count = teacherService.count(teacher);
        return  MapControl.getInstance().success().add("count",count).add("data",list).getMap();
    }

    @GetMapping("/list")
    public String list(){
        return LIST;
    }

    @GetMapping("/add")
    public String add(){
        return ADD;
    }
}
