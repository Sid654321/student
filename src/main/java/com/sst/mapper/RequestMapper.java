package com.sst.mapper;

import java.util.List;
import java.util.Map;

import com.sst.entity.Request;

public interface RequestMapper {

	public int create(Request request);

	public int delete(Integer id);

	public int update(Request request);

	public int updateSelective(Request request);

	public List<Request> query(Request request);


	public Request detail(Integer id);

	public int count(Request request);

}