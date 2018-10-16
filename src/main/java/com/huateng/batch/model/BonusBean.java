package com.huateng.batch.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/***
 * 积分模型对象类
 * @author data
 *
 */
public class BonusBean {
	private String txnCodeOra;
	private String txnSsnOra;    
	private String txnType;
	private String custId;
	private String cardNo;
	private String productType;
	private BigDecimal txnAmt;
	private String mcthNo;
	private String mccCode;
	private String tc;
	private String custBirthday;
	private String txnDate;
	private String txnTime;
	
	private List<TblBonusAccItf> tblBonusAccInfList = new ArrayList<TblBonusAccItf>();
	
	public String getTxnCodeOra() {
		return txnCodeOra;
	}

	public void setTxnCodeOra(String txnCodeOra) {
		this.txnCodeOra = txnCodeOra;
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

	
	
	
	public List<TblBonusAccItf> getTblBonusAccInfList() {
		return tblBonusAccInfList;
	}

	public void setTblBonusAccInfList(List<TblBonusAccItf> tblBonusAccInfList) {
		this.tblBonusAccInfList = tblBonusAccInfList;
	}

	public String getTxnSsnOra() {
		return txnSsnOra;
	}

	public void setTxnSsnOra(String txnSsnOra) {
		this.txnSsnOra = txnSsnOra;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public BigDecimal getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(BigDecimal txnAmt) {
		this.txnAmt = txnAmt;
	}

	public String getMcthNo() {
		return mcthNo;
	}

	public void setMcthNo(String mcthNo) {
		this.mcthNo = mcthNo;
	}

	public String getMccCode() {
		return mccCode;
	}

	public void setMccCode(String mccCode) {
		this.mccCode = mccCode;
	}

	public String getTc() {
		return tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}

	public String getCustBirthday() {
		return custBirthday;
	}

	public void setCustBirthday(String custBirthday) {
		this.custBirthday = custBirthday;
	}

	public static String getTableClum() {
		String sReturn = "TBL_TXN_ORA_DAILY(TASK_ID,TASK_DATE,TASK_SEQ,TXN_DATE,TXN_TIME,TXN_CODE_ORA,TXN_SSN_ORA,TXN_TYPE,CUST_ID,CARD_NO,CARD_TYPE,CRCYCD,MCC_CODE,CHANNEL,MCTH_NO,MCTH_NAME,TC,TXN_AMT,TXN_BANK)";
		return sReturn;
	}
	
	public static String getBeanClum() {
		String sReturn = ":taskId,:taskDate,:taskSeq,:txnDate,:txnTime,:txnCodeOra,:txnSsnOra,:txnType,:custId,:cardNo,:cardType,:crcycd,:mccCode,:channel,:mcthNo,:mcthName,:TC,:txnAmt,:txnBank";
		return sReturn;
	}
}
