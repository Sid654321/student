package com.sst.service;

import com.github.pagehelper.PageHelper;
import com.sst.entity.Clazz;

import javax.annotation.Resource;

import com.sst.mapper.ClazzMapper;
import com.sst.utils.PageHelperUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClazzService {

    @Resource
    private ClazzMapper clazzMapper;

    public int create(Clazz clazz) {
        return clazzMapper.create(clazz);
    }

    public int delete(String ids) {
        String[] strings = ids.split(",");
        int flag = 0;
        for (String id:strings) {
          flag = clazzMapper.delete(Integer.parseInt(id));
        }
        return flag;
    }

    public int update(Clazz clazz) {
        return clazzMapper.update(clazz);
    }

    public int updateSelective(Clazz clazz) {
        return clazzMapper.updateSelective(clazz);
    }

    public List<Clazz> query(Clazz clazz) {
        PageHelperUtils.pageHelper(clazz);
        return clazzMapper.query(clazz);
    }

    public Clazz detail(Integer id) {
        return clazzMapper.detail(id);
    }

    public int count(Clazz clazz) {
        return clazzMapper.count(clazz);
    }
}
