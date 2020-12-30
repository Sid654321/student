package com.sst.service;

import com.sst.entity.Request;

import javax.annotation.Resource;

import com.sst.mapper.RequestMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RequestService {

    @Resource
    private RequestMapper requestMapper;

    public int create(Request request) {
        return requestMapper.create(request);
    }

    public int delete(Integer id) {
        return requestMapper.delete(id);
    }

    public int update(Request request) {
        return requestMapper.update(request);
    }

    public int updateSelective(Request request) {
        return requestMapper.updateSelective(request);
    }

    public List<Request> query(Request request) {
        return requestMapper.query(request);
    }

    public Request detail(Integer id) {
        return requestMapper.detail(id);
    }

    public int count(Request request) {
        return requestMapper.count(request);
    }
}
