package com.sst.entity;

import com.sst.utils.Entity;
import java.util.Date;


/**
 * 
 * @author Sid
 * @time 2020-12-09 08:25:32
 */
public class Student extends Entity{

	private Integer id;
	private String stuNo;
	private String stuName;
	private String stuPwd;
	private String cardNo;
	private String agender;
	private Date birthday;
	private String phone;
	private String panme;
	private String telephone;
	private String addr;
	private Date joinDate;
	private String status;
	private Integer clazzId;
	private Integer subjectId;

	private Clazz clazz;
	private  Subject subject;
	private Integer teacherId;
	private String clazzName;

	public String getClazzName() {
		return clazzName;
	}

	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuPwd() {
		return stuPwd;
	}
	public void setStuPwd(String stuPwd) {
		this.stuPwd = stuPwd;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getAgender() {
		return agender;
	}
	public void setAgender(String agender) {
		this.agender = agender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPanme() {
		return panme;
	}
	public void setPanme(String panme) {
		this.panme = panme;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getClazzId() {
		return clazzId;
	}
	public void setClazzId(Integer clazzId) {
		this.clazzId = clazzId;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", stuNo='" + stuNo + '\'' +
				", stuName='" + stuName + '\'' +
				", stuPwd='" + stuPwd + '\'' +
				", cardNo='" + cardNo + '\'' +
				", agender='" + agender + '\'' +
				", birthday=" + birthday +
				", phone='" + phone + '\'' +
				", panme='" + panme + '\'' +
				", telephone='" + telephone + '\'' +
				", addr='" + addr + '\'' +
				", joinDate=" + joinDate +
				", status='" + status + '\'' +
				", clazzId=" + clazzId +
				", subjectId=" + subjectId +
				", clazz=" + clazz +
				", subject=" + subject +
				", teacherId=" + teacherId +
				'}';
	}
}