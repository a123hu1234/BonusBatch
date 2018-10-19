package com.huateng.batch.ItemWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huateng.batch.dao.TblBonusAccItfDao;
import com.huateng.batch.model.BonusBean;
import com.huateng.batch.model.TblBonusAccItf;
import com.huateng.constants.Constants;
import com.huateng.drool.DroolIntance;
import com.huateng.toprules.adapter.PackageResult.ActivityResult;
import com.huateng.toprules.adapter.PackageResult.RuleGroupResult;
import com.huateng.toprules.adapter.TopRules;
import com.huateng.toprules.adapter.TxnBonus;
import com.huateng.toprules.adapter.TxnBonusResult;
import com.huateng.util.ConfigProperties;
import com.huateng.util.StringUtil;
import com.huateng.util.Util;

@Component
public class BonusAcctInfWriter implements ItemWriter<BonusBean>{
	long taskDateSeq = 1;
	private Logger logger = LoggerFactory.getLogger(BonusAcctInfWriter.class);
	
	//@Autowired
	//private BonusBeanDao dao;
	@Autowired
	private TblBonusAccItfDao dao;
	@Override
	public void write(List<? extends BonusBean> items) throws Exception {
		logger.info("正在加载规则文件......");
		TopRules topRules = DroolIntance.getIntance().getTopRules();
		logger.info("开始计算积分");
		Map<String, String> regexMap = new HashMap<String, String>();
		regexMap.put("yyyyMMdd", Util.getCurrDate());
		//String dataLoca = ConfigProperties.getFullPathProperties("sqlldr.file.locate", regexMap);
		//logger.info("正在创建目录:" + dataLoca);
		//FileUtil.mkdirs(dataLoca);
		//logger.info("完成创建目录:" + dataLoca);

		String ruleFileName = ConfigProperties.getProperties("file.name.rule.JF");
		/**
		 * 将交易数据转化成规则引擎计算对象
		 */
		List<TxnBonus> list = convertToTxnBonus(items);
	
		topRules.execPkg(list, ruleFileName);
		//List<ActivityResult> results = new ArrayList<ActivityResult>();
		logger.info("保存计算结果");
		List<TblBonusAccItf> list2 = new ArrayList<>();
		for(TxnBonus txnBonus: list) { //TODO
			
			List<TblBonusAccItf> itfList = new ArrayList<>();
			writToBonusBean(itfList, txnBonus);
			list2.addAll(itfList);
		}
		dao.saveList(list2);
		//dao.save(items);
		
	}
	private void writToBonusBean(List<TblBonusAccItf> itfList, TxnBonus txnBonus) {
		List<ActivityResult> actResultList = txnBonus.getResult().activityGroupResults;
		Map<String,List<ActivityResult>> resultMap = new HashMap<String,List<ActivityResult>>();//保存按比例拆分积分的活动结果，key:积分计划类型,
		Map<String,ActivityResult> resultMap2 = new HashMap<String,ActivityResult>();//保存按最大,最小配置的活动结果key:积分计划类型,
		if (actResultList != null && actResultList.size() > 0) {
			
			for (ActivityResult ar : actResultList) {
				String valueType = ar.valueType;//1.最大 2.最小 3.按比例
				
				if (ar.result != null) {
					if (ar.ruleGroupResults != null && ar.ruleGroupResults.size() > 0) {
					//	for (int k = 0; k < ar.ruleGroupResults.size(); k++) {
						//	RuleGroupResult grp = (RuleGroupResult) ar.ruleGroupResults.get(k);//单一规则结果
							if(Constants.VALUE_TYPE_MAX.equals(valueType)) {
								getMaxResult(ar,resultMap2);
							}else if(Constants.VALUE_TYPE_MIN.equals(valueType)) {
								getMinResult(ar,resultMap2);
							}else if(Constants.VALUE_TYPE_PROPORTION.equals(valueType)) {
								getResultMap(ar,resultMap);
							}
							
					//	}
					}
				}
			}
		}
		/**
		 * 将按比例的积分计划添加到入账信息表中
		 */
		for(Entry<String,List<ActivityResult>> entry: resultMap.entrySet()) {
			List<ActivityResult> list = entry.getValue();
			for(ActivityResult result:list) {
				for (int k = 0; k < result.ruleGroupResults.size(); k++) {
					RuleGroupResult rg = (RuleGroupResult) result.ruleGroupResults.get(k);
					printGrpResult(itfList, txnBonus, result, rg);
				}
				
			}
		}
		
		/**
		 * 将按最大最小的积分计划添加到入账信息表中
		 */
		for(Entry<String,ActivityResult> entry:resultMap2.entrySet()) {
			ActivityResult result = entry.getValue();
			for (int k = 0; k < result.ruleGroupResults.size(); k++) {
				RuleGroupResult rg = (RuleGroupResult) result.ruleGroupResults.get(k);
				printGrpResult(itfList, txnBonus, result, rg);
			}
		}
		
		
	}
	
