package com.hgsoft.carowner.entity;
// Generated 2015-7-29 17:16:29 by Hibernate Tools 3.4.0.CR1

/**
 * CarType generated by hbm2java
 */
public class CarType implements java.io.Serializable {

	private String cartypeId;
	private String firstLetter;
	private String makeName;
	private String modelSeries;
	private String modelName;
	private String typeSeries;
	private String typeName;
	private String country;
	private String technology;
	private String vehicleClass;
	private String engineCapacity;
	private String transmission;

	public CarType() {
	}

	public CarType(String cartypeId, String firstLetter, String makeName,
			String modelSeries, String modelName, String typeName) {
		this.cartypeId = cartypeId;
		this.firstLetter = firstLetter;
		this.makeName = makeName;
		this.modelSeries = modelSeries;
		this.modelName = modelName;
		this.typeName = typeName;
	}

	public CarType(String cartypeId, String firstLetter, String makeName,
			String modelSeries, String modelName, String typeSeries,
			String typeName, String country, String technology,
			String vehicleClass, String engineCapacity, String transmission) {
		this.cartypeId = cartypeId;
		this.firstLetter = firstLetter;
		this.makeName = makeName;
		this.modelSeries = modelSeries;
		this.modelName = modelName;
		this.typeSeries = typeSeries;
		this.typeName = typeName;
		this.country = country;
		this.technology = technology;
		this.vehicleClass = vehicleClass;
		this.engineCapacity = engineCapacity;
		this.transmission = transmission;
	}

	public String getCartypeId() {
		return this.cartypeId;
	}

	public void setCartypeId(String cartypeId) {
		this.cartypeId = cartypeId;
	}

	public String getFirstLetter() {
		return this.firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public String getMakeName() {
		return this.makeName;
	}

	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}

	public String getModelSeries() {
		return this.modelSeries;
	}

	public void setModelSeries(String modelSeries) {
		this.modelSeries = modelSeries;
	}

	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getTypeSeries() {
		return this.typeSeries;
	}

	public void setTypeSeries(String typeSeries) {
		this.typeSeries = typeSeries;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTechnology() {
		return this.technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getVehicleClass() {
		return this.vehicleClass;
	}

	public void setVehicleClass(String vehicleClass) {
		this.vehicleClass = vehicleClass;
	}

	public String getEngineCapacity() {
		return this.engineCapacity;
	}

	public void setEngineCapacity(String engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

	public String getTransmission() {
		return this.transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

}
