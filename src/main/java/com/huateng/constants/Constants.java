package com.huateng.constants;

/** @(#)
*
*
* Modify Information:
* =============================================================================
*   Author             Date           Description
*   ------------      ----------     --------------------------------------------
*   zhanyaokang   		2012-9-29      description
*
*
* Copyright Notice:
* =============================================================================
*       Copyright 2012 Huateng Software, Inc. All rights reserved.
*
*       This software is the confidential and proprietary information of
*       Shanghai HUATENG Software Co., Ltd. ("Confidential Information").
*       You shall not disclose such Confidential Information and shall use it
*       only in accordance with the terms of the license agreement you entered
*       into with Huateng.
*
* Warning:
* =============================================================================
*
*/



/**
* <p><strong>Description:</strong> 批量程序静态常量，定义系统中经常用到，并且不会改变的值 </p>
* @author <a href="mailto: zhan_yaokang@huateng.com">湛耀康</a>
* @Company 上海华腾软件系统有限公司
* @version 1.0
* Copyright 2012, Shanghai Huateng Software Systems Co., Ltd.
* All right reserved.
*/
public class Constants {

	public static final String JOB_TYPE_NORMAL = "00";// 批量任务类型-普通任务
	public static final String JOB_TYPE_REPAIR = "01";// 批量任务类型-补跑任务
	public static final String JOB_TYPE_KILL_PROC = "02";// 批量任务类型-杀进程
	public static final String JOB_TYPE_COERCE = "99";// 批量任务类型-强制执行

	public static final String JOB_STATUS_NOT_RUN = "09";// 批量任务状态-未执行
	public static final String JOB_STATUS_SUCCESS = "00";// 批量任务状态-执行成功
	public static final String JOB_STATUS_FAIL = "01";// 批量任务状态-执行失败
	public static final String JOB_STATUS_WAIT = "02";// 批量任务状态-等待执行
	public static final String JOB_STATUS_RUNNING = "03";// 批量任务状态-执行中
	public static final String JOB_STATUS_PROC_KILLED = "04";// 批量任务状态-已杀进程
	public static final String JOB_STATUS_PROC_WAIT_FOR_KILLE = "05";// 批量任务状态-待杀进程
	
	public static final String SEQ_JOB_PROCE = "seq_job_proce";

	public static final String USAGE_KEY_JF = "JF";
	public static final String USAGE_KEY_MB = "MB";
	
	public static final String BONUS_PLAN_BOD="1111";//综合积分
	public static final String BONUS_PLAN_CREDITCARD = "1112";//贷记卡积分计划
	public static final String BONUS_PLAN_DEBITCARD = "1113";//借记卡积分计划
	

	
	public static final String TXN_CODE_ORA_CUST_INFO = "00";// 客户信息
	public static final String TXN_CODE_ORA_SAVINGS = "01";// 存款储蓄
	public static final String TXN_CODE_ORA_PERSONAL_LOAN = "02";// 个人贷款
	public static final String TXN_CODE_ORA_FINANCING = "03";// 理财
	public static final String TXN_CODE_ORA_FUND = "04";// 基金
	public static final String TXN_CODE_ORA_THIRD_PARTY_CUSTODY = "05";// 三方存管
	public static final String TXN_CODE_ORA_FINANCIAL_MESSAGE_SERVICE = "06";// 银信通
	public static final String TXN_CODE_ORA_WAGES_PAYING = "07";// 代发工资
	public static final String TXN_CODE_ORA_NET_BANK = "08";// 网银
	public static final String TXN_CODE_ORA_CREDIT_CARD = "09";// 贷记卡
	public static final String TXN_CODE_ORA_DEBIT_CARD = "10";// 借记卡
	public static final String TXN_CODE_ORA_CHANNEL = "11";// 渠道
	public static final String TXN_CODE_ORA_USER_CLASS="12";//客户类
	public static final String TXN_CODE_ORA_GOLD="13";//贵金属
	public static final String TXN_CODE_ORA_INSURE="14";//保险

	public static final String EVENT_CODE_NEW_USER="00";//新开客户
	