	/***
	 * 
	 * @param grp
	 * @param ar
	 * @param resultMap2
	 */
	private void getMinResult( ActivityResult ar, Map<String, ActivityResult> resultMap2) {
		String bonusType =ar.bonusType;
		ActivityResult result = resultMap2.get(bonusType);
		if(result == null) {
			resultMap2.put(bonusType, ar);
			return;
		}
		Double point = result.result.bonusPoint;
		Double point2 = ar.result.bonusPoint;
		if(point2.compareTo(point)<0) {//后一个的活动积分大于前一个的活动积分,替代前一个积分活动
			resultMap2.put(bonusType, ar);
		}
		
	}
	/***
	 * 
	 * @param grp
	 * @param ar
	 * @param resultMap2
	 */
	private void getMaxResult( ActivityResult ar, Map<String, ActivityResult> resultMap2) {
		String bonusType =ar.bonusType;
		ActivityResult result = resultMap2.get(bonusType);
		if(result == null) {
			resultMap2.put(bonusType, ar);
			return;
		}
		Double point = result.result.bonusPoint;
		Double point2 = ar.result.bonusPoint;
		if(point2.compareTo(point)>0) {//后一个的活动积分大于前一个的活动积分,替代前一个积分活动
			resultMap2.put(bonusType, ar);
		}
		
	}
	/***
	 * 
	 * @param grp
	 * @param ar
	 * @param resultMap
	 */
	private void getResultMap( ActivityResult ar, Map<String, List<ActivityResult>> resultMap) {
		String bonusType = ar.bonusType;//积分计划类型
		//String valueType = ar.valueType;//1.最大 2.最小 3.按比例
		String analysisValue= ar.analysisValue;//比例值
		
		List<ActivityResult> results = resultMap.get(bonusType);
		if(results == null) {
			results = new ArrayList<ActivityResult>();
			resultMap.put(bonusType, results);
		}
		
		String[] ss = analysisValue.split("\\|");
		for(String s :ss) {
			String proportion = s.split(",")[0];
			String bType = s.split(",")[1];
			ActivityResult result = create(proportion,bType,ar);
			results.add(result);
		}
		
		
		
		
	}
	/***
	 * 
	 * @param proportion
	 * @param bType
	 * @param ar
	 * @return
	 */
	private ActivityResult create(String proportion, String type, ActivityResult ar) {
		ActivityResult result = new ActivityResult();
		result.name = ar.name;
		result.periodStart = ar.periodStart;
		result.periodEnd = ar.periodEnd;
		result.validDate = ar.validDate;
		result.effectiveDate = ar.effectiveDate;
		result.bonusType = type;
		result.effectiveFlag = ar.effectiveFlag;
		result.bonusCycle = ar.bonusCycle;
		result.cyclePoints = ar.cyclePoints;
		result.valueType = ar.valueType;
		result.analysisValue = proportion;
		result.result.ruleName = ar.result.ruleName;// 规则名字
		result.result.bpPlanType = type;
		result.result.bonusPoint = ar.result.bonusPoint;// BIGINT
																														// 相关积分
		result.result.validDate = ar.result.validDate;// CHAR(8) 有效期
		result.result.effectiveDate = ar.result.effectiveDate;// 生效日期
		result.result.priority = ar.result.priority;// 优先级

		// result.results =ar.results;
		result.ruleGroupResults = ar.ruleGroupResults;
		return result;
	}
	/***
	 * 
	 * @param itfList
	 * @param txnBonus
	 * @param ar
	 * @param grp
	 */
	private void printGrpResult(List<TblBonusAccItf> itfList, TxnBonus txnBonus, ActivityResult ar, RuleGroupResult grp) {
		if (grp.results != null && grp.results.size() > 0) {
			int size = grp.results.size();
			for (int i = 0; i < size; i++) {
				TxnBonusResult rt = (TxnBonusResult) grp.results.get(i);
				printResult(itfList, txnBonus, rt, ar);
			}
		}
		
	}
	private void printResult(List<TblBonusAccItf> itfList, TxnBonus txnBonus, TxnBonusResult rt, ActivityResult ar) {
		int bonusPoint = (int) rt.getRoundBonusPoint();
		String valueType = ar.valueType;
		if(Constants.VALUE_TYPE_PROPORTION.equals(valueType)) {
			int proportion = Integer.parseInt(ar.analysisValue);
			bonusPoint = bonusPoint * proportion /100;
		}
		if (rt.isExec && rt.isEffect && bonusPoint > 0) {
			if (rt.isExec && rt.isEffect) {
				taskDateSeq++;// 计算待入账序列
				String activeId = ar.name != null ? ar.name.substring(2) : "";
				String ruleId = rt.ruleName != null ? rt.ruleName.substring(2) : "";
				TblBonusAccItf itf = new TblBonusAccItf();
				itf.setTaskId("1004");
				itf.setTaskDateSeq(taskDateSeq);
				itf.setTaskDate(Util.getCurrDate());
				itf.setUsageKey("JF");
				itf.setCustId(txnBonus.getCust_id());// /客户号
				itf.setAcctId(txnBonus.getAcct_no());
				itf.setActivityId(activeId);// activity_id 满足活动
				itf.setRuleId(ruleId);// rule_id 满足规则
				itf.setBonusSsnOra(txnBonus.getTxn_ssn_ora());// bonus_ssn_ora
																		// 原交易键值
																		// (原交易流水)
				itf.setTxnCodeOra(txnBonus.getTxn_code_ora());// txn_code_ora
																		// 原业务数据类型
				StringBuffer descSB = new StringBuffer();
				descSB.append("金额:").append(txnBonus.getTxn_amt());
				descSB.append(",笔数:").append(txnBonus.getTxn_cnt());
				if(Constants.VALUE_TYPE_PROPORTION.equals(valueType)) {
					descSB.append(",按比例拆分,拆分比例:" + Integer.parseInt(ar.analysisValue));
				}
				String desc = descSB.length() > 100 ? descSB.substring(0, 100) : descSB.toString();
				itf.setTxnDescOra(desc);// txn_desc_ora 原交易描述
				itf.setTxnTime(txnBonus.getTxn_time());
				itf.setTxnAmtOra(txnBonus.getTxn_amt());// txn_amt_ora
																// 原交易金额
				itf.setTxnCntOra(new Double(0));// txn_cnt_ora 原交易笔数
				itf.setTxnDateOra(txnBonus.getTxn_date());// txn_date_ora
																	// 原交易日期
				itf.setTxnBonus(new Double(bonusPoint));// txn_bonus 积分值
				itf.setBpPlanType(rt.bpPlanType);// Bp_plan_type
																// 积分计划
				itf.setValidDate(rt.validDate);// Valid_date 有效日期
				itf.setFlag("0");// 入账标识
				itf.setExtCoulmn1(txnBonus.getSign_flag());// 签约标志
				itf.setExtCoulmn2(txnBonus.getTxn_type());// 交易类型
				itf.setExtCoulmn3(txnBonus.getChannel());// 交易渠道
				itf.setFirstFlag(txnBonus.getFirst_flag());// 首笔交易
				itf.setProductType(txnBonus.getProduct_type());// 产品类型
				itf.setPeriod(txnBonus.getTxn_period()+"");// 周期
				itf.setClientSource(txnBonus.getClient_source());// 客户端来源
				itf.setTradeBank(txnBonus.getTrade_bank());//交易账户机构号
				itf.setCustBank(txnBonus.getCust_bank());//客户机构号
				// statistics_rule_id /统计规则，隐藏字段，用于统计时区分规则
				
				
				/*if (StringUtils.isNotBlank(txnBonus.getStatistics_rule_id())
						&& !Constants.STATISTICS_RULE_ID.equalsIgnoreCase

						(txnBonus.getStatistics_rule_id())) {
					bonusAccItf.setTxnDescOra("统计类,总金额："
							+ txnBonus.getTxn_total_amt() + "，总笔数："
							+ txnBonus.getTxn_cnt());
				}*/
			//	item.getTblBonusAccInfList().add(bonusAccItf);
				itfList.add(itf);
			}
		}

	}
	private List<TxnBonus> convertToTxnBonus(List<? extends BonusBean> items) {
		List<TxnBonus> list = new ArrayList<>();
		for(BonusBean item:items) {
			TxnBonus txnBonus = convertToTxnBonus(item);
			list.add(txnBonus);
		}
		return list;
	}
	
