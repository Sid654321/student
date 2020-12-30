package com.sst.entity;

import com.sst.utils.Entity;
import java.util.Date;


/**
 * 
 * @author Sid
 * @time 2020-12-09 08:25:32
 */
public class Job extends Entity{

	/**
	 * 
	 */

	private Integer id;
	/**
	 * 
	 */

	private String compName;
	/**
	 * 
	 */

	private String jobName;
	/**
	 * 
	 */

	private Integer salay;
	/**
	 * 
	 */

	private String remark;
	/**
	 * 
	 */

	private Integer stuId;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Integer getSalay() {
		return salay;
	}
	public void setSalay(Integer salay) {
		this.salay = salay;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
}