package com.sst.controller;

import com.sst.entity.Clazz;
import com.sst.entity.Subject;
import com.sst.service.ClazzService;
import com.sst.service.SubjectService;
import com.sst.utils.MapControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clazz")
public class ClazzController {

    private static final String LIST = "clazz/list";
    private static final String ADD = "clazz/add";
    private static final String UPDATE = "clazz/update";

    @Resource
    private ClazzService clazzService;
    @Resource
    private SubjectService subjectService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> create(@RequestBody Clazz clazz){
        int result = clazzService.create(clazz);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(String ids){
        int result = clazzService.delete(ids);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }


    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Clazz clazz){
        int result = clazzService.update(clazz);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        Clazz clazz = clazzService.detail(id);
       modelMap.addAttribute("clazz",clazz);
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("subjects",subjects);
       return UPDATE;
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String, Object> query(Clazz clazz){

        List<Clazz> list = clazzService.query(clazz);
        List<Subject> subjects = subjectService.query(null);
        list.forEach(clazz1 -> {
            subjects.forEach(subject -> {
                if (subject.getId().equals(clazz1.getSubjectId())){
                    clazz1.setSubject(subject);
                }
            });
        });

        int count = clazzService.count(clazz);
        return  MapControl.getInstance().success().add("count",count).add("data",list).getMap();
    }

    @GetMapping("/list")
    public String list(){
       return LIST;
    }
    @GetMapping("/add")
    public String add(ModelMap modelMap){
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("subjects",subjects);
        return ADD;
    }
}
