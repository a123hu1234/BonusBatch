package com.huateng.batch.model;

import java.io.Serializable;


/**
 * This is an object that contains data related to the TBL_ACCOUNT_INF table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="TBL_ACCOUNT_INF"
 */

public  class TblAccountInf  implements Serializable {

	public static String REF = "TblAccountInf";
	public static String PROP_PERIOD = "Period";
	public static String PROP_CHANL_NO = "ChanlNo";
	public static String PROP_KMH = "Kmh";
	public static String PROP_EXT_COULMN5 = "ExtCoulmn5";
	public static String PROP_CUST_ID = "CustId";
	public static String PROP_CARD_NO = "CardNo";
	public static String PROP_ACCT_ID = "AcctId";
	public static String PROP_TXN_AMT = "TxnAmt";
	public static String PROP_EXT_COULMN1 = "ExtCoulmn1";
	public static String PROP_EXT_COULMN2 = "ExtCoulmn2";
	public static String PROP_OPEN_BANK = "OpenBank";
	public static String PROP_EXT_COULMN3 = "ExtCoulmn3";
	public static String PROP_EXT_COULMN4 = "ExtCoulmn4";
	public static String PROP_ACCT_NAME = "AcctName";
	public static String PROP_OPEN_DATE = "OpenDate";
	public static String PROP_ACCT_STATE = "AcctState";
	public static String PROP_CHANGE_DATE = "ChangeDate";
	public static String PROP_OUTSIDE_ACCT = "OutsideAcct";
	public static String PROP_CURRENCY = "Currency";
	public static String PROP_PRODUCT_NO = "ProductNo";


