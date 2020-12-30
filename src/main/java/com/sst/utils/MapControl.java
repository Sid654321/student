package com.sst.utils;

import java.util.HashMap;
import java.util.Map;

public class MapControl {

    //目标对象
    private Map<String, Object> parammap = new HashMap<>();

    //私有构造器
    private MapControl(){

    }

    //获取实例
    public static MapControl getInstance(){
        return new MapControl();
    }
    //添加键值对
    public MapControl add(String key, Object value){
        parammap.put(key, value);
        return this;
    }

    //添加id
    public MapControl addId(Object value){
        parammap.put("id", value);
        return  this;
    }

    //添加Map
    public MapControl add (Map<String, Object> map){
        for (Map.Entry entry: map.entrySet()) {
            parammap.put((String) entry.getKey(), entry.getValue());
        }
        return  this;
    }
    //取出map
    public Map<String, Object> getMap(){
        return parammap;
    }
    //状态码：成功1000，失败1001
    public MapControl success(){
        parammap.put("code", Code.SUCCESS.getCode());
        parammap.put("msg", Code.SUCCESS.getMsg());
        return  this;
    }

    public MapControl error(){
        parammap.put("code", Code.ERROR.getCode());
        parammap.put("msg", Code.ERROR.getMsg());
        return  this;
    }

    public MapControl error(String msg){
        parammap.put("code", Code.ERROR.getCode());
        parammap.put("msg", msg);
        return  this;
    }

    public MapControl noEmpty(){
        parammap.put("code", Code.NOT_EMPTY.getCode());
        parammap.put("msg", Code.NOT_EMPTY.getMsg());
        return  this;
    }

    public MapControl noData(){
        parammap.put("code", Code.NODATA.getCode());
        parammap.put("msg", Code.NODATA.getMsg());
        return  this;
    }

    public MapControl loginError(){
        parammap.put("code", Code.LOGIN_ERROR.getCode());
        parammap.put("msg", Code.LOGIN_ERROR.getMsg());
        return  this;
    }
}