	public static final String TXN_CODE_BONUS_FEE= "9004";// VIP客户扣减
	public static final String TXN_CODE_BONUS_CONVERT= "9003";// 积分批量转换
	public static final String TXN_CODE_BONUS_EXPIRED= "9002";// 积分过期处理
	public static final String TXN_CODE_BONUS_ADD= "9001";// 积分产生
	public static final String TXN_CODE_MIBAO_ADD= "9101";// 米宝产生
	public static final String TXN_CODE_MIBAO_MERGE= "9104";// 米宝归并
	public static final String TXN_CODE_BONUS_MERGE= "9104";// 积分归并
	
	
	public static final String TXN_VALUE_EVENT_TYPE_SIGNED = "01";// 事件类型，签约
	
	public static final String TXN_TYPE_ADD="01";//01-积分累计类交易(积分累计、积分移植)
	public static final String TXN_TYPE_SUB="02";//	02-积分减少类交易(礼品兑换、活动兑换、活动到期)
	public static final String TXN_TYPE_CONV="05";//	05-积分转换类交易
	
	public static final String USER_SYSTEM="SYSTEM";//用户-系统
	public static final String CHANNEL_NO_BPS = "BPS";//渠道-积分系统
	 /**积分系统渠道标识 */
   public static final String JIFEN_CHANNEL_LOCAL="56";
	
	
	public static final String PARAM_CODE_CREDIT_CARD_CONVERT_DISCOUNT="CRCD_CONV_DIS"; //贷记卡积分转换比例参数
	public static final String PARAM_CODE_CREDIT_CARD_CONVERT_VALID_DATE="CRCD_CONV_DATE"; //贷记卡积分转换比例参数
	public static final String PARAM_CODE_CREDIT_CARD_CONVERT_PARENT="CRCD_CONV";//贷记卡转换父参数
	
	public static final String PARAM_CODE_CUST_FEE="VIP_CUST_FEE";
	public static final String PARAM_CODE_CUST_FEE_CURD_LEVEL_NORMAL="VIP_CUST_FEE_CARD_LV_NORMAL";
	public static final String PARAM_CODE_CUST_FEE_CURD_LEVEL_GOLD="VIP_CUST_FEE_CARD_LV_GOLD";
	public static final String PARAM_CODE_CUST_FEE_CURD_LEVEL_PLATINUM="VIP_CUST_FEE_CARD_LV_PLATINUM";
	public static final String PARAM_CODE_CUST_FEE_CURD_LEVEL_DIAMOND="VIP_CUST_FEE_CARD_LV_DIAMOND";
	
	public static final String PARAM_CODE_CUST_LEVEL = "CUST_LEVEL";
	public static final String PARAM_CODE_CARD_LEVEL = "CARD_LEVEL";
	
	public static final String PARAM_CODE_SMS_TEMPLATE_CUST_FEE = "SMS_TEMPLATE_CUST_FEE";
	
	public static final String EXPIRED_STATUS_EXP="1";//过期状态-已过期
	
	
	public static final String BRH_ID="0000";//总行

	public static final String ODS_CONSTANT_CARD_TYPE_JF_ACCOUNT="0";//客户级别积分账户
	public static final String ODS_CONSTANT_CARD_TYPE_DEBIT_CARD="1";//借记卡
	public static final String ODS_CONSTANT_CARD_TYPE_SAVINGS_ACCOUNT="2";//储蓄帐户
	public static final String ODS_CONSTANT_CARD_TYPE_LOAN_ACCOUNT="3";//贷款账号
	public static final String ODS_CONSTANT_CARD_TYPE_CREDIT_CARD="4";//贷记卡
	public static final String ODS_CONSTANT_CARD_TYPE_FINANCING="5";//理财
	public static final String ODS_CONSTANT_CARD_TYPE_FUND="6";//基金
	public static final String ODS_CONSTANT_CARD_THIRD_PARTY_CUSTODY ="7";//三方存管
	
	
	
