package com.hgsoft.carowner.entity;


import java.util.Date;

/**
 * Wifi generated by hbm2java
 */
public class Wifi implements java.io.Serializable {

	private String id;
	private String obdSn;
	private String ssid;
	private String autu;
	private String encrypt;
	private String wifiPwd;
	private String wifiState;
	private String recovery;
	private Date createTime;

	public Wifi() {
	}

	public Wifi(String obdSn, String ssid, String autu, String encrypt,
			String wifiPwd, String wifiState, String recovery, Date createTime) {
		this.obdSn = obdSn;
		this.ssid = ssid;
		this.autu = autu;
		this.encrypt = encrypt;
		this.wifiPwd = wifiPwd;
		this.wifiState = wifiState;
		this.recovery = recovery;
		this.createTime = createTime;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getObdSn() {
		return this.obdSn;
	}

	public void setObdSn(String obdSn) {
		this.obdSn = obdSn;
	}

	public String getSsid() {
		return this.ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getAutu() {
		return this.autu;
	}

	public void setAutu(String autu) {
		this.autu = autu;
	}

	public String getEncrypt() {
		return this.encrypt;
	}

	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}

	public String getWifiPwd() {
		return this.wifiPwd;
	}

	public void setWifiPwd(String wifiPwd) {
		this.wifiPwd = wifiPwd;
	}

	public String getWifiState() {
		return this.wifiState;
	}

	public void setWifiState(String wifiState) {
		this.wifiState = wifiState;
	}

	public String getRecovery() {
		return this.recovery;
	}

	public void setRecovery(String recovery) {
		this.recovery = recovery;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
