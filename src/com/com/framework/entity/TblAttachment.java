package com.framework.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

// default package


/**
 * TblAttachment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="TblAttachment")

public class TblAttachment  implements java.io.Serializable {

	public static final String TYPE_PIC="1";//1 图片
	
	public static final String TYPE_FILE="2";//2 文件
	
	private Integer id;
	private String name;
	private String newName;
	private String url;//web访问路径
	private String filePackage;
	private String filePath;//文件绝对路径
	private String uploadUserId;
	private Date uploadTime;
	private Integer deleteStatus;//删除状态
	private String attDesc;

	// Property accessors
	@GenericGenerator(name="generator", strategy="native")
	@Id @GeneratedValue(generator="generator")
	@Column(name="id",  nullable=false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="name", length=100)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="url", length=1000)

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="filePackage", length=1000)
	public String getFilePackage() {
		return filePackage;
	}

	public void setFilePackage(String filePackage) {
		this.filePackage = filePackage;
	}
	@Column(name="uploadUserId")

	public String getUploadUserId() {
		return this.uploadUserId;
	}

	public void setUploadUserId(String uploadUserId) {
		this.uploadUserId = uploadUserId;
	}

	@Column(name="uploadTime", length=19)

	public Date getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Column(name="filePath", length=1000)
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name="deleteStatus", length=1)
	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}


	
	@Column(name="newName", length=100)
	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	
	@Transient
	public String getSrcUrl() {
		return url+File.separator+newName;
	}
	
	/*@Transient
	public String getMinUrl() {
		return filePackage+File.separator+newName.substring(0, newName.lastIndexOf("."))+"_"+ThumbsupConstant.ARTICLE_SCALE_MIN+newName.substring(newName.lastIndexOf("."));
	}
	@Transient
	public String getMidUrl() {
		return filePackage+File.separator+newName.substring(0, newName.lastIndexOf("."))+"_"+ThumbsupConstant.ARTICLE_SCALE_MID+newName.substring(newName.lastIndexOf("."));
	}*/
	@Column(name="attDesc")
	public String getAttDesc() {
		return attDesc;
	}

	public void setAttDesc(String attDesc) {
		this.attDesc = attDesc;
	}
	
	
}