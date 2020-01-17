
package cc.eslink.entity;

import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 地址信息
 * 
 * @author 1919
 *
 */
@NoArgsConstructor
public class BizAddress0 implements java.io.Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 8415972336732855758L;
    /** 地址ID */
    private String            addressId;
    /** 外部地址ID */
    private String            outAddressId;
    /** 用户ID */
    private String            userId;
    /** 次序号 */
    private int               sortNo;
    /*** 字符型排序号 **/
    private String            sortNoNotNumeric;

    /** 省 */
    private String            province;
    /** 市 */
    private String            city;
    /** 区/县 */
    private String            county;
    /** 街道 */
    private String            street;
    /** 小区 */
    private String            housingEstateId;
    /** 楼栋号 */
    private String            buildingNo;
    /** 单元号 */
    private String            unitNo;
    /** 楼层 */
    private String            floorNo;
    /** 门牌号 */
    private String            houseNo;
    /** 地址描述 */
    private String            addressDesc;
    /** 房屋面积 */
    private String            houseSpace;
    /** 房产证号 */
    private String            houseCertNo;
    /** 建造年代 */
    private int               buildingAge;
    /** 片区 */
    private String            areasId;
    /** 状态 */
    private int               state;

    /** 是否隐藏显示 */
    private boolean           hideFlag;
    /** 分配标志位 */
    private Boolean           drawupFlag;
    /** 分配安检计划标识 */
    private boolean           distributeFlag;
    /** 是否采暖 */
    private boolean           cnFlag;
    /** 是否村村通 */
    private boolean           cctFlag;
    /** 管理所 */
    private String            manageOrg;
    /** 批次 */
    private String            batchNo;
    /** 黑名单标识 */
    private boolean           blackFlag;
    /** 修改状态 0：未修改；1：已修改 */
    private Integer           modifyState;
    /** 审核标识 0：未审核；1：审核通过；2：审核驳回 */
    private Integer           auditState;
    /** 确认标识 0：未确认；1：确认通过 */
    private Integer           confirmState;
    /** 来源 1：同步；2：新增 */
    private Integer           sourceType;
    /** 租户ID */
    private String            tenantId;
    /*用气类型*/
    private String            gasType;
    /** 创建时间 */
    private Date              createTime;
    /** 修改时间 */
    private Date              modifyTime;
    /** 开户时间 */
    private Date              openDate;
    // 地址类型
    private String            addressType;
    /** 用户来源 */
    private int               originPreId;
    /** 修改备注 */
    private String            updateRemark;

    public BizAddress0(String addressId, String tenantId) {
        this.addressId = addressId;
        this.tenantId = tenantId;
    }

    public String getUpdateRemark() {
        return updateRemark;
    }

    public void setUpdateRemark(String updateRemark) {
        this.updateRemark = updateRemark;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getOutAddressId() {
        return outAddressId;
    }

    public void setOutAddressId(String outAddressId) {
        this.outAddressId = outAddressId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public String getSortNoNotNumeric() {
        return sortNoNotNumeric;
    }

    public void setSortNoNotNumeric(String sortNoNotNumeric) {
        this.sortNoNotNumeric = sortNoNotNumeric;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHousingEstateId() {
        return housingEstateId;
    }

    public void setHousingEstateId(String housingEstateId) {
        this.housingEstateId = housingEstateId;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getAddressDesc() {
        return addressDesc;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc;
    }

    public String getHouseSpace() {
        return houseSpace;
    }

    public void setHouseSpace(String houseSpace) {
        this.houseSpace = houseSpace;
    }

    public String getHouseCertNo() {
        return houseCertNo;
    }

    public void setHouseCertNo(String houseCertNo) {
        this.houseCertNo = houseCertNo;
    }

    public int getBuildingAge() {
        return buildingAge;
    }

    public void setBuildingAge(int buildingAge) {
        this.buildingAge = buildingAge;
    }

    public String getAreasId() {
        return areasId;
    }

    public void setAreasId(String areasId) {
        this.areasId = areasId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Boolean getDrawupFlag() {
        return drawupFlag;
    }

    public void setDrawupFlag(Boolean drawupFlag) {
        this.drawupFlag = drawupFlag;
    }

    public boolean getDistributeFlag() {
        return distributeFlag;
    }

    public void setDistributeFlag(boolean distributeFlag) {
        this.distributeFlag = distributeFlag;
    }

    public String getManageOrg() {
        return manageOrg;
    }

    public void setManageOrg(String manageOrg) {
        this.manageOrg = manageOrg;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public boolean isBlackFlag() {
        return blackFlag;
    }

    public void setBlackFlag(boolean blackFlag) {
        this.blackFlag = blackFlag;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public boolean isHideFlag() {
        return hideFlag;
    }

    public void setHideFlag(boolean hideFlag) {
        this.hideFlag = hideFlag;
    }

    public String getGasType() {
        return gasType;
    }

    public void setGasType(String gasType) {
        this.gasType = gasType;
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

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Integer getModifyState() {
        return modifyState;
    }

    public void setModifyState(Integer modifyState) {
        this.modifyState = modifyState;
    }

    public Integer getAuditState() {
        return auditState;
    }

    public void setAuditState(Integer auditState) {
        this.auditState = auditState;
    }

    public Integer getConfirmState() {
        return confirmState;
    }

    public void setConfirmState(Integer confirmState) {
        this.confirmState = confirmState;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public boolean isCnFlag() {
        return cnFlag;
    }

    public void setCnFlag(boolean cnFlag) {
        this.cnFlag = cnFlag;
    }

    public boolean isCctFlag() {
        return cctFlag;
    }

    public void setCctFlag(boolean cctFlag) {
        this.cctFlag = cctFlag;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public int getOriginPreId() {
        return originPreId;
    }

    public void setOriginPreId(int originPreId) {
        this.originPreId = originPreId;
    }


}
