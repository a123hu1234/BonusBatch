package com.huateng.batch.Processor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

import com.huateng.batch.model.BonusBean;
import com.huateng.batch.model.TblBonusAccItf;
import com.huateng.toprules.adapter.PackageResult.ActivityResult;
import com.huateng.toprules.adapter.PackageResult.RuleGroupResult;
import com.huateng.toprules.adapter.TxnBonus;
import com.huateng.toprules.adapter.TxnBonusResult;
import com.huateng.util.StringUtil;
import com.huateng.util.Util;

public class ComputeBonusItemProcessor extends ValidatingItemProcessor<BonusBean> {
	// private String type = "JF";
	private Logger logger = LoggerFactory.getLogger(ComputeBonusItemProcessor.class);
	private long taskDateSeq;

	// private List<BonusBean> list = new ArrayList<BonusBean>();
	@Override
	public BonusBean process(BonusBean item) throws ValidationException {
		// logger.info("开始计算积分");
		super.process(item); // 需要执行super.process(item)才会调用自定义校验器
//		logger.info("正在加载规则文件......");
//		try {
//			TopRules topRules = DroolIntance.getIntance().getTopRules();
//			logger.info("开始计算积分");
//			Map<String, String> regexMap = new HashMap<String, String>();
//			regexMap.put("yyyyMMdd", Util.getCurrDate());
//			//String dataLoca = ConfigProperties.getFullPathProperties("sqlldr.file.locate", regexMap);
//			//logger.info("正在创建目录:" + dataLoca);
//			//FileUtil.mkdirs(dataLoca);
//			//logger.info("完成创建目录:" + dataLoca);
//
//			String ruleFileName = ConfigProperties.getProperties("file.name.rule.JF");
//			TxnBonus txnBonus = convertToTxnBonus(item);
//			List<TxnBonus> list = new ArrayList<TxnBonus>();
//			list.add(txnBonus);
//			topRules.execPkg(list, ruleFileName);
//			logger.info("保存计算结果");
//			writToBonusBean(item, txnBonus);// 将积分结果转化成待入账数据
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		logger.info("载规则文件完成");

		return item;
	}

	/***
	 * 
	 * @param item
	 * @param txnBonus
	 */
	private void writToBonusBean(BonusBean item, TxnBonus txnBonus) {
		List<ActivityResult> actResultList = txnBonus.getResult().activityGroupResults;
		if (actResultList != null && actResultList.size() > 0) {
			for (ActivityResult ar : actResultList) {
				if (ar.result != null) {
					if (ar.ruleGroupResults != null && ar.ruleGroupResults.size() > 0) {
						for (int k = 0; k < ar.ruleGroupResults.size(); k++) {
							RuleGroupResult grp = (RuleGroupResult) ar.ruleGroupResults.get(k);
							printGrpResult(item, txnBonus, ar, grp);
						}
					}
				}
			}
		}
	}

	private void printGrpResult(BonusBean item, TxnBonus txnBonus, ActivityResult ar, RuleGroupResult grp) {
		if (grp.results != null && grp.results.size() > 0) {
			int size = grp.results.size();
			for (int i = 0; i < size; i++) {
				TxnBonusResult rt = (TxnBonusResult) grp.results.get(i);
				printResult(item, txnBonus, rt, ar);
			}
		}

	}

	private void printResult(BonusBean item, TxnBonus txnBonus, TxnBonusResult rt, ActivityResult ar) {
		int bonusPoint = (int) rt.getRoundBonusPoint();
		if (rt.isExec && rt.isEffect && bonusPoint > 0) {
			if (rt.isExec && rt.isEffect) {
				taskDateSeq++;// 计算待入账序列
				String activeId = ar.name != null ? ar.name.substring(2) : "";
				String ruleId = rt.ruleName != null ? rt.ruleName.substring(2) : "";
				TblBonusAccItf bonusAccItf = new TblBonusAccItf();
				bonusAccItf.setTaskId("1004");
				bonusAccItf.setTaskDateSeq(taskDateSeq);
				bonusAccItf.setTaskDate(Util.getCurrDate());
				bonusAccItf.setUsageKey("JF");
				bonusAccItf.setCustId(txnBonus.getCust_id());// /客户号
				bonusAccItf.setAcctId(txnBonus.getAcct_no());
				bonusAccItf.setActivityId(activeId);// activity_id 满足活动
				bonusAccItf.setRuleId(ruleId);// rule_id 满足规则
				bonusAccItf.setBonusSsnOra(txnBonus.getTxn_ssn_ora());// bonus_ssn_ora
																		// 原交易键值
																		// (原交易流水)
				bonusAccItf.setTxnCodeOra(txnBonus.getTxn_code_ora());// txn_code_ora
																		// 原业务数据类型
				StringBuffer descSB = new StringBuffer();
				descSB.append("金额:").append(txnBonus.getTxn_amt());
				descSB.append(",笔数:").append(txnBonus.getTxn_cnt());
				String desc = descSB.length() > 100 ? descSB.substring(0, 100) : descSB.toString();
				bonusAccItf.setTxnDescOra(desc);// txn_desc_ora 原交易描述
				bonusAccItf.setTxnTime(txnBonus.getTxn_time());
				bonusAccItf.setTxnAmtOra(txnBonus.getTxn_amt());// txn_amt_ora
																// 原交易金额
				bonusAccItf.setTxnCntOra(new Double(0));// txn_cnt_ora 原交易笔数
				bonusAccItf.setTxnDateOra(txnBonus.getTxn_date());// txn_date_ora
																	// 原交易日期
				bonusAccItf.setTxnBonus(new Double(bonusPoint));// txn_bonus 积分值
				bonusAccItf.setBpPlanType(rt.bpPlanType);// Bp_plan_type
																// 积分计划
				bonusAccItf.setValidDate(rt.validDate);// Valid_date 有效日期
				bonusAccItf.setFlag("0");// 入账标识
				bonusAccItf.setExtCoulmn1(txnBonus.getSign_flag());// 签约标志
				bonusAccItf.setExtCoulmn2(txnBonus.getTxn_type());// 交易类型
				bonusAccItf.setExtCoulmn3(txnBonus.getChannel());// 交易渠道
				bonusAccItf.setFirstFlag(txnBonus.getFirst_flag());// 首笔交易
				bonusAccItf.setProductType(txnBonus.getProduct_type());// 产品类型
				bonusAccItf.setPeriod(txnBonus.getTxn_period()+"");// 周期
				bonusAccItf.setClientSource(txnBonus.getClient_source());// 客户端来源
				bonusAccItf.setTradeBank(txnBonus.getTrade_bank());//交易账户机构号
				bonusAccItf.setCustBank(txnBonus.getCust_bank());//客户机构号
				// statistics_rule_id /统计规则，隐藏字段，用于统计时区分规则
				
				
				/*if (StringUtils.isNotBlank(txnBonus.getStatistics_rule_id())
						&& !Constants.STATISTICS_RULE_ID.equalsIgnoreCase

						(txnBonus.getStatistics_rule_id())) {
					bonusAccItf.setTxnDescOra("统计类,总金额："
							+ txnBonus.getTxn_total_amt() + "，总笔数："
							+ txnBonus.getTxn_cnt());
				}*/
				item.getTblBonusAccInfList().add(bonusAccItf);
			}
		}

	}

	/**
	 * 建交易数据对象转变成规则引擎数据源对象
	 * 
	 * @param item
	 * @return
	 */
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
