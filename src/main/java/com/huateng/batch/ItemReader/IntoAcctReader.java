package com.huateng.batch.ItemReader;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.jdbc.core.RowMapper;

import com.huateng.batch.model.IntoAcctBean;
import com.huateng.batch.model.TblBonusAccItf;
import com.huateng.batch.model.TblBonusPlan;
import com.huateng.batch.model.TblBonusPlanDetail;
import com.huateng.util.Util;

//@Component
public class IntoAcctReader {

	// @Autowired
	// private JdbcTemplate template;
	private DataSource dataSource;



	/***
	 * 查到新的积分账户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public JdbcCursorItemReader<TblBonusPlan> getTblBonusPlanReader() throws Exception {
		String sql = "select distinct tbai.cust_id,tbai.acct_id,tbai.bp_plan_type "// 客户编号,账户编号,积分类型
				+ "from tbl_bonus_acc_itf tbai " // 查询积分待入账表
				+ "where  not exists( "// 不存在积分账户表
				+ "select tbp.cust_id from tbl_bonus_plan tbp "//
				+ "where tbp.cust_id=tbai.cust_id "// 客户号,账号，积分类型保持一致
				+ "and tbp.acct_id=tbai.acct_id "//
				+ "and tbai.bp_plan_type=tbp.bp_plan_type) "//
				+ "Order by  tbai.CUST_ID desc";// 客户号排序

		JdbcCursorItemReader<TblBonusPlan> reader = new JdbcCursorItemReader<>();
		reader.setDataSource(dataSource); // 设置数据源
		reader.setFetchSize(10000); // FetchSize设置为10000，表示每次从数据库中读10000条数据
		reader.setRowMapper(new TblBonusPlanMapper()); // 把数据库表中每条数据映射到对象中
		reader.setSql(sql);
		;
		return reader;

	}

	private class TblBonusPlanMapper implements RowMapper<TblBonusPlan> {

		@Override
		public TblBonusPlan mapRow(ResultSet rs, int rowNum) throws SQLException {
//			IntoAcctBean bean = new IntoAcctBean() {
//				{
//					setItf(new TblBonusAccItf());
//					setPlan(new TblBonusPlan());
//					setDetail(new TblBonusPlanDetail());
//				}
//			};
			
			TblBonusPlan plan = new TblBonusPlan();
			
			
			plan.setCustId(rs.getString("cust_id"));
			plan.setAcctId(rs.getString("acct_id"));
			plan.setUsageKey("JF");
			plan.setLockStatus("0");
			plan.setBpPlanType(rs.getString("bp_plan_type"));
			plan.setTotalBonus(0);
			plan.setValidBonus(0);
			plan.setApplyBonus(0);
			plan.setExpireBonus(0);
			plan.setCreateOper("SYSTEM");
			plan.setCreateDate(Util.getCurrDate());
			plan.setCreateTime(Util.getCurrTime2());
			plan.setModifyOper("SYSTEM");
			plan.setModifyDate(Util.getCurrDate());
			plan.setModifyTime(Util.getCurrTime2());

			return plan;
		}

	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/***
	 * 查到新的积分账户信息(带有效期信息)
	 * 
	 * @return
	 * @throws Exception
	 */
	public JdbcCursorItemReader<TblBonusPlanDetail> getTblBonusPlanDetailReader() throws Exception {
		String sql = "select distinct tbai.cust_id,tbai.acct_id,tbai.bp_plan_type,tbai.valid_date "// 客户编号,账户编号,积分类型,积分有效期
				+ "from tbl_bonus_acc_itf tbai " // 查询积分待入账表
				+ "where  not exists( "// 不存在积分账户表
				+ "select 1 from tbl_bonus_plan_detail tbpd "//
				+ "where tbpd.cust_id=tbai.cust_id "// 客户号,账号，积分类型保持一致
				+ "and tbpd.acct_id=tbai.acct_id "//
				+ "and tbpd.bp_plan_type=tbai.bp_plan_type "//
				+ "and tbpd.valid_date=tbai.valid_date) " //
				+ "Order by  tbai.CUST_ID desc";// 客户号排序

		JdbcCursorItemReader<TblBonusPlanDetail> reader = new JdbcCursorItemReader<>();
		reader.setDataSource(dataSource); // 设置数据源
		reader.setFetchSize(10000); // FetchSize设置为10000，表示每次从数据库中读10000条数据
		reader.setRowMapper(new TblBonusPlanDetailMapper()); // 把数据库表中每条数据映射到对象中
		reader.setSql(sql);
		;
		return reader;

	}

	private class TblBonusPlanDetailMapper implements RowMapper<TblBonusPlanDetail> {

