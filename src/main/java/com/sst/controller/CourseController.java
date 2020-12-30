package com.sst.controller;

import com.sst.entity.Course;
import com.sst.service.CourseService;
import com.sst.utils.MapControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {

    private static final String LIST = "course/list";
    private static final String ADD = "course/add";
    private static final String UPDATE = "course/update";

    @Resource
    private CourseService courseService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> create(@RequestBody Course course){
        int result = courseService.create(course);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(String ids){
        int result = courseService.delete(ids);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }


    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Course course){
        int result = courseService.update(course);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        Course course = courseService.detail(id);
        modelMap.addAttribute("course",course);
       return UPDATE;
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String, Object> query(@RequestBody Course course){
        List<Course> list = courseService.query(course);
        int count = courseService.count(course);
        return  MapControl.getInstance().success().add("count",count).add("data",list).getMap();
    }

    @GetMapping("/list")
    public String list(){
        return  LIST;
    }

    @GetMapping("/add")
    public String add(){
        return  ADD;
    }
}
