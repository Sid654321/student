package com.sst.controller;

import com.sst.entity.Student;
import com.sst.entity.Teacher;
import com.sst.entity.User;
import com.sst.service.StudentService;
import com.sst.service.TeacherService;
import com.sst.service.UserService;
import com.sst.utils.MD5Utils;
import com.sst.utils.MapControl;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @Resource
    private UserService userService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private StudentService studentService;

    @GetMapping("/login")
    public String login(){
        System.out.println("login控制器");
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String,Object> login(String username, String password, String type, String captcha, HttpSession session){
        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(username)||StringUtils.isEmpty(username)||StringUtils.isEmpty(username)){
            return MapControl.getInstance().error("数据不能为空").getMap();
        }


        if (!captcha.equalsIgnoreCase((String) session.getAttribute("captcha"))){
            return MapControl.getInstance().error("验证码错误").getMap();
        }
        password = MD5Utils.getMD5(password);
        //管理员登录
        if ("1".equals(type)){
            User user = new User();
            user.setUserName(username);
            user.setUserPwd(password);
            User userData = userService.login(user);
            if (userData != null){
                session.setAttribute("user",userData);
                session.setAttribute("type",1);
                System.out.println("管理员登录");

                return MapControl.getInstance().success().add("type",1).getMap();
            }else {
                return MapControl.getInstance().loginError().getMap();
            }
        }
        //老师登录
        if ("2".equals(type)){
            Teacher teacher = new Teacher();
            teacher.setTeacherName(username);
            teacher.setTeacherPwd(password);
            Teacher teacherData = teacherService.login(teacher);
            if (teacherData != null){
                session.setAttribute("user",teacherData);
                session.setAttribute("type",2);
                System.out.println("老师登录");
                return MapControl.getInstance().success().add("type",2).getMap();
            }else {
                return MapControl.getInstance().loginError().getMap();
            }

        }
        //学生登录
        if ("3".equals(type)){
            Student student = new Student();
            student.setStuName(username);
            student.setStuPwd(password);
            System.out.println("userName:"+username);
            System.out.println("password:"+password);
            Student studentData = studentService.login(student);
            if (studentData != null){
                session.setAttribute("user",studentData);
                session.setAttribute("type",3);
                System.out.println("学生登录");
                return MapControl.getInstance().success().add("type",3).getMap();
            }else {
                return MapControl.getInstance().loginError().getMap();
            }

        }

        return MapControl.getInstance().error().getMap();
    }


}
