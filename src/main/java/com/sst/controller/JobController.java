package com.sst.controller;

import com.sst.entity.Job;
import com.sst.service.JobService;
import com.sst.utils.MapControl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/job")
public class JobController {
    @Resource
    private JobService jobService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> create(Job job){
        int result = jobService.create(job);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id){
        int result = jobService.delete(id);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }


    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(Job job){
        int result = jobService.update(job);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/detail/{id}")
    @ResponseBody
    public Map<String, Object> detail(@PathVariable("id") Integer id){
        Job job = jobService.detail(id);
        if (job == null){
           return MapControl.getInstance().noData().getMap();
        }
        return  MapControl.getInstance().success().add("data",job).getMap();
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String, Object> query(Job job){

        List<Job> list = jobService.query(job);
        int count = jobService.count(job);
        return  MapControl.getInstance().success().add("data",list).getMap();
    }
}
