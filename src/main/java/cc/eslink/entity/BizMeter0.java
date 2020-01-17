
package cc.eslink.entity;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 表具信息
 * 
 * @author 1919
 *
 */
@NoArgsConstructor
public class BizMeter0 implements java.io.Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -3053856636752360993L;
    /** 表具ID */
    private String            meterId;
    /** 外部表具ID */
    private String            outMeterId;
    /** 用户ID */
    private String            userId;
    /** 地址ID */
    private String            addressId;
    /** 表号 */
    private String            meterNo;

    /** 表钢号 */
    private String            meterSteelNo;
    /** 表封号 */
    private String            meterSealNo;
    /** 表具类型 */
    private String            meterType;
    /** 表型号 */
    private String            meterModel;
    /** 表具规格 */
    private String            meterStandard;
    /** 用气类型 */
    private String            gasType;
    /** 厂商 */
    private String            manufactor;
    /** 出厂日期 */
    private Date              productDate;
    /** 安装日期 */
    private Date              installDate;
    /** 安装位置 */
    private String            installSeat;
    /** 使用年限 */
    private Integer           durableYears;
    /** 进气方向 */
    private String            inletDirection;
    /** 最大流量 */
    private String            mostFlow;
    /**
     * 开始使用时间
     */
    private String            startUseTime;
    /** 启用表见数 */
    private BigDecimal        startMeterNum;
    /** 初始表见数 */
    private BigDecimal        initMeterNum;
    /** 温度补偿类型 */
    private String            temCompType;
    /** 温度补偿系数 */
    private BigDecimal        temCompCoeff;
    /** 用气环境 */
    private String            gasEnvironment;
    /** 固定方式 */
    private String            fixedMode;
    /** 插卡方式 */
    private String            cardWay;
    /** 卡号 */
    private String            cardNo;
    /** 累计购气次数 */
    private int               totalBuygasCount;
    /** 累计购气量 */
    private BigDecimal        totalBuygasQuantity;
    /** 最后购买日期 */
    private String            lastBuyDate;
    /** 最后购买量 */
    private BigDecimal        lastBuyQuantity;
    /** 上次表读数 */
    private BigDecimal        lastMeterreadNum;
    /** 状态 */
    private int               state;
    /** 租户ID */
    private String            tenantId;
    /** 创建时间 */
    private Date              createTime;
    /** 修改时间 */
    private Date              modifyTime;

    public BizMeter0(String meterId, String tenantId) {
        this.meterId = meterId;
        this.tenantId = tenantId;
    }

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public String getOutMeterId() {
        return outMeterId;
    }

    public void setOutMeterId(String outMeterId) {
        this.outMeterId = outMeterId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getMeterNo() {
        return meterNo;
    }

    public void setMeterNo(String meterNo) {
        this.meterNo = meterNo;
    }

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public String getMeterModel() {
        return meterModel;
    }

    public void setMeterModel(String meterModel) {
        this.meterModel = meterModel;
    }

    public String getMeterStandard() {
        return meterStandard;
    }

    public void setMeterStandard(String meterStandard) {
        this.meterStandard = meterStandard;
    }

    public String getGasType() {
        return gasType;
    }

    public void setGasType(String gasType) {
        this.gasType = gasType;
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public String getInstallSeat() {
        return installSeat;
    }

    public void setInstallSeat(String installSeat) {
        this.installSeat = installSeat;
    }

    public Integer getDurableYears() {
        return durableYears;
    }

    public void setDurableYears(Integer durableYears) {
        this.durableYears = durableYears;
    }

    public String getInletDirection() {
        return inletDirection;
    }

    public void setInletDirection(String inletDirection) {
        this.inletDirection = inletDirection;
    }

    public String getMostFlow() {
        return mostFlow;
    }

    public void setMostFlow(String mostFlow) {
        this.mostFlow = mostFlow;
    }

    public BigDecimal getStartMeterNum() {
        return startMeterNum;
    }

    public void setStartMeterNum(BigDecimal startMeterNum) {
        this.startMeterNum = startMeterNum;
    }

    public BigDecimal getInitMeterNum() {
        return initMeterNum;
    }

    public void setInitMeterNum(BigDecimal initMeterNum) {
        this.initMeterNum = initMeterNum;
    }

    public String getTemCompType() {
        return temCompType;
    }

    public void setTemCompType(String temCompType) {
        this.temCompType = temCompType;
    }

    public BigDecimal getTemCompCoeff() {
        return temCompCoeff;
    }

    public void setTemCompCoeff(BigDecimal temCompCoeff) {
        this.temCompCoeff = temCompCoeff;
    }

    public String getGasEnvironment() {
        return gasEnvironment;
    }

    public void setGasEnvironment(String gasEnvironment) {
        this.gasEnvironment = gasEnvironment;
    }

    public String getFixedMode() {
        return fixedMode;
    }

    public void setFixedMode(String fixedMode) {
        this.fixedMode = fixedMode;
    }

    public String getCardWay() {
        return cardWay;
    }

    public void setCardWay(String cardWay) {
        this.cardWay = cardWay;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getTotalBuygasCount() {
        return totalBuygasCount;
    }

    public void setTotalBuygasCount(int totalBuygasCount) {
        this.totalBuygasCount = totalBuygasCount;
    }

    public BigDecimal getTotalBuygasQuantity() {
        return totalBuygasQuantity;
    }

    public void setTotalBuygasQuantity(BigDecimal totalBuygasQuantity) {
        this.totalBuygasQuantity = totalBuygasQuantity;
    }

    public String getLastBuyDate() {
        return lastBuyDate;
    }

    public void setLastBuyDate(String lastBuyDate) {
        this.lastBuyDate = lastBuyDate;
    }

    public BigDecimal getLastBuyQuantity() {
        return lastBuyQuantity;
    }

    public void setLastBuyQuantity(BigDecimal lastBuyQuantity) {
        this.lastBuyQuantity = lastBuyQuantity;
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

    public BigDecimal getLastMeterreadNum() {
        return lastMeterreadNum;
    }

    public void setLastMeterreadNum(BigDecimal lastMeterreadNum) {
        this.lastMeterreadNum = lastMeterreadNum;
    }

    public String getMeterSteelNo() {
        return meterSteelNo;
    }

    public void setMeterSteelNo(String meterSteelNo) {
        this.meterSteelNo = meterSteelNo;
    }

    public String getMeterSealNo() {
        return meterSealNo;
    }

    public void setMeterSealNo(String meterSealNo) {
        this.meterSealNo = meterSealNo;
    }

    public String getStartUseTime() {
        return startUseTime;
    }

    public void setStartUseTime(String startUseTime) {
        this.startUseTime = startUseTime;
    }
}