		@Override
		public TblBonusPlanDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
			IntoAcctBean bean = new IntoAcctBean() {
				{
					setItf(new TblBonusAccItf());
					setPlan(new TblBonusPlan());
					setDetail(new TblBonusPlanDetail());
				}
			};
			TblBonusPlanDetail detail = new TblBonusPlanDetail();
			bean.getItf().setCustId(rs.getString("cust_id"));
			bean.getItf().setAcctId(rs.getString("acct_id"));
			bean.getItf().setBpPlanType(rs.getString("bp_plan_type"));
			bean.getItf().setValidDate(rs.getString("valid_date"));

			detail.setCustId(rs.getString("cust_id"));
			detail.setAcctId(rs.getString("acct_id"));
			detail.setBpPlanType(rs.getString("bp_plan_type"));
			detail.setValidDate(rs.getString("valid_date"));

			detail.setTotalBonus(0);
			detail.setValidBonus(0);
			detail.setApplyBonus(0);
			detail.setExpireBonus(0);
			detail.setExpiredStatus("0");
			detail.setUsageKey("JF");
			detail.setCreateOper("SYSTEM");
			detail.setCreateDate(Util.getCurrDate());
			detail.setCreateTime(Util.getCurrTime2());
			detail.setModifyOper("SYSTEM");
			detail.setModifyDate(Util.getCurrDate());
			detail.setModifyTime(Util.getCurrTime2());
			detail.setLockStatus("0");

			return detail;
		}

	}

	public JdbcCursorItemReader<TblBonusAccItf> getbonusBookingReader() throws Exception {

		String sql = "select TASK_ID,TASK_DATE_SEQ,TASK_DATE,USAGE_KEY,CUST_ID,ACCT_ID,ACCT_TYPE,ACTIVITY_ID,"
				+ "RULE_ID,BONUS_SSN_ORA,TXN_CODE_ORA,TXN_DESC_ORA,TXN_AMT_ORA,TXN_CNT_ORA,TXN_DATE_ORA,"
				+ "TXN_BONUS,BP_PLAN_TYPE,VALID_DATE,FLAG,EXT_COULMN1,EXT_COULMN2,EXT_COULMN3,EXT_COULMN4,"
				+ "MCTH_NO,MCTH_NAME,TXN_TIME,FIRST_FLAG,PRODUCT_TYPE,PERIOD,CLIENT_SOURCE "
				+ "from tbl_bonus_acc_itf ";
		JdbcCursorItemReader<TblBonusAccItf> reader = new JdbcCursorItemReader<>();
		reader.setDataSource(dataSource); // 设置数据源
		reader.setFetchSize(10000); // FetchSize设置为10000，表示每次从数据库中读10000条数据
		reader.setRowMapper(new ItfRowMapper()); // 把数据库表中每条数据映射到对象中
		reader.setSql(sql);
		;
		return reader;

	}
	
	private class ItfRowMapper  implements RowMapper<TblBonusAccItf>{

		@Override
		public TblBonusAccItf mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			
			TblBonusAccItf itf = new TblBonusAccItf();
			itf.setTaskId(rs.getString("TASK_ID"));
			itf.setTaskDateSeq(rs.getLong("TASK_DATE_SEQ"));
			itf.setTaskDate(rs.getString("TASK_DATE"));
			itf.setUsageKey(rs.getString("USAGE_KEY"));
			itf.setCustId(rs.getString("CUST_ID"));
			itf.setAcctId(rs.getString("ACCT_ID"));
			itf.setAcctType(rs.getString("ACCT_TYPE"));
			itf.setActivityId(rs.getString("ACTIVITY_ID"));
			itf.setRuleId(rs.getString("RULE_ID"));
			itf.setBonusSsnOra(rs.getString("BONUS_SSN_ORA"));
			itf.setTxnCodeOra(rs.getString("TXN_CODE_ORA"));
			itf.setTxnDescOra(rs.getString("TXN_DESC_ORA"));
			itf.setTxnAmtOra(rs.getDouble("TXN_AMT_ORA"));
			itf.setTxnCntOra(rs.getDouble("TXN_CNT_ORA"));
			itf.setTxnDateOra(rs.getString("TXN_DATE_ORA"));
			itf.setTxnBonus(rs.getDouble("TXN_BONUS"));
			itf.setBpPlanType(rs.getString("BP_PLAN_TYPE"));
			itf.setValidDate(rs.getString("VALID_DATE"));
			itf.setFlag(rs.getString("FLAG"));
			itf.setExtCoulmn1(rs.getString("EXT_COULMN1"));
			itf.setExtCoulmn2(rs.getString("EXT_COULMN2"));
			itf.setExtCoulmn3(rs.getString("EXT_COULMN3"));
			itf.setTxnTime(rs.getString("TXN_TIME"));
			itf.setFirstFlag(rs.getString("FIRST_FLAG"));
			itf.setProductType(rs.getString("PRODUCT_TYPE"));
			itf.setPeriod(rs.getString("PERIOD"));
			itf.setClientSource(rs.getString("CLIENT_SOURCE"));

			return itf;
		}
		
	}

}
