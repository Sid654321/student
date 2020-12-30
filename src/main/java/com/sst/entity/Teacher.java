package com.sst.entity;

import com.sst.utils.Entity;
import java.util.Date;


/**
 * 
 * @author Sid
 * @time 2020-12-09 08:25:32
 */
public class Teacher extends Entity{

	/**
	 * 
	 */

	private Integer id;
	/**
	 * 
	 */

	private String teacherName;
	/**
	 * 
	 */

	private String teacherPwd;
	/**
	 * 
	 */

	private String remark;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherPwd() {
		return teacherPwd;
	}
	public void setTeacherPwd(String teacherPwd) {
		this.teacherPwd = teacherPwd;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}