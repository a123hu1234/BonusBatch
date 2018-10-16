package com.huateng.batch.model;

import java.math.BigDecimal;

public class TblTxnOraDaily {

	private String taskId = "";//任务编号
	private String taskDate = "";//任务时间
	private long taskSeq = 0;//任务流水号
	private String txnDate = "";//交易日期
	private String txnTime = "";//交易时间
	private String txnCodeOra = "";//交易码
	private String txnSsnOra = "";//交易流水号
	private String txnType = "";//交易类型描述
	private String custId = "";//客户号
	private String cardNo = "";//卡号
	private String cardType = "";//卡性质
	private String crcycd = "";//交易币种
	private String mccCode = "";// MCC码 
	private String channel = "";//交易渠道
	private String mcthNo = "";//商户号
	private String mcthName = "";// 商户名称
	private String TC;//TC信息
	private BigDecimal txnAmt = new BigDecimal(0);//交易金额
	private String txnBank = "";
	private String custBank = "";// 客户机构号
	private String signFlag = "";
	private String firstFlag = "";// 首次标志
	private String productType = "";// 产品类型
	private String period = "";// 周期
	private String clientSource = "";// 客户端来源
	private String ext1 = "";
	private String ext2 = "";
	private String ext3 = "";
	private String ext4 = "";
	private String ext5 = "";

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

	public String getClientSource() {
		return clientSource;
	}

	public void setClientSource(String clientSource) {
		this.clientSource = clientSource;
	}



	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId
	 *            the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the taskDate
	 */
	public String getTaskDate() {
		return taskDate;
	}

