package com.huateng.batch.ItemReader;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.OraclePagingQueryProvider;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.huateng.batch.model.BonusBean;

/***
 * 查询已经产生待入账积分的客户，但是还没有对应的tbl_bonus_plan记录的
 * @author data
 *
 */
public class InsertPlanReader {
	private DataSource dataSource;
	private String task_date;
	public  JdbcPagingItemReader<BonusBean> getReader()
			throws Exception {
		
		JdbcPagingItemReader<BonusBean> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);               //设置数据源
        reader.setFetchSize(5000);                         //FetchSize设置为2，表示每次从数据库中,2条数据
        reader.setRowMapper(new BeanPropertyRowMapper<BonusBean>(BonusBean.class));       //把数据库表中每条数据映射到对象中
        OraclePagingQueryProvider queryProvider = new OraclePagingQueryProvider();
        queryProvider.setSelectClause(" distinct tbai.cust_id,tbai.acct_id,tbai.bp_plan_type ");  //设置查询的列
        queryProvider.setFromClause("from tbl_bonus_acc_itf tbai ");     //设置查询的表
        queryProvider.setWhereClause("where 1=1 and not exists( select tbp.cust_id from tbl_bonus_plan tbp  where tbp.cust_id=tbai.cust_id and tbp.acct_id=tbai.acct_id and tbai.bp_plan_type=tbp.bp_plan_type )");
        Map<String, Order> sortKeys = new HashMap<>(); //定义一个map，用于存放排序列
        sortKeys.put("TXN_DATE", Order.ASCENDING);           //按id列升序排列
        sortKeys.put("TXN_TIME", Order.ASCENDING);           //按id列升序排列

       // sortKeys.put("age", Order.DESCENDING);         //按客户号的降序排列
        queryProvider.setSortKeys(sortKeys);           //设置排序列
        reader.setQueryProvider(queryProvider);
        return reader;

	
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
