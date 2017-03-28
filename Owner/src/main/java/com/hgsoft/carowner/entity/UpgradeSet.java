package com.hgsoft.carowner.entity;


import java.util.Date;

/**
 * Wifi generated by hbm2java
 */
public class UpgradeSet implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String obdSn;
	private String version;
	private String firmVersion;
	private Integer firmType;
	private String sendFlag;
	private Date createTime;
	private Date updateTime;
	private String valid;//是否下发升级请求0是1否
	private String validTrue;//是否真正下发升级请求0是默认为空
	private String auditOper;
	private Date auditTime;
	private String auditState;
	private String upgradeFlag;//升级状态
	private String vflag;//是否有效
	private Integer sendedCount;
	private Integer success;
	private Integer obdSpeed;
	private Integer gpsSpeed;
	private Integer speedCount;
	private String lastSpeedType;
	
	public UpgradeSet() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObdSn() {
		return obdSn;
	}

	public void setObdSn(String obdSn) {
		this.obdSn = obdSn;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(String sendFlag) {
		this.sendFlag = sendFlag;
	}

	public String getAuditOper() {
		return auditOper;
	}

	public void setAuditOper(String auditOper) {
		this.auditOper = auditOper;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public String getUpgradeFlag() {
		return upgradeFlag;
	}

	public void setUpgradeFlag(String upgradeFlag) {
		this.upgradeFlag = upgradeFlag;
	}

	public String getVflag() {
		return vflag;
	}

	public void setVflag(String vflag) {
		this.vflag = vflag;
	}


	public String getFirmVersion() {
		return firmVersion;
	}

	public void setFirmVersion(String firmVersion) {
		this.firmVersion = firmVersion;
	}

	public String getValidTrue() {
		return validTrue;
	}

	public void setValidTrue(String validTrue) {
		this.validTrue = validTrue;
	}

	public Integer getFirmType() {
		return firmType;
	}

	public void setFirmType(Integer firmType) {
		this.firmType = firmType;
	}

	public Integer getSendedCount() {
		return sendedCount;
	}

	public void setSendedCount(Integer sendedCount) {
		this.sendedCount = sendedCount;
	}

	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	
	public Integer getObdSpeed() {
		return obdSpeed;
	}

	public void setObdSpeed(Integer obdSpeed) {
		this.obdSpeed = obdSpeed;
	}

	public Integer getGpsSpeed() {
		return gpsSpeed;
	}

	public void setGpsSpeed(Integer gpsSpeed) {
		this.gpsSpeed = gpsSpeed;
	}

	public Integer getSpeedCount() {
		return speedCount;
	}

	public void setSpeedCount(Integer speedCount) {
		this.speedCount = speedCount;
	}

	public String getLastSpeedType() {
		return lastSpeedType;
	}

	public void setLastSpeedType(String lastSpeedType) {
		this.lastSpeedType = lastSpeedType;
	}

	@Override
	public String toString() {
		return "UpgradeSet [id=" + id + ", obdSn=" + obdSn + ", version=" + version + ", firmVersion=" + firmVersion
				+ ", firmType=" + firmType + ", sendFlag=" + sendFlag + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", valid=" + valid + ", validTrue=" + validTrue + ", auditOper=" + auditOper
				+ ", auditTime=" + auditTime + ", auditState=" + auditState + ", upgradeFlag=" + upgradeFlag
				+ ", vflag=" + vflag + ", sendedCount=" + sendedCount + ", success=" + success + ", obdSpeed="
				+ obdSpeed + ", gpsSpeed=" + gpsSpeed + ", speedCount=" + speedCount + ", lastSpeedType="
				+ lastSpeedType + "]";
	}

}
