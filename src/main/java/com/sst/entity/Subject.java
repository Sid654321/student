package com.sst.entity;

import com.sst.utils.Entity;
import java.util.Date;


/**
 * 
 * @author Sid
 * @time 2020-12-09 08:25:32
 */
public class Subject extends Entity{

	/**
	 * 
	 */

	private Integer id;
	/**
	 * 
	 */

	private String subjectName;
	/**
	 * 
	 */

	private String college;
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
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Subject{" +
				"id=" + id +
				", subjectName='" + subjectName + '\'' +
				", college='" + college + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}