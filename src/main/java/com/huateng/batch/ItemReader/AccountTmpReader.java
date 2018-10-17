package com.huateng.batch.ItemReader;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.OraclePagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileUrlResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.huateng.batch.BeanRowMapperTool.BeanRowMapper;
import com.huateng.batch.model.TblAccountInfTmp;
import com.huateng.util.Util;

import net.bytebuddy.implementation.bind.annotation.Super;

/*
 * account信息阅读器
 * by ygq
 */
@Configuration
@EnableBatchProcessing
public class AccountTmpReader {
	
	
	@Bean
	@StepScope
	public FlatFileItemReader<TblAccountInfTmp> accountTmpFileReader(@Value("#{jobParameters['input.file.name']}") String pathToFile)
			throws Exception {
		
		FlatFileItemReader<TblAccountInfTmp> reader = new FlatFileItemReader<TblAccountInfTmp>(); //
		reader.setResource(new FileUrlResource(pathToFile)); //
		reader.setLineMapper(new DefaultLineMapper<TblAccountInfTmp>() {
			{ //
				setLineTokenizer(new DelimitedLineTokenizer(Util.asciiToStrings("0x03")) {
					{
						
						setNames(TblAccountInfTmp.toArray());
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<TblAccountInfTmp>() {
					{
						setTargetType(TblAccountInfTmp.class);
					}
				});
			}
		});

		return reader;
	}
	
	
	/*@Bean
	@StepScope
	public FlatFileItemReader<TblAccountInfTmp> accountTmpReader(@Value("#{jobParameters['input.file.name']}") String pathToFile)
			throws Exception {
		FlatFileItemReader<TblAccountInfTmp> reader = new FlatFileItemReader<TblAccountInfTmp>(); //
		reader.setResource(new FileUrlResource(pathToFile)); //
		reader.setLineMapper(new DefaultLineMapper<TblAccountInfTmp>() {
			{ //
				setLineTokenizer(new DelimitedLineTokenizer(Util.asciiToStrings("0x03")) {
					{
						setNames(TblAccountInfTmp.toArray());
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<TblAccountInfTmp>() {
					{
						setTargetType(TblAccountInfTmp.class);
					}
				});
			}
		});

		return reader;
	}*/
	
	@Bean
	public JdbcPagingItemReader<TblAccountInfTmp> accountTmpDataBaseReader(DataSource dataSource)
			throws Exception {
		JdbcPagingItemReader<TblAccountInfTmp> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);               //设置数据源
        reader.setFetchSize(10000);                         //FetchSize设置为2，表示每次从数据库中,2条数据
        reader.setRowMapper(new BeanPropertyRowMapper<TblAccountInfTmp>(TblAccountInfTmp.class));       //把数据库表中每条数据映射到对象中
        OraclePagingQueryProvider queryProvider = new OraclePagingQueryProvider();
        queryProvider.setSelectClause("*");            //设置查询的列
        queryProvider.setFromClause("from Tbl_Account_Inf_Tmp");     //设置查询的表
        Map<String, Order> sortKeys = new HashMap<>(); //定义一个map，用于存放排序列
        sortKeys.put("Acct_ID", Order.ASCENDING);           //按id列升序排列
       // sortKeys.put("age", Order.DESCENDING);         //按age的降序排列
        queryProvider.setSortKeys(sortKeys);           //设置排序列
        reader.setQueryProvider(queryProvider);
        return reader;

	}
	

	

}
