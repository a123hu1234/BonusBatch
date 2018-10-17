package com.huateng.batch.model;

import com.huateng.constants.Constants;
import com.huateng.util.Util;

/**
 * 积分入账记录表
 * @author 11299
 *
 */
public class TblBonusDetail {
	private double pkBonusDetail;
	private String custId;
	private String acctId;
	private String usageKey;
	private String txnType;
	private String txnCode;
	private String bonusCdFlag;
	private String bonusSsn;
	private String bpPlanType;
	private String validDate;
	private double txnBonus;
	private double validBonus;
	private double bpValidBonus;
	private String activityId;
	private String ruleId;
	private String bonusSsnOra;
	private String txnCodeOra;
	private String txnDescOra;
	private double txnAmtOra;
	private double txnCntOra;
	private String txnDateOra;
	private String txnDate;
	private String txnTime;
	private String createDate;
	private String createTime;
	private String stlmDate;
	private String activityNm;
	private String ruleNm;
	private String detailDesc;
	private String channelNo;
	private String extCoulmn1;
	private String extCoulmn2;
	private String extCoulmn3;
	private double extCoulmn4;
	private String returnFlag;
	private String merchants;
	
	public double getPkBonusDetail() {
		return pkBonusDetail;
	}
	public void setPkBonusDetail(double pkBonusDetail) {
		this.pkBonusDetail = pkBonusDetail;
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
	public String getUsageKey() {
		return usageKey;
	}
	public void setUsageKey(String usageKey) {
		this.usageKey = usageKey;
	}
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public String getTxnCode() {
		return txnCode;
	}
	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}
	public String getBonusCdFlag() {
		return bonusCdFlag;
	}
	public void setBonusCdFlag(String bonusCdFlag) {
		this.bonusCdFlag = bonusCdFlag;
	}
	public String getBonusSsn() {
		return bonusSsn;
	}
	public void setBonusSsn(String bonusSsn) {
		this.bonusSsn = bonusSsn;
	}
	public String getBpPlanType() {
		return bpPlanType;
	}
	public void setBpPlanType(String bpPlanType) {
		this.bpPlanType = bpPlanType;
	}
	public String getValidDate() {
		return validDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public double getTxnBonus() {
		return txnBonus;
	}
	public void setTxnBonus(double txnBonus) {
		this.txnBonus = txnBonus;
	}
	public double getValidBonus() {
		return validBonus;
	}
	public void setValidBonus(double validBonus) {
		this.validBonus = validBonus;
	}
	public double getBpValidBonus() {
		return bpValidBonus;
	}
	public void setBpValidBonus(double bpValidBonus) {
		this.bpValidBonus = bpValidBonus;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getBonusSsnOra() {
		return bonusSsnOra;
	}
	public void setBonusSsnOra(String bonusSsnOra) {
		this.bonusSsnOra = bonusSsnOra;
	}
	public String getTxnCodeOra() {
		return txnCodeOra;
	}
	public void setTxnCodeOra(String txnCodeOra) {
		this.txnCodeOra = txnCodeOra;
	}
	public String getTxnDescOra() {
		return txnDescOra;
	}
	public void setTxnDescOra(String txnDescOra) {
		this.txnDescOra = txnDescOra;
	}
	public double getTxnAmtOra() {
		return txnAmtOra;
	}
	public void setTxnAmtOra(double txnAmtOra) {
		this.txnAmtOra = txnAmtOra;
	}
	public double getTxnCntOra() {
		return txnCntOra;
	}
	public void setTxnCntOra(double txnCntOra) {
		this.txnCntOra = txnCntOra;
	}
	public String getTxnDateOra() {
		return txnDateOra;
	}
	public void setTxnDateOra(String txnDateOra) {
		this.txnDateOra = txnDateOra;
	}
	public String getTxnDate() {
		return txnDate;
	}
	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}
	public String getTxnTime() {
		return txnTime;
	}
	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
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
	public String getStlmDate() {
		return stlmDate;
	}
	public void setStlmDate(String stlmDate) {
		this.stlmDate = stlmDate;
	}
	public String getActivityNm() {
		return activityNm;
	}
	public void setActivityNm(String activityNm) {
		this.activityNm = activityNm;
	}
	public String getRuleNm() {
		return ruleNm;
	}
	public void setRuleNm(String ruleNm) {
		this.ruleNm = ruleNm;
	}
	public String getDetailDesc() {
		return detailDesc;
	}
	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}
	public String getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
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
	public String getReturnFlag() {
		return returnFlag;
	}
	public void setReturnFlag(String returnFlag) {
		this.returnFlag = returnFlag;
	}
	public String getMerchants() {
		return merchants;
	}
	public void setMerchants(String merchants) {
		this.merchants = merchants;
	}
	
