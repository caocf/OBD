package com.hgsoft.carowner.entity;

// Generated 2015-8-17 16:10:37 by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * CarTest generated by hbm2java
 */
public class CarTest implements java.io.Serializable {

	private String id;
	private String obdSn;
	private Character engine;
	private Character power;
	private Character throttle;
	private Character discharge;
	private Character cooling;
	private Character idlingHigh;
	private Character idlingLow;
	private Character backup;
	private Date createTime;
	private Character valid;
	private String remark;

	public CarTest() {
	}

	public CarTest(String id) {
		this.id = id;
	}

	public CarTest(String id, String obdSn, Character engine, Character power,
			Character throttle, Character discharge, Character cooling,
			Character idlingHigh, Character idlingLow, Character backup,
			Date createTime, Character valid, String remark) {
		this.id = id;
		this.obdSn = obdSn;
		this.engine = engine;
		this.power = power;
		this.throttle = throttle;
		this.discharge = discharge;
		this.cooling = cooling;
		this.idlingHigh = idlingHigh;
		this.idlingLow = idlingLow;
		this.backup = backup;
		this.createTime = createTime;
		this.valid = valid;
		this.remark = remark;
	}

	public String getId() {
		return this.id;
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

	public Character getEngine() {
		return this.engine;
	}

	public void setEngine(Character engine) {
		this.engine = engine;
	}

	public Character getPower() {
		return this.power;
	}

	public void setPower(Character power) {
		this.power = power;
	}

	public Character getThrottle() {
		return this.throttle;
	}

	public void setThrottle(Character throttle) {
		this.throttle = throttle;
	}

	public Character getDischarge() {
		return this.discharge;
	}

	public void setDischarge(Character discharge) {
		this.discharge = discharge;
	}

	public Character getCooling() {
		return this.cooling;
	}

	public void setCooling(Character cooling) {
		this.cooling = cooling;
	}

	public Character getIdlingHigh() {
		return this.idlingHigh;
	}

	public void setIdlingHigh(Character idlingHigh) {
		this.idlingHigh = idlingHigh;
	}

	public Character getIdlingLow() {
		return this.idlingLow;
	}

	public void setIdlingLow(Character idlingLow) {
		this.idlingLow = idlingLow;
	}

	public Character getBackup() {
		return this.backup;
	}

	public void setBackup(Character backup) {
		this.backup = backup;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Character getValid() {
		return this.valid;
	}

	public void setValid(Character valid) {
		this.valid = valid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
