package com.sst.entity;

import com.sst.utils.Entity;
import java.util.Date;


/**
 * 
 * @author Sid
 * @time 2020-12-09 08:25:32
 */
public class Section extends Entity{

	/**
	 * 
	 */

	private Integer id;

	private Integer year;


	private String type;


	private Integer clazzId;

	private Integer teacherId;


	private Integer courseId;
	private Clazz clazz;
	private  Teacher teacher;
	private  Course course;
	//0表示未选，1表示已选
	private Integer selected = 0;

	public Integer getChecked() {
		return selected;
	}

	public void setChecked(Integer selected) {
		this.selected = selected;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	private String remark;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getClazzId() {
		return clazzId;
	}
	public void setClazzId(Integer clazzId) {
		this.clazzId = clazzId;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Section{" +
				"id=" + id +
				", year=" + year +
				", type='" + type + '\'' +
				", clazzId=" + clazzId +
				", teacherId=" + teacherId +
				", courseId=" + courseId +
				", clazz=" + clazz +
				", teacher=" + teacher +
				", course=" + course +
				", remark='" + remark + '\'' +
				'}';
	}
}

