package com.framework.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


/**
 * BasUsrInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="BasUser")
public class BasUser implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String userNo;
	private String userName;
	private String password;
	private Date createTime;
	private String status;
	private Integer createUserId;
	
	@GenericGenerator(name = "generator", strategy = "native")
	@Id 
	@GeneratedValue(generator = "generator")
	@Column(name="userId", nullable=false)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	@Column(name="userNo", length=50)
	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	
	
	@Column(name="userName", length=50)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="password", length=32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="createTime",columnDefinition="DATETIME")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	


	@Column(name="status",length=1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	@Column(name="createUserId",columnDefinition="INTEGER")
	public Integer getCreateUserId() {
		return createUserId;
	}
	
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}


}