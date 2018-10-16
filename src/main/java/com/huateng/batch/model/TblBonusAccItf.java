package com.huateng.batch.model;
/***
 * 待入账流水表
 * @author data
 * 
 *
 */
public class TblBonusAccItf{

	// Fields

	private String taskId;
	private Long taskDateSeq;
	private String taskDate;
	private String usageKey;
	private String custId;
	/** 证件号*/
	private String custIdNum;
	/** 手机号*/
	private String custMobile;
	private String activityId;
	private String ruleId;
	private String bonusSsnOra;
	private String txnCodeOra;
	private String txnDescOra;
	private Double txnAmtOra;
	private Double txnCntOra;
	private String txnDateOra;
	private Double txnBonus;
	private String bpPlanType;
	private String validDate;
	private String flag;
	private Double validBonus;
	private Double bpValidBonus;
	
	private String acctId;
	private String acctType;
	
	private String extCoulmn1;//签约标志
	private String extCoulmn2;//交易类型
	private String extCoulmn3;//交易渠道

    private String txnTime;//交易时间
	private String firstFlag;//首笔交易标志
	private String productType;//产品类型
	private String period;//周期
	private String clientSource;//客户端来源
	private String tradeBank;//交易帐户机构号
	private String custBank;//客户机构号

	public String getFirstFlag() {
		return firstFlag;
	}

	public void setFirstFlag(String firstFlag) {
		this.firstFlag = firstFlag;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
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

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getUsageKey() {
		return this.usageKey;
	}

	public Long getTaskDateSeq() {
		return taskDateSeq;
	}




	public void setTaskDateSeq(Long taskDateSeq) {
		this.taskDateSeq = taskDateSeq;
	}




	public String getTaskDate() {
		return taskDate;
	}




	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}




	public void setUsageKey(String usageKey) {
		this.usageKey = usageKey;
	}

	public String getCustId() {
		return this.custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustIdNum() {
		return custIdNum;
	}

	public void setCustIdNum(String custIdNum) {
		this.custIdNum = custIdNum;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public String getActivityId() {
		return this.activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getBonusSsnOra() {
		return this.bonusSsnOra;
	}

	public void setBonusSsnOra(String bonusSsnOra) {
		this.bonusSsnOra = bonusSsnOra;
	}

	public String getTxnCodeOra() {
		return this.txnCodeOra;
	}

	public void setTxnCodeOra(String txnCodeOra) {
		this.txnCodeOra = txnCodeOra;
	}

	public String getTxnDescOra() {
		return this.txnDescOra;
	}

	public void setTxnDescOra(String txnDescOra) {
		this.txnDescOra = txnDescOra;
	}

	public Double getTxnAmtOra() {
		return this.txnAmtOra;
	}

	public void setTxnAmtOra(Double txnAmtOra) {
		this.txnAmtOra = txnAmtOra;
	}

	public Double getTxnCntOra() {
		return this.txnCntOra;
	}

	public void setTxnCntOra(Double txnCntOra) {
		this.txnCntOra = txnCntOra;
	}

	public String getTxnDateOra() {
		return this.txnDateOra;
	}

	public void setTxnDateOra(String txnDateOra) {
		this.txnDateOra = txnDateOra;
	}

	public Double getTxnBonus() {
		return this.txnBonus;
	}

	public void setTxnBonus(Double txnBonus) {
		this.txnBonus = txnBonus;
	}

	public String getBpPlanType() {
		return this.bpPlanType;
	}

	public void setBpPlanType(String bpPlanType) {
		this.bpPlanType = bpPlanType;
	}

	public String getValidDate() {
		return this.validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Double getValidBonus() {
		return validBonus;
	}

	public void setValidBonus(Double validBonus) {
		this.validBonus = validBonus;
	}

	public Double getBpValidBonus() {
		return bpValidBonus;
	}

	public void setBpValidBonus(Double bpValidBonus) {
		this.bpValidBonus = bpValidBonus;
	}
	
	
	
	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getClientSource() {
		return clientSource;
	}

	public void setClientSource(String clientSource) {
		this.clientSource = clientSource;
	}

	public String getTradeBank() {
		return tradeBank;
	}

	public void setTradeBank(String tradeBank) {
		this.tradeBank = tradeBank;
	}

	public String getCustBank() {
		return custBank;
	}

	public void setCustBank(String custBank) {
		this.custBank = custBank;
	}

	public static String getTableClum() {
		String sReturn = "TBL_BONUS_ACC_ITF(TASK_DATE_SEQ,TASK_DATE,USAGE_KEY,CUST_ID,ACCT_ID,ACCT_TYPE,ACTIVITY_ID,RULE_ID,BONUS_SSN_ORA,TXN_CODE_ORA,TXN_DESC_ORA,TXN_AMT_ORA,TXN_CNT_ORA,TXN_DATE_ORA,TXN_BONUS,BP_PLAN_TYPE,VALID_DATE,FLAG,EXT_COULMN1,EXT_COULMN2,EXT_COULMN3,EXT_COULMN4,MCTH_NO,MCTH_NAME,TXN_TIME,FIRST_FLAG,PRODUCT_TYPE,PERIOD,CLIENT_SOURCE,TASK_ID)";
		return sReturn;
	}
	
	public static String getBeanClum() {
		String sReturn = ":taskDateSeq,:taskDate,:usageKey,:custId,:acctId,:acctType,:activityId,:ruleId,:bonusSsnOra,:txnCodeOra,:txnDescOra,:txnAmtOra,:txnCntOra,:txnDateOra,:txnBonus,:bpPlanType,:validDate,:flag,:extCoulmn1,:extCoulmn2,:extCoulmn3,:extCoulmn4,:mcthNo,:mcthName,:txnTime,:firstFlag,:productType,:period,:clientSource,:taskId";
		return sReturn;
	}
}