	// constructors
	public TblAccountInf () {
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public TblAccountInf (
		java.lang.String custId,
		java.lang.String acctId) {

		this.setCustId(custId);
		this.setAcctId(acctId);
		initialize();
	}

	protected void initialize () {}



	// fields
	private java.lang.String custId;
	private java.lang.String acctId;
	private java.lang.String changeDate;
	private java.lang.String acctName;
	private java.lang.String currency;
	private java.math.BigDecimal txnAmt;
	private java.lang.String kmh;
	private java.lang.String productNo;
	private java.lang.String period;
	private java.lang.String openBank;
	private java.lang.String openDate;
	private java.lang.String acctState;
	private java.lang.String outsideAcct;
	private java.lang.String cardNo;
	private java.lang.String extCoulmn1;
	private java.lang.String extCoulmn2;
	private java.lang.String extCoulmn3;
	private java.lang.String extCoulmn4;
	private java.lang.String extCoulmn5;
	private java.lang.String chanlNo;






	/**
	 * Return the value associated with the column: CUST_ID
	 */
	public java.lang.String getCustId () {
		return custId;
	}

	/**
	 * Set the value related to the column: CUST_ID
	 * @param custId the CUST_ID value
	 */
	public void setCustId (java.lang.String custId) {
		this.custId = custId;
	}



	/**
	 * Return the value associated with the column: ACCT_ID
	 */
	public java.lang.String getAcctId () {
		return acctId;
	}

	/**
	 * Set the value related to the column: ACCT_ID
	 * @param acctId the ACCT_ID value
	 */
	public void setAcctId (java.lang.String acctId) {
		this.acctId = acctId;
	}



	/**
	 * Return the value associated with the column: CHANGE_DATE
	 */
	public java.lang.String getChangeDate () {
		return changeDate;
	}

	/**
	 * Set the value related to the column: CHANGE_DATE
	 * @param changeDate the CHANGE_DATE value
	 */
	public void setChangeDate (java.lang.String changeDate) {
		this.changeDate = changeDate;
	}



	/**
	 * Return the value associated with the column: ACCT_NAME
	 */
	public java.lang.String getAcctName () {
		return acctName;
	}

	/**
	 * Set the value related to the column: ACCT_NAME
	 * @param acctName the ACCT_NAME value
	 */
	public void setAcctName (java.lang.String acctName) {
		this.acctName = acctName;
	}



	/**
	 * Return the value associated with the column: CURRENCY
	 */
	public java.lang.String getCurrency () {
		return currency;
	}

	/**
	 * Set the value related to the column: CURRENCY
	 * @param currency the CURRENCY value
	 */
	public void setCurrency (java.lang.String currency) {
		this.currency = currency;
	}



	/**
	 * Return the value associated with the column: TXN_AMT
	 */
	public java.math.BigDecimal getTxnAmt () {
		return txnAmt;
	}

	/**
	 * Set the value related to the column: TXN_AMT
	 * @param txnAmt the TXN_AMT value
	 */
	public void setTxnAmt (java.math.BigDecimal txnAmt) {
		this.txnAmt = txnAmt;
	}



	/**
	 * Return the value associated with the column: KMH
	 */
	public java.lang.String getKmh () {
		return kmh;
	}

	/**
	 * Set the value related to the column: KMH
	 * @param kmh the KMH value
	 */
	public void setKmh (java.lang.String kmh) {
		this.kmh = kmh;
	}



	/**
	 * Return the value associated with the column: PRODUCT_NO
	 */
	public java.lang.String getProductNo () {
		return productNo;
	}

	/**
	 * Set the value related to the column: PRODUCT_NO
	 * @param productNo the PRODUCT_NO value
	 */
	public void setProductNo (java.lang.String productNo) {
		this.productNo = productNo;
	}



	/**
	 * Return the value associated with the column: PERIOD
	 */
	public java.lang.String getPeriod () {
		return period;
	}

	/**
	 * Set the value related to the column: PERIOD
	 * @param period the PERIOD value
	 */
	public void setPeriod (java.lang.String period) {
		this.period = period;
	}



	/**
	 * Return the value associated with the column: OPEN_BANK
	 */
	public java.lang.String getOpenBank () {
		return openBank;
	}

	/**
	 * Set the value related to the column: OPEN_BANK
	 * @param openBank the OPEN_BANK value
	 */
	public void setOpenBank (java.lang.String openBank) {
		this.openBank = openBank;
	}



	/**
	 * Return the value associated with the column: OPEN_DATE
	 */
	public java.lang.String getOpenDate () {
		return openDate;
	}

	/**
	 * Set the value related to the column: OPEN_DATE
	 * @param openDate the OPEN_DATE value
	 */
	public void setOpenDate (java.lang.String openDate) {
		this.openDate = openDate;
	}



	/**
	 * Return the value associated with the column: ACCT_STATE
	 */
	public java.lang.String getAcctState () {
		return acctState;
	}

	/**
	 * Set the value related to the column: ACCT_STATE
	 * @param acctState the ACCT_STATE value
	 */
	public void setAcctState (java.lang.String acctState) {
		this.acctState = acctState;
	}



	/**
	 * Return the value associated with the column: OUTSIDE_ACCT
	 */
	public java.lang.String getOutsideAcct () {
		return outsideAcct;
	}

	/**
	 * Set the value related to the column: OUTSIDE_ACCT
	 * @param outsideAcct the OUTSIDE_ACCT value
	 */
	public void setOutsideAcct (java.lang.String outsideAcct) {
		this.outsideAcct = outsideAcct;
	}



	/**
	 * Return the value associated with the column: CARD_NO
	 */
	public java.lang.String getCardNo () {
		return cardNo;
	}

	/**
	 * Set the value related to the column: CARD_NO
	 * @param cardNo the CARD_NO value
	 */
	public void setCardNo (java.lang.String cardNo) {
		this.cardNo = cardNo;
	}



	/**
	 * Return the value associated with the column: EXT_COULMN1
	 */
	public java.lang.String getExtCoulmn1 () {
		return extCoulmn1;
	}

	/**
	 * Set the value related to the column: EXT_COULMN1
	 * @param extCoulmn1 the EXT_COULMN1 value
	 */
	public void setExtCoulmn1 (java.lang.String extCoulmn1) {
		this.extCoulmn1 = extCoulmn1;
	}



	/**
	 * Return the value associated with the column: EXT_COULMN2
	 */
	public java.lang.String getExtCoulmn2 () {
		return extCoulmn2;
	}

	/**
	 * Set the value related to the column: EXT_COULMN2
	 * @param extCoulmn2 the EXT_COULMN2 value
	 */
	public void setExtCoulmn2 (java.lang.String extCoulmn2) {
		this.extCoulmn2 = extCoulmn2;
	}



	/**
	 * Return the value associated with the column: EXT_COULMN3
	 */
	public java.lang.String getExtCoulmn3 () {
		return extCoulmn3;
	}

	/**
	 * Set the value related to the column: EXT_COULMN3
	 * @param extCoulmn3 the EXT_COULMN3 value
	 */
	public void setExtCoulmn3 (java.lang.String extCoulmn3) {
		this.extCoulmn3 = extCoulmn3;
	}



	/**
	 * Return the value associated with the column: EXT_COULMN4
	 */
	public java.lang.String getExtCoulmn4 () {
		return extCoulmn4;
	}

	/**
	 * Set the value related to the column: EXT_COULMN4
	 * @param extCoulmn4 the EXT_COULMN4 value
	 */
	public void setExtCoulmn4 (java.lang.String extCoulmn4) {
		this.extCoulmn4 = extCoulmn4;
	}



	/**
	 * Return the value associated with the column: EXT_COULMN5
	 */
	public java.lang.String getExtCoulmn5 () {
		return extCoulmn5;
	}

	/**
	 * Set the value related to the column: EXT_COULMN5
	 * @param extCoulmn5 the EXT_COULMN5 value
	 */
	public void setExtCoulmn5 (java.lang.String extCoulmn5) {
		this.extCoulmn5 = extCoulmn5;
	}



	/**
	 * Return the value associated with the column: CHANL_NO
	 */
	public java.lang.String getChanlNo () {
		return chanlNo;
	}

	/**
	 * Set the value related to the column: CHANL_NO
	 * @param chanlNo the CHANL_NO value
	 */
	public void setChanlNo (java.lang.String chanlNo) {
		this.chanlNo = chanlNo;
	}




	public static String[] toArray() {
		// TODO Auto-generated method stub
		String[] array = new String[] {"changeDate","custId","acctId","acctName","currency","txnAmt","kmh", "extCoulmn1","acctState","openBank","openDate","ExtCoulmn2","cardNo","outsideAcct"};
		return array;
	}
	/*
	 * 表字段
	 */
	public static String getTableClum() {
		String sReturn = "TBL_Account_INF(CUST_ID,ACCT_ID,CHANGE_DATE,ACCT_NAME,CURRENCY,TXN_AMT,KMH,PRODUCT_NO,PERIOD,OPEN_BANK,OPEN_DATE,ACCT_STATE,OUTSIDE_ACCT,CARD_NO,EXT_COULMN1,EXT_COULMN2,EXT_COULMN3,EXT_COULMN4,EXT_COULMN5,CHANL_NO)";
		return sReturn;
	}
	
	public static String getBeanClum() {
		String sReturn = ":custId,:acctId,:changeDate,:acctName,:currency,:txnAmt,:kmh,:productNo,:period,:openBank,:openDate,:acctState,:outsideAcct,:cardNo,:extCoulmn1,:extCoulmn2,:extCoulmn3,:extCoulmn4,:extCoulmn5,:chanlNo";
				return sReturn;
	}


	public String toString () {
		return super.toString();
	}


}