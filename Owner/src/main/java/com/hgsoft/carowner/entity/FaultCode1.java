package com.hgsoft.carowner.entity;



// Generated 2015-10-29 10:10:28 by Hibernate Tools 3.4.0.CR1

/**
 * FaultCode1 generated by hbm2java
 */
public class FaultCode1 implements java.io.Serializable {

	private Integer id;
	private String dtcId;
	private String code;
	private Integer level;
	private String faultDesc;
	private String help;
	private String advice;
	private String goloHelp;
	private String goloAdvice;

	public FaultCode1() {
	}

	public FaultCode1(String dtcId, String code, Integer level, String faultDesc,
			String help, String advice, String goloHelp, String goloAdvice) {
		this.dtcId = dtcId;
		this.code = code;
		this.level = level;
		this.faultDesc = faultDesc;
		this.help = help;
		this.advice = advice;
		this.goloHelp = goloHelp;
		this.goloAdvice = goloAdvice;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDtcId() {
		return this.dtcId;
	}

	public void setDtcId(String dtcId) {
		this.dtcId = dtcId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getHelp() {
		return this.help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public String getAdvice() {
		return this.advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getGoloHelp() {
		return this.goloHelp;
	}

	public void setGoloHelp(String goloHelp) {
		this.goloHelp = goloHelp;
	}

	public String getGoloAdvice() {
		return this.goloAdvice;
	}

	public void setGoloAdvice(String goloAdvice) {
		this.goloAdvice = goloAdvice;
	}

	public String getFaultDesc() {
		return faultDesc;
	}

	public void setFaultDesc(String faultDesc) {
		this.faultDesc = faultDesc;
	}

}