	/*************************报表类型***********************************/
   /** 活动无限大积分池默认值**/
   public final static double RULE_ACTIVITY_MAX_BONUS = 999999999999999999999999999999.0;
   /** 活动积分池剩余积分大于该值就认为无限大**/
   public final static double RULE_ACTIVITY_MIN_BONUS = 99999999999999999999999999999.0;
	/** 积分到期预告表 **/
	public static final String REPORT_TYPE_BONUS_PLAN_DETAIL = "00";
	/** 活动明细报表 **/
	public static final String REPORT_TYPE_BONUS_DETAIL = "01";
	/** 礼品商清算月报表 **/
	public static final String REPORT_TYPE_MCHT_AUDIT = "02";
	/** 积分调整及转账明细月报表 **/
	public static final String REPORT_TYPE_TRANSFER_ADJUST_DETAIL = "03";
	/** 无客户号积分明细月报表 **/
   public static final String REPORT_TYPE_NO_CUST_DETAIL = "04";
	/** 礼品配送报表 **/
	public static final String REPORT_TYPE_GOODS_DELIVERY_DETAIL = "05";
	/** 成本分摊表 **/
	public static final String REPORT_TYPE_COST_SHARE_DETAIL = "06";
	/** 规则活动报表 **/
	public static final String REPORT_TYPE_ACTIVITY_RULE = "07";
	/**机构汇总报表**/
	public static final String REPORT_TYPE_BONUS_BANK_SUM = "08";
	
	/** 贷记卡比例父参数 **/
	public static final String CREDIT_CARD_CONVER_RATE_PARENT_CODE = "CRE_CARD_CONV";
	/** 贷记卡有效期父参数 **/
	public static final String CREDIT_CARD_CONVER_DATE_PARENT_CODE = "CRE_CARD_CONV";
	/** 贷记卡有效期参数 **/
	public static final String CREDIT_CARD_CONVER_DATE_PARAM_CODE = "CRE_CARD_CONV_DATE";
   /** 贷记卡比例参数 **/
	public static final String CREDIT_CARD_CONVER_RATE_PARAM_CODE = "CRE_CARD_CONV_RATE";
	
	/** 借记卡比例父参数 **/
	public static final String DEBIT_CARD_CONVER_RATE_PARENT_CODE = "DEB_CARD_CONV";
	/** 借记卡有效期父参数 **/
	public static final String DEBIT_CARD_CONVER_DATE_PARENT_CODE = "DEB_CARD_CONV";
	/** 借记卡有效期参数 **/
	public static final String DEBIT_CARD_CONVER_DATE_PARAM_CODE = "DEB_CARD_CONV_DATE";
   /** 借记卡比例参数 **/
	public static final String DEBIT_CARD_CONVER_RATE_PARAM_CODE = "DEB_CARD_CONV_RATE";
	
	
	/**积分变动数据汇总报表**/
	public static final String REPORT_TYPE_BONUS_CHANGE_SUM_DAY = "10";//日报表
	public static final String REPORT_TYPE_BONUS_CHANGE_SUM_MONTH = "11";//月报表
	public static final String REPORT_TYPE_BONUS_CHANGE_SUM_SEASON = "12";//季报表 
	public static final String REPORT_TYPE_BONUS_CHANGE_SUM_YEAR = "13";//年报表
	
	/**网点积分情况统计报表*/
	public static final String REPORT_TYPE_BONUS_LATTICE = "14";//
	public static final String REPORT_TYPE_BONUS_LATTICE_YEAR = "15";//本年
	/**积分调整统计报表*/
	public static final String REPORT_TYPE_BONUS_ADJUST_STATIS = "16";
	/**客户积分区间情况表*/
	public static final String REPORT_TYPE_BONUS_CUST_SCALE = "17";
	
	/** 客户类型**/
	public static final String CUST_TYPE_PER = "1";//个人客户
	
	/**统计规则ID 普通规则默认值*/
	public static final String STATISTICS_RULE_ID = "NORMAL_RULE";
	
	/**积分价值*/
	public static final String JI_FEN_WORTH = "jifenWorth";
	/**米宝价值*/
	public static final String MI_BAO_WORTH = "mibaoWorth";
	