	public static String getTableClum() {
		String s = "TBL_BONUS_DETAIL(PK_BONUS_DETAIL,MERCHANTS,TXN_BONUS,VALID_BONUS,BP_VALID_BONUS,TXN_AMT_ORA,TXN_CNT_ORA,EXT_COULMN4,CUST_ID,ACCT_ID,USAGE_KEY,TXN_TYPE,TXN_CODE,BONUS_CD_FLAG,BONUS_SSN,BP_PLAN_TYPE,VALID_DATE,ACTIVITY_ID,RULE_ID,BONUS_SSN_ORA,TXN_CODE_ORA,TXN_DESC_ORA,TXN_DATE_ORA,TXN_DATE,TXN_TIME,CREATE_DATE,CREATE_TIME,STLM_DATE,ACTIVITY_NM,RULE_NM,DETAIL_DESC,CHANNEL_NO,EXT_COULMN1,EXT_COULMN2,EXT_COULMN3,RETURN_FLAG)";
		return s;
	}

	public static String getBeanClum() {
		String s = "SEQ_BONUS_DETAIL.nextVal,:merchants,:txnBonus,:validBonus,:bpValidBonus,:txnAmtOra,:txnCntOra,:extCoulmn4,:custId,:acctId,:usageKey,:txnType,:txnCode,:bonusCdFlag,:bonusSsn,:bpPlanType,:validDate,:activityId,:ruleId,:bonusSsnOra,:txnCodeOra,:txnDescOra,:txnDateOra,:txnDate,:txnTime,:createDate,:createTime,:stlmDate,:activityNm,:ruleNm,:detailDesc,:channelNo,:extCoulmn1,:extCoulmn2,:extCoulmn3,:returnFlag";
		return s;
	}
	
	public void init(TblBonusAccItf itf,String SSN) {
		this.setCustId(itf.getCustId());// CUST_ID, 客户号
		this.setAcctId(itf.getAcctId());//ACCT_ID, 账号
		this.setUsageKey(itf.getUsageKey());// USAGE_KEY, 部门标识
		this.setTxnType(" ");// TXN_TYPE, 交易类型
		this.setTxnCode(Constants.TXN_CODE_BONUS_ADD);// TXN_CODE, 交易代码
		//this.setBonusCdFlag();
		this.setBonusSsn(SSN);
		this.setBpPlanType(itf.getBpPlanType());
		this.setValidDate(itf.getValidDate());
		this.setTxnBonus(itf.getTxnBonus());
		this.setValidBonus(itf.getTxnBonus());
		this.setBpValidBonus(itf.getTxnBonus());
		this.setActivityId(itf.getActivityId());
		this.setRuleId(itf.getRuleId());
		this.setBonusSsnOra(itf.getBonusSsnOra());// BONUS_SSN_ORA, 原交易键值
		this.setTxnCodeOra(itf.getTxnCodeOra());// TXN_CODE_ORA, 原业务数据类型
		this.setTxnDescOra(itf.getTxnDescOra());// TXN_DESC_ORA, 原交易描述
		this.setTxnAmtOra(itf.getTxnAmtOra());// TXN_AMT_ORA, 原交易金额 积分计算的金额值
		this.setTxnCntOra(itf.getTxnCntOra());// TXN_CNT_ORA, 原交易笔数 统计类才有
		this.setTxnDateOra(itf.getTxnDateOra());// TXN_DATE_ORA, 原交易日期
		this.setTxnDate(itf.getTxnDateOra());
		this.setTxnTime(itf.getTxnTime() == null?"000000":itf.getTxnTime());
		this.setCreateDate(Util.getCurrDate());
		this.setCreateTime(Util.getCurrTime2());
		this.setStlmDate(Util.getCurrDate());
		this.setActivityNm("");
		this.setRuleNm("");
		this.setDetailDesc("");
		this.setChannelNo("");
		//this.setExtCoulmn1();
		//this.setExtCoulmn2();
		//this.setExtCoulmn3();
		//this.setExtCoulmn4();
		this.setReturnFlag("0");
	//	this.setMerchants();
	}
}
