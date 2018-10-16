package com.huateng.batch.ItemReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.OraclePagingQueryProvider;
import org.springframework.jdbc.core.RowMapper;

import com.huateng.batch.model.IntoAcctBean;
import com.huateng.batch.model.TblBonusAccItf;
import com.huateng.batch.model.TblBonusPlan;
import com.huateng.batch.model.TblBonusPlanDetail;
import com.huateng.util.Util;

//@Component
public class IntoAcctReader{
	
	//@Autowired
	//private JdbcTemplate template;
	private DataSource dataSource;
	
	public  JdbcPagingItemReader<IntoAcctBean> getReader()
			throws Exception {
		
		JdbcPagingItemReader<IntoAcctBean> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);               //设置数据源
        reader.setFetchSize(5000);                         //FetchSize设置为2，表示每次从数据库中,2条数据
        reader.setRowMapper(new MyRowMapper());       //把数据库表中每条数据映射到对象中
        OraclePagingQueryProvider queryProvider = new OraclePagingQueryProvider();
        queryProvider.setSelectClause("distinct tbai.cust_id,tbai.acct_id,tbai.bp_plan_type ");  //设置查询的列
        queryProvider.setFromClause("from tbl_bonus_acc_itf tbai");     //设置查询的表
        queryProvider.setWhereClause("where  not exists(select tbp.cust_id from tbl_bonus_plan tbp where tbp.cust_id=tbai.cust_id and tbp.acct_id=tbai.acct_id and tbai.bp_plan_type=tbp.bp_plan_type)");
        Map<String, Order> sortKeys = new HashMap<>(); //定义一个map，用于存放排序列
        sortKeys.put("TXN_DATE", Order.ASCENDING);           //按id列升序排列
        sortKeys.put("TXN_TIME", Order.ASCENDING);           //按id列升序排列

