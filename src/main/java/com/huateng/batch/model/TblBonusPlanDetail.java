package com.huateng.batch.model;

import com.huateng.util.Util;

/***
 * 积分计划(带有效期)表
 * @author data
 *
 */
public class TblBonusPlanDetail {
	private String pkBonusPlanDetail; //主键
	private String usageKey;//部门标识
	private String custId;//客户号
	private String acctId;//账号
	private String bpPlanType;//积分类型
	private double totalBonus;//总产生积分
	private double validBonus;//可用积分
	private double applyBonus;//已用积分
	private double expireBonus;//过期积分 
	private String validDate;//积分失效日期
	private String expiredStatus;//到期处理状态
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
	private String lockStatus;//状态； 0-正常，1-冻结
	
	public String getPkBonusPlanDetail() {
		return pkBonusPlanDetail;
	}
	public void setPkBonusPlanDetail(String pkBonusPlanDetail) {
		this.pkBonusPlanDetail = pkBonusPlanDetail;
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
	public String getValidDate() {
		return validDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public String getExpiredStatus() {
		return expiredStatus;
	}
	public void setExpiredStatus(String expiredStatus) {
		this.expiredStatus = expiredStatus;
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
	public String getLockStatus() {
		return lockStatus;
	}
	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}
	public static String getTableClum() {
		String s= "TBL_BONUS_PLAN_DETAIL(PK_BONUS_PLAN_DETAIL,USAGE_KEY,CUST_ID,ACCT_ID,BP_PLAN_TYPE,TOTAL_BONUS,VALID_BONUS,APPLY_BONUS,EXPIRE_BONUS,VALID_DATE,EXPIRED_STATUS,CREATE_OPER,CREATE_DATE,CREATE_TIME,MODIFY_OPER,MODIFY_DATE,MODIFY_TIME,EXT_COULMN1,EXT_COULMN2,EXT_COULMN3,EXT_COULMN4,LOCK_STATUS)";
		return s;
	}
	public static String getBeanClum() {
		String s="SEQ_BONUS_PLAN_DETAIL.nextVal,:usageKey,:custId,:acctId,:bpPlanType,:totalBonus,:validBonus,:applyBonus,:expireBonus,:validDate,:expiredStatus,:createOper,:createDate,:createTime,:modifyOper,:modifyDate,:modifyTime,:extCoulmn1,:extCoulmn2,:extCoulmn3,:extCoulmn4,:lockStatus";
		return s;
	}
	
	public void init(TblBonusAccItf itf) {
		//this.setPkBonusPlanDetail(itf.get); //主键
		this.setUsageKey(itf.getUsageKey());//部门标识
		this.setCustId(itf.getCustId());//客户号
		this.setAcctId(itf.getAcctId());//账号
		this.setBpPlanType(itf.getBpPlanType());//积分类型
		this.setTotalBonus(itf.getTxnBonus());//总产生积分
		this.setValidBonus(itf.getTxnBonus());//可用积分
		this.setApplyBonus(0);//已用积分
		this.setExpireBonus(0);//过期积分 
		this.setValidDate(itf.getValidDate());//积分失效日期
		this.setExpiredStatus("0");//到期处理状态
		this.setCreateOper("SYSTEM");//创建人
		this.setCreateDate(Util.getCurrDate());//创建日期
		this.setCreateTime(Util.getCurrTime2());//创建时间
		this.setModifyOper("SYSTEM");//更新人
		this.setModifyDate(Util.getCurrDate());//更新日期
		this.setModifyTime(Util.getCurrTime2());//更新时间
		//this.setExtCoulmn1(itf.get);//扩展字段1
		//this.setExtCoulmn2(itf.get);//扩展字段2
		//this.setExtCoulmn3(itf.get);//扩展字段3
		//this.setExtCoulmn4(itf.get);//扩展字段4
		this.setLockStatus("0");//状态； 0-正常，1-冻结
	}
}
