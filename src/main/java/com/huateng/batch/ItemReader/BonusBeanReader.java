package com.huateng.batch.ItemReader;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.OraclePagingQueryProvider;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.huateng.batch.model.BonusBean;

public class BonusBeanReader {
	private DataSource dataSource;
	private String task_date;

	public JdbcPagingItemReader<BonusBean> getReader() throws Exception {

		JdbcPagingItemReader<BonusBean> reader = new JdbcPagingItemReader<>();
		reader.setDataSource(dataSource); // 设置数据源
		reader.setFetchSize(5000); // FetchSize设置为2，表示每次从数据库中,2条数据
		reader.setPageSize(reader.getPage() + 10);
		reader.setRowMapper(new BeanPropertyRowMapper<BonusBean>(BonusBean.class)); // 把数据库表中每条数据映射到对象中
		OraclePagingQueryProvider queryProvider = new OraclePagingQueryProvider();
		queryProvider.setSelectClause(
				" t1.TXN_CODE_ORA,t1.TXN_SSN_ORA,t1.TXN_TYPE,t1.CUST_ID,t1.CARD_NO,t1.PRODUCT_TYPE,t1.TXN_AMT,t1.MCTH_NO,t1.MCC_CODE,t1.TC,t1.TXN_DATE,t1.TXN_TIME,t2.CUST_BIRTHDAY"); // 设置查询的列
		queryProvider.setFromClause("from TBL_TXN_ORA_DAILY t1 left join TBL_CUSt_INF t2 on t1.CUST_ID=t2.CUST_ID"); // 设置查询的表
		queryProvider.setWhereClause("where 1=1 ");
		Map<String, Order> sortKeys = new HashMap<>(); // 定义一个map，用于存放排序列
		sortKeys.put("TXN_DATE", Order.ASCENDING); // 按id列升序排列
		sortKeys.put("TXN_TIME", Order.ASCENDING); // 按id列升序排列

		// sortKeys.put("age", Order.DESCENDING); //按客户号的降序排列
		queryProvider.setSortKeys(sortKeys); // 设置排序列
		reader.setQueryProvider(queryProvider);
		return reader;

	}

	public JdbcCursorItemReader<BonusBean> getReader2() throws Exception {

		JdbcCursorItemReader<BonusBean> reader = new JdbcCursorItemReader<>();
		reader.setDataSource(dataSource); // 设置数据源
		reader.setFetchSize(10000); // FetchSize设置为2，表示每次从数据库中,2条数据
		
		reader.setRowMapper(new BeanPropertyRowMapper<BonusBean>(BonusBean.class)); // 把数据库表中每条数据映射到对象中
		reader.setSql(
				"select t1.TXN_CODE_ORA,t1.TXN_SSN_ORA,t1.TXN_TYPE,t1.CUST_ID,t1.CARD_NO,t1.PRODUCT_TYPE,t1.TXN_AMT,t1.MCTH_NO,t1.MCC_CODE,t1.TC,t1.TXN_DATE,t1.TXN_TIME,t2.CUST_BIRTHDAY "
				+ "from TBL_TXN_ORA_DAILY t1 left join TBL_CUSt_INF t2 on t1.CUST_ID=t2.CUST_ID where 1=1 order by t1.TXN_DATE desc,t1.TXN_TIME desc");
	
		return reader;

	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