	/**
	 * @param taskDate
	 *            the taskDate to set
	 */
	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}

	/**
	 * @return the taskSeq
	 */
	public long getTaskSeq() {
		return taskSeq;
	}

	/**
	 * @param taskSeq
	 *            the taskSeq to set
	 */
	public void setTaskSeq(long taskSeq) {
		this.taskSeq = taskSeq;
	}

	/**
	 * @return the txnDate
	 */
	public String getTxnDate() {
		return txnDate;
	}

	/**
	 * @param txnDate
	 *            the txnDate to set
	 */
	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}

	/**
	 * @return the txnTime
	 */
	public String getTxnTime() {
		return txnTime;
	}

	/**
	 * @param txnTime
	 *            the txnTime to set
	 */
	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	/**
	 * @return the txnCodeOra
	 */
	public String getTxnCodeOra() {
		return txnCodeOra;
	}

	/**
	 * @param txnCodeOra
	 *            the txnCodeOra to set
	 */
	public void setTxnCodeOra(String txnCodeOra) {
		this.txnCodeOra = txnCodeOra;
	}

	/**
	 * @return the txnSsnOra
	 */
	public String getTxnSsnOra() {
		return txnSsnOra;
	}

	/**
	 * @param txnSsnOra
	 *            the txnSsnOra to set
	 */
	public void setTxnSsnOra(String txnSsnOra) {
		this.txnSsnOra = txnSsnOra;
	}

	/**
	 * @return the txnType
	 */
	public String getTxnType() {
		return txnType;
	}

	/**
	 * @param txnType
	 *            the txnType to set
	 */
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	/**
	 * @return the custId
	 */
	public String getCustId() {
		return custId;
	}

	/**
	 * @param custId
	 *            the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}

	/**
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo
	 *            the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * @param cardType
	 *            the cardType to set
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return the crcycd
	 */
	public String getCrcycd() {
		return crcycd;
	}

	/**
	 * @param crcycd
	 *            the crcycd to set
	 */
	public void setCrcycd(String crcycd) {
		this.crcycd = crcycd;
	}

	/**
	 * @return the mccCode
	 */
	public String getMccCode() {
		return mccCode;
	}

	/**
	 * @param mccCode
	 *            the mccCode to set
	 */
	public void setMccCode(String mccCode) {
		this.mccCode = mccCode;
	}

	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * @return the mcthNo
	 */
	public String getMcthNo() {
		return mcthNo;
	}

	/**
	 * @param mcthNo
	 *            the mcthNo to set
	 */
	public void setMcthNo(String mcthNo) {
		this.mcthNo = mcthNo;
	}

	/**
	 * @return the mcthName
	 */
	public String getMcthName() {
		return mcthName;
	}

	/**
	 * @param mcthName
	 *            the mcthName to set
	 */
	public void setMcthName(String mcthName) {
		this.mcthName = mcthName;
	}

	/**
	 * @return the txnAmt
	 */
	public BigDecimal getTxnAmt() {
		return txnAmt;
	}

	/**
	 * @param txnAmt
	 *            the txnAmt to set
	 */
	public void setTxnAmt(double BigDecimal) {
		this.txnAmt = txnAmt;
	}

	/**
	 * @return the txnBank
	 */
	public String getTxnBank() {
		return txnBank;
	}

	/**
	 * @param txnBank
	 *            the txnBank to set
	 */
	public void setTxnBank(String txnBank) {
		this.txnBank = txnBank;
	}

	public String getCustBank() {
		return custBank;
	}

	public void setCustBank(String custBank) {
		this.custBank = custBank;
	}

	/**
	 * @return the signFlag
	 */
	public String getSignFlag() {
		return signFlag;
	}

	/**
	 * @param signFlag
	 *            the signFlag to set
	 */
	public void setSignFlag(String signFlag) {
		this.signFlag = signFlag;
	}

	/**
	 * @return the ext1
	 */
	public String getExt1() {
		return ext1;
	}

	/**
	 * @param ext1
	 *            the ext1 to set
	 */
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	/**
	 * @return the ext2
	 */
	public String getExt2() {
		return ext2;
	}

	/**
	 * @param ext2
	 *            the ext2 to set
	 */
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	/**
	 * @return the ext3
	 */
	public String getExt3() {
		return ext3;
	}

	/**
	 * @param ext3
	 *            the ext3 to set
	 */
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	/**
	 * @return the ext4
	 */
	public String getExt4() {
		return ext4;
	}

	/**
	 * @param ext4
	 *            the ext4 to set
	 */
	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

	/**
	 * @return the ext5
	 */
	public String getExt5() {
		return ext5;
	}

	/**
	 * @param ext5
	 *            the ext5 to set
	 */
	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}

	public String toSqlldrDataStr() {
		StringBuffer sqlldrStr = new StringBuffer();
		sqlldrStr.append(taskId).append("|");
		sqlldrStr.append(taskDate).append("|");
		sqlldrStr.append(taskSeq).append("|");
		sqlldrStr.append(txnDate).append("|");
		sqlldrStr.append(txnTime).append("|");
		sqlldrStr.append(txnCodeOra).append("|");
		sqlldrStr.append(txnSsnOra).append("|");
		sqlldrStr.append(txnType).append("|");
		sqlldrStr.append(custId).append("|");
		sqlldrStr.append(cardNo).append("|");
		sqlldrStr.append(cardType).append("|");
		sqlldrStr.append(crcycd).append("|");
		sqlldrStr.append(mccCode).append("|");
		sqlldrStr.append(channel).append("|");
		sqlldrStr.append(mcthNo).append("|");
		sqlldrStr.append(mcthName).append("|");
		sqlldrStr.append(txnAmt).append("|");
		sqlldrStr.append(txnBank).append("|");
		sqlldrStr.append(signFlag).append("|");
		sqlldrStr.append(firstFlag).append("|");
		sqlldrStr.append(productType).append("|");
		sqlldrStr.append(period).append("|");
		sqlldrStr.append(clientSource).append("|");
		sqlldrStr.append(custBank).append("|");

		sqlldrStr.append(ext1).append("|");
		sqlldrStr.append(ext2).append("|");
		sqlldrStr.append(ext3).append("|");
		sqlldrStr.append(ext4).append("|");
		sqlldrStr.append(ext5).append("|");

		return sqlldrStr.toString();
	}

	public String getTC() {
		return TC;
	}

	public void setTC(String tC) {
		TC = tC;
	}

	public void setTxnAmt(BigDecimal txnAmt) {
		this.txnAmt = txnAmt;
	}
	
	public static String[] toArray() {
		//交易日期,交易时间,交易码,交易流水号,交易类型描述,客户号,卡号,卡性质,交易币种,MCC码,交易渠道,商户号,商户名称,TC信息,交易金额,客户机构号
		String[] array = new String[] {"TXN_DATE","TXN_TIME","TXN_CODE_ORA","TXN_SSN_ORA","TXN_TYPE","CUST_ID","CARD_NO", "CARD_TYPE","CRCYCD","MCC_CODE","CHANNEL","MCTH_NO","MCTH_NAME","TC","TXN_AMT","TXN_BANK"};
		return array;
	}
	
	/***
	 * 
	 * @return
	 */
	public static String getTableClum() {
		String sReturn = "TBL_TXN_ORA_DAILY(TASK_ID,TASK_DATE,TASK_SEQ,TXN_DATE,TXN_TIME,TXN_CODE_ORA,TXN_SSN_ORA,TXN_TYPE,CUST_ID,CARD_NO,CARD_TYPE,CRCYCD,MCC_CODE,CHANNEL,MCTH_NO,MCTH_NAME,TC,TXN_AMT,TXN_BANK)";
		return sReturn;
	}
	public static String getBeanClum() {
		String sReturn = ":taskId,:taskDate,:taskSeq,:txnDate,:txnTime,:txnCodeOra,:txnSsnOra,:txnType,:custId,:cardNo,:cardType,:crcycd,:mccCode,:channel,:mcthNo,:mcthName,:TC,:txnAmt,:txnBank";
		return sReturn;
	}
}
