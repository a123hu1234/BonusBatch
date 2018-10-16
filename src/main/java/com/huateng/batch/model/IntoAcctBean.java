package com.huateng.batch.model;

/***
 * 
 * @author data
 *
 */
public class IntoAcctBean {
	/**
	 * 积分计划表
	 */
	private TblBonusPlan plan;
	/**
	 * 积分待入账表
	 */
	private TblBonusAccItf itf;
	/***
	 * 积分待入账详情表
	 */
	private TblBonusPlanDetail detail;
	
	
	
	public TblBonusPlan getPlan() {
		return plan;
	}
	public void setPlan(TblBonusPlan plan) {
		this.plan = plan;
	}
	public TblBonusAccItf getItf() {
		return itf;
	}
	public void setItf(TblBonusAccItf itf) {
		this.itf = itf;
	}
	public TblBonusPlanDetail getDetail() {
		return detail;
	}
	public void setDetail(TblBonusPlanDetail detail) {
		this.detail = detail;
	}
}
