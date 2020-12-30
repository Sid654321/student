package com.sst.controller;

import com.sst.entity.Clazz;
import com.sst.entity.Course;
import com.sst.entity.Score;
import com.sst.entity.Student;
import com.sst.service.ClazzService;
import com.sst.service.CourseService;
import com.sst.service.ScoreService;
import com.sst.service.SectionService;
import com.sst.utils.MapControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/score")
public class ScoreController {
    @Resource
    private ScoreService scoreService;
    @Resource
    private SectionService sectionService;
    @Resource
    private ClazzService clazzService;
    @Resource
    private CourseService courseService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> create(String sectionIds, String courseIds  , HttpSession session){
        int result = scoreService.create(sectionIds, courseIds, session);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }




    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Score score){
        int result = scoreService.update(score);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/detail/{id}")
    @ResponseBody
    public Map<String, Object> detail(@PathVariable("id") Integer id){
        Score score = scoreService.detail(id);
        if (score == null){
           return MapControl.getInstance().noData().getMap();
        }
        return  MapControl.getInstance().success().add("data",score).getMap();
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String, Object> query(@RequestBody Score score){
        List<Score> list = scoreService.query(score);
        int count = scoreService.count(score);
        return  MapControl.getInstance().success().add("count",count).add("data",list).getMap();
    }

    @GetMapping("list")
    public String list(){
        return "score/list";
    }

    @GetMapping("listOnTeacher")
    public String listOnTeacher(){
        return "score/listOnTeacher";
    }

    @PostMapping("queryForTeacher")
    @ResponseBody
    public Map<String, Object> queryForTeacher(@RequestBody Score score){
        String clazzName = score.getClazzName();
        String courseName = score.getCourseName();

        if(clazzName != null&& clazzName.length()!=0){
            List<Clazz> clazzes = clazzService.query(null);
            clazzes.forEach(clazz -> {
                if(clazz.getClazzName().equals(clazzName)){

                    score.setClazzId(clazz.getId());
                }
            });
        }
        if(courseName != null&&courseName.length()!=0){

            List<Course> courses = courseService.query(null);
            courses.forEach(course -> {
                System.out.println(course.getCourseName());
                if(course.getCourseName().equals(courseName)){
                    System.out.println(course.getCourseName());
                    score.setCourseId(course.getId());
                }
            });
        }
        System.out.println(score);
        List<Score> scores = scoreService.queryForTeacher(score);
        int count = scores.size();

        return MapControl.getInstance().success().add("count",count).add("data",scores).getMap();
    }

    @GetMapping("score")
    public String score(Integer id, ModelMap modelMap){
        modelMap.addAttribute("id", id);
        return "score/score";

    }
}
