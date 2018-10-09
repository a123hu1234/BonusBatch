package com.huateng.batch.model;

public class TblCustInfTmp {
	
	private String custId;
	private String custName;
	private String usageKey;
	private String openBrh;
	private String custType;
	private String custIdType;
	private String custIdNum;
	private String custSex;
	private String hunyzk;
	private String xuelii;
	private String custBirthday;
	private String openDate;
	private String closeDate;
	private String custMobile;
	private String custAddr;
	private String custLevel;
	private String custBonusStatus;
	private String modifyDate;
	private String modifyTime;
	private String extCoulmn1;
	private String extCoulmn2;
	private String extCoulmn3;
	private String extCoulmn4;
	private String familyAddr;
	private String familyAddrMobile;
	private String officeAddr;
	private String officeAddrMobile;
	private String certAddr;
	private String certAddrMobile;
	private String chanlNo;
	private String relatType;
	private String renzDate;

	

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getUsageKey() {
		return usageKey;
	}

	public void setUsageKey(String usageKey) {
		this.usageKey = usageKey;
	}

	public String getOpenBrh() {
		return openBrh;
	}

	public void setOpenBrh(String openBrh) {
		this.openBrh = openBrh;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCustIdType() {
		return custIdType;
	}

	public void setCustIdType(String custIdType) {
		this.custIdType = custIdType;
	}

	public String getCustIdNum() {
		return custIdNum;
	}

	public void setCustIdNum(String custIdNum) {
		this.custIdNum = custIdNum;
	}

	public String getCustSex() {
		return custSex;
	}

	public void setCustSex(String custSex) {
		this.custSex = custSex;
	}

	public String getHunyzk() {
		return hunyzk;
	}

	public void setHunyzk(String hunyzk) {
		this.hunyzk = hunyzk;
	}

	public String getXuelii() {
		return xuelii;
	}

	public void setXuelii(String xuelii) {
		this.xuelii = xuelii;
	}

	public String getCustBirthday() {
		return custBirthday;
	}

	public void setCustBirthday(String custBirthday) {
		this.custBirthday = custBirthday;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public String getCustAddr() {
		return custAddr;
	}

	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}

	public String getCustLevel() {
		return custLevel;
	}

	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}

	public String getCustBonusStatus() {
		return custBonusStatus;
	}

	public void setCustBonusStatus(String custBonusStatus) {
		this.custBonusStatus = custBonusStatus;
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

	public String getExtCoulmn4() {
		return extCoulmn4;
	}

	public void setExtCoulmn4(String extCoulmn4) {
		this.extCoulmn4 = extCoulmn4;
	}

	public String getFamilyAddr() {
		return familyAddr;
	}

	public void setFamilyAddr(String familyAddr) {
		this.familyAddr = familyAddr;
	}

	public String getFamilyAddrMobile() {
		return familyAddrMobile;
	}

	public void setFamilyAddrMobile(String familyAddrMobile) {
		this.familyAddrMobile = familyAddrMobile;
	}

	public String getOfficeAddr() {
		return officeAddr;
	}

	public void setOfficeAddr(String officeAddr) {
		this.officeAddr = officeAddr;
	}

	public String getOfficeAddrMobile() {
		return officeAddrMobile;
	}

	public void setOfficeAddrMobile(String officeAddrMobile) {
		this.officeAddrMobile = officeAddrMobile;
	}

	public String getCertAddr() {
		return certAddr;
	}

	public void setCertAddr(String certAddr) {
		this.certAddr = certAddr;
	}

	public String getCertAddrMobile() {
		return certAddrMobile;
	}

	public void setCertAddrMobile(String certAddrMobile) {
		this.certAddrMobile = certAddrMobile;
	}

	public String getChanlNo() {
		return chanlNo;
	}

	public void setChanlNo(String chanlNo) {
		this.chanlNo = chanlNo;
	}

	public String getRelatType() {
		return relatType;
	}

	public void setRelatType(String relatType) {
		this.relatType = relatType;
	}

	public String getRenzDate() {
		return renzDate;
	}

	public void setRenzDate(String renzDate) {
		this.renzDate = renzDate;
	}

	/***
	 * 
	 * @return
	 */
	public static String getTableClum() {
		String sReturn = "TBL_CUST_INF_TMP(CUST_ID,CUST_NAME,USAGE_KEY,OPEN_BRH,CUST_TYPE,CUST_ID_TYPE,CUST_ID_NUM,CUST_SEX,HUNYZK,XUELII,CUST_BIRTHDAY,OPEN_DATE,CLOSE_DATE,CUST_MOBILE,CUST_ADDR,CUST_LEVEL,CUST_BONUS_STATUS,MODIFY_DATE,MODIFY_TIME,EXT_COULMN1,EXT_COULMN2,EXT_COULMN3,EXT_COULMN4,FAMILY_ADDR,FAMILY_ADDR_MOBILE,OFFICE_ADDR,OFFICE_ADDR_MOBILE,CERT_ADDR,CERT_ADDR_MOBILE,CHANL_NO,RELAT_TYPE,RENZ_DATE)";
		return sReturn;
	}

	public static String getBeanClum() {
		String sReturn = ":custId,:custName,:usageKey,:openBrh,:custType,:custIdType,:custIdNum,:custSex,:hunyzk,:xuelii,:custBirthday,:openDate,:closeDate,:custMobile,:custAddr,:custLevel,:custBonusStatus,:modifyDate,:modifyTime,:extCoulmn1,:extCoulmn2,:extCoulmn3,:extCoulmn4,:familyAddr,:familyAddrMobile,:officeAddr,:officeAddrMobile,:certAddr,:certAddrMobile,:chanlNo,:relatType,:renzDate";
		return sReturn;
	}

	public static String[] toArray() {
		//String[] array = new String[] {  "custId", "custName", "usageKey", "openBrh", "custType",
		//		"custIdType", "custIdNum", "custSex", "hunyzk", "xuelii", "custBirthday", "openDate", "closeDate",
		//		"custMobile", "custAddr", "custLevel", "custBonusStatus", "modifyDate", "modifyTime", "extCoulmn1",
		//		"extCoulmn2", "extCoulmn3", "extCoulmn4", "familyAddr", "familyAddrMobile", "officeAddr",
		//		"officeAddrMobile", "certAddr", "certAddrMobile", "chanlNo", "relatType", "renzDate" };
		//核心客户号|证件类型|证件号码|当事人中文名称|性别|电话|邮箱|地址|类别|出生日期|所属机构|建立时间
		String[] array = new String[] {"custId","custIdType","custIdNum","custName","custSex","custMobile","officeAddr", "custAddr","custLevel","custBirthday","openBrh","openDate"};
		return array;
	}
}
