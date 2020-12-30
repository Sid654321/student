package com.sst.mapper;

import java.util.List;
import java.util.Map;

import com.sst.entity.Score;

public interface ScoreMapper {

	public int create(Score score);

	public int delete(Score score);

	public int update(Score score);

	public int updateSelective(Score score);

	public List<Score> query(Score score);

	public List<Score> queryForTeacher(Score score);

	public List<Integer> distinctCourse();

	public Score detail(Integer id);

	public int count(Score score);

}