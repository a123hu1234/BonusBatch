package com.huateng.batch.configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/***
 * 导入数据到客户临时表
 * 
 * @author data
 *
 */
@Configuration
@EnableBatchProcessing
public class DeleteWriterConfig extends ItemWriterAdapter<Object> {
	private DriverManagerDataSource dataSource;
	private String sql;

	public DriverManagerDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DriverManagerDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	@Bean
	public void write(List<? extends Object> items) throws Exception {
		System.out.println("init write delete");
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
		System.out.println("init delete data complete.");
	}		
	public void afterPropertiesSet() throws Exception {	   
//		Assert.notNull(dataSource, "dataSource limit must be set");	   
	//	Assert.notNull(sql, "sql limit must be set");	
		}


}
