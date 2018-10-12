package com.huateng.batch.model;

import java.io.Serializable;


/**
 * This is an object that contains data related to the TBL_CARD_INF table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="TBL_CARD_INF"
 */

public abstract class TblCardInf  implements Serializable {

	public static String REF = "TblCardInf";
	public static String PROP_CARD_ID = "CardId";
	public static String PROP_CHANL_NO = "ChanlNo";
	public static String PROP_CARD_LEVEL = "CardLevel";
	public static String PROP_ACCT_NO = "AcctNo";
	public static String PROP_KAAAJZ = "Kaaajz";
	public static String PROP_CARD_STA = "CardSta";
	public static String PROP_CUST_ID = "CustId";
	public static String PROP_KAAAXZ = "Kaaaxz";
	public static String PROP_EXT_COULMN1 = "ExtCoulmn1";
	public static String PROP_EXT_COULMN2 = "ExtCoulmn2";
	public static String PROP_EXT_COULMN3 = "ExtCoulmn3";
	public static String PROP_EXT_COULMN4 = "ExtCoulmn4";
	public static String PROP_RENZ_DATE = "RenzDate";
	public static String PROP_ID = "Id";
	public static String PROP_CARD_PRD_NAME = "CardPrdName";
	public static String PROP_CARD_BANK = "CardBank";
	public static String PROP_CARD_PRD = "CardPrd";
	public static String PROP_KAAAZL = "Kaaazl";
	public static String PROP_CARD_TYPE = "CardType";
	public static String PROP_FAKARQ = "Fakarq";
	public static String PROP_USAGE_KEY = "UsageKey";


	// constructors
	public TblCardInf () {
		
	}





	private int hashCode = Integer.MIN_VALUE;

	// primary key
	//private java.lang.Integer id;

	// fields
	private java.lang.String usageKey;
	private java.lang.String custId;
	private java.lang.String cardId;
	private java.lang.String cardType;
	private java.lang.String cardBank;
	private java.lang.String cardPrd;
	private java.lang.String kaaajz;
	private java.lang.String kaaaxz;
	private java.lang.String kaaazl;
	private java.lang.String cardLevel;
	private java.lang.String extCoulmn1;
	private java.lang.String extCoulmn2;
	private java.lang.String extCoulmn3;
	private java.math.BigDecimal extCoulmn4;
	private java.lang.String cardPrdName;
	private java.lang.String fakarq;
	private java.lang.String cardSta;
	private java.lang.String acctNo;
	private java.lang.String chanlNo;
	private java.lang.String renzDate;







	/**
	 * Return the value associated with the column: USAGE_KEY
	 */
	public java.lang.String getUsageKey () {
		return usageKey;
	}

	/**
	 * Set the value related to the column: USAGE_KEY
	 * @param usageKey the USAGE_KEY value
	 */
	public void setUsageKey (java.lang.String usageKey) {
		this.usageKey = usageKey;
	}



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
	 * Return the value associated with the column: CARD_ID
	 */
	public java.lang.String getCardId () {
		return cardId;
	}

	/**
	 * Set the value related to the column: CARD_ID
	 * @param cardId the CARD_ID value
	 */
	public void setCardId (java.lang.String cardId) {
		this.cardId = cardId;
	}



	/**
	 * Return the value associated with the column: CARD_TYPE
	 */
	public java.lang.String getCardType () {
		return cardType;
	}

	/**
	 * Set the value related to the column: CARD_TYPE
	 * @param cardType the CARD_TYPE value
	 */
	public void setCardType (java.lang.String cardType) {
		this.cardType = cardType;
	}



	/**
	 * Return the value associated with the column: CARD_BANK
	 */
	public java.lang.String getCardBank () {
		return cardBank;
	}

	/**
	 * Set the value related to the column: CARD_BANK
	 * @param cardBank the CARD_BANK value
	 */
	public void setCardBank (java.lang.String cardBank) {
		this.cardBank = cardBank;
	}



	/**
	 * Return the value associated with the column: CARD_PRD
	 */
	public java.lang.String getCardPrd () {
		return cardPrd;
	}

