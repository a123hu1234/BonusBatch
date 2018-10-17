package com.huateng.batch.model;

import com.huateng.util.Util;

/***
 * 积分计划表
 * @author data
 *
 */
public class TblBonusPlan {
	private String pkBonusPlan; //主键
	private String usageKey;//默认JF
	private String custId; //客户号
	private String acctId; //账号
	private String lockStatus;//状态
	private String bpPlanType;//积分类型
	private double totalBonus;//总产生积分
	private double validBonus;//可用积分
	private double applyBonus;//已用积分
	private double expireBonus;//过期积分
	private String createOper;//创建人
	private String createDate;//创建日期
	private String createTime;//创建时间
	private String modifyOper;//更新人
	private String modifyDate;//更新日期
	private String modifyTime;//更新时间
	private String extCoulmn1;//扩展字段1
	private String extCoulmn2;//扩展字段2
	private String extCoulmn3;//扩展字段3
	private double extCoulmn4;//扩展字段4

	public String getPkBonusPlan() {
		return pkBonusPlan;
	}

	public void setPkBonusPlan(String pkBonusPlan) {
		this.pkBonusPlan = pkBonusPlan;
	}

	public String getUsageKey() {
		return usageKey;
	}

	public void setUsageKey(String usageKey) {
		this.usageKey = usageKey;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public String getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getBpPlanType() {
		return bpPlanType;
	}

	public void setBpPlanType(String bpPlanType) {
		this.bpPlanType = bpPlanType;
	}

	public double getTotalBonus() {
		return totalBonus;
	}

	public void setTotalBonus(double totalBonus) {
		this.totalBonus = totalBonus;
	}

	public double getValidBonus() {
		return validBonus;
	}

	public void setValidBonus(double validBonus) {
		this.validBonus = validBonus;
	}

	public double getApplyBonus() {
		return applyBonus;
	}

	public void setApplyBonus(double applyBonus) {
		this.applyBonus = applyBonus;
	}

	public double getExpireBonus() {
		return expireBonus;
	}

	public void setExpireBonus(double expireBonus) {
		this.expireBonus = expireBonus;
	}

	public String getCreateOper() {
		return createOper;
	}

	public void setCreateOper(String createOper) {
		this.createOper = createOper;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyOper() {
		return modifyOper;
	}

	public void setModifyOper(String modifyOper) {
		this.modifyOper = modifyOper;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getExtCoulmn1() {
		return extCoulmn1;
	}

	public void setExtCoulmn1(String extCoulmn1) {
		this.extCoulmn1 = extCoulmn1;
	}

	public String getExtCoulmn2() {
		return extCoulmn2;
	}

	public void setExtCoulmn2(String extCoulmn2) {
		this.extCoulmn2 = extCoulmn2;
	}

	public String getExtCoulmn3() {
		return extCoulmn3;
	}

	public void setExtCoulmn3(String extCoulmn3) {
		this.extCoulmn3 = extCoulmn3;
	}

	public double getExtCoulmn4() {
		return extCoulmn4;
	}

	public void setExtCoulmn4(double extCoulmn4) {
		this.extCoulmn4 = extCoulmn4;
	}

	public static String getTableClum() {
		String s = "TBL_BONUS_PLAN(PK_BONUS_PLAN,USAGE_KEY,CUST_ID,ACCT_ID,LOCK_STATUS,BP_PLAN_TYPE,TOTAL_BONUS,VALID_BONUS,APPLY_BONUS,EXPIRE_BONUS,CREATE_OPER,CREATE_DATE,CREATE_TIME,MODIFY_OPER,MODIFY_DATE,MODIFY_TIME,EXT_COULMN1,EXT_COULMN2,EXT_COULMN3,EXT_COULMN4)";
		return s;
	}

	public static String getBeanClum() {
		String s = "SEQ_BONUS_PLAN.nextVal,:usageKey,:custId,:acctId,:lockStatus,:bpPlanType,:totalBonus,:validBonus,:applyBonus,:expireBonus,:createOper,:createDate,:createTime,:modifyOper,:modifyDate,:modifyTime,:extCoulmn1,:extCoulmn2,:extCoulmn3,:extCoulmn4";
		return s;
	}
	
	public void init(TblBonusAccItf itf) {
		//this.setPkBonusPlan(itf.get);//主键
		this.setUsageKey(itf.getUsageKey());//默认JF
		this.setCustId(itf.getCustId());//客户号
		this.setAcctId(itf.getAcctId());//账号
		this.setLockStatus("0");//状态
		this.setBpPlanType(itf.getBpPlanType());//积分类型
		this.setTotalBonus(itf.getTxnBonus());//总产生积分
		this.setValidBonus(itf.getTxnBonus());//可用积分
		//this.setApplyBonus(itf.get);//已用积分
		//this.setExpireBonus(itf.get);//过期积分
		this.setCreateOper("SYSTEM");//创建人
		this.setCreateDate(Util.getCurrDate());//创建日期
		this.setCreateTime(Util.getCurrTime2());//创建时间
		this.setModifyOper("SYSTEM");//更新人
		this.setModifyDate(Util.getCurrDate());//更新日期
		this.setModifyTime(Util.getCurrTime2());//更新时间
	//	this.setExtCoulmn1(itf.get);//扩展字段1
	//	this.setExtCoulmn2(itf.get);//扩展字段2
	//	this.setExtCoulmn3(itf.get);//扩展字段3
	//	this.setExtCoulmn4(itf.get);//扩展字段4
		
	}
}
