package com.sst.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice {

    private  final String ERROR = "error";

    @ExceptionHandler(value = PermissionException.class)
    public ModelAndView noPermission(PermissionException e){
        ModelAndView modelAndView = new ModelAndView(ERROR);
        System.out.println(e.getMessage());
        modelAndView.addObject(ERROR,e.getMessage());
        return modelAndView;
    }
    @ExceptionHandler(value=RuntimeException.class)
    @ResponseBody
    public Map<String,Object> runtimeException(RuntimeException e){
        e.printStackTrace();
        return MapControl.getInstance().error().getMap();
    }

}
