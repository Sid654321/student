package com.sst.controller;

import com.sst.entity.User;
import com.sst.service.UserService;
import com.sst.utils.MD5Utils;
import com.sst.utils.MapControl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final String LIST = "user/list";
    private static final String ADD = "user/add";
    private static final String UPDATE = "user/update";

    @Resource
    private UserService userService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> create(@RequestBody User user){
        user.setUserPwd(MD5Utils.getMD5(user.getUserPwd()));
        int result = userService.create(user);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(String ids){
        int result = userService.delete(ids);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }


    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody User user){
        int result = userService.update(user);
        if (result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        User user = userService.detail(id);
        modelMap.addAttribute("user",user);
        return UPDATE;
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String, Object> query(@RequestBody User user){
        List<User> list = userService.query(user);
        int count = userService.count(user);
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
