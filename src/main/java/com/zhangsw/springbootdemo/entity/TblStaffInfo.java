package com.zhangsw.springbootdemo.entity;

// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * TblNews entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="TblStaffInfo")
public class TblStaffInfo  implements java.io.Serializable {


    // Fields    

	private Integer id;
	private Integer createStaffId;
	private Date createTime;
	private Integer updateStaffId;
	private Date updateTime;
	private String groupStaffCode;
	private String staffNumber;
	private String name;
	private Integer sex;
	private String birthPlace;
	private Date birthDate;
	private String nativePlace;
	private String nation;
	private Integer politics;
	private String idNumber;
	private String province;
	private String district;
	private String street;
	private String address;
	private String postcode;
	private Integer accountNature;
	private Integer staffType;
	private Integer staffLevel;
	private Date jobDate;
	private Date groupDate;
	private Date companyDate;
	private Integer befGroupWorkAge;
	private Integer identity;
	private Integer source;
	private String isDidMilitaryService;
	private Integer health;
	private Integer status;
	private String mobileNumber;
	private String telNumber;
	private String liveAddress;
	private String livePostcode;
	private Integer injuryLevel;
	private String isRegular;
	private Date regularDate;
	private String isArchived;
	private Integer childrenNum;
	private String salaryAccount;
	private String mealCardNo;
	private String pfbankCardNo;
	private String othercardNo;
	private String estimate;
   
    // Property accessors
    @GenericGenerator(name="generator", strategy="native")
    @Id @GeneratedValue(generator="generator")
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
    @Column(name="create_staff_id")
	public Integer getCreateStaffId() {
		return createStaffId;
	}

	public void setCreateStaffId(Integer createStaffId) {
		this.createStaffId = createStaffId;
	}
	@Column(name="update_staff_id")
	public Integer getUpdateStaffId() {
		return updateStaffId;
	}

	public void setUpdateStaffId(Integer updateStaffId) {
		this.updateStaffId = updateStaffId;
	}
	@Column(name="update_time")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name="group_staff_code")
	public String getGroupStaffCode() {
		return groupStaffCode;
	}

	public void setGroupStaffCode(String groupStaffCode) {
		this.groupStaffCode = groupStaffCode;
	}
	@Column(name="staff_number")
	public String getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name="sex")
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	@Column(name="birth_place")
	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	@Column(name="birth_date")
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	@Column(name="native_place")
	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	@Column(name="nation")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	@Column(name="politics")
	public Integer getPolitics() {
		return politics;
	}

	public void setPolitics(Integer politics) {
		this.politics = politics;
	}
	@Column(name="id_number")
	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	@Column(name="province")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	@Column(name="district")
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	@Column(name="sreet")
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	@Column(name="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="postcode")
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	@Column(name="account_nature")
	public Integer getAccountNature() {
		return accountNature;
	}

	public void setAccountNature(Integer accountNature) {
		this.accountNature = accountNature;
	}
	@Column(name="staff_type")
	public Integer getStaffType() {
		return staffType;
	}

	public void setStaffType(Integer staffType) {
		this.staffType = staffType;
	}
	@Column(name="staff_level")
	public Integer getStaffLevel() {
		return staffLevel;
	}

	public void setStaffLevel(Integer staffLevel) {
		this.staffLevel = staffLevel;
	}
	@Column(name="job_date")
	public Date getJobDate() {
		return jobDate;
	}

	public void setJobDate(Date jobDate) {
		this.jobDate = jobDate;
	}
	@Column(name="group_date")
	public Date getGroupDate() {
		return groupDate;
	}

	public void setGroupDate(Date groupDate) {
		this.groupDate = groupDate;
	}
	@Column(name="company_date")
	public Date getCompanyDate() {
		return companyDate;
	}

	public void setCompanyDate(Date companyDate) {
		this.companyDate = companyDate;
	}
	@Column(name="bef_group_work_age")
	public Integer getBefGroupWorkAge() {
		return befGroupWorkAge;
	}

	public void setBefGroupWorkAge(Integer befGroupWorkAge) {
		this.befGroupWorkAge = befGroupWorkAge;
	}
	@Column(name="identity")
	public Integer getIdentity() {
		return identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}
	@Column(name="source")
	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}
	@Column(name="is_did_military_service")
	public String getIsDidMilitaryService() {
		return isDidMilitaryService;
	}

	public void setIsDidMilitaryService(String isDidMilitaryService) {
		this.isDidMilitaryService = isDidMilitaryService;
	}
	@Column(name="health")
	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}
	@Column(name="status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name="mobile_number")
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Column(name="tel_number")
	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	@Column(name="live_address")
	public String getLiveAddress() {
		return liveAddress;
	}

	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}
	@Column(name="live_postcode")
	public String getLivePostcode() {
		return livePostcode;
	}

	public void setLivePostcode(String livePostcode) {
		this.livePostcode = livePostcode;
	}
	@Column(name="injury_level")
	public Integer getInjuryLevel() {
		return injuryLevel;
	}

	public void setInjuryLevel(Integer injuryLevel) {
		this.injuryLevel = injuryLevel;
	}
	@Column(name="is_regular")
	public String getIsRegular() {
		return isRegular;
	}

	public void setIsRegular(String isRegular) {
		this.isRegular = isRegular;
	}
	@Column(name="regular_date")
	public Date getRegularDate() {
		return regularDate;
	}

	public void setRegularDate(Date regularDate) {
		this.regularDate = regularDate;
	}
	@Column(name="is_archived")
	public String getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(String isArchived) {
		this.isArchived = isArchived;
	}
	@Column(name="children_num")
	public Integer getChildrenNum() {
		return childrenNum;
	}

	public void setChildrenNum(Integer childrenNum) {
		this.childrenNum = childrenNum;
	}
	@Column(name="salary_account")
	public String getSalaryAccount() {
		return salaryAccount;
	}
	public void setSalaryAccount(String salaryAccount) {
		this.salaryAccount = salaryAccount;
	}
	@Column(name="meal_card_no")
	public String getMealCardNo() {
		return mealCardNo;
	}

	public void setMealCardNo(String mealCardNo) {
		this.mealCardNo = mealCardNo;
	}
	@Column(name="pfbank_card_no")
	public String getPfbankCardNo() {
		return pfbankCardNo;
	}

	public void setPfbankCardNo(String pfbankCardNo) {
		this.pfbankCardNo = pfbankCardNo;
	}
	@Column(name="othercard_no")
	public String getOthercardNo() {
		return othercardNo;
	}

	public void setOthercardNo(String othercardNo) {
		this.othercardNo = othercardNo;
	}
	@Column(name="estimate")
	public String getEstimate() {
		return estimate;
	}

	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}
	
}