        queryProvider.setSortKeys(sortKeys);           //设置排序列
        reader.setQueryProvider(queryProvider);
        return reader;

	
	}
	
	/***
	 * 查到新的积分账户信息
	 * @return
	 * @throws Exception
	 */
	public  JdbcCursorItemReader<IntoAcctBean> getReader2()
			throws Exception {
		String sql = "select distinct tbai.cust_id,tbai.acct_id,tbai.bp_plan_type "//客户编号,账户编号,积分类型
				+ "from tbl_bonus_acc_itf tbai " //查询积分待入账表
				+ "where  not exists( "//不存在积分账户表
				+ "select tbp.cust_id from tbl_bonus_plan tbp "//
				+ "where tbp.cust_id=tbai.cust_id "//客户号,账号，积分类型保持一致
				+ "and tbp.acct_id=tbai.acct_id "//
				+ "and tbai.bp_plan_type=tbp.bp_plan_type) "//
				+"Order by  tbai.CUST_ID desc";//客户号排序
		
		JdbcCursorItemReader<IntoAcctBean> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);               //设置数据源
        reader.setFetchSize(10000);                    //FetchSize设置为10000，表示每次从数据库中读10000条数据
        reader.setRowMapper(new MyRowMapper());       //把数据库表中每条数据映射到对象中
        reader.setSql(sql);;
        return reader;

	
	}
	
	private class MyRowMapper implements  RowMapper<IntoAcctBean>{

		@Override
		public IntoAcctBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			IntoAcctBean bean = new IntoAcctBean() {
				{
					setItf(new TblBonusAccItf());
					setPlan(new TblBonusPlan());
					setDetail(new TblBonusPlanDetail());
				}
			};
			bean.getItf().setCustId(rs.getString("cust_id"));
			bean.getItf().setAcctId(rs.getString("acct_id"));
			bean.getItf().setBpPlanType(rs.getString("bp_plan_type"));
			bean.getPlan().setCustId(rs.getString("cust_id"));
			bean.getPlan().setAcctId(rs.getString("acct_id"));
			bean.getPlan().setUsageKey("JF");
			bean.getPlan().setLockStatus("0");
			bean.getPlan().setBpPlanType(rs.getString("bp_plan_type"));
			bean.getPlan().setTotalBonus(0);
			bean.getPlan().setValidBonus(0);
			bean.getPlan().setApplyBonus(0);
			bean.getPlan().setExpireBonus(0);
			bean.getPlan().setCreateOper("SYSTEM");
			bean.getPlan().setCreateDate(Util.getCurrDate());
			bean.getPlan().setCreateTime(Util.getCurrTime2());
			bean.getPlan().setModifyOper("SYSTEM");
			bean.getPlan().setModifyDate(Util.getCurrDate());
			bean.getPlan().setModifyTime(Util.getCurrTime2());
			
			return bean;
		}
		
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	/***
	 * 查到新的积分账户信息(带有效期信息)
	 * @return
	 * @throws Exception
	 */
	public  JdbcCursorItemReader<IntoAcctBean> getReader3()
			throws Exception {
		String sql = "select distinct tbai.cust_id,tbai.acct_id,tbai.bp_plan_type,tbai.valid_date "//客户编号,账户编号,积分类型,积分有效期
				+ "from tbl_bonus_acc_itf tbai " //查询积分待入账表
				+ "where  not exists( "//不存在积分账户表
				+ "select 1 from tbl_bonus_plan_detail tbpd "//
				+ "where tbpd.cust_id=tbai.cust_id "//客户号,账号，积分类型保持一致
				+ "and tbpd.acct_id=tbai.acct_id "//
				+ "and tbpd.bp_plan_type=tbai.bp_plan_type "//
				+ "and tbpd.valid_date=tbai.valid_date) " //
				+"Order by  tbai.CUST_ID desc";//客户号排序
		
		JdbcCursorItemReader<IntoAcctBean> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);               //设置数据源
        reader.setFetchSize(10000);                    //FetchSize设置为10000，表示每次从数据库中读10000条数据
        reader.setRowMapper(new MyRowMapper1());       //把数据库表中每条数据映射到对象中
        reader.setSql(sql);;
        return reader;

	
	}
	
	private class MyRowMapper1 implements  RowMapper<IntoAcctBean>{

		@Override
		public IntoAcctBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			IntoAcctBean bean = new IntoAcctBean() {
				{
					setItf(new TblBonusAccItf());
					setPlan(new TblBonusPlan());
					setDetail(new TblBonusPlanDetail());
				}
			};
			bean.getItf().setCustId(rs.getString("cust_id"));
			bean.getItf().setAcctId(rs.getString("acct_id"));
			bean.getItf().setBpPlanType(rs.getString("bp_plan_type"));
			bean.getItf().setValidDate(rs.getString("valid_date"));
			
			bean.getDetail().setCustId(rs.getString("cust_id"));
			bean.getDetail().setAcctId(rs.getString("acct_id"));
			bean.getDetail().setBpPlanType(rs.getString("bp_plan_type"));
			bean.getDetail().setValidDate(rs.getString("valid_date"));
			
			bean.getDetail().setTotalBonus(0);
			bean.getDetail().setValidBonus(0);
			bean.getDetail().setApplyBonus(0);
			bean.getDetail().setExpireBonus(0);
			bean.getDetail().setExpiredStatus("0");
			bean.getDetail().setUsageKey("JF");
			bean.getDetail().setCreateOper("SYSTEM");
			bean.getDetail().setCreateDate(Util.getCurrDate());
			bean.getDetail().setCreateTime(Util.getCurrTime2());
			bean.getDetail().setModifyOper("SYSTEM");
			bean.getDetail().setModifyDate(Util.getCurrDate());
			bean.getDetail().setModifyTime(Util.getCurrTime2());
			bean.getDetail().setLockStatus("0");
			
			return bean;
		}
		
	}


}
