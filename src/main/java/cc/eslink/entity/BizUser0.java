package cc.eslink.entity;

import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户信息
 * @author 1919
 *
 */
@NoArgsConstructor
public class BizUser0 implements java.io.Serializable{

	/** serialVersionUID */
	private static final long serialVersionUID = 6503262569292291245L;
	/** 用户ID */
	private String userId;
	/** 外部用户ID */
	private String outUserId;
	/** 用户号 */
	private String userNo;
	/** 用户类型 */
	private String userType;
	/** 用户名称 */
	private String userName;
	/** 性别 */
	private String gender;
	/** 出生日期 */
	private Date birthDate;
	/** 身份证号 */
	private String certificateNo;
	/** 证件类型 */
	private String certificateType;
	/** 联系地址 */
	private String contactAddress;
    /** 邮箱 */
    private String            mailbox;
    /** 联系人 */
    private String            contactUser;
	/** 手机 */
	private String mobile;
	/** 电话 */
	private String telephone;
	/** 单位名称 */
	private String unitName;
	/** 单位性质 */
	private String unitType;
	/** 单位描述 */
	private String unitDesc;
    /** 是否是新用户 */
    private Boolean           newUserFlag;
    /** 其他信息 */
    private String            otherInfo;
    /** 标记 */
    private String            tagIds;
	/** 状态 */
	private int state;
	/** 租户ID */
	private String tenantId;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date modifyTime;

	public BizUser0(String userId, String tenantId) {
		this.userId = userId;
		this.tenantId = tenantId;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
    public String getOutUserId() {
        return outUserId;
    }
    
    public void setOutUserId(String outUserId) {
        this.outUserId = outUserId;
    }
    public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getContactUser() {
        return contactUser;
    }

    public void setContactUser(String contactUser) {
        this.contactUser = contactUser;
    }

    public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getUnitDesc() {
		return unitDesc;
	}
	public void setUnitDesc(String unitDesc) {
		this.unitDesc = unitDesc;
	}

    public Boolean getNewUserFlag() {
        return newUserFlag;
    }

    public void setNewUserFlag(Boolean newUserFlag) {
        this.newUserFlag = newUserFlag;
    }
    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
}
