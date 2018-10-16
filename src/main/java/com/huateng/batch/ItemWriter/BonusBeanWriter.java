package com.huateng.batch.ItemWriter;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import com.huateng.batch.model.BonusBean;

/***
 * 
 * @author data
 *
 */
public class BonusBeanWriter {
	private DataSource dataSource;
	public ItemWriter<BonusBean> getWriter() {
		JdbcBatchItemWriter<BonusBean> insertWriter = new JdbcBatchItemWriter<BonusBean>();
		insertWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<BonusBean>());
		//	String sql = "insert into " + TblAccountInfTmp.getTableClum() + "values(" + TblAccountInfTmp.getBeanClum() + ")";
		String sql = "insert into " + BonusBean.getTableClum() +" values (" + BonusBean.getBeanClum() +")";
		insertWriter.setSql(sql); // 3
			
		//deleteWriter.setSql("delete from  tbl_Account_inf where Account_id='“”'"); // 3
		insertWriter.setDataSource(dataSource);
		
		return insertWriter;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
}