	/**
	 * Set the value related to the column: CARD_PRD
	 * @param cardPrd the CARD_PRD value
	 */
	public void setCardPrd (java.lang.String cardPrd) {
		this.cardPrd = cardPrd;
	}



	/**
	 * Return the value associated with the column: KAAAJZ
	 */
	public java.lang.String getKaaajz () {
		return kaaajz;
	}

	/**
	 * Set the value related to the column: KAAAJZ
	 * @param kaaajz the KAAAJZ value
	 */
	public void setKaaajz (java.lang.String kaaajz) {
		this.kaaajz = kaaajz;
	}



	/**
	 * Return the value associated with the column: KAAAXZ
	 */
	public java.lang.String getKaaaxz () {
		return kaaaxz;
	}

	/**
	 * Set the value related to the column: KAAAXZ
	 * @param kaaaxz the KAAAXZ value
	 */
	public void setKaaaxz (java.lang.String kaaaxz) {
		this.kaaaxz = kaaaxz;
	}



	/**
	 * Return the value associated with the column: KAAAZL
	 */
	public java.lang.String getKaaazl () {
		return kaaazl;
	}

	/**
	 * Set the value related to the column: KAAAZL
	 * @param kaaazl the KAAAZL value
	 */
	public void setKaaazl (java.lang.String kaaazl) {
		this.kaaazl = kaaazl;
	}



	/**
	 * Return the value associated with the column: CARD_LEVEL
	 */
	public java.lang.String getCardLevel () {
		return cardLevel;
	}

	/**
	 * Set the value related to the column: CARD_LEVEL
	 * @param cardLevel the CARD_LEVEL value
	 */
	public void setCardLevel (java.lang.String cardLevel) {
		this.cardLevel = cardLevel;
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
	public java.math.BigDecimal getExtCoulmn4 () {
		return extCoulmn4;
	}

	/**
	 * Set the value related to the column: EXT_COULMN4
	 * @param extCoulmn4 the EXT_COULMN4 value
	 */
	public void setExtCoulmn4 (java.math.BigDecimal extCoulmn4) {
		this.extCoulmn4 = extCoulmn4;
	}



	/**
	 * Return the value associated with the column: CARD_PRD_NAME
	 */
	public java.lang.String getCardPrdName () {
		return cardPrdName;
	}

	/**
	 * Set the value related to the column: CARD_PRD_NAME
	 * @param cardPrdName the CARD_PRD_NAME value
	 */
	public void setCardPrdName (java.lang.String cardPrdName) {
		this.cardPrdName = cardPrdName;
	}



	/**
	 * Return the value associated with the column: FAKARQ
	 */
	public java.lang.String getFakarq () {
		return fakarq;
	}

	/**
	 * Set the value related to the column: FAKARQ
	 * @param fakarq the FAKARQ value
	 */
	public void setFakarq (java.lang.String fakarq) {
		this.fakarq = fakarq;
	}



	/**
	 * Return the value associated with the column: CARD_STA
	 */
	public java.lang.String getCardSta () {
		return cardSta;
	}

	/**
	 * Set the value related to the column: CARD_STA
	 * @param cardSta the CARD_STA value
	 */
	public void setCardSta (java.lang.String cardSta) {
		this.cardSta = cardSta;
	}



	/**
	 * Return the value associated with the column: ACCT_NO
	 */
	public java.lang.String getAcctNo () {
		return acctNo;
	}

	/**
	 * Set the value related to the column: ACCT_NO
	 * @param acctNo the ACCT_NO value
	 */
	public void setAcctNo (java.lang.String acctNo) {
		this.acctNo = acctNo;
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



	/**
	 * Return the value associated with the column: RENZ_DATE
	 */
	public java.lang.String getRenzDate () {
		return renzDate;
	}

	/**
	 * Set the value related to the column: RENZ_DATE
	 * @param renzDate the RENZ_DATE value
	 */
	public void setRenzDate (java.lang.String renzDate) {
		this.renzDate = renzDate;
	}







	public String toString () {
		return super.toString();
	}


}