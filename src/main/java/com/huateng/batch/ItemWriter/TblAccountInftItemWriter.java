package com.huateng.batch.ItemWriter;
/*
 * 
 * pringbatch 写入总线集合
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.huateng.batch.BeanRowMapperTool.BeanRowMapper;
import com.huateng.batch.dao.TblAccountInfDaoI;
import com.huateng.batch.model.TblAccountInf;
import com.huateng.batch.model.TblAccountInfTmp;

@Component("tblAccountInftItemWriter") 
public class TblAccountInftItemWriter implements ItemWriter<TblAccountInfTmp> {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private TblAccountInfDaoI TblAccountInfDaoImpl;
	BeanRowMapper<TblAccountInfTmp> tblAccountInfTmpMapper = new BeanRowMapper<TblAccountInfTmp>(TblAccountInfTmp.class);
	




	public void afterPropertiesSet() throws Exception {
		//Assert.notNull(dataSource, "dataSource limit must be set");
		//Assert.notNull(sql, "sql limit must be set");
	}


	@Override
	public void write(List<? extends TblAccountInfTmp> items) throws Exception {
		for(Object bean :items) {
			TblAccountInfDaoImpl.delete((TblAccountInf)bean);
			TblAccountInfDaoImpl.save((TblAccountInf)bean);
		}
		
	}

	


}
