package com.sst.controller;

import com.sst.entity.Subject;
import com.sst.service.SubjectService;
import com.sst.utils.MapControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/subject")
public class SubjectController {
    private static final String LIST = "subject/list";
    private static final String ADD = "subject/add";
    private static final String UPDATE = "subject/update";
    @Resource
    private SubjectService subjectService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> create(@RequestBody Subject subject){
        int result = subjectService.create(subject);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }




    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(String ids){
        int result = subjectService.delete(ids);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }


    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Subject subject){
        int result = subjectService.update(subject);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        Subject subject = subjectService.detail(id);
        modelMap.addAttribute("subject",subject);
        return UPDATE;
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String, Object> query(@RequestBody Subject subject){
        List<Subject> list = subjectService.query(subject);
        int count = subjectService.count(subject);
        return  MapControl.getInstance().success().add("data",list).add("count",count).getMap();
    }

    @GetMapping("/list")
    public String list(){
        return LIST;
    }

    @GetMapping("/add")
    public String  add(){
        System.out.println("增加科目信息");
        return ADD;
    }



}
