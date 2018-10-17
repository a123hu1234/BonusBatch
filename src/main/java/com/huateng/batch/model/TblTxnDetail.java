package com.huateng.batch.model;

import com.huateng.constants.Constants;
import com.huateng.util.Util;

/**
 * 积分交易记录表
 * @author data
 *
 */
public class TblTxnDetail {
	private double pkTxnDetail;
	private String usageKey;
	private String acqSsn;
	private String bonusSsn;
	private String keyReversal;
	private String chnlNo;
	private String txnDate;
	private String txnTime;
	private String txnType;
	private String txnCode;
	private String txnDesc;
	private String txnExtInfo;
	private String custId;
	private String acctId;
	private String bpPlanType;
	private String custIdRef;
	private String acctIdRef;
	private String bpPlanTypeRef;
	private double txnBonus;
	private String bonusCdFlag;
	private String txnMchtNo;
	private String brhId;
	private String replyCode;
	private String txnStatus;
	private String replyMessage;
	private String createDate;
	private String createTime;
	private String orderId;
	private String oprUser;
	private String checkUser;
	private double extCoulmn4;
	private String extCoulmn3;
	private String extCoulmn2;
	private String extCoulmn1;
	private String oraTxnDate;
	private String returnFlag;
	private String productType;
	private String period;
	private String clientSource;
	public double getPkTxnDetail() {
		return pkTxnDetail;
	}
	public void setPkTxnDetail(double pkTxnDetail) {
		this.pkTxnDetail = pkTxnDetail;
	}
	public String getUsageKey() {
		return usageKey;
	}
	public void setUsageKey(String usageKey) {
		this.usageKey = usageKey;
	}
	public String getAcqSsn() {
		return acqSsn;
	}
	public void setAcqSsn(String acqSsn) {
		this.acqSsn = acqSsn;
	}
	public String getBonusSsn() {
		return bonusSsn;
	}
	public void setBonusSsn(String bonusSsn) {
		this.bonusSsn = bonusSsn;
	}
	public String getKeyReversal() {
		return keyReversal;
	}
	public void setKeyReversal(String keyReversal) {
		this.keyReversal = keyReversal;
	}
	public String getChnlNo() {
		return chnlNo;
	}
	public void setChnlNo(String chnlNo) {
		this.chnlNo = chnlNo;
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
	public String getTxnDesc() {
		return txnDesc;
	}
	public void setTxnDesc(String txnDesc) {
		this.txnDesc = txnDesc;
	}
	public String getTxnExtInfo() {
		return txnExtInfo;
	}
	public void setTxnExtInfo(String txnExtInfo) {
		this.txnExtInfo = txnExtInfo;
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
	public String getCustIdRef() {
		return custIdRef;
	}
	public void setCustIdRef(String custIdRef) {
		this.custIdRef = custIdRef;
	}
	public String getAcctIdRef() {
		return acctIdRef;
	}
	public void setAcctIdRef(String acctIdRef) {
		this.acctIdRef = acctIdRef;
	}
	public String getBpPlanTypeRef() {
		return bpPlanTypeRef;
	}
	public void setBpPlanTypeRef(String bpPlanTypeRef) {
		this.bpPlanTypeRef = bpPlanTypeRef;
	}
	public double getTxnBonus() {
		return txnBonus;
	}
	public void setTxnBonus(double txnBonus) {
		this.txnBonus = txnBonus;
	}
	public String getBonusCdFlag() {
		return bonusCdFlag;
	}
	public void setBonusCdFlag(String bonusCdFlag) {
		this.bonusCdFlag = bonusCdFlag;
	}
	public String getTxnMchtNo() {
		return txnMchtNo;
	}
	public void setTxnMchtNo(String txnMchtNo) {
		this.txnMchtNo = txnMchtNo;
	}
	public String getBrhId() {
		return brhId;
	}
	public void setBrhId(String brhId) {
		this.brhId = brhId;
	}
	public String getReplyCode() {
		return replyCode;
	}
	public void setReplyCode(String replyCode) {
		this.replyCode = replyCode;
	}
	public String getTxnStatus() {
		return txnStatus;
	}
	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}
	public String getReplyMessage() {
		return replyMessage;
	}
	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
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
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOprUser() {
		return oprUser;
	}
	public void setOprUser(String oprUser) {
		this.oprUser = oprUser;
	}
	public String getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}
	public double getExtCoulmn4() {
		return extCoulmn4;
	}
	public void setExtCoulmn4(double extCoulmn4) {
		this.extCoulmn4 = extCoulmn4;
	}
	public String getExtCoulmn3() {
		return extCoulmn3;
	}
	public void setExtCoulmn3(String extCoulmn3) {
		this.extCoulmn3 = extCoulmn3;
	}
	public String getExtCoulmn2() {
		return extCoulmn2;
	}
	public void setExtCoulmn2(String extCoulmn2) {
		this.extCoulmn2 = extCoulmn2;
	}
	public String getExtCoulmn1() {
		return extCoulmn1;
	}
	public void setExtCoulmn1(String extCoulmn1) {
		this.extCoulmn1 = extCoulmn1;
	}
	public String getOraTxnDate() {
		return oraTxnDate;
	}
	public void setOraTxnDate(String oraTxnDate) {
		this.oraTxnDate = oraTxnDate;
	}
	public String getReturnFlag() {
		return returnFlag;
	}
	public void setReturnFlag(String returnFlag) {
		this.returnFlag = returnFlag;
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
	public static String getTableClum() {
		String s = "TBL_TXN_DETAIL(PK_TXN_DETAIL, USAGE_KEY,ACQ_SSN,BONUS_SSN,KEY_REVERSAL,CHNL_NO,TXN_DATE,TXN_TIME,TXN_TYPE,TXN_CODE,TXN_DESC,TXN_EXT_INFO,CUST_ID,ACCT_ID,BP_PLAN_TYPE,CUST_ID_REF,ACCT_ID_REF,BP_PLAN_TYPE_REF,TXN_BONUS,BONUS_CD_FLAG,TXN_MCHT_NO,BRH_ID,REPLY_CODE,TXN_STATUS,REPLY_MESSAGE,CREATE_DATE,CREATE_TIME,ORDER_ID,OPR_USER,CHECK_USER,EXT_COULMN4,EXT_COULMN3,EXT_COULMN2,EXT_COULMN1,ORA_TXN_DATE,RETURN_FLAG,PRODUCT_TYPE,PERIOD,CLIENT_SOURCE)";
		return s;
	}
	public static String getBeanClum() {
		String s = "SEQ_TXN_DETAIL.nextVal,:usageKey,:acqSsn,:bonusSsn,:keyReversal,:chnlNo,:txnDate,:txnTime,:txnType,:txnCode,:txnDesc,:txnExtInfo,:custId,:acctId,:bpPlanType,:custIdRef,:acctIdRef,:bpPlanTypeRef,:txnBonus,:bonusCdFlag,:txnMchtNo,:brhId,:replyCode,:txnStatus,:replyMessage,:createDate,:createTime,:orderId,:oprUser,:checkUser,:extCoulmn4,:extCoulmn3,:extCoulmn2,:extCoulmn1,:oraTxnDate,:returnFlag,:productType,:period,:clientSource";
		return s;
	}
	
	public void init(TblBonusAccItf itf,String SSN) {
		this.setUsageKey(itf.getUsageKey());//
		this.setAcqSsn("");//ACQ_SSN, 无前端流水号 
		this.setBonusSsn(SSN);// BONUS_SSN,
		this.setKeyReversal("");// KEY_REVERSAL, 批量积分不冲正
		this.setChnlNo("");// CHNL_NO, 交易渠道
		this.setTxnDate(itf.getTxnDateOra());//TXN_DATE,
		this.setTxnTime(itf.getTxnTime() == null ? "000000":itf.getTxnTime());// TXN_TIME,
		this.setTxnType("");//TXN_TYPE,
		this.setTxnCode( Constants.TXN_CODE_BONUS_ADD);  //9001 积分产生
		this.setTxnDesc(itf.getTxnDescOra());// TXN_DESC,
		this.setTxnExtInfo("");// TXN_EXT_INFO,
		this.setCustId(itf.getCustId());
		this.setAcctId(itf.getAcctId());
		this.setBpPlanType(itf.getBpPlanType());
		this.setCustIdRef("");
		this.setAcctIdRef("");
		this.setBpPlanTypeRef("");
		this.setTxnBonus(itf.getTxnBonus());
		this.setBonusCdFlag(Constants.bonusCdFlag.add.getValue());// BONUS_CD_FLAG,
		this.setTxnMchtNo("");
		this.setBrhId("");//机构号
		this.setReplyCode("");// REPLY_CODE, 应答码
		this.setTxnStatus("0");// TXN_STATUS, 详见6.12交易标志0=正常
		this.setReplyMessage("");// REPLY_MESSAGE, 应答描述
		this.setCreateDate(Util.getCurrDate());
		this.setCreateTime(Util.getCurrTime2());
		//this.setOrderId(itf.get);
		//this.setOprUser("SYSTEM");
		//this.setCheckUser(itf.get);
		//this.setExtCoulmn4(itf.get);
		//this.setExtCoulmn3(itf.get);
		//this.setExtCoulmn2(itf.get);
		//this.setExtCoulmn1(itf.get);
		//this.setOraTxnDate(itf.get);
		//this.setReturnFlag(itf.get);
		this.setProductType(itf.getProductType());
		this.setPeriod(itf.getPeriod());
		this.setClientSource(itf.getClientSource());
	}
}
