package com.sst.mapper;

import java.util.List;
import java.util.Map;

import com.sst.entity.Section;

public interface SectionMapper {

	public int create(Section section);

	public int delete(Integer id);

	public int update(Section section);

	public int updateSelective(Section section);

	public List<Section> query(Section section);

	public List<Section> queryStudentSection(Integer id);


	public Section detail(Integer id);

	public int count(Section section);



}