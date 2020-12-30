package com.sst.service;

import com.sst.entity.Job;

import javax.annotation.Resource;

import com.sst.mapper.JobMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobService {

    @Resource
    private JobMapper jobMapper;

    public int create(Job job) {
        return jobMapper.create(job);
    }

    public int delete(Integer id) {
        return jobMapper.delete(id);
    }

    public int update(Job job) {
        return jobMapper.update(job);
    }

    public int updateSelective(Job job) {
        return jobMapper.updateSelective(job);
    }

    public List<Job> query(Job job) {
        return jobMapper.query(job);
    }

    public Job detail(Integer id) {
        return jobMapper.detail(id);
    }

    public int count(Job job) {
        return jobMapper.count(job);
    }
}
