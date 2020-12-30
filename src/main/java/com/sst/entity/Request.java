package com.sst.entity;

import com.sst.utils.Entity;
import java.util.Date;


/**
 * 
 * @author Sid
 * @time 2020-12-09 08:25:32
 */
public class Request extends Entity{

	/**
	 * 
	 */

	private Integer id;
	/**
	 * 
	 */

	private String title;
	/**
	 * 
	 */

	private String reason;
	/**
	 * 
	 */

	private String type;
	/**
	 * 
	 */

	private Date createDate;
	/**
	 * 
	 */

	private Integer stuId;
	/**
	 * 
	 */

	private Integer status;
	/**
	 * 
	 */

	private String attach;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
}