package com.sst.controller;

import com.sst.entity.Request;
import com.sst.service.RequestService;
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
@RequestMapping("/request")
public class RequestController {
    @Resource
    private RequestService requestService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> create(Request request){
        int result = requestService.create(request);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id){
        int result = requestService.delete(id);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }


    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(Request request){
        int result = requestService.update(request);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/detail/{id}")
    @ResponseBody
    public Map<String, Object> detail(@PathVariable("id") Integer id){
        Request request = requestService.detail(id);
        if (request == null){
           return MapControl.getInstance().noData().getMap();
        }
        return  MapControl.getInstance().success().add("data",request).getMap();
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String, Object> query(Request request){

        List<Request> list = requestService.query(request);
        int count = requestService.count(request);
        return  MapControl.getInstance().success().add("data",list).getMap();
    }
}
