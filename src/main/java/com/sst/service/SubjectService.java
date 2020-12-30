package com.sst.service;

import com.github.pagehelper.PageHelper;
import com.sst.entity.Subject;

import javax.annotation.Resource;

import com.sst.mapper.SubjectMapper;
import com.sst.utils.PageHelperUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SubjectService {

    @Resource
    private SubjectMapper subjectMapper;

    public int create(Subject subject) {
        return subjectMapper.create(subject);
    }

    public int delete(String ibs) {
        String[] strings = ibs.split(",");
        int flag = 0;
        for (String id: strings) {
            flag = subjectMapper.delete(Integer.parseInt(id));
        }
        return flag;
    }

    public int update(Subject subject) {
        return subjectMapper.update(subject);
    }

    public int updateSelective(Subject subject) {
        return subjectMapper.updateSelective(subject);
    }

    public List<Subject> query(Subject subject) {
        PageHelperUtils.pageHelper(subject);
        return subjectMapper.query(subject);
    }

    public Subject detail(Integer id) {
        Subject subject = new Subject();
        subject.setId(id);
        return subjectMapper.detail(subject);
    }

    public int count(Subject subject) {
        return subjectMapper.count(subject);
    }
}