	/**最大*/
	public static final String VALUE_TYPE_MAX="1";
	/**最小*/
	public static final String VALUE_TYPE_MIN="2";
	/**按比例*/
	public static final String VALUE_TYPE_PROPORTION="3";
	
	/**
	 * 积分账户有效期  到期处理状态
	 */
	public static enum expiredStatus  {
		past("1","已过期");
		private final String value;
		private final String desc;
		expiredStatus(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}
	}
	/**
	 * 到期积分扣减
	 */
	public static enum dueDiscRate  {
		paramCode("bonus_past_dic_rate","到期积分扣减 数据字典编号");
		private final String value;
		private final String desc;
		dueDiscRate(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}
	}
	
	/**
	 * 米宝卡 状态
	 */
	public static enum miBaoCardStatus  {
		paramCode("miBaoCard_status","米宝卡 状态 数据字典编号"),
		normal("0","正常"),
		logOff("1","销户");
		private final String value;
		private final String desc;
		miBaoCardStatus(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}
	}
	/**
	 * 米宝卡过期月份
	 */
	public static enum miBaoPastMonth  {
		paramCode("miBao_past_month","米宝卡过期月份 数据字典编号");
		private final String value;
		private final String desc;
		miBaoPastMonth(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}
	}
	
	public static enum acctType {
		paramCode("ACCT_TYPE","账户类型");
		private final String value;
		private final String desc;
		acctType(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}
	
	}
	
	/**
	 * 积分类型 
	 */
	public static enum bpPlanType  {
		paramCode("point_type","积分类型  数据字典编号"),
		standard("1001","标准积分"),
		addValue("1002","增值积分"),
		unitName("1003","联名卡积分");
		private final String value;
		private final String desc;
		bpPlanType(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}
	}
	/**
	 * 交易类型
	 */
	public static enum txnType  {
		paramCode("txnType","交易类型数据字典编号"),
		A09("A09","活动赠送"),
		A10("A10","积分购买 "),
		A99("A99","其他"),
		A21("A21","贷记卡刷卡消费"),
		A22("A22","银联港澳通消费"),
		A23("A23","银联卡境外消费"),
		A24("A24","灵活分期"),
		A25("A25","账单分期"),
		A26("A26","现金分期"),
		A27("A27","大额分期"),
		A28("A28","透支取现"),
		A01("A01","借记卡刷卡消费"),
		A04("A04","积分兑换"),
		A05("A05","积分转账"),
		A06("A06","积分转换"),
		A07("A07","积分调整"),
		A08("A08","到期积分扣减"),
		A00("A00","初始化历史数据"),
		A33("A33","积分账户归并处理"),
		// 米宝交易类型
		B01("B01","注册签约"),
		B02("B02","充值 "),
		B03("B03","缴费"),
		B04("B04","代收罚款"),
		B05("B05","在线影票"),
		B06("B06","普通跨行"),
		B07("B07","非税缴费"),
		B08("B08","购物支付"),
		B09("B09","米宝冲正"),
		B10("B10","购买理财产品"),
		B11("B11","米宝转赠(转出)"),
		B12("B12","米宝转赠(转入)"),
		B13("B13","消费米宝"),
		B14("B14","消费米宝卡"),
		B15("B15","米宝卡到期处理"),
		B16("B16","米宝卡消费冲正"),
		B21("B21","99商城"),
		B22("B22","移动彩票"),
		B23("B23","代保管"),
		B24("B24","回购"),
		B25("B25","e账户资金沉淀"),
		B31("B31","米宝调整"),
		B32("B32","米宝充值"),
		B33("B33","米宝归并处理");
		private final String value;
		private final String desc;
		txnType(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}
	}
	/**
	 * 积分交易方向 
	 */
	public static enum bonusCdFlag  {
		add("d","增加"),
		reduce("c","减少"),
		pase("p","到期扣减");
		private final String value;
		private final String desc;
		bonusCdFlag(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}
	}
	
	public static enum klxbhh{
		paramCode("KLXBHH","卡产品");
		private final String value;
		private final String desc;
		klxbhh(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}
	}
	
}