	private TxnBonus convertToTxnBonus(BonusBean item) {
		TxnBonus txnBonus = new TxnBonus();
		txnBonus.setCust_id(StringUtil.trim(item.getCustId()));// 核心客户号
		txnBonus.setCust_name("");// 核心客户名
		txnBonus.setBirday(StringUtil.trim(item.getCustBirthday()));// 出生日期
		txnBonus.setCertno(StringUtil.trim(""));// 客户证件号
		txnBonus.setCerttp(StringUtil.trim(""));// 证件类型
		txnBonus.setGenter(StringUtil.trim(""));// 性别
		txnBonus.setHunyzk(StringUtil.trim(""));// 婚姻情况
		txnBonus.setXuelii(StringUtil.trim(""));// 学历
		txnBonus.setPhoneno(StringUtil.trim(""));// 手机号码
		txnBonus.setDizhii(StringUtil.trim(""));// 地址
		txnBonus.setCust_type(StringUtil.trim(""));// 客户类型
		txnBonus.setOpen_date(StringUtil.trim(""));// 开户日期

		txnBonus.setAcct_no(StringUtil.trim(item.getCardNo()));// 卡号/活期一本通号/定期一本通号/e电子账户
		txnBonus.setAcct_type(StringUtil.trim(""));// 帐户类型
		// txnBonus.setFakarq(StringUtil.trim(rs.getString(" ")));// 发卡日期
		txnBonus.setCard_bank(StringUtil.trim(""));// 所属机构
		txnBonus.setCard_prod(StringUtil.trim(""));// 卡产品
		txnBonus.setCard_level(StringUtil.trim(""));// 卡等级
		txnBonus.setKaaajz(StringUtil.trim(""));// 卡介质
		txnBonus.setKaaaxz(StringUtil.trim(""));// 卡性质
		txnBonus.setKaaazl(StringUtil.trim(""));// 卡种类
		txnBonus.setTxn_date(StringUtil.trim(item.getTxnDate()));// 交易日期
		txnBonus.setTxn_time(StringUtil.trim(item.getTxnTime()));// 交易时间
		txnBonus.setTxn_code_ora(StringUtil.trim(item.getTxnCodeOra()));// 交易码
		txnBonus.setTxn_ssn_ora(StringUtil.trim(item.getTxnSsnOra()));// 交易流水号
		String txn_type = StringUtil.trim(item.getTxnType());// 交易类型
																// 防入数据库时报NULL异常
		// txnBonus.setTxn_type(StringUtils.isBlank(txn_type) ? " " : txn_type);// 交易类型
		txnBonus.setCrcycd(StringUtil.trim(""));// 交易币种
		txnBonus.setMcc_code(StringUtil.trim(item.getMccCode()));// mcc码
		txnBonus.setChannel(StringUtil.trim(""));// 交易渠道
		txnBonus.setMcth_no(StringUtil.trim(item.getMcthNo()));// 商户号
		txnBonus.setMcth_name(StringUtil.trim(""));// 商户名称
		txnBonus.setTxn_amt(String.valueOf(item.getTxnAmt()));// 交易金额
		txnBonus.setTxn_bank(StringUtil.trim(""));// 交易所属机构
		txnBonus.setTrandt(StringUtil.trim(""));// 开通签约日期
		txnBonus.setSign_flag(StringUtil.trim(""));// 开立米宝账户标志
		txnBonus.setFirst_flag(StringUtil.trim("")); // 预留字段
														// 首笔交易标志
		txnBonus.setProduct_type(StringUtil.trim("")); // 预留字段
														// 产品类型
		txnBonus.setTxn_period(StringUtil.trim("")); // 预留字段 周期
		txnBonus.setClient_source(StringUtil.trim("")); // 客户端来源
		txnBonus.setTrade_bank(StringUtil.trim("")); // 交易账户机构号
		txnBonus.setCust_bank(StringUtil.trim("")); // 客户机构号
		txnBonus.setMCC(StringUtil.trim(item.getMccCode()));// poc相关字段
		txnBonus.setTC(StringUtil.trim(item.getTc()));// poc相关字段
		txnBonus.setMerchantNo(StringUtil.trim(item.getMcthNo()));// poc相关字段
		txnBonus.setBirthday_today(StringUtil.trim(item.getTxnDate().equals(item.getCustBirthday())?"1":""));

		// txnBonus.setFront_cust(StringUtil.trim(rs.getString("FRONT_CUST")));
		// txnBonus.setIsStatistic(StringUtil.trim(rs.getString("ISSTATISTIC")));

		txnBonus.convertData();

		// dataPackage(connection, txnBonus, retMap, custQueryMap, custNumMap);
		// txnBonus.setField1(StringUtil.trim(rs.getString(" "))); // 贷款类型
		// txnBonus.setField2(StringUtil.trim(rs.getString(" "))); // 预留字段
		// txnBonus.setField3(StringUtil.trim(rs.getString(" "))); // 预留字段
		// txnBonus.setField4(StringUtil.trim(rs.getString(" "))); // 预留字段
		// txnBonus.setField5(StringUtil.trim(rs.getString(" "))); // 预留字段
		return txnBonus;
	}
	
}
